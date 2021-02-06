package com.moringaschool.issuetracker.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class Group {

    @SerializedName("groupId")
    @Expose
    private Integer groupId;
    @SerializedName("groupName")
    @Expose
    private String groupName;
    @SerializedName("userAdminId")
    @Expose
    private Integer userAdminId;

    /**
     * No args constructor for use in serialization
     */
    public Group() {
    }

    /**
     * @param groupName
     * @param groupId
     * @param userAdminId
     */
    public Group(Integer groupId, String groupName, Integer userAdminId) {
        super();
        this.groupId = groupId;
        this.groupName = groupName;
        this.userAdminId = userAdminId;
    }

    public Group(String groupName, Integer userAdminId) {
        super();
        this.groupName = groupName;
        this.userAdminId = userAdminId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getUserAdminId() {
        return userAdminId;
    }

    public void setUserAdminId(Integer userAdminId) {
        this.userAdminId = userAdminId;
    }

}
