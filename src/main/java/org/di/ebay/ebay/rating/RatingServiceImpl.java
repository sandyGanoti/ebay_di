package org.di.ebay.ebay.rating;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.di.ebay.ebay.RatingTransformer;
import org.di.ebay.ebay.transferables.RatingDTO;
import org.di.ebay.entities.ebay.Rating;
import org.di.ebay.exceptions.http.InvalidDtoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	private RatingDAO ratingDAO;
	@Autowired
	private RatingTransformer ratingTransformer;

	@Override
	public Long create( RatingDTO ratingDTODto ) {
		Optional<Rating> entity = ratingTransformer.transformToEntity( ratingDTODto );
		if ( !entity.isPresent() ) {
			throw new InvalidDtoException( "Invalid rating fields." );
		}
		return ratingDAO.save( entity.get() ).getId();
	}

	@Override
	public List<RatingDTO> getAll() {
		return ratingTransformer.transformToDtos( ratingDAO.getAll() );
	}

	@Override
	public RatingDTO get( Long ratingId ) {
		Optional<Rating> rating = ratingDAO.get( ratingId );
		if ( !rating.isPresent() ) {
			throw new EntityNotFoundException( "No rating found with the provided id" );
		}
		return ratingTransformer.transformToDto( rating.get() ).get();
	}

	@Override
	public List<RatingDTO> getMany( List<Long> ratingIds ) {
		return ratingTransformer.transformToDtos( ratingDAO.getMany( ratingIds ) );
	}

	@Override
	public void delete( Long ratingId, Long userId ) {
		Optional<Rating> rating = ratingDAO.get( ratingId );
		if ( !rating.isPresent() ) {
			throw new EntityNotFoundException( "No rating found with the provided id" );
		}
		ratingDAO.delete( ratingId );
	}

	@Transactional
	public void update( Long ratingId, RatingDTO entity ) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<RatingDTO> getUserRatings( final long ratedUserId ) {
		return ratingTransformer.transformToDtos( ratingDAO.getUserRatings( ratedUserId ) );
	}

}
