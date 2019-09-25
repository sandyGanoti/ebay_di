package org.di.ebay.entities.ebay;

import java.time.Instant;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity()
@Table(name = "rating"
		//		,
		//		uniqueConstraints = @UniqueConstraint(columnNames = { "user",
		//															  "role_rated_user",
		//															  "rater_user_id" })
)
public class Rating {

	private Long id;
	private User user;
	private Role roleRatedUser;
	private Long raterUserId;
	private int rating;
	private Instant time;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId( final Long id ) {
		this.id = id;
	}

	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(unique = true)
	public User getUser() {
		return user;
	}

	public void setUser( final User user ) {
		this.user = user;
	}

	@Column(name = "role_rated_user")
	@NotNull
	public Role getRoleRatedUser() {
		return roleRatedUser;
	}

	public void setRoleRatedUser( final Role roleRatedUser ) {
		this.roleRatedUser = roleRatedUser;
	}

	@Column(name = "rater_user_id")
	@NotNull
	public Long getRaterUserId() {
		return raterUserId;
	}

	public void setRaterUserId( final Long raterUserId ) {
		this.raterUserId = raterUserId;
	}

	@NotNull
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

	@Override
	public boolean equals( final Object o ) {
		if ( this == o ) {
			return true;
		}
		if ( o == null || getClass() != o.getClass() ) {
			return false;
		}
		final Rating rating1 = (Rating) o;
		return rating == rating1.rating && Objects.equals( user, rating1.user ) && Objects.equals(
				roleRatedUser,
				rating1.roleRatedUser ) && raterUserId == rating1.raterUserId && Objects.equals(
				time, rating1.time );
	}

	@Override
	public int hashCode() {
		return Objects.hash( user, raterUserId, roleRatedUser, rating, time );
	}
}
