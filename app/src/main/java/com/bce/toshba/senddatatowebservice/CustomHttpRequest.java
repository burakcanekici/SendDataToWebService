package com.bce.toshba.senddatatowebservice;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CustomHttpRequest extends AsyncTask<String, Integer, String> {
    private String REQUEST_MODE = "";

    @Override
    protected String doInBackground(String... params) {
        String response = "";

        REQUEST_MODE = params[0];
        String my_url = params[1];
        JSONObject my_data = null;
        try {
            my_data = new JSONObject(params[2]);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            URL url = new URL(my_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod(REQUEST_MODE);
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            try{
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setChunkedStreamingMode(0);

                // to write tha data in our request
                OutputStream outputStream = new BufferedOutputStream(httpURLConnection.getOutputStream());
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);

                //outputStreamWriter.write("temp=" + my_data);
                //outputStreamWriter.write("scale=F");
                outputStreamWriter.write(CreateRequestParam(my_data));
                outputStreamWriter.flush();
                outputStreamWriter.close();

                //httpURLConnection.getResponseCode() == 200

                InputStream is = httpURLConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                StringBuilder builder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }
                response = builder.toString();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                httpURLConnection.disconnect();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return response;
    }

    public String CreateRequestParam(JSONObject jsonObject) throws JSONException, UnsupportedEncodingException {
        StringBuilder req = new StringBuilder();
        Iterator<String> iteratorKeys = jsonObject.keys();

        while (iteratorKeys.hasNext()){
            String key = (String) iteratorKeys.next();
            req.append(key + "=" + jsonObject.get(key).toString());
            req.append("&");
        }
        return req.toString().substring(0,req.toString().length()-1);
    }
}
