package org.di.ebay.ebay.messaging;

import java.util.List;

import org.di.ebay.CrudService;
import org.di.ebay.ebay.transferables.MessagingDTO;
import org.di.ebay.ebay.transferables.MessagingLimitedDTO;

public interface MessagingService extends CrudService<Long, MessagingDTO> {

	boolean isNewMessage( final long recipientId );

	List<MessagingLimitedDTO> getSentInfoBySenderId( final long senderId );

	List<MessagingLimitedDTO> getIncomingInfoByRecipientId( final long recipientId );

	List<MessagingDTO> getChatBySenderIdAndRecipientId( final long senderId,
			final long recipientId );

	boolean readChatBySenderIdAndRecipientId( final long senderId, final long recipientId );

}
