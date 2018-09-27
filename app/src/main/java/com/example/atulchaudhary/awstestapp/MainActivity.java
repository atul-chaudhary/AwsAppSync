package com.example.atulchaudhary.awstestapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.amazonaws.demo.posts.AdToDosMutation;
import com.amazonaws.demo.posts.GetPostQuery;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.appsync.AWSAppSyncClient;
import com.amazonaws.mobileconnectors.appsync.fetcher.AppSyncResponseFetchers;
import com.apollographql.apollo.GraphQLCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;

import javax.annotation.Nonnull;

public class MainActivity extends AppCompatActivity {


    private AWSAppSyncClient mAWSAppSyncClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAWSAppSyncClient = AWSAppSyncClient.builder()
                .context(getApplicationContext())
                .awsConfiguration(new AWSConfiguration(getApplicationContext()))
                .build();

        //queryData();
        saveData();

    }


    public void queryData() {
        mAWSAppSyncClient.query(GetPostQuery.builder().build())
                .responseFetcher(AppSyncResponseFetchers.CACHE_AND_NETWORK)
                .enqueue(postsCallback);
    }

    private GraphQLCall.Callback<GetPostQuery.Data> postsCallback = new GraphQLCall.Callback <GetPostQuery.Data>() {
        @Override
        public void onResponse(@Nonnull final Response<GetPostQuery.Data> response) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                   Log.e("logyeah",">>  "+response.data());
                }
            });
        }

        @Override
        public void onFailure(@Nonnull ApolloException e) {
            Log.e("myTag", "Failed to perform AllPostsQuery", e);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //PostsActivity.this.mSwipeRefreshLayout.setRefreshing(false);
                }
            });
        }
    };


    private void saveData() {

        AdToDosMutation.Data expected = new AdToDosMutation.Data(new AdToDosMutation.AdToDos(
                "Post",
                "2",
                "title",
                "sasas",
                "message"
        ));

        AdToDosMutation adToDosMutation = AdToDosMutation.builder().build();
        ClientFactory.getInstance(this).mutate(adToDosMutation, expected).enqueue(postsSaveCallback);
    }

    private GraphQLCall.Callback<AdToDosMutation.Data> postsSaveCallback = new GraphQLCall.Callback<AdToDosMutation.Data>() {
        @Override
        public void onResponse(@Nonnull final Response<AdToDosMutation.Data> response) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                   // mAwsProg.setVisibility(View.INVISIBLE);
                    Log.e("SahajLOG", "ResponseSave>> " +response.data());
                    //Toast.makeText(AddPostActivity.this, "Added post", Toast.LENGTH_SHORT).show();
                    //AddPostActivity.this.finish();
                }
            });
        }

        @Override
        public void onFailure(@Nonnull final ApolloException e) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.e("SahajLOG", "Error::>> " +e);
                    //Toast.makeText(AddPostActivity.this, "Failed to add post", Toast.LENGTH_SHORT).show();
                    //AddPostActivity.this.finish();
                }
            });
        }
    };
}
