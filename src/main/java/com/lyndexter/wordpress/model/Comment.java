package com.lyndexter.wordpress.model;

import com.opencsv.bean.CsvBindByPosition;
import java.sql.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Comment {
  @CsvBindByPosition(position = 1) private String uid;
  @CsvBindByPosition(position = 2) private String message;
  @CsvBindByPosition(position = 3) private Date creationDate;
  private User userByUserUid;
  private Post postByPostUid;

  @Id
  @Column(name = "uid")
  public String getUid() {
    return uid;
  }

  public void setUid(String uid) {
    this.uid = uid;
  }

  @Basic
  @Column(name = "message")
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Basic
  @Column(name = "creation_date")
  public Date getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Comment comment = (Comment) o;
    return Objects.equals(uid, comment.uid) && Objects.equals(message, comment.message) && Objects.equals(creationDate,
        comment.creationDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uid, message, creationDate);
  }

  @ManyToOne
  @JoinColumn(name = "user", referencedColumnName = "uid", nullable = false)
  public User getUserByUserUid() {
    return userByUserUid;
  }

  public void setUserByUserUid(User userByUserUid) {
    this.userByUserUid = userByUserUid;
  }

  @ManyToOne
  @JoinColumn(name = "post_uid", referencedColumnName = "uid", nullable = false)
  public Post getPostByPostUid() {
    return postByPostUid;
  }

  public void setPostByPostUid(Post postByPostUid) {
    this.postByPostUid = postByPostUid;
  }

  @Override
  public String toString() {
    return "Comment{" + "uid='" + uid + '\'' + ", message='" + message + '\'' + ", creationDate=" + creationDate
        + ", userByUserUid=" + userByUserUid + '}';
  }
}
