package org.di.ebay.ebay.auction_item;

import java.time.Clock;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.di.ebay.entities.ebay.Category;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDAO {

	@PersistenceContext
	private EntityManager entityManager;
	private Clock clock;

	CategoryDAO() {
		this( Clock.systemUTC() );
	}

	CategoryDAO( Clock clock ) {
		this.clock = clock;
	}

	public List<Category> getCategories() {
		TypedQuery<Category> query = entityManager.createQuery( "FROM Category", Category.class );
		return query.getResultList();
	}

}
