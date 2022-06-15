package com.lyndexter.wordpress.model;

import java.sql.Date;

public class PostPreview {

  private String uid;
  private String title;
  private Date creationDate;
  private int publishStatus;

  public PostPreview(String uid, String title, Date creationDate, int publishStatus) {
    this.uid = uid;
    this.title = title;
    this.creationDate = creationDate;
    this.publishStatus = publishStatus;
  }

  public PostPreview(Post post) {
    this(post.getUid(), post.getTitle(), post.getCreationDate(), post.getPublishStatus());
  }

  public String getUid() {
    return uid;
  }

  public void setUid(String uid) {
    this.uid = uid;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Date getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }

  public int getPublishStatus() {
    return publishStatus;
  }

  public void setPublishStatus(int publishStatus) {
    this.publishStatus = publishStatus;
  }
}
