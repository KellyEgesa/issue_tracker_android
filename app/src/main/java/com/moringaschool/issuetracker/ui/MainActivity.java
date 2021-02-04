package com.moringaschool.issuetracker.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.moringaschool.issuetracker.R;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

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

    Date currentDate = new Date();

    private static void openDrawer(DrawerLayout drawer) {
        drawer.openDrawer(GravityCompat.START);
    }

    private static void closeDrawer(DrawerLayout drawer) {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
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
    }

    public void ClickMenu(View view) {
        openDrawer(drawer);
    }

    @Override
    public void onClick(View v) {
        if (v == mCreateGroup) {
            FragmentManager fm = getSupportFragmentManager();
            AddGroupFragment addGroupFragment = new AddGroupFragment();
            addGroupFragment.show(fm, "Group Fragment");
        }
        if (v == mAddNewProject) {
            FragmentManager fm = getSupportFragmentManager();
            AddProjectFragment addProjectFragment = new AddProjectFragment();
            addProjectFragment.show(fm, "Project Fragment");
        }
    }
}