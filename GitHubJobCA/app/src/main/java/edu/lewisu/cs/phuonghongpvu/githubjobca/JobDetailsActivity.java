package edu.lewisu.cs.phuonghongpvu.githubjobca;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class JobDetailsActivity extends AppCompatActivity {
    Job job = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        job= intent.getParcelableExtra("job");

        TextView titleTextView = findViewById(R.id.titleTextView);

        if (job !=null){
            titleTextView.setText(job.getTitle());

            TextView typeTextView = findViewById(R.id.typeTextView);
            typeTextView.setText(job.getType());

            TextView urlTextView =findViewById(R.id.urlTextView);
            urlTextView.setText(job.getUrl());

            TextView descriptionTextView = findViewById(R.id.descriptionTextView);
            descriptionTextView.setText(Html.fromHtml(job.getDescription()));

            TextView locationTextView = findViewById(R.id.locationTextView);
            locationTextView.setText(job.getLocation());

            TextView companyTextView = findViewById(R.id.companyTextView);
            companyTextView.setText(job.getCompany());
        }else {
            titleTextView.setText(getResources().getString(R.string.download_error));
        }
    }



}
