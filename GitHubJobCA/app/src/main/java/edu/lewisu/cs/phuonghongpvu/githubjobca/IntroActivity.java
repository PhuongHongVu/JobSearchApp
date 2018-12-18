package edu.lewisu.cs.phuonghongpvu.githubjobca;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class IntroActivity extends AppCompatActivity {
  //  private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        // Launch Another activity
        Button jobInfoButton = findViewById(R.id.jobInfoButton);
        jobInfoButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent infoIntent = new Intent(getApplicationContext(),JobListActivity.class);
                startActivity(infoIntent);
            }
        });
    }
}
