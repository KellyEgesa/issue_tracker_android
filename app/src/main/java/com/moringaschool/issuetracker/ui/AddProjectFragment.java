package com.moringaschool.issuetracker.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.moringaschool.issuetracker.Network.IssueClient;
import com.moringaschool.issuetracker.Network.IssueTrackerApi;
import com.moringaschool.issuetracker.R;
import com.moringaschool.issuetracker.models.Group;
import com.moringaschool.issuetracker.models.ProjectList;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddProjectFragment extends DialogFragment implements View.OnClickListener {
    CalendarView mCalendarView;
    EditText mProjectName;
    EditText mProjectDescription;
    Button mCreateProject;
    Button mCancel;
    ProgressBar mProgressBar;
    LinearLayout mLayout;
    Timestamp mTimestamp;

    String pattern = "yyyy-MM-dd";
    Date date;

    private int group;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_project, container, false);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().setTitle("New Project Dialog");
        mProjectName = (EditText) rootView.findViewById(R.id.textAddProjectName);
        mProjectDescription = (EditText) rootView.findViewById(R.id.textAddProjectDescription);
        mCreateProject = (Button) rootView.findViewById(R.id.buttonCreateProject);
        mCancel = (Button) rootView.findViewById(R.id.buttonCancelCreateProject);
        mCalendarView = (CalendarView) rootView.findViewById(R.id.addProjectCalendarView);
        mLayout = (LinearLayout) rootView.findViewById(R.id.layoutCreateProject);
        mProgressBar = (ProgressBar) rootView.findViewById(R.id.progressBarCreateProject);

        mCreateProject.setOnClickListener(this);
        mCancel.setOnClickListener(this);

        Bundle bundle = this.getArguments();
        assert bundle != null;
        group = bundle.getInt("Group");


        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                date = new Date(year, month, dayOfMonth);
            }
        });


        return rootView;
    }

    @Override
    public void onClick(View v) {
        if (v == mCancel) {
            dismiss();
        }
        if (v == mCreateProject) {
            createProject();
        }

    }

    private void createProject() {
        final String projectName = mProjectName.getText().toString().trim();
        final String projectDescription = mProjectDescription.getText().toString().trim();

        if (!isValidProjectName(projectName) || !isValidDescription(projectDescription) || !isValidDate(date.toString())) {
            return;
        }
        mProgressBar.setVisibility(View.VISIBLE);
        mLayout.setVisibility(View.GONE);
        ProjectList projectList = new ProjectList(projectName, projectDescription, group, date.toString());
        IssueTrackerApi client = IssueClient.urlRequest();
        Call<ProjectList> call = client.createProject(projectList);
        call.enqueue(new Callback<ProjectList>() {
            @Override
            public void onResponse(Call<ProjectList> call, Response<ProjectList> response) {
                if (response.isSuccessful()) {
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    intent.putExtra("groupuid", response.body().getGroupId());
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<ProjectList> call, Throwable t) {

            }
        });

    }

    private boolean isValidProjectName(String name) {
        if (name.equals("")) {
            mProjectName.setError("Enter a project name");
            return false;
        }
        return true;
    }

    private boolean isValidDate(String timestamp) {
        if (timestamp.equals("")) {
            mCreateProject.setError("No date picked");
            return false;
        }
        return true;
    }

    private boolean isValidDescription(String description) {
        if (description.equals("")) {
            mProjectName.setError("Enter a project name");
            return false;
        }
        return true;
    }


}