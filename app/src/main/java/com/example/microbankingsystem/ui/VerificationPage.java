package com.example.microbankingsystem.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.microbankingsystem.R;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class VerificationPage extends AppCompatActivity {

    Button btn_verification_check, btn_sync;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_page);

        btn_verification_check = findViewById(R.id.btn_verification_check);
        btn_sync = findViewById(R.id.btn_sync);

        Bundle extras;
        extras = getIntent().getExtras();
        String transaction_type = extras.getString("type");
        if (transaction_type.equals("normal")){
            btn_sync.setVisibility(View.INVISIBLE);
        }

        btn_sync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Response response = new Response();
                response.execute();

            }
        });

        btn_verification_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openOptionsFragment();
            }
        });
    }

    public class Response extends AsyncTask {

        @Override
        protected String doInBackground(Object[] objects) {
            OkHttpClient client = new OkHttpClient();

            String agentID = "190488J";

            String url = "http://10.0.2.2:8083/syncAgent/" + agentID;

            Request request = new Request.Builder().url(url).build();

            okhttp3.Response response = null;
            try {
                response = client.newCall(request).execute();
                System.out.println(response.body().string());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private void openOptionsFragment(){
        Intent intent = new Intent(this, OptionsFragment.class);
        startActivity(intent);
    }

}
