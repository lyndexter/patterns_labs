package com.lyndexter.wordpress.service.imlementation;

import com.lyndexter.wordpress.exception.NoSuchEntityException;
import com.lyndexter.wordpress.model.Post;
import com.lyndexter.wordpress.model.PublishStatus;
import com.lyndexter.wordpress.repository.PostRepository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class PostSesrvice extends CommonService<Post, String> {

  private final PostRepository postRepository;

  public PostSesrvice(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  @Override
  protected JpaRepository<Post, String> getRepository() {
    return postRepository;
  }

  @Override
  protected void throwException() {
    throw new NoSuchEntityException("Can't find this  post");
  }

  @Override
  protected Post mergeEntities(Post newEntity, Post entity) {
    newEntity.setBody(Optional.of(entity.getBody()).orElse(newEntity.getBody()));
    newEntity.setTitle(Optional.of(entity.getTitle()).orElse(newEntity.getTitle()));
    newEntity.setExpirationDate(Optional.of(entity.getExpirationDate()).orElse(newEntity.getExpirationDate()));
    newEntity.setLastModificationDate(
        Optional.of(entity.getLastModificationDate()).orElse(newEntity.getLastModificationDate()));
    newEntity.setPublishStatus(Optional.of(entity.getPublishStatus()).orElse(newEntity.getPublishStatus()));
    newEntity.setCommentCount(Optional.of(entity.getCommentCount()).orElse(newEntity.getCommentCount()));
    return newEntity;
  }

  public void changeStatus(String postId, PublishStatus status) {
    if (getRepository().existsById(postId)) {
      Post post = getRepository().findById(postId).get();
      post.setPublishStatus(status.ordinal());
      getRepository().save(post);
    }
    throwException();
  }

  public void changeCommentsCount(String postId, long commentsCount) {
    if (getRepository().existsById(postId)) {
      Post post = getRepository().findById(postId).get();
      post.setCommentCount(commentsCount);
      getRepository().save(post);
    }
    throwException();
  }

}
