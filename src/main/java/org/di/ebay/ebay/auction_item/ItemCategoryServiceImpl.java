package org.di.ebay.ebay.auction_item;

import java.util.List;
import java.util.stream.Collectors;

import org.di.ebay.ebay.AuctionItemTransformer;
import org.di.ebay.ebay.CategoryTransformer;
import org.di.ebay.ebay.transferables.ItemCategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class ItemCategoryServiceImpl implements ItemCategoryService {

	@Autowired
	private ItemCategoryDAO itemCategoryDAO;

	@Autowired
	private AuctionItemDAO auctionItemDAO;


	@Autowired
	private AuctionItemTransformer auctionItemTransformer;

	@Autowired
	private CategoryTransformer categoryTransformer;

	@Override
	public List<ItemCategoryDTO> getAll() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Long create( ItemCategoryDTO itemCategoryDTO ) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void update( Long auctionId, ItemCategoryDTO itemCategoryDTO ) {
		throw new UnsupportedOperationException();

	}

	@Override
	public ItemCategoryDTO get( Long auctionId ) {
		throw new UnsupportedOperationException();

	}

	@Override
	public List<ItemCategoryDTO> getMany( List<Long> auctionIds ) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void delete( Long auctionId, Long userId ) {
		throw new UnsupportedOperationException();

	}

	@Override
	public List<ItemCategoryDTO> getAuctionsByCategory( final String category ) {
		return itemCategoryDAO.getAuctionsByCategory( category )
				.stream()
				.map( itemCategory -> new ItemCategoryDTO(
						categoryTransformer.transformToDto( itemCategory.getCategory() ).get(),
						auctionItemTransformer.transformToDto( itemCategory.getAuctionItem() )
								.get() ) )
				.collect( Collectors.toList() );
	}

		@Override
		public List<ItemCategoryDTO> getAuctionsByCategoryAndText( final String text ) {
			return itemCategoryDAO.getAuctionsByCategoryAndText( text )
					.stream()
					.map( itemCategory -> new ItemCategoryDTO(
							categoryTransformer.transformToDto( itemCategory.getCategory() ).get(),
							auctionItemTransformer.transformToDto( itemCategory.getAuctionItem() )
									.get() ) )
					.collect( Collectors.toList() );
		}

}
