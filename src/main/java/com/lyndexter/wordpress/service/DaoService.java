package com.lyndexter.wordpress.service;

import java.util.List;
import javax.transaction.Transactional;

public interface DaoService<T, ID> {

  List<T> getEntities();

  @Transactional
  T getEntity(ID id);

  @Transactional
  T createEntity(T entity);

  @Transactional
  T updateEntity(T entity, ID id);

  @Transactional
  T deleteEntity(ID id);
}
