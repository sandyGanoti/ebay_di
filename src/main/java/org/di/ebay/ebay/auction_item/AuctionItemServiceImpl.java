package org.di.ebay.ebay.auction_item;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.di.ebay.ebay.AuctionItemTransformer;
import org.di.ebay.ebay.transferables.AuctionItemDTO;
import org.di.ebay.ebay.transferables.CategoryDTO;
import org.di.ebay.ebay.transferables.UserLimitedDTO;
import org.di.ebay.entities.ebay.AuctionItem;
import org.di.ebay.exceptions.http.EntityNotFoundException;
import org.di.ebay.exceptions.http.InvalidDtoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class AuctionItemServiceImpl implements AuctionItemService {

	@Autowired
	private AuctionItemDAO auctionItemDAO;

	@Autowired
	private CategoryDAO categoryDAO;

	@Autowired
	private AuctionItemTransformer auctionItemTransformer;

	@Override
	public List<AuctionItemDTO> getAll() {
		return auctionItemTransformer.transformToDtos( auctionItemDAO.getAll() );
	}

	@Override
	public Long create( AuctionItemDTO auctionDto ) {
		//		if ( !auctionDto.timesCorrect() ) {
		//			throw new InvalidDtoException( "Auction item startDate and endsDate are not valid" );
		//		}
		Optional<AuctionItem> entity = auctionItemTransformer.transformToEntity( auctionDto );
		if ( !entity.isPresent() ) {
			throw new InvalidDtoException( "Invalid auction item fields." );
		}
		return auctionItemDAO.save( entity.get() ).getId();
	}

	@Override
	public boolean setAuctionActive( final long auctionId, final long userId ) {
		return auctionItemDAO.setAuctionActive( auctionId, userId );
	}

	@Override
	public void update( Long auctionId, AuctionItemDTO auctionItemDto ) {
		if ( !auctionItemDto.timesCorrect() ) {
			throw new InvalidDtoException( "Auction item startDate and endsDate are not valid" );
		}
		Optional<AuctionItem> entity = auctionItemTransformer.transformToEntity( auctionItemDto );
		if ( !entity.isPresent() ) {
			throw new InvalidDtoException( "Invalid auction item." );
		}
		Optional<AuctionItem> auction = auctionItemDAO.get( auctionId );
		if ( !auction.isPresent() ) {
			throw new EntityNotFoundException( "No auction item found with the provided id" );
		}
		auctionItemDAO.update( auctionId, entity.get() );
	}

	@Override
	public AuctionItemDTO get( Long auctionId ) {
		Optional<AuctionItem> auctionItem = auctionItemDAO.get( auctionId );
		if ( !auctionItem.isPresent() ) {
			throw new EntityNotFoundException( "No auction item found with the provided id" );
		}
		return auctionItemTransformer.transformToDto( auctionItem.get() ).get();
	}

	@Override
	public List<AuctionItemDTO> getMany( List<Long> auctionIds ) {
		return auctionItemTransformer.transformToDtos( auctionItemDAO.getMany( auctionIds ) );
	}

	@Override
	public void delete( Long auctionItemId, Long userId ) {
		auctionItemDAO.delete( auctionItemId );
	}

	@Override
	public List<AuctionItemDTO> getActiveAuctions() {
		return auctionItemTransformer.transformToDtos( auctionItemDAO.getActiveAuctions() );
	}

	@Override
	public List<CategoryDTO> getCategories() {
		return categoryDAO.getCategories()
				.stream()
				.map( entity -> CategoryDTO.builder().name( entity.getName() ).build() )
				.collect( Collectors.toList() );
	}

	@Override
	public List<AuctionItemDTO> getAuctionsByUser( final long userId, final UserLimitedDTO user ) {
		List<AuctionItemDTO> auctionItemDTOS = auctionItemTransformer.transformToDtos( auctionItemDAO.getAuctionsByUser( userId ) );
		auctionItemDTOS.stream()
				.forEach( auctionItemDTO -> auctionItemDTO.getBids()
						.stream()
						.forEach( bidDTO -> bidDTO.setBidderUsername( user.getUsername() ) ) );
		return auctionItemDTOS;
	}

	@Override
	public List<AuctionItemDTO> getActiveAuctionsByUser( final long userId,
			final UserLimitedDTO user ) {
		List<AuctionItemDTO> auctionItemDTOS = auctionItemTransformer.transformToDtos(
				auctionItemDAO.getActiveAuctionsByUser( userId ) );

		auctionItemDTOS.stream()
				.forEach( auctionItemDTO -> auctionItemDTO.getBids()
						.stream()
						.forEach( bidDTO -> bidDTO.setBidderUsername( user.getUsername() ) ) );
		return auctionItemDTOS;
	}

}
