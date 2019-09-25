package org.di.ebay.ebay.auction_item;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.di.ebay.entities.ebay.AuctionItem;
import org.springframework.stereotype.Repository;

@Repository
public class AuctionItemDAO {

	@PersistenceContext
	private EntityManager entityManager;

	public Optional<AuctionItem> get( final Long auctionItemId ) {
		TypedQuery<AuctionItem> query = entityManager.createQuery(
				"FROM AuctionItem where id = :id", AuctionItem.class )
				.setParameter( "id", auctionItemId );
		try {
			return Optional.of( query.getSingleResult() );
		} catch ( NoResultException e ) {
			return Optional.empty();
		}
	}

	public List<AuctionItem> getMany( final List<Long> auctionItemIds ) {
		TypedQuery<AuctionItem> query = entityManager.createQuery(
				"FROM AuctionItem where id in (:ids)", AuctionItem.class )
				.setParameter( "ids", auctionItemIds );
		return query.getResultList();
	}

	@Transactional
	public void update( Long auctionId, AuctionItem entity ) {
		throw new UnsupportedOperationException();
	}

	public List<AuctionItem> getAll() {
		TypedQuery<AuctionItem> query = entityManager.createQuery( "FROM AuctionItem",
				AuctionItem.class );
		return query.getResultList();
	}

	@Transactional
	public AuctionItem save( final AuctionItem entity ) {
		entityManager.persist( entity );
		return entity;
	}

	@Transactional
	public void delete( final Long auctionItemId ) {
		Query query = entityManager.createQuery( "DELETE from AuctionItem where id= :id" )
				.setParameter( "id", auctionItemId );
		query.executeUpdate();
		query = entityManager.createQuery( "DELETE from ItemCategory where auction_item= :id" )
				.setParameter( "id", auctionItemId );
		query.executeUpdate();
	}

	public List<AuctionItem> getActiveAuctions() {
		TypedQuery<AuctionItem> query = entityManager.createQuery(
				"FROM AuctionItem where active = :active", AuctionItem.class )
				.setParameter( "active", true );
		return query.getResultList();
	}

	@Transactional
	public boolean setAuctionActive( final long auctionItemId, final long userId ) {
		String queryString = new StringBuilder().append( "update AuctionItem " )
				.append( "set active = 1 " )
				.append( "where id = :auctionItemId " )
				.append( "and userId = :userId " )
				.toString();
		Query query = entityManager.createQuery( queryString )
				.setParameter( "auctionItemId", auctionItemId )
				.setParameter( "userId", userId );

		return query.executeUpdate() == 1;
	}

	public List<AuctionItem> getAuctionsByUser( final long userId ) {
		TypedQuery<AuctionItem> query = entityManager.createQuery(
				"FROM AuctionItem where userId = :userId", AuctionItem.class )
				.setParameter( "userId", userId );
		return query.getResultList();
	}

	public List<AuctionItem> getActiveAuctionsByUser( final long userId ) {
		TypedQuery<AuctionItem> query = entityManager.createQuery(
				"FROM AuctionItem where userId = :userId and active=1", AuctionItem.class )
				.setParameter( "userId", userId );
		return query.getResultList();
	}

}
