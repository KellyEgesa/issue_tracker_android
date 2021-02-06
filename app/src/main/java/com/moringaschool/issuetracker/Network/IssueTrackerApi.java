package com.moringaschool.issuetracker.Network;

import com.moringaschool.issuetracker.models.AddUser;
import com.moringaschool.issuetracker.models.Group;
import com.moringaschool.issuetracker.models.Main;
import com.moringaschool.issuetracker.models.ProjectList;
import com.moringaschool.issuetracker.models.Ticket;
import com.moringaschool.issuetracker.models.TicketUser;
import com.moringaschool.issuetracker.models.User;
import com.moringaschool.issuetracker.models.UserGroup;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IssueTrackerApi {
    @POST("/user/new")
    Call<User> createUser(@Body User user);

    @POST("/group/new")
    Call<Group> createGroup(@Body Group group);

    @POST("/project/new")
    Call<ProjectList> createProject(@Body ProjectList projectList);

    @POST("/ticket/new")
    Call<Ticket> createTicket(@Body Ticket ticket);

    @POST("/addUser")
    Call<List<User>> addUserToGroup(@Body AddUser addUser);

    @POST("/ticketUser")
    Call<TicketUser> assignTicket(@Body TicketUser ticketUser);


    @GET("group/{id}/")
    Call<Main> getGroupDetails(@Path("id") int groupId);

    @GET("/user/firebase/{firebase}")
    Call<UserGroup> getUserGroups(@Path("firebase") String fireBase);

    @GET("/user/email/{email}")
    Call<User> searchUserEmail(@Path("email") String email);


}
