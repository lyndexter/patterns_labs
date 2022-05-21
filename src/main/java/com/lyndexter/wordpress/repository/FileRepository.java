package com.lyndexter.wordpress.repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public interface FileRepository {

  List<String[]> readFile(String fileName);

  File getFile(String fileName);
  
  Iterable<String[]> getLineReader(String fileName) throws FileNotFoundException;
}
