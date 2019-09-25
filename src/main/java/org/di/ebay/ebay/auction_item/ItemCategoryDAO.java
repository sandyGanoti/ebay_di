package org.di.ebay.ebay.auction_item;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.di.ebay.entities.ebay.AuctionItem;
import org.di.ebay.entities.ebay.Category;
import org.di.ebay.entities.ebay.ItemCategory;
import org.springframework.stereotype.Repository;

@Repository
public class ItemCategoryDAO {

	@PersistenceContext
	private EntityManager entityManager;

	public List<ItemCategory> getAuctionsByCategory( final String category ) {
		TypedQuery<ItemCategory> query = entityManager.createQuery(
				"FROM ItemCategory where category_name = :category", ItemCategory.class )
				.setParameter( "category", category );
		return query.getResultList();
	}

	public List<ItemCategory> getAuctionsByCategoryAndText( final String text ) {
		TypedQuery<AuctionItem> query = entityManager.createQuery(
				"FROM AuctionItem where description like :text",
				AuctionItem.class ).setParameter( "text", "%" + text + "%" );
		List<AuctionItem> auctionItems = query.getResultList();
		List<Long> ids = auctionItems.stream().map( AuctionItem::getId).collect( Collectors.toList() );

		TypedQuery<ItemCategory> queryIC = entityManager.createQuery(
				"FROM ItemCategory where auction_item in ( :ids )", ItemCategory.class )
				.setParameter( "ids", ids );
		return queryIC.getResultList();
	}

}
