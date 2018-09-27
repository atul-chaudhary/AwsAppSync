package com.example.atulchaudhary.awstestapp;

import com.amazonaws.regions.Regions;

public class Constants {
    public static final String APPSYNC_API_URL = "https://oegkwbozprehpcg7uabstrtylm.appsync-api.ap-south-1.amazonaws.com/graphql"; // TODO: Update the endpoint URL as specified on AppSync console

    // API Key Authorization
    public static final String APPSYNC_API_KEY = "da2-e2pc44ff6jcs3byqzohcaov2l4"; // TODO: Copy the API Key specified on the AppSync Console
    public static final Regions APPSYNC_REGION = Regions.AP_SOUTH_1;


    // IAM based Authorization (Cognito Identity)
    public static final String COGNITO_IDENTITY = "ap-south-1:b536d063-74b8-4878-9614-d05890c2b5a4"; // TODO: Update the Cognito Identity Pool ID
    public static final Regions COGNITO_REGION = Regions.AP_SOUTH_1; // TODO: Update the region to match the Cognito Identity Pool region

}
