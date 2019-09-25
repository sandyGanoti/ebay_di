package org.di.ebay.ebay.auction_item;

import java.util.List;

import org.di.ebay.CrudService;
import org.di.ebay.ebay.transferables.AuctionItemDTO;
import org.di.ebay.ebay.transferables.CategoryDTO;
import org.di.ebay.ebay.transferables.UserLimitedDTO;

public interface AuctionItemService extends CrudService<Long, AuctionItemDTO> {

	List<AuctionItemDTO> getActiveAuctions();

	boolean setAuctionActive( long auctionId, long userId );

	List<CategoryDTO> getCategories();

	List<AuctionItemDTO> getAuctionsByUser( long userId,  UserLimitedDTO user );

	List<AuctionItemDTO> getActiveAuctionsByUser( long userId, UserLimitedDTO user );


}
