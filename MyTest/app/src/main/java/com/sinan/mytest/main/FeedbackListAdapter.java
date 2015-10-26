package com.sinan.mytest.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sinan.mytest.R;

import java.text.DecimalFormat;
import java.util.List;


/**
 * Custom Array adapter for Feedback list.
 *
 * @author Sinan Shoukath
 */
public class FeedbackListAdapter extends ArrayAdapter<Feedback> {
  private final int listRowResId;
  private final Context context;
  private final List<Feedback> feedbacks;

  public FeedbackListAdapter(Context context, int resource, List<Feedback> feedbacks) {
    super(context, resource, feedbacks);
    this.context = context;
    this.listRowResId = resource;
    this.feedbacks = feedbacks;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    View view = convertView;
    if (view == null) {
      LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      view = inflater.inflate(listRowResId, null);
    }
    Feedback feedback = feedbacks.get(position);
    TextView average = (TextView)view.findViewById(R.id.average_m);
    TextView billNumber = (TextView)view.findViewById(R.id.bill_number);
    TextView tableNumber = (TextView)view.findViewById(R.id.table_number);
    String averageValue = getAverage(feedback);
    average.setText(averageValue);
    billNumber.setText("Bill Number     "+feedback.getBillNumber());
    tableNumber.setText("Table Number     "+feedback.getTableNumber());
    return view;
  }

  private String getAverage(Feedback feedback) {
    float average = (Float.valueOf(feedback.getM1()) + Float.valueOf(feedback.getM2()) + Float.valueOf(feedback.getM3()) +
        Float.valueOf(feedback.getM4())+ Float.valueOf(feedback.getM5())) / 5;
    return new DecimalFormat("#.##").format(average);
  }
}
