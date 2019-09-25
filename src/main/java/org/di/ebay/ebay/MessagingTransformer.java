package org.di.ebay.ebay;

import java.util.Optional;

import org.di.ebay.BaseTransformer;
import org.di.ebay.ebay.transferables.MessagingDTO;
import org.di.ebay.entities.ebay.Messaging;
import org.springframework.stereotype.Component;

@Component
public class MessagingTransformer extends BaseTransformer<Messaging, MessagingDTO> {

	@Override
	public Optional<MessagingDTO> transformToDto( final Messaging entity ) {
		if ( entity == null ) {
			return Optional.empty();
		}
		MessagingDTO dto = MessagingDTO.builder()
				.id( entity.getId() )
				.sender( entity.getSender() )
				.recipient( entity.getRecipient() )
				.messageBody( entity.getMessageBody() )
				.createdAt( entity.getCreatedAt() )
				.build();
		return Optional.of( dto );
	}

	@Override
	public Optional<Messaging> transformToEntity( final MessagingDTO dto ) {
		if ( dto == null ) {
			return Optional.empty();
		}
		Messaging entity = new Messaging();
		entity.setId( dto.getId() );
		entity.setSender( dto.getSender() );
		entity.setRecipient( dto.getRecipient() );
		entity.setMessageBody( dto.getMessageBody() );
		entity.setCreatedAt( dto.getCreatedAt() );
		entity.setReadStatus( false );
		return Optional.ofNullable( entity );
	}

}
