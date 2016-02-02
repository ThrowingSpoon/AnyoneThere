package uk.co.liammartin.anyonethere;

import org.apache.http.NameValuePair;

import java.util.ArrayList;
import java.util.List;

public class URLWithParams {

    public String url;
    public List<NameValuePair> nameValuePairs;

    public URLWithParams()
    {
        nameValuePairs = new ArrayList<NameValuePair>();
    }
}