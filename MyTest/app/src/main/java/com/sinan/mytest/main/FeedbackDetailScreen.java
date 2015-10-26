package com.sinan.mytest.main;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.sinan.mytest.R;
import com.sinan.mytest.models.Feedback;
import com.sinan.mytest.utils.FeedbackPagerAdapter;
import com.sinan.mytest.utils.Preference;

import java.util.List;

public class FeedbackDetailScreen extends Activity {
  private ViewPager pager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_feedback_detail_screen);
    int position = getIntent().getIntExtra("position", 0);
    pager = (ViewPager)findViewById(R.id.feedback_pager);
    Preference prefs = new Preference(getApplicationContext());
    List<Feedback> feedbacks = prefs.getFeedbacks();
    FeedbackPagerAdapter adapter = new FeedbackPagerAdapter(feedbacks, FeedbackDetailScreen.this);
    pager.setAdapter(adapter);
    pager.setCurrentItem(position);
    Button prevButton = (Button)findViewById(R.id.prev_button);
    Button nextButton = (Button)findViewById(R.id.next_button);
    prevButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        showPreviousPage();
      }
    });
    nextButton.setOnClickListener(new View.OnClickListener() {
      @Override  public void onClick(View v) {
        showNextPage();
      }
    });
  }

  private void showNextPage() {
    int currentPage = pager.getCurrentItem();
    int totalPages = pager.getAdapter().getCount();
    int nextPage = currentPage+1;
    if (nextPage >= totalPages) {
      return;
    }
    pager.setCurrentItem(nextPage, true);
  }

  private void showPreviousPage() {
    int currentPage = pager.getCurrentItem();
    int previousPage = currentPage-1;
    if (previousPage < 0) {
      return;
    }
    pager.setCurrentItem(previousPage, true);
  }
}
