package org.di.ebay;

import java.util.List;

public interface CrudService<Id, Dto> {
  
  List<Dto> getAll();

  Id create(Dto dto);

  Dto get(Id id);

  List<Dto> getMany(List<Id> ids);

  void update(Id id, Dto dto);

  void delete(Id id, Id userId);

}
