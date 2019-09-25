package org.di.ebay.ebay;

import java.util.Optional;

import org.di.ebay.BaseTransformer;
import org.di.ebay.ebay.transferables.CategoryDTO;
import org.di.ebay.entities.ebay.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryTransformer extends BaseTransformer<Category, CategoryDTO> {
	@Autowired
	private AuctionItemTransformer itemTransformer;

	@Override
	public Optional<CategoryDTO> transformToDto( final Category entity ) {
		if ( entity == null ) {
			return Optional.empty();
		}
		CategoryDTO dto = CategoryDTO.builder().name( entity.getName() ).build();
		return Optional.of( dto );
	}

	@Override
	public Optional<Category> transformToEntity( final CategoryDTO dto ) {
		if ( dto == null ) {
			return Optional.empty();
		}
		Category entity = new Category();
		entity.setName( dto.getName() );

		return Optional.ofNullable( entity );
	}

}
