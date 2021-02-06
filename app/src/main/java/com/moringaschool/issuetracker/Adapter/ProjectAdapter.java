package com.moringaschool.issuetracker.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.issuetracker.R;
import com.moringaschool.issuetracker.models.ProjectList;
import com.moringaschool.issuetracker.ui.AddTicket;
import com.moringaschool.issuetracker.ui.MainActivity;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.projectViewHolder> {
    private Context mContext;
    private List<ProjectList> mProject;

    public ProjectAdapter(Context context, List<ProjectList> projectLists) {
        mContext = context;
        mProject = projectLists;
    }

    @NonNull
    @Override
    public projectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_projects_main, parent, false);
        projectViewHolder viewHolder = new projectViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull projectViewHolder holder, int position) {
        holder.bindProjectList(mProject.get(position));
    }

    @Override
    public int getItemCount() {
        return mProject.size();
    }


    public class projectViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.textProjectName)
        TextView projectName;
        @BindView(R.id.textGroupDescription)
        TextView projectDescription;
        @BindView(R.id.textTime)
        TextView projectDuration;
        @BindView(R.id.cardViewProject)
        CardView mCard;
        Context context;

        public projectViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            context = itemView.getContext();
        }

        public void bindProjectList(ProjectList projectList) {
            projectName.setText(projectList.getProjectName());
            projectDuration.setText(projectList.getDuration());
            projectDescription.setText(projectList.getProjectDescription());
            mCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, AddTicket.class);
                    intent.putExtra("projectId", projectList.getProjectId());
                    context.startActivity(intent);
                }
            });
        }

        @Override
        public void onClick(View v) {

        }
    }
}
