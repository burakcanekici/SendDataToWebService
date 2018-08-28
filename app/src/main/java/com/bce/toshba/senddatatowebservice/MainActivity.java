package com.bce.toshba.senddatatowebservice;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends Activity {

    EditText et_input;
    TextView tv_result;
    Button b_calc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_input = (EditText) findViewById(R.id.et_input);
        tv_result = (TextView) findViewById(R.id.tv_result);
        b_calc = (Button) findViewById(R.id.b_calc);


        b_calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String my_url = "https://php.radford.edu/~jcdavis/D2L/classes/it425/lectmat/webserv/tempws_api.php";
                String my_data = "?temp=40&scale=F";

                String temp = et_input.getText().toString();
                String scale = "F";

                JSONObject reqObj = new JSONObject();
                try {
                    reqObj.put("temp", temp);
                    reqObj.put("scale", scale);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                String res = null;
                try {
                    res = new CustomHttpRequest().execute("GET", my_url, reqObj.toString()).get().toString();
                    tv_result.setText(res);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}

