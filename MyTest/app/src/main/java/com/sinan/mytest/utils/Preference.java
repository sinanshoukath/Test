package com.sinan.mytest.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sinan.mytest.models.Feedback;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Preference class to handle data cache.
 *
 * @author Sinan Shoukath
 */
public class Preference {
  private static final String PREFS = "my.prefs";
  private static final String FEEDBACK_LIST_PREFS_KEY = "feedback.list.prefs.key";

  private final SharedPreferences prefs;
  private final Gson gson;

  public Preference(Context context) {
    prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
    gson = new Gson();
  }

  public List<Feedback> getFeedbacks() {
    String feedbacks = prefs.getString(FEEDBACK_LIST_PREFS_KEY, null);
    Type listType = new TypeToken<List<Feedback>>() {}.getType();
    return gson.fromJson(feedbacks, listType);
  }

  public void updateFeedbacks(List<Feedback> feedbacks) {
    SharedPreferences.Editor editor = prefs.edit();
    editor.putString(FEEDBACK_LIST_PREFS_KEY, gson.toJson(feedbacks));
    editor.apply();
  }
}
