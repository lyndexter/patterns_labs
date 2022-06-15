package com.lyndexter.wordpress.controller;

import com.lyndexter.wordpress.model.Post;
import com.lyndexter.wordpress.model.PostDto;
import com.lyndexter.wordpress.model.PublishStatus;

public interface EditorController {

  Post createPost(PostDto postDto);

  Post editPost(Post post, String id);

  Post deletePost(String id);

  void changePostStatus(PublishStatus status, String id);

  void hideComments(Boolean isVisible, String id);
}
