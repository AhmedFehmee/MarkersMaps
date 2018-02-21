package kickstart.com.kickstarttask.Service;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.GoogleMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import kickstart.com.kickstarttask.Model.Content;
import kickstart.com.kickstarttask.Model.EntryItem;
import kickstart.com.kickstarttask.Model.Feed;
import kickstart.com.kickstarttask.Model.Title;
import kickstart.com.kickstarttask.Model.Updated;

/**
 * Created by Fehoo on 2/21/2018.
 */

public class SpreadsheetsService {
    public void callSpreadsheetsService(final Context context , final ArrayList<EntryItem> entryList) {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);
        //this is the url where you want to send the request
        //TODO: replace with your own url to send request, as I am using my own localhost for this tutorial
        String url =
                "https://spreadsheets.google.com/feeds/list/0Ai2EnLApq68edEVRNU0xdW9QX1BqQXhHRl9sWDNfQXc/od6/public/basic?alt=json";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            entryList.clear();
                            // Display the response string.
                            JSONObject jsonObject = new JSONObject(response);

                            Feed feedObj = new Feed();
                            JSONObject feedObject = jsonObject.getJSONObject("feed");
                            JSONArray entryArray = feedObject.getJSONArray("entry");
                            for (int i = 0; i < entryArray.length(); i++) {
                                EntryItem entryItem = new EntryItem();

                                JSONObject entryObj = entryArray.getJSONObject(i);
                                //Content Object
                                JSONObject contentObj = entryObj.getJSONObject("content");
                                Content contentModel = new Content();
                                contentModel.setT(contentObj.getString("$t"));
                                contentModel.setType(contentObj.getString("type"));
                                entryItem.setContent(contentModel);
                                //Title Object
                                Title titleModel = new Title();
                                JSONObject titletObj = entryObj.getJSONObject("title");
                                titleModel.setType(titletObj.getString("type"));
                                titleModel.setT(titletObj.getString("$t"));
                                entryItem.setTitle(titleModel);
                                //Update Object
                                Updated updateModel = new Updated();
                                JSONObject updatedObj = entryObj.getJSONObject("updated");
                                updateModel.setT(titletObj.getString("$t"));
                                entryItem.setUpdated(updateModel);
                                entryList.add(entryItem);
                            }
                            feedObj.setEntry(entryList);
                        } catch (JSONException e) {
                            Toast.makeText(context,"Check Internet Connection",Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener()

        {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,"Check Internet Connection",Toast.LENGTH_LONG).show();
            }
        }) {
        };
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
