package com.lyndexter.wordpress.controller;

import com.lyndexter.wordpress.model.Comment;
import com.lyndexter.wordpress.model.Post;
import com.lyndexter.wordpress.model.PostPreview;
import java.util.Collection;

public interface ViewController {

  Post viewPost(String postUid);

  Collection<PostPreview> viewAllPosts();

  Comment addComment(Comment comment, String postUid);

  Comment editComment(Comment comment, String commentId);

  void deleteComment(String commentUid);

  Collection<Comment> viewCommentsToPost(String postUid);
}
