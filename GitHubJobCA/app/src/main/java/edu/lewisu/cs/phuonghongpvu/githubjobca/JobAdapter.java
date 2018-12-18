package edu.lewisu.cs.phuonghongpvu.githubjobca;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.JobViewHolder> {
    private List<Job> jobs;
    private Context context;
    private final JobAdapterOnClickHandler clickHandler;

    public interface JobAdapterOnClickHandler{
        void onClick(Job job);
    }

    public JobAdapter(JobAdapterOnClickHandler clickHandler){
        this.clickHandler = clickHandler;
    }

    @NonNull
    @Override
    public JobViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.job_list_item, viewGroup, false);
        return new JobViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JobViewHolder jobViewHolder, int i) {
        Job job = jobs.get(i);
        String title = job.getTitle();
        String location = job.getLocation();
        String titleLocation = context.getResources().getString(R.string.tile_location, title, location);
        jobViewHolder.jobDataTextView.setText(titleLocation);
    }

    @Override
    public int getItemCount() {
       if (jobs != null){
           return jobs.size();
       }
        return 0;
    }
    public void setJobData(List<Job> jobData){
        jobs = jobData;
        notifyDataSetChanged();
    }
    class JobViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final TextView jobDataTextView;
        public JobViewHolder(@NonNull View itemView) {
            super(itemView);
            jobDataTextView = itemView.findViewById(R.id.jobDataTextView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            Job job = jobs.get(adapterPosition);
            clickHandler.onClick(job);
        }
    }
}
