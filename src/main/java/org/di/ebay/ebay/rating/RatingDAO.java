package org.di.ebay.ebay.rating;

import java.time.Clock;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.di.ebay.entities.ebay.Rating;
import org.springframework.stereotype.Repository;

@Repository
public class RatingDAO {
	@PersistenceContext
	private EntityManager entityManager;
	private Clock clock;

	RatingDAO() {
		this( Clock.systemUTC() );
	}

	RatingDAO( Clock clock ) {
		this.clock = clock;
	}

	public List<Rating> getAll() {
		TypedQuery<Rating> query = entityManager.createQuery( "FROM Rating", Rating.class );
		return query.getResultList();
	}

	public Optional<Rating> get( final Long ratingId ) {
		TypedQuery<Rating> query = entityManager.createQuery( "FROM Rating where id = :id",
				Rating.class ).setParameter( "id", ratingId );
		try {
			return Optional.of( query.getSingleResult() );
		} catch ( NoResultException e ) {
			return Optional.empty();
		}
	}

	public List<Rating> getMany( final List<Long> ratingIds ) {
		TypedQuery<Rating> query = entityManager.createQuery( "FROM Rating where id in (:ids)",
				Rating.class ).setParameter( "ids", ratingIds );
		return query.getResultList();
	}

	@Transactional
	public Rating save( final Rating entity ) {
		//		entity.setDateCreated( Instant.now( clock ) );
		entityManager.persist( entity );
		return entity;
	}

	@Transactional
	public void delete( final Long ratingId ) {
		Query query = entityManager.createQuery( "Delete from Rating where id = :id" )
				.setParameter( "id", ratingId );
		query.executeUpdate();
	}

	public List<Rating> getUserRatings( final Long ratedUserId ) {
		TypedQuery<Rating> query = entityManager.createQuery(
				"FROM Rating where rated_user_id = id", Rating.class )
				.setParameter( "id", ratedUserId );
		return query.getResultList();
	}

}
