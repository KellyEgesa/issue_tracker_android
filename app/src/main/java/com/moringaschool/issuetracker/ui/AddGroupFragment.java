package com.moringaschool.issuetracker.ui;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.moringaschool.issuetracker.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddGroupFragment extends DialogFragment implements View.OnClickListener {

    EditText mNewGroupName;
    ProgressBar mProgressBar;
    Button mCreateGroup;
    Button mCancelGroup;
    LinearLayout mLayout;

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
            Toast.makeText(getActivity(), mNewGroupName.getText(), Toast.LENGTH_LONG).show();
        }
    }
}