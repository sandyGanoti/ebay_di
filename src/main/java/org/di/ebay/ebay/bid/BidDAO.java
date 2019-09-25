package org.di.ebay.ebay.bid;

import java.time.Clock;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.di.ebay.entities.ebay.AuctionItem;
import org.di.ebay.entities.ebay.Bid;
import org.springframework.stereotype.Repository;

@Repository
public class BidDAO {

	@PersistenceContext
	private EntityManager entityManager;
	private Clock clock;

	BidDAO() {
		this( Clock.systemUTC() );
	}

	BidDAO( Clock clock ) {
		this.clock = clock;
	}

	public Optional<Bid> get( final long bidId ) {
		TypedQuery<Bid> query = entityManager.createQuery( "FROM Bid where id = :id", Bid.class )
				.setParameter( "id", bidId );
		try {
			return Optional.of( query.getSingleResult() );
		} catch ( NoResultException e ) {
			return Optional.empty();
		}
	}

	public List<Bid> getMany( final List<Long> bidIds ) {
		TypedQuery<Bid> query = entityManager.createQuery( "FROM Bid where id in (:ids)",
				Bid.class ).setParameter( "ids", bidIds );
		return query.getResultList();
	}

	@Transactional
	public void update( final Long bidId, final Bid entity ) {
		throw new UnsupportedOperationException();
	}

	public List<Bid> getAll() {
		TypedQuery<Bid> query = entityManager.createQuery( "FROM Bid", Bid.class );
		return query.getResultList();
	}

	@Transactional
	public Bid save( final Bid entity ) throws Exception {
		try {
			entity.setTime( Instant.now( clock ) );
			entityManager.persist( entity );
			return entity;
		} catch ( ConstraintViolationException e ) {
			throw new Exception( "User cannot bid in the same auction with the same amount" );
		}
	}

	@Transactional
	public void delete( final long bidId ) {
		Query query = entityManager.createQuery( "Delete from Bid where id = :id" )
				.setParameter( "id", bidId );
		query.executeUpdate();
	}

	public List<Bid> getBidsByAuctionItemId( final long auctionItemId ) {
		TypedQuery<Bid> query = entityManager.createQuery( "FROM Bid where auction_item_id = :id",
				Bid.class ).setParameter( "id", auctionItemId );
		return query.getResultList();
	}

	public List<Bid> getBidsByUserId( final long userId ) {
		TypedQuery<Bid> query = entityManager.createQuery( "FROM Bid where user_id = :id",
				Bid.class ).setParameter( "id", userId );
		return query.getResultList();
	}

	public List<Bid> getBidsByAuctionItemIdAndUserId( final long auctionItemId,
			final long userId ) {
		TypedQuery<Bid> query = entityManager.createQuery(
				"FROM Bid where auction_item_id = :id and user_id = :userId", Bid.class )
				.setParameter( "id", auctionItemId )
				.setParameter( "userId", userId );
		return query.getResultList();
	}

	public Optional<Bid> getWinningBidForAuction( final long auctionItemId ) {
		TypedQuery<Bid> query = entityManager.createQuery(
				"SELECT *, max(amount) FROM Bid where auction_item_id = :id group by user_id ",
				Bid.class ).setParameter( "id", auctionItemId );
		List<Bid> bids = query.getResultList();
		return bids.isEmpty() ? Optional.empty() : Optional.of( bids.get( 0 ) );
	}

	public List<Bid> getBids( final Set<Long> bidIds ) {
		TypedQuery<Bid> query = entityManager.createQuery( "FROM Bid where id in (:ids)",
				Bid.class ).setParameter( "ids", bidIds );
		return query.getResultList();
	}

	public List<Long> getAuctionsWhereUserBidded( final long userId ) {
		TypedQuery<AuctionItem> query = entityManager.createQuery(
				"SELECT auctionItem FROM Bid bid where bid.userId = :id", AuctionItem.class )
				.setParameter( "id", userId );
		return query.getResultList()
				.stream()
				.map( bid -> bid.getId() )
				.collect( Collectors.toList() );
	}
}
