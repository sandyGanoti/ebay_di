package org.di.ebay;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class BaseTransformer<Entity, Dto> implements Transformer<Entity, Dto> {

  @Override
  public abstract Optional<Dto> transformToDto(Entity entity);

  @Override
  public abstract Optional<Entity> transformToEntity(Dto dto);

  @Override
  public List<Dto> transformToDtos(List<Entity> entities) {
    return entities.stream()
        .map(this::transformToDto)
        .filter(dto -> dto.isPresent())
        .map(optional -> optional.get())
        .collect(Collectors.toList());
  }

}
