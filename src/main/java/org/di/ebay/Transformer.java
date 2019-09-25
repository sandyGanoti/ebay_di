package org.di.ebay;

import java.util.List;
import java.util.Optional;

public interface Transformer<Entity, Dto> {
	
	Optional<Dto> transformToDto(Entity entity);
	
	Optional<Entity> transformToEntity(Dto dto);
	
	List<Dto> transformToDtos(List<Entity> entities);

}
