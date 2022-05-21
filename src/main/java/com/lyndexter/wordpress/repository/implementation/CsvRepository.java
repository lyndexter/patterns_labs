package com.lyndexter.wordpress.repository.implementation;

import com.lyndexter.wordpress.exception.NoSuchFileException;
import com.lyndexter.wordpress.repository.FileRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CsvRepository implements FileRepository {

  @Override
  public List<String[]> readFile(String fileName) {
    try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
      return reader.readAll();
//      List<String[]> fileLines = reader.readAll();
//      return fileLines.stream().map(line -> Arrays.stream(line).collect(Collectors.toList()))
//          .collect(Collectors.toList());
    } catch (IOException | CsvException e) {
      throw new NoSuchFileException(e.getMessage());
    }
  }

  @Override
  public File getFile(String fileName) {
    return new File(fileName);
  }

  @Override
  public Iterable<String[]>  getLineReader(String fileName) throws FileNotFoundException {
    return new CSVReader(new FileReader(fileName));
  }
}
