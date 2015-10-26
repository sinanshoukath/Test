package com.sinan.mytest.client;

import com.sinan.mytest.models.Feedback;
import com.sinan.mytest.models.FeedbackResults;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.PUT;
import retrofit.http.Path;


public interface ClientInterface {
  @GET("/classes/Feedback")
  void groupList(Callback<FeedbackResults> callback);

  @PUT("/classes/Feedback/{objectId}")
  void updateFeedback(@Body Feedback feedback, @Path("objectId") String objectId, Callback<Feedback> callback);
}
