package uk.co.liammartin.anyonethere;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class LandingScreen extends Activity {

    private List<user> users;
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
        users = new ArrayList<>();
        users.add(new user("USER"));
        users.add(new user("USER"));
        users.add(new user("USER"));
        users.add(new user("USER"));
        users.add(new user("USER"));
        users.add(new user("USER"));
        users.add(new user("USER"));
        users.add(new user("USER"));
        users.add(new user("USER"));
        users.add(new user("USER"));
        users.add(new user("USER"));
        users.add(new user("USER"));
        users.add(new user("USER"));
        users.add(new user("USER"));
        users.add(new user("USER"));
        users.add(new user("USER"));
        users.add(new user("USER"));
        users.add(new user("USER"));
        users.add(new user("USER"));
        users.add(new user("USER"));
        users.add(new user("USER"));
        users.add(new user("USER"));
        users.add(new user("USER"));
        users.add(new user("USER"));
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
