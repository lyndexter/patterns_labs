package com.lyndexter.wordpress.model;

import java.sql.Date;
import java.util.Objects;

public class PostDto {

  private byte isPage;
  private String title;
  private Date creationDate;
  private Date expirationDate;
  private Date lastModificationDate;
  private int publishStatus;
  private long commentCount;
  private String body;
  private User author;

  public byte getIsPage() {
    return isPage;
  }

  public void setIsPage(byte isPage) {
    this.isPage = isPage;
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

  public Date getExpirationDate() {
    return expirationDate;
  }

  public void setExpirationDate(Date expirationDate) {
    this.expirationDate = expirationDate;
  }

  public Date getLastModificationDate() {
    return lastModificationDate;
  }

  public void setLastModificationDate(Date lastModificationDate) {
    this.lastModificationDate = lastModificationDate;
  }

  public int getPublishStatus() {
    return publishStatus;
  }

  public void setPublishStatus(int publishStatus) {
    this.publishStatus = publishStatus;
  }

  public long getCommentCount() {
    return commentCount;
  }

  public void setCommentCount(long commentCount) {
    this.commentCount = commentCount;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public User getAuthor() {
    return author;
  }

  public void setAuthor(User author) {
    this.author = author;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PostDto postDto = (PostDto) o;
    return isPage == postDto.isPage && publishStatus == postDto.publishStatus && commentCount == postDto.commentCount
        && Objects.equals(title, postDto.title) && Objects.equals(creationDate, postDto.creationDate) && Objects.equals(
        expirationDate, postDto.expirationDate) && Objects.equals(lastModificationDate, postDto.lastModificationDate)
        && Objects.equals(body, postDto.body) && Objects.equals(author, postDto.author);
  }

  @Override
  public int hashCode() {
    return Objects.hash(isPage, title, creationDate, expirationDate, lastModificationDate, publishStatus, commentCount,
        body, author);
  }

  @Override
  public String toString() {
    return "PostDto{" + "isPage=" + isPage + ", title='" + title + '\'' + ", creationDate=" + creationDate
        + ", expirationDate=" + expirationDate + ", lastModificationDate=" + lastModificationDate + ", publishStatus="
        + publishStatus + ", commentCount=" + commentCount + ", body='" + body + '\'' + ", author=" + author + '}';
  }

  public Post createPost() {
    Post post = new Post();
    post.setIsPage(isPage);
    post.setTitle(title);
    post.setCreationDate(creationDate);
    post.setExpirationDate(expirationDate);
    post.setLastModificationDate(lastModificationDate);
    post.setPublishStatus(publishStatus);
    post.setCommentCount(commentCount);
    post.setBody(body);
    post.setAuthor(author);
    return post;
  }
}
