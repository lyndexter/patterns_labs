package com.lyndexter.wordpress.controller.implementation;

import com.lyndexter.wordpress.controller.ViewController;
import com.lyndexter.wordpress.model.Comment;
import com.lyndexter.wordpress.model.Post;
import com.lyndexter.wordpress.model.PostPreview;
import com.lyndexter.wordpress.service.imlementation.CommentSesrvice;
import com.lyndexter.wordpress.service.imlementation.PostSesrvice;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class DefaultViewController implements ViewController {

  private final PostSesrvice postSesrvice;
  private final CommentSesrvice commentSesrvice;

  public DefaultViewController(PostSesrvice postSesrvice, CommentSesrvice commentSesrvice) {
    this.postSesrvice = postSesrvice;
    this.commentSesrvice = commentSesrvice;
  }

  @Override
  @GetMapping(value = "/wordpress/post/{postUid}")
  public Post viewPost(@PathVariable String postUid) {
    return postSesrvice.getEntity(postUid);
  }

  @Override
  @GetMapping(value = "/wordpress/post")
  public Collection<PostPreview> viewAllPosts() {
    return postSesrvice.getEntities().stream().map(PostPreview::new).collect(Collectors.toList());
  }

  @Override
  @PostMapping(value = "/wordpress/post/{postUid}/comment")
  public Comment addComment(@RequestBody Comment comment, @PathVariable String postUid) {
    Comment newComment = commentSesrvice.createEntity(comment);
    Post post = postSesrvice.getEntity(postUid);
    post.getCommentsByUid().add(newComment);
    postSesrvice.updateEntity(post, postUid);
    return newComment;
  }

  @Override
  @PutMapping(value = "/wordpress/post/comment{commentId}")
  public Comment editComment(@RequestBody Comment comment, @PathVariable String commentId) {
    return commentSesrvice.updateEntity(comment, commentId);
  }

  @Override
  @DeleteMapping(value = "/wordpress/post/comment/{commentUid}")
  public void deleteComment(@PathVariable String commentUid) {
    commentSesrvice.deleteEntity(commentUid);
  }

  @Override
  @GetMapping(value = "/wordpress/post/{postUid}/comment")
  public Collection<Comment> viewCommentsToPost(@PathVariable String postUid) {
    return commentSesrvice.getEntities()
        .stream()
        .filter(comment -> Objects.equals(comment.getPostByPostUid().getUid(), postUid))
        .collect(Collectors.toList());
  }
}
