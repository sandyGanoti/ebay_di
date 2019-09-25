package org.di.ebay.ebay;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
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
		try {
			MessageDigest digest = MessageDigest.getInstance( "SHA-256" );
			byte[] hash = digest.digest( dto.getPassword().getBytes( StandardCharsets.UTF_8 ) );
			String encoded = Base64.getEncoder().encodeToString( hash );

			entity.setPassword( String.valueOf( encoded ) );
		} catch ( NoSuchAlgorithmException e ) {
		}
		entity.setFirstName( dto.getFirstName() );
		entity.setLastName( dto.getLastName() );
		entity.setPhoneNumber( dto.getPhoneNumber() );
		entity.setCountry( dto.getCountry() );
		entity.setCreatedAt( dto.getCreatedAt() );
		entity.setEmail( dto.getEmail() );

		return Optional.ofNullable( entity );
	}

}
