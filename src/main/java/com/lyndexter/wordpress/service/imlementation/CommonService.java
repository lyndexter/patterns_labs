package com.lyndexter.wordpress.service.imlementation;

import com.lyndexter.wordpress.service.DaoService;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class CommonService<T, ID> implements DaoService<T, ID> {

  protected abstract JpaRepository<T, ID> getRepository();

  protected abstract void throwException();

  protected abstract T mergeEntities(T newEntity, T entity);

  @Override
  public List<T> getEntities() {
    return getRepository().findAll();
  }

  @Override
  public T getEntity(ID id) {
    if (getRepository().existsById(id)) {
      return getRepository().findById(id).get();
    }
    throwException();
    return null;
  }

  @Override
  public T createEntity(T entity) {
    return getRepository().save(entity);
  }

  @Override
  public T updateEntity(T entity, ID id) {
    if (getRepository().existsById(id)) {
      T newEntity = getRepository().findById(id).get();
      return getRepository().save(mergeEntities(newEntity, entity));
    }
    throwException();
    return null;
  }

  @Override
  public T deleteEntity(ID id) {
    if (getRepository().existsById(id)) {
      T entity = getRepository().findById(id).get();
      getRepository().deleteById(id);
      return entity;
    }
    throwException();
    return null;
  }
}
