package com.lyndexter.wordpress.controller;

import com.lyndexter.wordpress.model.Comment;
import com.lyndexter.wordpress.model.Post;
import java.util.Collection;

public interface ViewController {

  Post viewPost(String postUid);

  Comment addComment(Comment comment);

  Comment editComment(Comment comment);

  void deleteComment(String commentUid);

  Collection<Comment> viewCommentsToPost(String postUid);
}
