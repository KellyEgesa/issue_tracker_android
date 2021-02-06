package com.moringaschool.issuetracker.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class User {

    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("userId")
    @Expose
    private Integer userId;
    @SerializedName("fireBaseUserId")
    @Expose
    private String fireBaseUserId;
    @SerializedName("email")
    @Expose
    private String email;

    /**
     * No args constructor for use in serialization
     */
    public User() {
    }

    /**
     * @param fireBaseUserId
     * @param userId
     * @param email
     * @param username
     */
    public User(String username, Integer userId, String fireBaseUserId, String email) {
        super();
        this.username = username;
        this.userId = userId;
        this.fireBaseUserId = fireBaseUserId;
        this.email = email;
    }

    public User(String username, String fireBaseUserId, String email) {
        super();
        this.username = username;
        this.fireBaseUserId = fireBaseUserId;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFireBaseUserId() {
        return fireBaseUserId;
    }

    public void setFireBaseUserId(String fireBaseUserId) {
        this.fireBaseUserId = fireBaseUserId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
