package com.moringaschool.issuetracker.Classes;

public class Priority {
    private int priority;

    public Priority(int priority) {
        if (priority < 5) {
            this.priority = priority;
        }
    }

    public String getPriority() {
        String returnStatement = "";
        if (priority == 1) {
            returnStatement = "Urgent Critical";
        } else if (priority == 2) {
            returnStatement = "Urgent Non-Critical";
        } else if (priority == 3) {
            returnStatement = "Low Critical";
        } else if (priority == 4) {
            returnStatement = "Low Non-Critical";
        }
        return returnStatement;
    }
}
