package uk.co.liammartin.anyonethere;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LandingScreen extends Activity {
    final String TAG = "LandingScreen.java";

    //OkHttpClient
    private final OkHttpClient client = new OkHttpClient();

    //Data
    List<user> users = new ArrayList<>();
    ArrayList<String> usernames = new ArrayList<>();
    ArrayList<String> ids = new ArrayList<>();

    //Views
    Button refresh_button;
    RecyclerView rv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_screen);
        //Get the RecyclerView
        rv = (RecyclerView) findViewById(R.id.rv);

/*        //Getting the refresh button and attaching an onclick listener
        refresh_button = (Button) findViewById(R.id.refresh_button);
        refresh_button.setOnClickListener(this);*/

        //Create a LinearLayoutManager and set it to the RecyclerView
        //This will mean the RecyclerView will add items below eachother
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);
        //Create an instance of RVAdapter so we can set up the RecyclerView using an adapter
        initializeAdapter();
        //Pull the data down from the server and create data objects from them
        initializeData();
    }

    /*@Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.refresh_button:
                initializeData();
                break;

            default:
                Log.d(TAG, "Click item not recognised!");
                break;
        }
    }*/

    /**
     * Initialize the data
     */
    public void initializeData() {
        try {
            getUsernames();
            //messages.add(new user("TEST"));
        } catch (Exception e) {
            Log.d("123123", e.toString());
        }
    }

    /**
     * Create an instance of RVAdapter (Recycler View Adapter) using an ArrayList of data
     * objects, in this case we are using user objects (from user.java)
     */
    private void initializeAdapter() {
        RVAdapter adapter = new RVAdapter(users);
        rv.setAdapter(adapter);
    }

    public void getUsernames() throws Exception {

        //Building our request
        Request request = new Request.Builder()
                .url("http://134.83.83.25:47309/Liam")
                .build();

        //Queue up the client call to server
        client.newCall(request).enqueue(new Callback() {

            String responseString;

            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, e.toString());
            }

            //Getting our response
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful())
                    throw new IOException("Unexpected code " + response);

                //Displaying the data in logcat
                Headers responseHeaders = response.headers();
                for (int i = 0; i < responseHeaders.size(); i++) {
                    System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                }

                //Taking the response and updating the usernames on the *MAIN THREAD*
                responseString = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        displayUsernames(responseString);
                        rv.getAdapter().notifyDataSetChanged();
                    }
                });
                try {
                    Thread.sleep(3000, 100);
                    getUsernames();
                }catch (Exception e){
                    Log.d(TAG,e.toString());
                }
            }
        });

    }

    /**
     * Display all of the entries from the JSONArray string in the RecyclerView
     *
     * @param response The JSONArray received from the server
     */
    private void displayUsernames(String response) {
        try {
            usernames.clear();
            users.clear();
            ids.clear();
            JSONArray array = new JSONArray(response);
            for (int i = 0; i < array.length(); i++) {
                JSONObject row = array.getJSONObject(i);
                Log.d(TAG, row.toString());
                Log.d(TAG, row.getString("USER_NAME"));
                usernames.add(row.getString("USER_NAME"));
                ids.add(row.getString("USER_ID"));
            }
            for (int i = 0; i < ids.size(); i++) {
                users.add(new user(usernames.get(i), ids.get(i)));
            }
//            rv.getAdapter().notifyItemRangeChanged(0, rv.getAdapter().getItemCount());
        } catch (Exception e) {
            Log.d(TAG, e.toString());
        }
    }

}
