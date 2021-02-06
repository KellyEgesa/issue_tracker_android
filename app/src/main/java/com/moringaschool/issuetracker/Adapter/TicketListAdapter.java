package com.moringaschool.issuetracker.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.issuetracker.R;
import com.moringaschool.issuetracker.models.ProjectList;
import com.moringaschool.issuetracker.models.Ticket;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TicketListAdapter extends RecyclerView.Adapter<TicketListAdapter.TicketsViewHolder> {
    private Context mContext;
    private List<Ticket> mTicket;

    public TicketListAdapter(Context context, List<Ticket> projectLists) {
        mContext = context;
        mTicket = projectLists;
    }

    @NonNull
    @Override
    public TicketsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_tickets, parent, false);
        TicketsViewHolder viewHolder = new TicketsViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TicketsViewHolder holder, int position) {
        holder.bindTicketList(mTicket.get(position));
    }

    @Override
    public int getItemCount() {
        return mTicket.size();
    }

    public class TicketsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textTicketName)
        TextView mTicketName;
        @BindView(R.id.finishButton)
        RadioButton mCheck;
        @BindView(R.id.textTicketDescription)
        TextView mTicketDescription;
        @BindView(R.id.textTime)
        TextView mTicketDuration;

        public TicketsViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindTicketList(Ticket ticket) {
            mTicketName.setText(ticket.getTicketName());
            mTicketDescription.setText(ticket.getTicketDescription());
            mTicketDuration.setText(ticket.getTicketDueDate());
        }
    }
}
