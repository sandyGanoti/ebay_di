package org.di.ebay.ebay.auction_item;

import java.util.List;

import org.di.ebay.CrudService;
import org.di.ebay.ebay.transferables.ItemCategoryDTO;

public interface ItemCategoryService extends CrudService<Long, ItemCategoryDTO> {

	List<ItemCategoryDTO> getAuctionsByCategory( String category );

	List<ItemCategoryDTO> getAuctionsByCategoryAndText( String text );

}
