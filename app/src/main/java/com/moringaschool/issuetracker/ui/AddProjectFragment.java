package com.moringaschool.issuetracker.ui;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.moringaschool.issuetracker.R;

import java.sql.Timestamp;
import java.util.Date;

public class AddProjectFragment extends DialogFragment implements View.OnClickListener {
    CalendarView mCalendarView;
    EditText mProjectName;
    EditText mProjectDescription;
    Button mCreateProject;
    Button mCancel;
    ProgressBar mProgressBar;
    LinearLayout mLayout;
    Timestamp mTimestamp;


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


        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                mTimestamp = new Timestamp(new Date(year, month, dayOfMonth).getTime());
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

        if (!isValidProjectName(projectName) || !isValidDescription(projectDescription) || !isValidDate(mTimestamp)) {
            return;
        }
        mProgressBar.setVisibility(View.VISIBLE);
        mLayout.setVisibility(View.GONE);

    }

    private boolean isValidProjectName(String name) {
        if (name.equals("")) {
            mProjectName.setError("Enter a project name");
            return false;
        }
        return true;
    }

    private boolean isValidDate(Timestamp timestamp) {
        if (timestamp == null) {
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