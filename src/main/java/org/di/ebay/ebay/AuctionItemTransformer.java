package org.di.ebay.ebay;

import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;

import org.di.ebay.BaseTransformer;
import org.di.ebay.ebay.bid.BidService;
import org.di.ebay.ebay.transferables.AuctionItemDTO;
import org.di.ebay.ebay.user.UserService;
import org.di.ebay.entities.ebay.AuctionItem;
import org.di.ebay.entities.ebay.Category;
import org.di.ebay.entities.ebay.ItemCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuctionItemTransformer extends BaseTransformer<AuctionItem, AuctionItemDTO> {

	@Autowired
	private CategoryTransformer categoryTransformer;

	@Autowired
	private BidService bidService;

	@Autowired
	private UserService userService;

	@Override
	public Optional<AuctionItemDTO> transformToDto( final AuctionItem entity ) {
		if ( entity == null ) {
			return Optional.empty();
		}
		AuctionItemDTO dto = AuctionItemDTO.builder()
				.id( entity.getId() )
				.name( entity.getName() )
				.description( entity.getDescription() )
				.location( entity.getLocation() )
				.country( entity.getCountry() )
				.categories( entity.getCategories()
						.stream()
						.map( category -> categoryTransformer.transformToDto( category )
								.orElse( null ) )
						.filter( categoryDTO -> categoryDTO != null )
						.collect( Collectors.toSet() ) )
				.firstBid( entity.getFirstBid() )
				.startedAt( entity.getStartedAt() )
				.endsAt( entity.getEndsAt() )
				.currently( entity.getCurrently() )
				.userId( entity.getUserId() )
				.active( entity.getActive() )
				.bids( new HashSet( bidService.getBidsByAuctionItemId( entity.getId() ) ) )
				.build();
		dto.getBids().stream().forEach( bidDTO -> bidDTO.setBidderUsername( userService.getUserInfo( entity.getUserId() ).getUsername() ) );
		return Optional.of( dto );
	}

	@Override
	public Optional<AuctionItem> transformToEntity( final AuctionItemDTO dto ) {
		if ( dto == null ) {
			return Optional.empty();
		}
		AuctionItem entity = new AuctionItem();
		entity.setId( dto.getId() );
		entity.setName( dto.getName() );
		entity.setItemCategories( dto.getCategories()
				.stream()
				.map( categoryDTO -> categoryTransformer.transformToEntity( categoryDTO )
						.orElse( null ) )
				.filter( category -> category != null )
				.map( category -> getItemCategory( entity, category ) )
				.collect( Collectors.toSet() ) );
		entity.setDescription( dto.getDescription() );
		entity.setLocation( dto.getLocation() );
		entity.setCountry( dto.getCountry() );
		entity.setFirstBid( dto.getFirstBid() );
		entity.setStartedAt( dto.getStartedAt() );
		entity.setEndsAt( dto.getEndsAt() );
		entity.setCurrently( dto.getCurrently() );
		entity.setUserId( dto.getUserId() );
		entity.setActive( dto.getActive() );

		return Optional.ofNullable( entity );
	}

	private ItemCategory getItemCategory( final AuctionItem auctionItem, final Category category ) {
		ItemCategory itemCategory = new ItemCategory();
		itemCategory.setAuctionItem( auctionItem );
		itemCategory.setCategory( category );
		return itemCategory;
	}

}
