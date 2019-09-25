package org.di.ebay.ebay.user;

import java.util.List;

import org.di.ebay.ebay.transferables.UserDTO;
import org.di.ebay.ebay.transferables.UserLimitedDTO;
import org.di.ebay.CrudService;

public interface UserService extends CrudService<Long, UserDTO> {

	UserLimitedDTO getUserInfo( Long userId );

	UserLimitedDTO login( String username, String password );

	List<UserLimitedDTO> getLimitedMany( List<Long> userId );

}
