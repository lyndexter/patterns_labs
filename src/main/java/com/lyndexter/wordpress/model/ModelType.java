package com.lyndexter.wordpress.model;

import java.util.Arrays;
import java.util.Objects;

public enum ModelType {
  COMMENT("Comment", Comment.class, 3), POST("Post", Post.class, 2), USER("User", User.class, 1), Default("Default",
      Object.class, 0);

  private String className;
  private Class classType;
  private int savePriority;

  ModelType(String className, Class classType, int savePriority) {
    this.className = className;
    this.classType = classType;
    this.savePriority = savePriority;
  }

  public static Class getClassTypeByName(String className) {
    return Arrays.stream(ModelType.values()).filter(modelType -> Objects.equals(modelType.className, className))
        .findFirst().orElse(Default).classType;
  }

  public static ModelType valueBy(String className) {
    return Arrays.stream(ModelType.values()).filter(modelType -> Objects.equals(modelType.className, className))
        .findFirst().orElse(Default);
  }

  public String getClassName() {
    return className;
  }

  public int getSavePriority() {
    return savePriority;
  }
}
