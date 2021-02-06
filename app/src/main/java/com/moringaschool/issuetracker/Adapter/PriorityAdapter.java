package com.moringaschool.issuetracker.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.issuetracker.Classes.Priority;
import com.moringaschool.issuetracker.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PriorityAdapter extends RecyclerView.Adapter<PriorityAdapter.PriorityViewHolder> {
    private Context mContext;
    private int mPriority;
    private int mSelectedItem;

    public PriorityAdapter(Context context, int priority) {
        mContext = context;
        mPriority = priority;
    }

    @NonNull
    @Override
    public PriorityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_priority, parent, false);
        PriorityViewHolder priorityViewHolder = new PriorityViewHolder(view);
        return priorityViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PriorityViewHolder holder, int position) {
        holder.bindPriority(position + 1);
    }

    @Override
    public int getItemCount() {
        return mPriority;
    }

    public class PriorityViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.priorityRadioButton)
        RadioButton mPriorityRadioButton;

        public PriorityViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mPriorityRadioButton.setOnClickListener(this);


        }

        public void bindPriority(int position) {
            Priority priority = new Priority(position);
            mPriorityRadioButton.setText(priority.getPriority());
            mPriorityRadioButton.setChecked(position == mSelectedItem);
        }

        @Override
        public void onClick(View v) {
            mSelectedItem = getAdapterPosition();
            notifyItemChanged(0, mPriority);
        }
    }
}
