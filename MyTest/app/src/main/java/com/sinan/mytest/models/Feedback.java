package com.sinan.mytest.models;


/**
 * Feedback represents model for feedback data.
 *
 * @author Sinan Shoukath
 */
public class Feedback {
  private final String objectId;
  private final String billNumber;
  private String comment;
  private final String overallScore;
  private final String tableNumber;
  private final String m1;
  private final String m2;
  private final String m3;
  private final String m4;
  private final String m5;

  public Feedback(String objectId, String billNumber, String comment, String overallScore, String tableNumber, String m1, String m2,
      String m3, String m4, String m5) {
    this.objectId = objectId;
    this.billNumber = billNumber;
    this.comment = comment;
    this.overallScore = overallScore;
    this.tableNumber = tableNumber;
    this.m1 = m1;
    this.m2 = m2;
    this.m3 = m3;
    this.m4 = m4;
    this.m5 = m5;
  }

  public String getObjectId() {
    return objectId;
  }

  public String getBillNumber() {
    return billNumber;
  }

  public String getComment() {
    return comment;
  }

  public String getOverallScore() {
    return overallScore;
  }

  public String getTableNumber() {
    return tableNumber;
  }

  public String getM1() {
    return m1;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public String getM2() {
    return m2;
  }

  public String getM3() {
    return m3;
  }

  public String getM4() {
    return m4;
  }

  public String getM5() {
    return m5;
  }
}
