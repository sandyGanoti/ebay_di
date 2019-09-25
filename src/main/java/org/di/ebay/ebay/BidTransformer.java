package org.di.ebay.ebay;

import java.util.Optional;

import org.di.ebay.BaseTransformer;
import org.di.ebay.ebay.transferables.AuctionItemDTO;
import org.di.ebay.ebay.transferables.BidDTO;
import org.di.ebay.entities.ebay.AuctionItem;
import org.di.ebay.entities.ebay.Bid;
import org.di.ebay.exceptions.http.EntityNotFoundException;
import org.di.ebay.ebay.auction_item.AuctionItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BidTransformer extends BaseTransformer<Bid, BidDTO> {

	@Autowired
	private AuctionItemTransformer auctionItemTransformer;

	@Autowired
	private AuctionItemService auctionItemService;

	@Override
	public Optional<BidDTO> transformToDto( final Bid entity ) {
		if ( entity == null ) {
			return Optional.empty();
		}
		BidDTO dto = BidDTO.builder()
				.id( entity.getId() )
				.amount( entity.getAmount() )
				.time( entity.getTime() )
				.auctionItemId( entity.getAuctionItem().getId() )
				.userId( entity.getUserId() )
				.build();
		return Optional.of( dto );
	}

	@Override
	public Optional<Bid> transformToEntity( final BidDTO dto ) {
		if ( dto == null ) {
			return Optional.empty();
		}
		AuctionItemDTO auctionItemDTO = auctionItemService.get( dto.getAuctionItemId() );

		Optional<AuctionItem> auctionItem = auctionItemTransformer.transformToEntity(
				auctionItemDTO );
		if ( auctionItem == null ) {
			throw new EntityNotFoundException( "AuctionItem for requested bid was not found" );
		}
		if ( !auctionItemDTO.canBeEntered() ) {
			throw new EntityNotFoundException( "AuctionItem for requested bid is not active" );
		}
		Bid entity = new Bid();
		entity.setId( dto.getId() != null ? dto.getId() : 0 );
		entity.setAmount( dto.getAmount() );
		entity.setTime( dto.getTime() );
		entity.setAuctionItem( auctionItemTransformer.transformToEntity( auctionItemDTO ).get() );
		entity.setUserId( dto.getUserId() );

		return Optional.ofNullable( entity );
	}

}
