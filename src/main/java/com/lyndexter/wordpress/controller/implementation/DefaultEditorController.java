package com.lyndexter.wordpress.controller.implementation;

import com.lyndexter.wordpress.controller.EditorController;
import com.lyndexter.wordpress.model.Comment;
import com.lyndexter.wordpress.model.Post;
import com.lyndexter.wordpress.model.PostDto;
import com.lyndexter.wordpress.model.PublishStatus;
import com.lyndexter.wordpress.service.imlementation.CommentSesrvice;
import com.lyndexter.wordpress.service.imlementation.PostSesrvice;
import java.util.Objects;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class DefaultEditorController implements EditorController {

  private final PostSesrvice service;
  private final CommentSesrvice commentSesrvice;

  public DefaultEditorController(PostSesrvice service, CommentSesrvice commentSesrvice) {
    this.service = service;
    this.commentSesrvice = commentSesrvice;
  }

  @Override
  @PostMapping(value = "/wordpress/post")
  public Post createPost(@RequestBody PostDto postDto) {
    return service.createEntity(postDto.createPost());
  }

  @Override
  @PutMapping(value = "/wordpress/post/{id}")
  public Post editPost(@RequestBody Post post, @PathVariable String id) {
    return service.updateEntity(post, id);
  }

  @Override
  @DeleteMapping(value = "/wordpress/post/{id}")
  public Post deletePost(@PathVariable String id) {
    commentSesrvice.getEntities()
        .stream()
        .filter(comment -> Objects.equals(comment.getPostByPostUid().getUid(), id))
        .map(Comment::getUid)
        .forEach(commentSesrvice::deleteEntity);
    return service.deleteEntity(id);
  }

  @Override
  @PostMapping(value = "/wordpress/post/{id}/status")
  public void changePostStatus(@RequestBody PublishStatus status, @PathVariable String id) {
    service.changeStatus(id, status);
  }

  @Override
  @PostMapping(value = "/wordpress/post/{id}/hide-comments")
  public void hideComments(@RequestBody Boolean isVisible, @PathVariable String id) {
    long commentsCount = 0;
    if (Boolean.TRUE.equals(isVisible)) {
      commentsCount = commentSesrvice.getEntities()
          .stream()
          .filter(comment -> Objects.equals(comment.getPostByPostUid().getUid(), id))
          .count();
    }
    service.changeCommentsCount(id, commentsCount);
  }
}
