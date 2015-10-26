package com.sinan.mytest.utils;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sinan.mytest.R;
import com.sinan.mytest.client.ClientConfig;
import com.sinan.mytest.client.ClientInterface;
import com.sinan.mytest.models.Feedback;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * Custom pager adapter for feedback details view pager.
 *
 * @author Sinan Shoukath
 */
public class FeedbackPagerAdapter extends PagerAdapter {
  private final List<Feedback> feedbacks;
  private final Activity activity;

  public FeedbackPagerAdapter(List<Feedback> feedbacks, Activity activity) {
    this.feedbacks = feedbacks;
    this.activity = activity;
  }

  @Override
  public Object instantiateItem(final ViewGroup container, final int position) {
    LayoutInflater inflater = LayoutInflater.from(activity.getApplicationContext());
    View view = inflater.inflate(R.layout.feedback_page_item, null);
    TextView scoreText = (TextView)view.findViewById(R.id.score_text);
    TextView m1Text = (TextView)view.findViewById(R.id.m1);
    TextView m2Text = (TextView)view.findViewById(R.id.m2);
    TextView m3Text = (TextView)view.findViewById(R.id.m3);
    TextView m4Text = (TextView)view.findViewById(R.id.m4);
    TextView m5Text = (TextView)view.findViewById(R.id.m5);
    final Preference prefs = new Preference(activity.getApplicationContext());
    final TextView comment = (TextView)view.findViewById(R.id.comment_text);
    comment.setVisibility(View.VISIBLE);
    final EditText commentEdit = (EditText)view.findViewById(R.id.comment_edit);
    commentEdit.setVisibility(View.INVISIBLE);
    Button saveButton = (Button)view.findViewById(R.id.save_button);
    final ProgressBar progressBar = (ProgressBar)view.findViewById(R.id.progress);
    final Feedback feedback = feedbacks.get(position);
    scoreText.setText(feedback.getOverallScore());
    m1Text.setText(feedback.getM1());
    m2Text.setText(feedback.getM2());
    m3Text.setText(feedback.getM3());
    m4Text.setText(feedback.getM4());
    m5Text.setText(feedback.getM5());
    comment.setText(feedback.getComment().equals("") ? "No comments added" : feedback.getComment());
    comment.setOnClickListener(new View.OnClickListener() {
      @Override  public void onClick(View v) {
        comment.setVisibility(View.INVISIBLE);
        commentEdit.setVisibility(View.VISIBLE);
        commentEdit.setText(feedback.getComment());
        commentEdit.setSelection(feedback.getComment().length());
        commentEdit.requestFocus();
        showSoftKeyboard();
      }
    });
    saveButton.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        final String newComment = commentEdit.getText().toString();
        if (!newComment.equals("")) {
          progressBar.setVisibility(View.VISIBLE);
          ClientInterface clientInterface = ClientConfig.createClientInterface();
          feedback.setComment(newComment);
          clientInterface.updateFeedback(feedback, feedback.getObjectId(), new Callback<Feedback>() {
            @Override
            public void success(Feedback feedback, Response response) {
              closeSoftKeyboard();
              prefs.updateFeedbacks(feedbacks);
              progressBar.setVisibility(View.GONE);
              comment.setVisibility(View.VISIBLE);
              commentEdit.setVisibility(View.INVISIBLE);
              notifyDataSetChanged();
            }

            @Override
            public void failure(RetrofitError error) {
              progressBar.setVisibility(View.GONE);
              error.printStackTrace();
            }
          });
        }
      }
    });
    container.addView(view);
    return view;
  }

  @Override
  public int getCount() {
    return feedbacks.size();
  }

  @Override
  public boolean isViewFromObject(View view, Object object) {
    return view == object;
  }

  @Override
  public void destroyItem(View container, int position, Object object) {
    ((ViewPager) container).removeView((View) object);
  }

  @Override
  public int getItemPosition(Object object) {
    return POSITION_NONE;
  }

  public void showSoftKeyboard() {
    InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
    inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
  }

  public void closeSoftKeyboard() {
    InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
    if (activity.getCurrentFocus() != null) {
      inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(),0);
    }
  }
}
