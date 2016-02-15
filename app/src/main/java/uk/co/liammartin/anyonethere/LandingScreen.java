package uk.co.liammartin.anyonethere;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.List;

public class LandingScreen extends Activity implements JSONClient.GetJSONListener {

    ArrayList<String> usernames = new ArrayList<>();
    String data_url = "http://134.83.83.25:47309/Json";
    final String TAG = "LandingScreen.java";
    private List<user> users = new ArrayList<>();
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_screen);
        //Get the RecyclerView
        rv = (RecyclerView) findViewById(R.id.rv);

        //Create a LinearLayoutManager and set it to the RecyclerView
        //This will mean the RecyclerView will add items below eachother
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        //Create instances for data (just random test data at the moment)
        initializeData();

        //Create an instance of RVAdapter so we can set up the RecyclerView using
        //an adapter
        initializeAdapter();
    }


    /**
     * Create some data objects (random data just for testing at the moment)
     */
    private void initializeData() {

        URLWithParams mURLWithParams = new URLWithParams();
        mURLWithParams.url = data_url;

        try {
            JSONClient asyncPoster = new JSONClient(this);
            asyncPoster.execute(mURLWithParams);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void onRemoteCallComplete(String result) {

        Log.d(TAG, "received json catalog:" + result);
        //Log.d(TAG, result);

        usernames.add("TEST");

        try {
            JSONArray array = new JSONArray(result);
            for (int i = 0; i < array.length(); i++) {
                JSONObject row = array.getJSONObject(i);
                Log.d(TAG,row.toString());
                Log.d(TAG,row.getString("USERNAME"));
                usernames.add(row.getString("USERNAME"));
            }


        }catch(Exception e){
            Log.e(TAG,e.toString());
        }
        for(String username : usernames) {
            users.add(new user(username));
        }

        rv.getAdapter().notifyDataSetChanged();


    }

    /**
     * Create an instance of RVAdapter (Recycler View Adapter) using an ArrayList of data
     * objects, in this case we are using user objects (from user.java)
     */
    private void initializeAdapter() {
        RVAdapter adapter = new RVAdapter(users);
        rv.setAdapter(adapter);
    }
}
