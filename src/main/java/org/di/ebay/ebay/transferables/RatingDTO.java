package org.di.ebay.ebay.transferables;

import java.time.Instant;

import org.di.ebay.entities.ebay.Role;

public class RatingDTO {

	private Long id;
	private UserDTO ratedUser;
	private Role roleRatedUser;
	private Long raterUserId;
	private int rating;
	private Instant time;

	public static Builder builder() {
		return new Builder();
	}

	public Long getId() {
		return id;
	}

	public void setId( final Long id ) {
		this.id = id;
	}

	public UserDTO getRatedUser() {
		return ratedUser;
	}

	public void setRatedUser( final UserDTO ratedUser ) {
		this.ratedUser = ratedUser;
	}

	public Role getRoleRatedUser() {
		return roleRatedUser;
	}

	public void setRoleRatedUser( final Role roleRatedUser ) {
		this.roleRatedUser = roleRatedUser;
	}

	public Long getRaterUserId() {
		return raterUserId;
	}

	public void setRaterUserId( final Long raterUserId ) {
		this.raterUserId = raterUserId;
	}

	public int getRating() {
		return rating;
	}

	public void setRating( final int rating ) {
		this.rating = rating;
	}

	public Instant getTime() {
		return time;
	}

	public void setTime( final Instant time ) {
		this.time = time;
	}

	public static class Builder {
		private RatingDTO tmp = new RatingDTO();

		public Builder is( Long id ) {
			tmp.setId( id );
			return this;
		}

		public Builder ratedUser( UserDTO ratedUser ) {
			tmp.setRatedUser( ratedUser );
			return this;
		}

		public Builder raterUserId( Long raterUserId ) {
			tmp.setRaterUserId( raterUserId );
			return this;
		}

		public Builder roleRatedUser( Role roleRatedUser ) {
			tmp.setRoleRatedUser( roleRatedUser );
			return this;
		}

		public Builder rating( int rating ) {
			tmp.setRating( rating );
			return this;
		}

		public Builder time( Instant time ) {
			tmp.setTime( time );
			return this;
		}

		public RatingDTO build() {
			RatingDTO ratingDTO = new RatingDTO();
			ratingDTO.setId( tmp.getId() );
			ratingDTO.setRatedUser( tmp.getRatedUser() );
			ratingDTO.setRoleRatedUser( tmp.getRoleRatedUser() );
			ratingDTO.setRaterUserId( tmp.getRaterUserId() );
			ratingDTO.setRating( tmp.getRating() );
			ratingDTO.setTime( tmp.getTime() );

			return ratingDTO;
		}
	}

}
