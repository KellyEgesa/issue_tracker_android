package com.moringaschool.issuetracker.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.issuetracker.R;
import com.moringaschool.issuetracker.models.User;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GroupMemberListAdapter extends RecyclerView.Adapter<GroupMemberListAdapter.GroupMembersListViewHolder> {
    private Context mContext;
    private List<User> mGroupMembers;

    public GroupMemberListAdapter(Context context, List<User> groupMembers) {
        mContext = context;
        mGroupMembers = groupMembers;
    }

    @NonNull
    @Override
    public GroupMembersListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_group_members_main, parent, false);
        GroupMembersListViewHolder viewHolder = new GroupMembersListViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GroupMembersListViewHolder holder, int position) {
        holder.bindGroupMembers(mGroupMembers.get(position));
    }

    @Override
    public int getItemCount() {
        return mGroupMembers.size();
    }

    public class GroupMembersListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textuserNameMain)
        TextView mTextView;

        public GroupMembersListViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindGroupMembers(User user) {
            mTextView.setText(user.getUsername());
        }
    }
}
