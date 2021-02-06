
package com.moringaschool.issuetracker.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.moringaschool.issuetracker.models.*;


public class Main {

    @SerializedName("group")
    @Expose
    private Group group;
    @SerializedName("projectList")
    @Expose
    private List<ProjectList> projectList = null;
    @SerializedName("users")
    @Expose
    private List<User> users = null;
    @SerializedName("tickets")
    @Expose
    private List<Ticket> tickets = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Main() {
    }

    /**
     * 
     * @param projectList
     * @param tickets
     * @param users
     * @param group
     */
    public Main(Group group, List<ProjectList> projectList, List<User> users, List<Ticket> tickets) {
        super();
        this.group = group;
        this.projectList = projectList;
        this.users = users;
        this.tickets = tickets;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public List<ProjectList> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<ProjectList> projectList) {
        this.projectList = projectList;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

}
