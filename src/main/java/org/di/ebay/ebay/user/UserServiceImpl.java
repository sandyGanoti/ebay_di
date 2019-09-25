package org.di.ebay.ebay.user;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.di.ebay.ebay.UserTransformer;
import org.di.ebay.ebay.transferables.UserDTO;
import org.di.ebay.ebay.transferables.UserLimitedDTO;
import org.di.ebay.entities.ebay.User;
import org.di.ebay.exceptions.http.EntityNotFoundException;
import org.di.ebay.exceptions.http.InvalidDtoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private UserTransformer userTransformer;

	@Override
	public List<UserDTO> getAll() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Long create( final UserDTO userDTO ) {
		Optional<User> entity = userTransformer.transformToEntity( userDTO );
		if ( !entity.isPresent() ) {
			throw new InvalidDtoException( "Invalid user fields." );
		}
		try {
			return userDAO.save( entity.get() ).getId();
		} catch ( Exception e ) {
			return null;
		}
	}

	@Override
	public void update( final Long userId, final UserDTO userDTO ) {
		throw new UnsupportedOperationException();
	}

	@Override
	public UserDTO get( final Long userId ) {
		throw new UnsupportedOperationException();
	}

	@Override
	public UserLimitedDTO getUserInfo( final Long userId ) {
		Optional<UserLimitedDTO> user = userDAO.getUserInfo( userId );
		if ( !user.isPresent() ) {
			throw new EntityNotFoundException( "No user found with the provided id" );
		}
		return user.get();

	}

	@Override
	public UserLimitedDTO login( final String username, final String password ) {
		String encoded = null;
		try {
			MessageDigest digest = MessageDigest.getInstance( "SHA-256" );
			byte[] hash = digest.digest( password.getBytes( StandardCharsets.UTF_8 ) );
			encoded = Base64.getEncoder().encodeToString( hash );
		} catch ( NoSuchAlgorithmException e ) {
		}

		Optional<UserLimitedDTO> user = userDAO.login( username, encoded );
		if ( !user.isPresent() ) {
			throw new EntityNotFoundException( "No user found with the provided id" );
		}
		return user.get();

	}

	@Override
	public List<UserDTO> getMany( final List<Long> userIds ) {
		return userTransformer.transformToDtos( userDAO.getMany( userIds ) );

	}

	public List<UserLimitedDTO> getLimitedMany( final List<Long> userIds ) {
		return userDAO.getLimitedMany( userIds );

	}

	@Override
	public void delete( Long userIdToDelete, Long userId ) {
		throw new UnsupportedOperationException();
	}

}
