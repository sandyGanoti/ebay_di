package org.di.ebay.ebay.rating;

import java.util.List;

import org.di.ebay.CrudService;
import org.di.ebay.ebay.transferables.RatingDTO;

public interface RatingService extends CrudService<Long, RatingDTO> {

	List<RatingDTO> getUserRatings( long userId );

}
