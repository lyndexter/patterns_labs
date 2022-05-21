package com.lyndexter.wordpress.service.imlementation;

import com.lyndexter.wordpress.exception.NoSuchEntityException;
import com.lyndexter.wordpress.model.Post;
import com.lyndexter.wordpress.model.User;
import com.lyndexter.wordpress.repository.PostRepository;
import com.lyndexter.wordpress.repository.UserRepository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class UserSesrvice extends CommonService<User, String> {

  private final UserRepository userRepository;

  public UserSesrvice(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  protected JpaRepository<User, String> getRepository() {
    return userRepository;
  }

  @Override
  protected void throwException() {
    throw new NoSuchEntityException("Can't find this  user");
  }

  @Override
  protected User mergeEntities(User newEntity, User entity) {
    newEntity.setName(Optional.of(entity.getName()).orElse(newEntity.getName()));
    //    newEntity.setName(entity.getName() != null ? entity.getName() : newEntity.getName());
    //    newEntity.setLocation(entity.getLocation() != null ? entity.getLocation() : newEntity.getLocation());
    return newEntity;
  }

}
