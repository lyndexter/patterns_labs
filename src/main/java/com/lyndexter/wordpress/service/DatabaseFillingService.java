package com.lyndexter.wordpress.service;

import com.opencsv.exceptions.CsvChainedException;
import com.opencsv.exceptions.CsvFieldAssignmentException;
import java.lang.reflect.InvocationTargetException;

public interface DatabaseFillingService {
  
  void fillDatabaseFromFile(String fileName);
  
  <T> T createModel(Class<T> classType, String[] args)
      throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException,
      ClassNotFoundException, CsvChainedException, CsvFieldAssignmentException; 
  
  
}
