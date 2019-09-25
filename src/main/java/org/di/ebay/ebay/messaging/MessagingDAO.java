package org.di.ebay.ebay.messaging;

import java.time.Clock;
import java.time.Instant;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.di.ebay.ebay.transferables.MessagingLimitedDTO;
import org.di.ebay.entities.ebay.Messaging;
import org.springframework.stereotype.Repository;

@Repository
public class MessagingDAO {

	@PersistenceContext
	private EntityManager entityManager;
	private Clock clock;

	MessagingDAO() {
		this( Clock.systemUTC() );
	}

	MessagingDAO( Clock clock ) {
		this.clock = clock;
	}

	/* fetch info for recipients that sender=userId has sent messages to */
	public List<MessagingLimitedDTO> getSentInfoBySenderId( final long userId ) {
		TypedQuery<MessagingLimitedDTO> query = entityManager.createQuery(
				"SELECT NEW org.di.ebay.ebay.transferables.MessagingLimitedDTO(m.id, u.username, m.createdAt) FROM Messaging m LEFT JOIN User u ON m.recipient = u.id WHERE m.sender = :userId GROUP BY u.id",
				MessagingLimitedDTO.class ).setParameter( "userId", userId );
		return query.getResultList();
	}

	/* fetch info for senders that recipient=userId has received messages from */
	public List<MessagingLimitedDTO> getIncomingInfoByRecipientId( final long userId ) {
		TypedQuery<MessagingLimitedDTO> query = entityManager.createQuery(
				"SELECT NEW org.di.ebay.ebay.transferables.MessagingLimitedDTO(m.id, u.username, m.createdAt) FROM Messaging m LEFT JOIN User u ON m.recipient = u.id WHERE m.recipient = :userId GROUP BY u.id",
				MessagingLimitedDTO.class ).setParameter( "userId", userId );
		return query.getResultList();
	}

	public boolean isNewMessage( final long recipientId ) {
		TypedQuery<Long> query = entityManager.createQuery(
				"SELECT COUNT(*) FROM Messaging WHERE readStatus = 0 AND recipient = :recipientId",
				Long.class ).setParameter( "recipientId", recipientId );
		return query.getSingleResult() > 0;
	}

	public List<Messaging> getChatBySenderIdAndRecipientId( final long senderId,
			final long recipientId ) {
		TypedQuery<Messaging> query = entityManager.createQuery(
				"FROM Messaging WHERE recipient = :recipientId and sender = :senderId ORDER BY created_at ASC",
				Messaging.class )
				.setParameter( "recipientId", recipientId )
				.setParameter( "senderId", senderId );
		return query.getResultList();
	}

	@Transactional
	public boolean readChatBySenderIdAndRecipientId( final long senderId, final long recipientId ) {
		String queryString = new StringBuilder().append( "update Messaging " )
				.append( "set read_status = 1 " )
				.append( "where recipient = :recipientId " )
				.append( "and sender = :senderId " )
				.toString();
		Query query = entityManager.createQuery( queryString )
				.setParameter( "recipientId", recipientId )
				.setParameter( "senderId", senderId );
		return query.executeUpdate() > 0;
	}

	@Transactional
	public Messaging save( final Messaging messaging ) {
		messaging.setCreatedAt( Instant.now( clock ) );
		entityManager.persist( messaging );

		return messaging;
	}

	@Transactional
	public void delete( final Long messagingId, final Long userId ) {
		TypedQuery<Messaging> query = entityManager.createQuery( "FROM Messaging WHERE id= :id",
				Messaging.class ).setParameter( "id", messagingId );
		Messaging messaging = query.getSingleResult();
		if ( messaging == null || ( !messaging.getSender()
				.equals( userId ) && messaging.getRecipient().equals( userId ) ) ) {
			return;
		}
		Query deleteQuery = entityManager.createQuery(
				"DELETE FROM Messaging WHERE recipient= :recipient and sender= :sender" )
				.setParameter( "recipient", messaging.getRecipient() )
				.setParameter( "sender", messaging.getSender() );
		deleteQuery.executeUpdate();
	}
}
