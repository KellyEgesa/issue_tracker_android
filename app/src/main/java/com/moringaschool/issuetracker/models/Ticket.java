package com.moringaschool.issuetracker.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ticket {

    @SerializedName("ticketName")
    @Expose
    private String ticketName;
    @SerializedName("ticketId")
    @Expose
    private Integer ticketId;
    @SerializedName("ticketProjectId")
    @Expose
    private Integer ticketProjectId;
    @SerializedName("ticketDescription")
    @Expose
    private String ticketDescription;
    @SerializedName("ticketStatus")
    @Expose
    private Integer ticketStatus;
    @SerializedName("ticketPriority")
    @Expose
    private Integer ticketPriority;
    @SerializedName("ticketDueDate")
    @Expose
    private String ticketDueDate;

    /**
     * No args constructor for use in serialization
     */
    public Ticket() {
    }

    /**
     * @param ticketDueDate
     * @param ticketProjectId
     * @param ticketPriority
     * @param ticketStatus
     * @param ticketDescription
     * @param ticketId
     * @param ticketName
     */
    public Ticket(String ticketName, Integer ticketId, Integer ticketProjectId, String ticketDescription, Integer ticketStatus, Integer ticketPriority, String ticketDueDate) {
        super();
        this.ticketName = ticketName;
        this.ticketId = ticketId;
        this.ticketProjectId = ticketProjectId;
        this.ticketDescription = ticketDescription;
        this.ticketStatus = ticketStatus;
        this.ticketPriority = ticketPriority;
        this.ticketDueDate = ticketDueDate;
    }

    public Ticket(String ticketName, String ticketDescription, String ticketDueDate, Integer ticketProjectId) {
        super();
        this.ticketName = ticketName;
        this.ticketDescription = ticketDescription;
        this.ticketProjectId = ticketProjectId;
        this.ticketDueDate = ticketDueDate;
    }

    public String getTicketName() {
        return ticketName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public Integer getTicketProjectId() {
        return ticketProjectId;
    }

    public void setTicketProjectId(Integer ticketProjectId) {
        this.ticketProjectId = ticketProjectId;
    }

    public String getTicketDescription() {
        return ticketDescription;
    }

    public void setTicketDescription(String ticketDescription) {
        this.ticketDescription = ticketDescription;
    }

    public Integer getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(Integer ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public Integer getTicketPriority() {
        return ticketPriority;
    }

    public void setTicketPriority(Integer ticketPriority) {
        this.ticketPriority = ticketPriority;
    }

    public String getTicketDueDate() {
        return ticketDueDate;
    }

    public void setTicketDueDate(String ticketDueDate) {
        this.ticketDueDate = ticketDueDate;
    }

}
