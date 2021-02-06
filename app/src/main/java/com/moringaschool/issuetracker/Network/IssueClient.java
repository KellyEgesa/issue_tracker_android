package com.moringaschool.issuetracker.Network;

import com.moringaschool.issuetracker.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class IssueClient {
    public static Retrofit retrofit = null;

    public static IssueTrackerApi urlRequest() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(Constants.ISSUE_TRACKER_API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(IssueTrackerApi.class);
    }
}
