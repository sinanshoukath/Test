package com.sinan.mytest.models;

import java.util.List;

public class FeedbackResults {
  private final List<Feedback> results;

  public FeedbackResults(List<Feedback> results) {
    this.results = results;
  }

  public List<Feedback> getResults() {
    return results;
  }
}
