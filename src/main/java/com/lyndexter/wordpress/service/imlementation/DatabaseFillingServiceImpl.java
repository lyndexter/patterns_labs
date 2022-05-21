package com.lyndexter.wordpress.service.imlementation;

import com.lyndexter.wordpress.model.Comment;
import com.lyndexter.wordpress.model.ModelType;
import com.lyndexter.wordpress.model.Post;
import com.lyndexter.wordpress.model.User;
import com.lyndexter.wordpress.repository.FileRepository;
import com.lyndexter.wordpress.repository.implementation.CsvRepository;
import com.lyndexter.wordpress.service.DaoService;
import com.lyndexter.wordpress.service.DatabaseFillingService;
import com.opencsv.bean.MappingStrategy;
import com.opencsv.bean.util.OpencsvUtils;
import com.opencsv.exceptions.CsvChainedException;
import com.opencsv.exceptions.CsvFieldAssignmentException;
import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import org.springframework.stereotype.Service;

@Service
public class DatabaseFillingServiceImpl implements DatabaseFillingService {
  private static Comparator<String[]> comparator=
      Comparator.comparing(line -> ModelType.valueBy(line[0]).getSavePriority());

  private FileRepository fileRepository;
  private DaoService<Post, String> postService;
  private DaoService<User, String> userService;
  private DaoService<Comment, String> commentService;

  public DatabaseFillingServiceImpl(CsvRepository fileRepository, PostSesrvice postService, UserSesrvice userService,
      CommentSesrvice commentService) {
    this.fileRepository=fileRepository;
    this.postService=postService;
    this.userService=userService;
    this.commentService=commentService;
  }

  @Override
  public void fillDatabaseFromFile(String fileName) {
    try {
      List<String[]> modelLines=fileRepository.readFile(fileName);
      modelLines.sort(comparator);

      for (String[] modelLine : modelLines) {
        Class clazz=ModelType.getClassTypeByName(modelLine[0]);
        Object entity=createModel(clazz, modelLine);
        System.out.println(saveModel(entity, modelLine));
      }
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (CsvChainedException e) {
      e.printStackTrace();
    } catch (CsvFieldAssignmentException e) {
      e.printStackTrace();
    }

  }

  @Override
  public <T> T createModel(Class<T> classType, String[] args)
      throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException,
      IllegalAccessException, CsvChainedException, CsvFieldAssignmentException {
    MappingStrategy<? extends T> mappingStrategy=
        OpencsvUtils.determineMappingStrategy(classType, Locale.getDefault(), "");
    return mappingStrategy.populateNewBean(args);
  }

  public <T> boolean saveModel(T entity, String[] args) {
    String className=entity.getClass().getSimpleName();
    if (className.equals(ModelType.COMMENT.getClassName())) {
      Comment comment=(Comment) entity;
      User user=userService.getEntity(args[4]);
      comment.setUserByUserUid(user);
      Post post=postService.getEntity(args[5]);
      comment.setPostByPostUid(post);
      commentService.createEntity(comment);
    } else if (className.equals(ModelType.POST.getClassName())) {
      User user=userService.getEntity(args[10]);
      Post post=(Post) entity;
      post.setAuthor(user);
      postService.createEntity(post);
    } else if (className.equals(ModelType.USER.getClassName())) {
      userService.createEntity((User) entity);
    } else {
      return false;
    }
    return true;
  }
}
