import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.apache.commons.lang3.RandomStringUtils;

public class Main {

  public static void main(final String[] args) {
    File file=new File("src/main/resources/test1.csv");
    try {
      FileWriter outputfile=new FileWriter(file);
      CSVWriter writer=new CSVWriter(outputfile);
      
      List<String[]> users=generateUsers();
      List<String[]> posts=generatePosts(users);
      List<String[]> comments=generateComments(users, posts);
      
      writer.writeAll(posts);
      writer.writeAll(comments);
      writer.writeAll(users);
      
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static List<String[]> generateComments(List<String[]> users, List<String[]> posts) {
    List<String[]> comments=new ArrayList<>();
    List<String> usersUiD=users.stream().map(user -> user[1]).collect(Collectors.toList());
    List<String> postsUiD=posts.stream().map(post -> post[1]).collect(Collectors.toList());
    
    for (int i=0; i < 900; i++) {
      String[] line=new String[] {"Comment",
          UUID.randomUUID().toString(),
          RandomStringUtils.random(40, true, true),
          createRandomDate(),
          usersUiD.get(createRandomIntBetween(0, usersUiD.size()-1)),
          postsUiD.get(createRandomIntBetween(0, postsUiD.size()-1))};
      comments.add(line);
    }
    return comments;
  }

  public static List<String[]> generateUsers() {
    List<String[]> users=new ArrayList<>();
    
    for (int i=0; i < 100; i++) {
      String[] line=new String[] {"User",
          UUID.randomUUID().toString(),
          RandomStringUtils.random(16, true, true),
          RandomStringUtils.random(16, true, true),
          RandomStringUtils.random(16, true, true),
          RandomStringUtils.random(16, true, true),
          RandomStringUtils.random(30, true, true)};
      users.add(line);
    }
    return users;
  }

  public static List<String[]> generatePosts(List<String[]> users) {
    List<String[]> posts=new ArrayList<>();
    List<String> usersUiD=users.stream().map(user -> user[1]).collect(Collectors.toList());
    
    for (int i=0; i < 100; i++) {
      String[] line=new String[] {"Post",
          UUID.randomUUID().toString(),
          String.valueOf(createRandomIntBetween(0, 1)),
          RandomStringUtils.random(20, true, true),
          createRandomDate(),
          createRandomDate(),
          createRandomDate(),
          String.valueOf(createRandomIntBetween(1, 3)),
          String.valueOf(createRandomIntBetween(0, 40)),
          RandomStringUtils.random(30, true, true),
          usersUiD.get(createRandomIntBetween(0, usersUiD.size()-1))};
      posts.add(line);
    }
    return posts;
  }

  public static String createRandomDate() {
    int day=createRandomIntBetween(1, 28);
    int month=createRandomIntBetween(1, 12);
    int year=createRandomIntBetween(2010, 2022);
    return LocalDate.of(year, month, day).toString();
  }

  public static int createRandomIntBetween(int start, int end) {
    return start + (int) Math.round(Math.random() * (end - start));
  }
}