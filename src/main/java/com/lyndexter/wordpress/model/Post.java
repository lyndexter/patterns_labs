package com.lyndexter.wordpress.model;

import com.opencsv.bean.CsvBindByPosition;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Post {

  @CsvBindByPosition(position = 1)
  private String uid;
  @CsvBindByPosition(position = 2)
  private byte isPage;
  @CsvBindByPosition(position = 3)
  private String title;
  @CsvBindByPosition(position = 4)
  private Date creationDate;
  @CsvBindByPosition(position = 5)
  private Date expiratioDate;
  @CsvBindByPosition(position = 6)
  private Date lastModificationDate;
  @CsvBindByPosition(position = 7)
  private int publishStatus;
  @CsvBindByPosition(position = 8)
  private long commentCount;
  @CsvBindByPosition(position = 9)
  private String body;
  private User author;
  private Collection<Comment> commentsByUid;

  @Id
  @Column(name = "uid")
  public String getUid() {
    return uid;
  }

  public void setUid(String uid) {
    this.uid = uid;
  }

  @Basic
  @Column(name = "is_page")
  public byte getIsPage() {
    return isPage;
  }

  public void setIsPage(byte isPage) {
    this.isPage = isPage;
  }

  @Basic
  @Column(name = "title")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @Basic
  @Column(name = "creation_date")
  public Date getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }

  @Basic
  @Column(name = "expiratio_date")
  public Date getExpiratioDate() {
    return expiratioDate;
  }

  public void setExpiratioDate(Date expiratioDate) {
    this.expiratioDate = expiratioDate;
  }

  @Basic
  @Column(name = "last_modification_date")
  public Date getLastModificationDate() {
    return lastModificationDate;
  }

  public void setLastModificationDate(Date lastModificationDate) {
    this.lastModificationDate = lastModificationDate;
  }

  @Basic
  @Column(name = "publish_status")
  public int getPublishStatus() {
    return publishStatus;
  }

  public void setPublishStatus(int publishStatus) {
    this.publishStatus = publishStatus;
  }

  @Basic
  @Column(name = "comment_count")
  public long getCommentCount() {
    return commentCount;
  }

  public void setCommentCount(long commentCount) {
    this.commentCount = commentCount;
  }

  @Basic
  @Column(name = "body")
  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Post post = (Post) o;
    return isPage == post.isPage && publishStatus == post.publishStatus && commentCount == post.commentCount
        && Objects.equals(uid, post.uid) && Objects.equals(title, post.title)
        && Objects.equals(creationDate, post.creationDate) && Objects.equals(expiratioDate, post.expiratioDate)
        && Objects.equals(lastModificationDate, post.lastModificationDate) && Objects.equals(body, post.body);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uid, isPage, title, creationDate, expiratioDate, lastModificationDate, publishStatus,
        commentCount, body);
  }


  @OneToMany(mappedBy = "postByPostUid")
  public Collection<Comment> getCommentsByUid() {
    return commentsByUid;
  }

  public void setCommentsByUid(Collection<Comment> commentsByUid) {
    this.commentsByUid = commentsByUid;
  }

  @ManyToOne
  @JoinColumn(name = "author", referencedColumnName = "uid", nullable = false)
  public User getAuthor() {
    return author;
  }

  public void setAuthor(User author) {
    this.author = author;
  }

  @Override
  public String toString() {
    return "Post{" + "uid='" + uid + '\'' + ", isPage=" + isPage + ", title='" + title + '\'' + ", creationDate="
        + creationDate + ", expiratioDate=" + expiratioDate + ", lastModificationDate=" + lastModificationDate
        + ", publishStatus=" + publishStatus + ", commentCount=" + commentCount + ", body='" + body + '\'' + ", author="
        + author + '}';
  }
}
