package com.moringaschool.issuetracker.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import android.preference.PreferenceManager;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.moringaschool.issuetracker.Constants;
import com.moringaschool.issuetracker.Network.IssueClient;
import com.moringaschool.issuetracker.Network.IssueTrackerApi;
import com.moringaschool.issuetracker.R;
import com.moringaschool.issuetracker.models.AddUser;
import com.moringaschool.issuetracker.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddGroupMemberFragment extends DialogFragment implements View.OnClickListener {
    ProgressBar mProgressBar;
    LinearLayout mLayout;
    EditText mEmail;
    Button mAdd;
    Button mCancel;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private int groupUid;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_add_group_member, container, false);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mProgressBar = (ProgressBar) rootView.findViewById(R.id.progressBarAddGroupMember);
        mLayout = (LinearLayout) rootView.findViewById(R.id.layoutAddGroupMember);
        mEmail = (EditText) rootView.findViewById(R.id.textAddGroupMemberEmail);
        mAdd = (Button) rootView.findViewById(R.id.buttonAddGroupMember);
        mCancel = (Button) rootView.findViewById(R.id.buttonCancelAddGroupMember);

        Bundle bundle = this.getArguments();
        assert bundle != null;
        groupUid = bundle.getInt("Group");


        mAdd.setOnClickListener(this);
        mCancel.setOnClickListener(this);


        return rootView;
    }

    @Override
    public void onClick(View v) {
        if (v == mCancel) {
            dismiss();
        }
        if (v == mAdd) {
            searchMember();
        }
    }

    private void searchMember() {
        String email = mEmail.getText().toString().trim();

        if (!isEmailValid(email)) {
            return;
        }
        mProgressBar.setVisibility(View.VISIBLE);
        mLayout.setVisibility(View.GONE);
        IssueTrackerApi client = IssueClient.urlRequest();
        Call<User> call = client.searchUserEmail(email);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User user = response.body();
                    Toast.makeText(getContext(), "User found", Toast.LENGTH_LONG).show();
                    assert user != null;
                    addMember(user.getUserId(), groupUid);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                mProgressBar.setVisibility(View.GONE);
                mLayout.setVisibility(View.VISIBLE);
            }
        });

    }

    public void addMember(int userId, int groupId) {
        AddUser addUser = new AddUser(userId, groupId);
        IssueTrackerApi client = IssueClient.urlRequest();
        Call<List<User>> call = client.addUserToGroup(addUser);
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                Toast.makeText(getContext(), "User Added Successfully", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getContext(), MainActivity.class);
                intent.putExtra("groupuid", groupUid);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private boolean isEmailValid(String email) {
        boolean isGoodEmail = (email != null && Patterns.EMAIL_ADDRESS.matcher(email).matches());
        if (!isGoodEmail) {
            mEmail.setError("Enter a valid email address");
            return false;
        }
        return true;
    }
}