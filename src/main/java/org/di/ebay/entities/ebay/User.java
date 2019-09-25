package org.di.ebay.entities.ebay;

import java.time.Instant;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "user",
		uniqueConstraints = { @UniqueConstraint(name = "email", columnNames = { "email" }),
							  @UniqueConstraint(name = "username", columnNames = { "username" }) })
public class User {

	private Long id;
	private String password;
	private String firstName;
	private String lastName;
	private String username;
	private String phoneNumber;
	private String country;
	private Instant createdAt;
	private String email;

	public User() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId( final Long id ) {
		this.id = id;
	}

	@Column(nullable = true, length = 60)
	public String getPassword() {
		return password;
	}

	public void setPassword( final String password ) {
		this.password = password;
	}

	@Column(name = "first_name")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName( final String firstName ) {
		this.firstName = firstName;
	}

	@Column(name = "last_name")
	public String getLastName() {
		return lastName;
	}

	public void setLastName( final String lastName ) {
		this.lastName = lastName;
	}

	@Column(name = "username")
	public String getUsername() {
		return username;
	}

	public void setUsername( final String username ) {
		this.username = username;
	}

	@Column(name = "phone_number")
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber( final String phoneNumber ) {
		this.phoneNumber = phoneNumber;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry( final String country ) {
		this.country = country;
	}

	@Column(name = "created_at")
	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt( final Instant createdAt ) {
		this.createdAt = createdAt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail( final String email ) {
		this.email = email;
	}

	@Override
	public boolean equals( final Object o ) {
		if ( this == o ) {
			return true;
		}
		if ( o == null || getClass() != o.getClass() ) {
			return false;
		}
		final User user = (User) o;
		return Objects.equals( id, user.id ) && Objects.equals( firstName,
				user.firstName ) && Objects.equals( lastName, user.lastName ) && Objects.equals(
				username, user.username ) && Objects.equals( phoneNumber,
				user.phoneNumber ) && Objects.equals( country, user.country ) && Objects.equals(
				createdAt, user.createdAt ) && Objects.equals( email, user.email );
	}

	@Override
	public int hashCode() {
		return Objects.hash( id, firstName, lastName, username, phoneNumber, country, createdAt,
				email );
	}
}
