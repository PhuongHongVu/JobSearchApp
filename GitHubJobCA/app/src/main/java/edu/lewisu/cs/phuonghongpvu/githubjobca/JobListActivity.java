package edu.lewisu.cs.phuonghongpvu.githubjobca;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class JobListActivity extends AppCompatActivity implements JobAdapter.JobAdapterOnClickHandler{
    private final static String TAG = JobListActivity.class.getSimpleName();
    private final static String URL_STRING = "https://jobs.github.com/positions.json?utf8=%E2%9C%93&description=data+science&location=california";
    private ProgressBar progressBar;
    private JobAdapter jobAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_list);

        progressBar = findViewById(R.id.ListProgressbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

       jobAdapter = new JobAdapter(this);
        recyclerView.setAdapter(jobAdapter);


        DownloadJob downloadJob = new DownloadJob(this);
        downloadJob.execute(URL_STRING);
    }

    @Override
    public void onClick(Job job) {
        Intent detailIntent = new Intent(this, JobDetailsActivity.class);
        detailIntent.putExtra("job", job);
        startActivity(detailIntent);
    }

    private static class DownloadJob extends AsyncTask<String, Void, ArrayList<Job>> {
        private WeakReference<JobListActivity> activityReference;

        DownloadJob(JobListActivity context){
            activityReference = new WeakReference<>(context);
        }

        @Override
        protected void onPreExecute() {
           JobListActivity activity = activityReference.get();
           activity.progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected ArrayList<Job> doInBackground(String... strings) {
            String jsonData = "";
            ArrayList<Job> jobs = new ArrayList<>();

            HttpURLConnection urlConnection = null;

            try{
                URL url = new URL (strings[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream is = urlConnection.getInputStream();
                Scanner scanner = new Scanner(is);
                scanner.useDelimiter("\\A");

                boolean hasInput = scanner.hasNext();
                if (hasInput){
                    jsonData = scanner.next();
                }else {
                    return null;
                }

            }catch (Exception ex){
                Log.d(TAG, ex.toString());
                return null;

            }finally {
                if (urlConnection !=null){
                    urlConnection.disconnect();
                }
            }
            String title;
            String company;
            String type;
            String description;
            String location;
            String url;


            try{
                JSONArray jsonArray = new JSONArray(jsonData);

                for (int i=0; i<jsonArray.length(); i++) {
                    JSONObject jobObject = jsonArray.getJSONObject(i);
                    title = jobObject.getString("title");
                    type = jobObject.getString("type");
                    company = jobObject.getString("company");
                    location = jobObject.getString("location");
                    url = jobObject.getString("url");
                    description = jobObject.getString("description");

                    Job job = new Job( title, company, type, location, url, description);
                    jobs.add(job);
                }
                return jobs;

            }catch (Exception ex){
                Log.d(TAG, ex.toString());
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<Job> jobs) {
            JobListActivity activity = activityReference.get();
            if(activity == null || activity.isFinishing()) return;
            activity.progressBar.setVisibility(View.INVISIBLE);
            if (jobs !=null){
                activity.jobAdapter.setJobData(jobs);
            }
        }
    }
}
