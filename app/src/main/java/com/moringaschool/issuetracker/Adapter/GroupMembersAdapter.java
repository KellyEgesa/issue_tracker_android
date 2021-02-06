package com.moringaschool.issuetracker.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.issuetracker.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GroupMembersAdapter extends RecyclerView.Adapter<GroupMembersAdapter.GroupMembersViewHolder> {
    private Context mContext;
    private List mGroupMembers;

    public GroupMembersAdapter(Context context, List groupMembers) {
        mContext = context;
        mGroupMembers = groupMembers;
    }

    @NonNull
    @Override
    public GroupMembersAdapter.GroupMembersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_group_members, parent, false);
        GroupMembersViewHolder viewHolder = new GroupMembersViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GroupMembersViewHolder holder, int position) {
        holder.bindGroupMembers(mGroupMembers.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mGroupMembers.size();
    }

    public class GroupMembersViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.userRadioButton)
        RadioButton mRadioButton;
        private Context mContext;
        private int position;

        public GroupMembersViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();

            mRadioButton.setOnClickListener(this);

        }

        public void bindGroupMembers(Object a, int position) {
            mRadioButton.setText(a.toString());
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(mContext, mGroupMembers.get(position).toString(), Toast.LENGTH_LONG).show();
        }
    }
}
