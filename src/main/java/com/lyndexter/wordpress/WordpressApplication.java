package com.lyndexter.wordpress;

import com.lyndexter.wordpress.service.DatabaseFillingService;
import com.lyndexter.wordpress.service.imlementation.DatabaseFillingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class WordpressApplication implements CommandLineRunner {
  @Autowired private ApplicationContext applicationContext;

  public static void main(String[] args) {
    SpringApplication.run(WordpressApplication.class, args);
  }

  @Override
  public void run(String... args) {
//    DatabaseFillingService myBean=applicationContext.getBean(DatabaseFillingServiceImpl.class);
//    myBean.fillDatabaseFromFile("src/main/resources/test1.csv");
  }
}

