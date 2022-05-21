package com.lyndexter.wordpress.controller;

import com.lyndexter.wordpress.model.Post;
import com.lyndexter.wordpress.model.PublishStatus;

public interface EditorController {

  Post createPost(Post post);
  
  Post editPost(Post post);
  
  Post deletePost(Post post);
  
  Post changePostStatus(PublishStatus status);

  void hideComments(Boolean isVisible);
}
