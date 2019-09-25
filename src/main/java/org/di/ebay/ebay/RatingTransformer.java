package org.di.ebay.ebay;

import java.util.Optional;

import org.di.ebay.BaseTransformer;
import org.di.ebay.ebay.transferables.RatingDTO;
import org.di.ebay.entities.ebay.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RatingTransformer extends BaseTransformer<Rating, RatingDTO> {

	@Autowired
	private UserTransformer userTransformer;

	@Override
	public Optional<RatingDTO> transformToDto( final Rating entity ) {
		if ( entity == null ) {
			return Optional.empty();
		}
		RatingDTO dto = RatingDTO.builder()
				.ratedUser( userTransformer.transformToDto( entity.getUser() ).orElse( null ) )
				.raterUserId( entity.getRaterUserId() )
				.roleRatedUser( entity.getRoleRatedUser() )
				.rating( entity.getRating() )
				.time( entity.getTime() )
				.build();
		return Optional.of( dto );
	}

	@Override
	public Optional<Rating> transformToEntity( final RatingDTO dto ) {
		if ( dto == null ) {
			return Optional.empty();
		}
		Rating entity = new Rating();
		entity.setId( dto.getId() );
		entity.setUser( userTransformer.transformToEntity( dto.getRatedUser() ).orElse( null ) );
		entity.setRaterUserId( dto.getRaterUserId() );
		entity.setRating( dto.getRating() );
		entity.setTime( dto.getTime() );
		return Optional.ofNullable( entity );
	}
}