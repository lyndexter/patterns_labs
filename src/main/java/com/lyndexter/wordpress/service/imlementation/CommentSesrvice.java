package com.lyndexter.wordpress.service.imlementation;

import com.lyndexter.wordpress.exception.NoSuchEntityException;
import com.lyndexter.wordpress.model.Comment;
import com.lyndexter.wordpress.model.Post;
import com.lyndexter.wordpress.repository.CommentRepository;
import com.lyndexter.wordpress.repository.PostRepository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentSesrvice extends CommonService<Comment, String> {

  private final CommentRepository commentRepository;

  public CommentSesrvice(CommentRepository commentRepository) {
    this.commentRepository = commentRepository;
  }

  @Override
  protected JpaRepository<Comment, String> getRepository() {
    return commentRepository;
  }

  @Override
  protected void throwException() {
    throw new NoSuchEntityException("Can't find this comment");
  }

  @Override
  protected Comment mergeEntities(Comment newEntity, Comment entity) {
    newEntity.setMessage(Optional.of(entity.getMessage()).orElse(newEntity.getMessage()));
    //    newEntity.setName(entity.getName() != null ? entity.getName() : newEntity.getName());
    //    newEntity.setLocation(entity.getLocation() != null ? entity.getLocation() : newEntity.getLocation());
    return newEntity;
  }

}
