package uk.co.liammartin.anyonethere;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ChatScreen extends Activity {

    //Views
    TextView chatHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Setting the activity up
        setContentView(R.layout.chat_screen);
        chatHeader = (TextView) findViewById(R.id.chat_header);

        //Getting data from the Intent
        Bundle chat_data = getIntent().getExtras();
        String chattingTo = chat_data.getString("username");
        chatHeader.setText(getString(R.string.chat_header, chattingTo));
    }
}
