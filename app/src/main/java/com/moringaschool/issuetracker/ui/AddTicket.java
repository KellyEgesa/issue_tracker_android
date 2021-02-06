package com.moringaschool.issuetracker.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.moringaschool.issuetracker.Adapter.GroupMembersAdapter;
import com.moringaschool.issuetracker.Adapter.PriorityAdapter;
import com.moringaschool.issuetracker.Network.IssueClient;
import com.moringaschool.issuetracker.Network.IssueTrackerApi;
import com.moringaschool.issuetracker.R;
import com.moringaschool.issuetracker.models.ProjectList;
import com.moringaschool.issuetracker.models.Ticket;
import com.moringaschool.issuetracker.models.User;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddTicket extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.textAddTicketName)
    EditText addTicketName;
    @BindView(R.id.textAddTicketDescription)
    EditText addTicketDescription;
    @BindView(R.id.addTicketCalendarView)
    CalendarView mCalendarView;
    @BindView(R.id.buttonCreateTicket)
    Button createTicket;
    @BindView(R.id.buttonCancelCreateTicket)
    Button cancelTicket;
    @BindView(R.id.progressBarCreateTicket)
    ProgressBar progressBar;
    @BindView(R.id.layoutCreateTicket)
    LinearLayout mLayout;

    Date date;
    private int groupId;
    private GroupMembersAdapter groupMembersAdapter;
    private PriorityAdapter priorityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ticket);
        ButterKnife.bind(this);

        groupId = getIntent().getIntExtra("projectId", 0);


        createTicket.setOnClickListener(this);
        cancelTicket.setOnClickListener(this);

        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                date = new Date(year, month, dayOfMonth);
            }
        });
    }

    public void newTicket() {

    }

    @Override
    public void onClick(View v) {
        if (v == createTicket) {
            final String projectName = addTicketName.getText().toString().trim();
            final String projectDescription = addTicketDescription.getText().toString().trim();

            progressBar.setVisibility(View.VISIBLE);
            mLayout.setVisibility(View.GONE);

            Ticket ticket = new Ticket(projectName, projectDescription, date.toString(), groupId);
            if (v == createTicket) {
                IssueTrackerApi client = IssueClient.urlRequest();
                Call<Ticket> call = client.createTicket(ticket);
                call.enqueue(new Callback<Ticket>() {
                    @Override
                    public void onResponse(Call<Ticket> call, Response<Ticket> response) {
                        progressBar.setVisibility(View.GONE);
                        mLayout.setVisibility(View.VISIBLE);

                    }

                    @Override
                    public void onFailure(Call<Ticket> call, Throwable t) {
                        progressBar.setVisibility(View.GONE);
                        mLayout.setVisibility(View.VISIBLE);
                        Toast.makeText(AddTicket.this, "FAILED", Toast.LENGTH_LONG).show();
                    }
                });
            }
        }
        if (v == cancelTicket) {
            finish();
        }
    }
}