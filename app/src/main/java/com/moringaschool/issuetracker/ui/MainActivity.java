package com.moringaschool.issuetracker.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.moringaschool.issuetracker.Adapter.GroupListAdapter;
import com.moringaschool.issuetracker.Adapter.GroupMemberListAdapter;
import com.moringaschool.issuetracker.Adapter.GroupMembersAdapter;
import com.moringaschool.issuetracker.Adapter.ProjectAdapter;
import com.moringaschool.issuetracker.Adapter.TicketListAdapter;
import com.moringaschool.issuetracker.Constants;
import com.moringaschool.issuetracker.Network.IssueClient;
import com.moringaschool.issuetracker.Network.IssueTrackerApi;
import com.moringaschool.issuetracker.R;
import com.moringaschool.issuetracker.models.Group;
import com.moringaschool.issuetracker.models.Main;
import com.moringaschool.issuetracker.models.ProjectList;
import com.moringaschool.issuetracker.models.Ticket;
import com.moringaschool.issuetracker.models.User;
import com.moringaschool.issuetracker.models.UserGroup;

import org.parceler.Parcels;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.textDateView)
    TextView mDate;
    @BindView(R.id.addGroupCard)
    CardView mCreateGroup;
    @BindView(R.id.addNewProject)
    ImageView mAddNewProject;
    @BindView(R.id.addNewGroupMember)
    ImageView mAddNewGroupMember;
    @BindView(R.id.addTicket)
    FloatingActionButton mAddTicket;
    @BindView(R.id.textViewPersonal)
    TextView mPersonal;
    @BindView(R.id.textViewOrderBy)
    TextView mOrder;
    @BindView(R.id.cardLogOut)
    CardView mLogOut;
    @BindView(R.id.recyclerViewListMembers)
    RecyclerView mRecyclerViewListMembers;
    @BindView(R.id.recyclerViewProjects)
    RecyclerView mRecyclerViewProjects;
    @BindView(R.id.recyclerViewListGroup)
    RecyclerView mRecyclerViewListGroup;
    @BindView(R.id.textDrawerUserName)
    TextView mUserName;
    @BindView(R.id.textGroupNameHeader)
    TextView mHeader;
    @BindView(R.id.recyclerViewTickets)
    RecyclerView mRecyclerViewTickets;

    Date currentDate = new Date();
    private List<Group> groups;
    private List<ProjectList> projectLists;
    private List<Ticket> ticketList;
    private ArrayList<User> groupMembers;
    private String order = "Priority";
    private String personal = "All";
    private String userUid;
    private int groupId;
    private User user;
    private GroupListAdapter groupListAdapter;
    private GroupMemberListAdapter groupMemberListAdapter;
    private ProjectAdapter projectAdapter;
    private TicketListAdapter ticketListAdapter;
    private Group currentGroup;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    private static void openDrawer(DrawerLayout drawer) {
        drawer.openDrawer(GravityCompat.START);
    }

    private static void closeDrawer(DrawerLayout drawer) {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
    }

    public void setGroupId(int newGroupId) {
        this.groupId = newGroupId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");
        String strDate = formatter.format(currentDate);
        mDate.setText(strDate);

        mCreateGroup.setOnClickListener(this);
        mAddNewProject.setOnClickListener(this);
        mAddNewGroupMember.setOnClickListener(this);
        mAddTicket.setOnClickListener(this);
        mOrder.setOnClickListener(this);
        mPersonal.setOnClickListener(this);
        mLogOut.setOnClickListener(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        userUid = mSharedPreferences.getString(Constants.USER_UID, null);

        Bundle extras = getIntent().getExtras();
        String userName;
        int groupId = getIntent().getIntExtra("groupuid", 0);
        if (groupId > 0) {
            setGroupId(groupId);
        }
        ;

        getUserDetails(userUid);

        if (groupId > 0) {
            setGroupId(groupId);
            getGroupUsers(groupId);
        }


    }

    public void ClickMenu(View view) {
        openDrawer(drawer);
    }

    public void openCalendar(View view) {
        Intent intent = new Intent(MainActivity.this, CalendarActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        if (v == mCreateGroup) {
            FragmentManager fm = getSupportFragmentManager();
            AddGroupFragment addGroupFragment = new AddGroupFragment();
            Bundle bundle = new Bundle();
            bundle.putString("User", user.getUsername());
            bundle.putInt("User", user.getUserId());
            addGroupFragment.setArguments(bundle);
            addGroupFragment.show(fm, "Group Fragment");
        }
        if (v == mAddNewProject) {
            FragmentManager fm = getSupportFragmentManager();
            AddProjectFragment addProjectFragment = new AddProjectFragment();
            Bundle bundle = new Bundle();
            if (groupId > 0) {
                bundle.putInt("Group", groupId);
            }
            addProjectFragment.setArguments(bundle);
            addProjectFragment.show(fm, "Project Fragment");
        }
        if (v == mAddNewGroupMember) {
            FragmentManager fm = getSupportFragmentManager();
            AddGroupMemberFragment addGroupMemberFragment = new AddGroupMemberFragment();
            Bundle bundle = new Bundle();
            if (groupId > 0) {
                bundle.putInt("Group", groupId);
            }
            addGroupMemberFragment.setArguments(bundle);
            addGroupMemberFragment.show(fm, "Add group member");
        }
        if (v == mAddTicket) {
            Intent intent = new Intent(MainActivity.this, AddTicket.class);
            intent.putExtra("", Parcels.wrap(groupMembers));
            startActivity(intent);
        }
        if (v == mOrder) {
            String order = mOrder.getText().toString();

            if (order.equals("Order By: Priority")) {
                mOrder.setText("Order By: Date");
            } else {
                mOrder.setText("Order By: Priority");
            }
        }
        if (v == mPersonal) {
            String order = mPersonal.getText().toString();

            if (order.equals("All")) {
                mPersonal.setText("Personal");
            } else {
                mPersonal.setText("All");
            }
        }
        if (v == mLogOut) {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(MainActivity.this, LogIn.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
    }

    private void getUserDetails(String userUid) {
        IssueTrackerApi client = IssueClient.urlRequest();
        Call<UserGroup> call = client.getUserGroups(userUid);
        call.enqueue(new Callback<UserGroup>() {
            @Override
            public void onResponse(Call<UserGroup> call, Response<UserGroup> response) {
                user = response.body().getUser();
                if (user.getUserId() > 0) {
                    mEditor.putInt("User Id", user.getUserId()).apply();
                }
                groups = response.body().getGroups();
                if (groups.size() > 0) {
                    setGroupId(groups.get(0).getGroupId());
                }

                groupListAdapter = new GroupListAdapter(MainActivity.this, groups);
                mRecyclerViewListGroup.setAdapter(groupListAdapter);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                mRecyclerViewListGroup.setLayoutManager(layoutManager);


            }

            @Override
            public void onFailure(Call<UserGroup> call, Throwable t) {
            }
        });
    }


    private void getGroupUsers(int groupId) {
        IssueTrackerApi client = IssueClient.urlRequest();
        Call<Main> call = client.getGroupDetails(groupId);
        call.enqueue(new Callback<Main>() {
            @Override
            public void onResponse(Call<Main> call, Response<Main> response) {
                mHeader.setText(response.body().getGroup().getGroupName());
                groupMembers = (ArrayList<User>) response.body().getUsers();
                groupMemberListAdapter = new GroupMemberListAdapter(MainActivity.this, response.body().getUsers());
                mRecyclerViewListMembers.setAdapter(groupMemberListAdapter);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
                mRecyclerViewListMembers.setLayoutManager(layoutManager);

                projectLists = response.body().getProjectList();
                projectAdapter = new ProjectAdapter(MainActivity.this, projectLists);
                mRecyclerViewProjects.setAdapter(projectAdapter);
                RecyclerView.LayoutManager layoutManagerProject = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
                mRecyclerViewProjects.setLayoutManager(layoutManagerProject);

                ticketList = response.body().getTickets();
                if (ticketList != null) {
                    ticketListAdapter = new TicketListAdapter(MainActivity.this, ticketList);
                    mRecyclerViewTickets.setAdapter(ticketListAdapter);
                    RecyclerView.LayoutManager layoutManagerTicket = new LinearLayoutManager(MainActivity.this);
                    mRecyclerViewTickets.setLayoutManager(layoutManagerTicket);
                }

            }

            @Override
            public void onFailure(Call<Main> call, Throwable t) {

            }
        });
    }
}