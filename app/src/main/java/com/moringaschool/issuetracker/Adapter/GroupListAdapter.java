package com.moringaschool.issuetracker.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.issuetracker.Constants;
import com.moringaschool.issuetracker.R;
import com.moringaschool.issuetracker.models.Group;
import com.moringaschool.issuetracker.ui.MainActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GroupListAdapter extends RecyclerView.Adapter<GroupListAdapter.GroupListViewHolder> {
    private Context mContext;
    private List<Group> mListGroup;

    public GroupListAdapter(Context context, List<Group> listGroup) {
        mContext = context;
        mListGroup = listGroup;
    }


    @NonNull
    @Override
    public GroupListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_groups, parent, false);
        GroupListAdapter.GroupListViewHolder viewHolder = new GroupListAdapter.GroupListViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GroupListViewHolder holder, int position) {
        holder.bindGroupList(mListGroup.get(position));
    }

    @Override
    public int getItemCount() {
        return mListGroup.size();
    }

    public class GroupListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.groupCard)
        CardView mGroup;
        @BindView(R.id.textGroupList)
        TextView mText;
        Context context;
        private SharedPreferences mSharedPreferences;
        private SharedPreferences.Editor mEditor;
        private int position;


        public GroupListViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mGroup.setOnClickListener(this);

            context = itemView.getContext();

        }

        public void bindGroupList(Group group) {
            mText.setText(group.getGroupName());
            this.position = group.getGroupId();
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
//            Toast.makeText(context, position, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(context, MainActivity.class);
            intent.putExtra("groupuid", mListGroup.get(position).getGroupId());
            context.startActivity(intent);

        }
    }
}
