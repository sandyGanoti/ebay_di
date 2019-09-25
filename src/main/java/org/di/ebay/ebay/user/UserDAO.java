package org.di.ebay.ebay.user;

import java.time.Clock;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.di.ebay.ebay.transferables.UserLimitedDTO;
import org.di.ebay.entities.ebay.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {
	@PersistenceContext
	private EntityManager entityManager;
	private Clock clock;

	UserDAO() {
		this( Clock.systemUTC() );
	}

	UserDAO( Clock clock ) {
		this.clock = clock;
	}

	@Transactional
	public User save( final User user ) {
		user.setCreatedAt( Instant.now( clock ) );

		try {
			entityManager.persist( user );
		} catch ( Exception e ) {
			return null;
		}
		return user;
	}

	public Optional<User> get( final Long userId ) {
		TypedQuery<User> query = entityManager.createQuery( "FROM User where id = :id", User.class )
				.setParameter( "id", userId );
		try {
			return Optional.of( query.getSingleResult() );
		} catch ( NoResultException e ) {
			return Optional.empty();
		}
	}

	@Cacheable("user_limited")
	public Optional<UserLimitedDTO> getUserInfo( final Long userId ) {
		TypedQuery<UserLimitedDTO> query = entityManager.createQuery(
				"SELECT NEW org.di.ebay.ebay.transferables.UserLimitedDTO(u.username, u.id) FROM User u where id = :id",
				UserLimitedDTO.class ).setParameter( "id", userId );
		try {
			return Optional.of( query.getSingleResult() );
		} catch ( NoResultException e ) {
			return Optional.empty();
		}
	}

	public Optional<UserLimitedDTO> login( final String username, final String password ) {
		TypedQuery<UserLimitedDTO> query = entityManager.createQuery(
				"SELECT NEW org.di.ebay.ebay.transferables.UserLimitedDTO(u.username, u.id) FROM User u where username = :username and password= :password",
				UserLimitedDTO.class )
				.setParameter( "username", username )
				.setParameter( "password", password );
		try {
			return Optional.of( query.getSingleResult() );
		} catch ( NoResultException e ) {
			return Optional.empty();
		}
	}

	public List<User> getMany( final List<Long> userIds ) {
		TypedQuery<User> query = entityManager.createQuery( "FROM User where id in (:ids)",
				User.class ).setParameter( "ids", userIds );
		return query.getResultList();
	}

	public List<UserLimitedDTO> getLimitedMany( final List<Long> userIds ) {
		TypedQuery<UserLimitedDTO> query = entityManager.createQuery(
				"SELECT NEW org.di.ebay.ebay.transferables.UserLimitedDTO(u.username, u.id) FROM User u where id in (:ids)",
				UserLimitedDTO.class ).setParameter( "ids", userIds );
		return query.getResultList();
	}
}
