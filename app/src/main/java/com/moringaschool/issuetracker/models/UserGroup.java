
package com.moringaschool.issuetracker.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserGroup {

    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("groups")
    @Expose
    private List<Group> groups = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public UserGroup() {
    }

    /**
     * 
     * @param groups
     * @param user
     */
    public UserGroup(User user, List<Group> groups) {
        super();
        this.user = user;
        this.groups = groups;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

}
