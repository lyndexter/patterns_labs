package com.lyndexter.wordpress.service.imlementation;

import com.lyndexter.wordpress.exception.NoSuchEntityException;
import com.lyndexter.wordpress.model.Post;
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
    //    newEntity.setName(entity.getName() != null ? entity.getName() : newEntity.getName());
    //    newEntity.setLocation(entity.getLocation() != null ? entity.getLocation() : newEntity.getLocation());
    return newEntity;
  }

}
