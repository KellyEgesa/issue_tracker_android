package com.moringaschool.issuetracker.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProjectList {

    @SerializedName("projectName")
    @Expose
    private String projectName;
    @SerializedName("projectDescription")
    @Expose
    private String projectDescription;
    @SerializedName("projectId")
    @Expose
    private Integer projectId;
    @SerializedName("groupId")
    @Expose
    private Integer groupId;
    @SerializedName("duration")
    @Expose
    private String duration;

    /**
     * No args constructor for use in serialization
     */
    public ProjectList() {
    }

    /**
     * @param duration
     * @param groupId
     * @param projectDescription
     * @param projectName
     * @param projectId
     */
    public ProjectList(String projectName, String projectDescription, Integer projectId, Integer groupId, String duration) {
        super();
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.projectId = projectId;
        this.groupId = groupId;
        this.duration = duration;
    }

    public ProjectList(String projectName, String projectDescription, Integer groupId, String duration) {
        super();
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.groupId = groupId;
        this.duration = duration;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

}
