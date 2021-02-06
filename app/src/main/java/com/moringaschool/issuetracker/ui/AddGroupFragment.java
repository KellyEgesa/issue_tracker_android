package com.moringaschool.issuetracker.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.moringaschool.issuetracker.Constants;
import com.moringaschool.issuetracker.Network.IssueClient;
import com.moringaschool.issuetracker.Network.IssueTrackerApi;
import com.moringaschool.issuetracker.R;
import com.moringaschool.issuetracker.models.Group;
import com.moringaschool.issuetracker.models.User;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddGroupFragment extends DialogFragment implements View.OnClickListener {

    EditText mNewGroupName;
    ProgressBar mProgressBar;
    Button mCreateGroup;
    Button mCancelGroup;
    LinearLayout mLayout;
    private List<Group> groups;
    private int user;
    private String userName;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_group, container, false);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().setTitle("New Group Dialog");
        mNewGroupName = (EditText) rootView.findViewById(R.id.textAddGroupName);
        mCreateGroup = (Button) rootView.findViewById(R.id.buttonCreateGroup);
        mCancelGroup = (Button) rootView.findViewById(R.id.buttonCancelCreateGroup);
        mLayout = (LinearLayout) rootView.findViewById(R.id.layoutCreateGroup);
        mProgressBar = (ProgressBar) rootView.findViewById(R.id.progressBarCreateGroup);
        mCreateGroup.setOnClickListener(this);
        mCancelGroup.setOnClickListener(this);
        Bundle bundle = this.getArguments();
        user = bundle.getInt("User");
        userName = bundle.getString("User");

        return rootView;
    }

    @Override
    public void onClick(View v) {
        if (v == mCancelGroup) {
            dismiss();
        }
        if (v == mCreateGroup) {
            mProgressBar.setVisibility(View.VISIBLE);
            mLayout.setVisibility(View.GONE);
            createGroup();
        }
    }


    public void createGroup() {
        String groupName = mNewGroupName.getText().toString().trim();
        Group group = new Group(groupName, user);
        IssueTrackerApi client = IssueClient.urlRequest();
        Call<Group> call = client.createGroup(group);
        call.enqueue(new Callback<Group>() {
            @Override
            public void onResponse(Call<Group> call, Response<Group> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getContext(), response.body().getGroupName() + " added", Toast.LENGTH_LONG).show();
                    mProgressBar.setVisibility(View.GONE);
                    mLayout.setVisibility(View.VISIBLE);
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    intent.putExtra("groupuid", response.body().getGroupId());
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<Group> call, Throwable t) {
                Toast.makeText(getContext(), "Adding a new group failed", Toast.LENGTH_LONG).show();
                mProgressBar.setVisibility(View.GONE);
                mLayout.setVisibility(View.VISIBLE);
            }
        });

    }
}
