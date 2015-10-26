package com.sinan.mytest.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.sinan.mytest.R;
import com.sinan.mytest.client.ClientConfig;
import com.sinan.mytest.client.ClientInterface;
import com.sinan.mytest.models.Feedback;
import com.sinan.mytest.models.FeedbackResults;
import com.sinan.mytest.utils.FeedbackListAdapter;
import com.sinan.mytest.utils.Preference;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Main activity which shows feedback list.
 *
 * @author Sinan Shoukath
 */
public class HomeScreen extends Activity {
  private ProgressBar progressBar;
  private Preference prefs;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    prefs = new Preference(getApplicationContext());
    progressBar = (ProgressBar)findViewById(R.id.progress);
    progressBar.setVisibility(View.VISIBLE);
    ClientInterface clientInterface = ClientConfig.createClientInterface();
    clientInterface.groupList(new Callback<FeedbackResults>() {
      @Override
      public void success(FeedbackResults feedbackResult, Response response) {
        List<Feedback> feedbacks = feedbackResult.getResults();
        prefs.updateFeedbacks(feedbacks);
        populateItems(feedbacks);
      }

      @Override
      public void failure(RetrofitError error) {
        error.printStackTrace();
      }
    });
  }

  private void populateItems(List<Feedback> feedbacks) {
    ListView feedbacksList = (ListView) findViewById(R.id.feedbacks_list);
    FeedbackListAdapter adapter = new FeedbackListAdapter(HomeScreen.this, R.layout.feedback_list_item, feedbacks);
    feedbacksList.setAdapter(adapter);
    progressBar.setVisibility(View.GONE);
    feedbacksList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(HomeScreen.this, FeedbackDetailScreen.class);
        intent.putExtra("position", position);
        HomeScreen.this.startActivity(intent);
      }
    });
  }
}
