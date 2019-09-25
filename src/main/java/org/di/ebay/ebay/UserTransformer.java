package org.di.ebay.ebay;

import java.util.Optional;

import org.di.ebay.BaseTransformer;
import org.di.ebay.ebay.transferables.UserDTO;
import org.di.ebay.entities.ebay.User;
import org.springframework.stereotype.Component;

@Component
public class UserTransformer extends BaseTransformer<User, UserDTO> {

	@Override
	public Optional<UserDTO> transformToDto( final User entity ) {
		if ( entity == null ) {
			return Optional.empty();
		}
		UserDTO userDTO = UserDTO.builder()
				.id( entity.getId() )
				.username( entity.getUsername() )
				.build();

		return Optional.of( userDTO );
	}

	@Override
	public Optional<User> transformToEntity( final UserDTO dto ) {
		if ( dto == null ) {
			return Optional.empty();
		}
		User entity = new User();
		entity.setId( dto.getId() );
		entity.setUsername( dto.getUsername() );
		entity.setPassword( dto.getPassword() );
		entity.setFirstName( dto.getFirstName() );
		entity.setLastName( dto.getLastName() );
		entity.setPhoneNumber( dto.getPhoneNumber() );
		entity.setCountry( dto.getCountry() );
		entity.setCreatedAt( dto.getCreatedAt() );
		entity.setEmail( dto.getEmail() );

		return Optional.ofNullable( entity );
	}

}
