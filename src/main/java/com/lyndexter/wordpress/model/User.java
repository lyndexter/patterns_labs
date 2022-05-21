package com.lyndexter.wordpress.model;

import com.opencsv.bean.CsvBindByPosition;
import java.util.Collection;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.annotations.GeneratorType;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
public class User {

  @CsvBindByPosition(position = 1)
  private String uid;
  @CsvBindByPosition(position = 2)
  private String username;
  @CsvBindByPosition(position = 3)
  private String password;
  @CsvBindByPosition(position = 4)
  private String email;
  @CsvBindByPosition(position = 5)
  private String role;
  @CsvBindByPosition(position = 6)
  private String name;
  private Collection<Comment> commentsByUid;
  private Collection<Post> postsByUid;

  @Id
  @Column(name = "uid")
  public String getUid() {
    return uid;
  }

  public void setUid(String uid) {
    this.uid = uid;
  }

  @Basic
  @Column(name = "username")
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  @Basic
  @Column(name = "password")
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Basic
  @Column(name = "email")
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Basic
  @Column(name = "role")
  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  @Basic
  @Column(name = "name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(uid, user.uid) && Objects.equals(username, user.username)
        && Objects.equals(password, user.password) && Objects.equals(email, user.email)
        && Objects.equals(role, user.role) && Objects.equals(name, user.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uid, username, password, email, role, name);
  }

  @OneToMany(mappedBy = "userByUserUid")
  public Collection<Comment> getCommentsByUid() {
    return commentsByUid;
  }

  public void setCommentsByUid(Collection<Comment> commentsByUid) {
    this.commentsByUid = commentsByUid;
  }

  @OneToMany(mappedBy = "author")
  public Collection<Post> getPostsByUid() {
    return postsByUid;
  }

  public void setPostsByUid(Collection<Post> postsByUid) {
    this.postsByUid = postsByUid;
  }

  @Override
  public String toString() {
    return "User{" + "uid='" + uid + '\'' + ", username='" + username + '\'' + ", password='" + password + '\''
        + ", email='" + email + '\'' + ", role='" + role + '\'' + ", name='" + name + '\'' + ", commentsByUid="
        + commentsByUid + ", postsByUid=" + postsByUid + '}';
  }
}
