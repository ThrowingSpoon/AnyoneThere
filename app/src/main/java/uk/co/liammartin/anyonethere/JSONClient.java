package uk.co.liammartin.anyonethere;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class JSONClient extends AsyncTask<URLWithParams, Void, String> {
    private final static String TAG = "JSONClient";

    ProgressDialog progressDialog ;
    GetJSONListener getJSONListener;
    public JSONClient(GetJSONListener listener){
        this.getJSONListener = listener;
    }

    @Override
    protected String doInBackground(URLWithParams... urls) {
        return connect(urls[0].url, urls[0].nameValuePairs);
    }

    public static String connect(String url, List<NameValuePair> pairs)
    {
        HttpClient httpclient = new DefaultHttpClient();

        if(url == null)
        {
            Log.d(TAG, "want to connect, but url is null");
        }
        else
        {
            Log.d(TAG, "starting connect with url " + url);
        }

        if(pairs == null)
        {
            Log.d(TAG, "want to connect, though pairs is null");
        }
        else
        {
            Log.d(TAG, "starting connect with this many pairs: " + pairs.size());
            for(NameValuePair pair : pairs)
            {
                Log.d(TAG, "example: " + pair.toString());
            }
        }

        // Execute the request
        HttpResponse response;
        try {
            // Prepare a request object
            HttpGet httpGet = new HttpGet(url);
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new UrlEncodedFormEntity(pairs));
            response = httpclient.execute(httpGet);
            // Examine the response status
            Log.i(TAG,response.getStatusLine().toString());

            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
            String json = reader.readLine();
            return json;

        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }



    @Override
    protected void onPostExecute(String json ) {
        getJSONListener.onRemoteCallComplete(json);
    }


    public interface GetJSONListener {
        public void onRemoteCallComplete(String jsonFromNet);
    }

}