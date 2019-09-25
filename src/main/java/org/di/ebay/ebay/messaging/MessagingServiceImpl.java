package org.di.ebay.ebay.messaging;

import java.util.List;
import java.util.Optional;

import org.di.ebay.ebay.MessagingTransformer;
import org.di.ebay.ebay.transferables.MessagingDTO;
import org.di.ebay.ebay.transferables.MessagingLimitedDTO;
import org.di.ebay.entities.ebay.Messaging;
import org.di.ebay.exceptions.http.InvalidDtoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class MessagingServiceImpl implements MessagingService {

	@Autowired
	private MessagingDAO messagingDAO;

	@Autowired
	private MessagingTransformer messagingTransformer;

	@Override
	public List<MessagingDTO> getAll() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Long create( final MessagingDTO messagingDTO ) {
		Optional<Messaging> entity = messagingTransformer.transformToEntity( messagingDTO );
		if ( !entity.isPresent() ) {
			throw new InvalidDtoException( "Invalid messaging fields." );
		}
		return messagingDAO.save( entity.get() ).getId();
	}

	@Override
	public void update( final Long messagingId, final MessagingDTO messagingDTO ) {
		throw new UnsupportedOperationException();
	}

	@Override
	public MessagingDTO get( final Long messagingId ) {
		throw new UnsupportedOperationException();

	}

	@Override
	public List<MessagingDTO> getMany( final List<Long> bidIds ) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void delete( final Long messagingId, final Long userId ) {
		messagingDAO.delete( messagingId, userId );
	}

	@Override
	public boolean isNewMessage( final long recipientId ) {
		return messagingDAO.isNewMessage( recipientId );
	}

	@Override
	public List<MessagingLimitedDTO> getSentInfoBySenderId( final long senderId ) {
		return messagingDAO.getSentInfoBySenderId( senderId );
	}

	@Override
	public List<MessagingLimitedDTO> getIncomingInfoByRecipientId( final long recipientId ) {
		return messagingDAO.getIncomingInfoByRecipientId( recipientId );
	}

	@Override
	public List<MessagingDTO> getChatBySenderIdAndRecipientId( final long senderId,
			final long recipientId ) {
		return messagingTransformer.transformToDtos(
				messagingDAO.getChatBySenderIdAndRecipientId( senderId, recipientId ) );
	}

	@Override
	public boolean readChatBySenderIdAndRecipientId( final long senderId, final long recipientId ) {
		return messagingDAO.readChatBySenderIdAndRecipientId( senderId, recipientId );
	}
}
