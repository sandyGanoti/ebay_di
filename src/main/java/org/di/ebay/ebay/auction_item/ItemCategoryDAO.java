package org.di.ebay.ebay.auction_item;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.di.ebay.entities.ebay.ItemCategory;
import org.springframework.stereotype.Repository;

@Repository
public class ItemCategoryDAO {

	@PersistenceContext
	private EntityManager entityManager;

	// TODO: douleuei??? Prepei na xrhsimopoihsw to item_category table
	public List<ItemCategory> getAuctionsByCategory( final String category ) {
		TypedQuery<ItemCategory> query = entityManager.createQuery(
				"FROM ItemCategory where category_name = :category", ItemCategory.class )
				.setParameter( "category", category );
		return query.getResultList();
	}

	//TODO: the seach for text should be rethought and implemented
	public List<ItemCategory> getAuctionsByCategoryAndText( final String text ) {
		TypedQuery<ItemCategory> query = entityManager.createQuery(
				"FROM AuctionItem where category_name = :category and active = 1",
				ItemCategory.class ).setParameter( "category", text );
		return query.getResultList();
	}

}
