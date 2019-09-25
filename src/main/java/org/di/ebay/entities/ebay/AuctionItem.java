package org.di.ebay.entities.ebay;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "auction_item")
public class AuctionItem {

	private Long id;
	private String name;
	private Set<ItemCategory> itemCategories;
	private String description;
	private String location;
	private String country;

	/* auction_item related info */
	private BigDecimal firstBid;
	private Instant startedAt;
	private Instant endsAt;
	private BigDecimal currently;
	private Long userId;
	private Boolean active;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName( final String name ) {
		this.name = name;
	}

	@OneToMany(mappedBy = "auctionItem", cascade = CascadeType.ALL)
	public Set<ItemCategory> getItemCategories() {
		return itemCategories;
	}

	public void setItemCategories( Set<ItemCategory> itemCategories ) {
		this.itemCategories = itemCategories;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription( final String description ) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation( final String location ) {
		this.location = location;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry( final String country ) {
		this.country = country;
	}

	@Column(name = "first_bid")
	public BigDecimal getFirstBid() {
		return firstBid;
	}

	public void setFirstBid( final BigDecimal firstBid ) {
		this.firstBid = firstBid;
	}

	@Column(name = "started_at")
	public Instant getStartedAt() {
		return startedAt;
	}

	public void setStartedAt( final Instant startedAt ) {
		this.startedAt = startedAt;
	}

	@Column(name = "ends_at")
	public Instant getEndsAt() {
		return endsAt;
	}

	public void setEndsAt( final Instant endsAt ) {
		this.endsAt = endsAt;
	}

	public BigDecimal getCurrently() {
		return currently;
	}

	public void setCurrently( final BigDecimal currently ) {
		this.currently = currently;
	}

	@Column(name = "user_id")
	public Long getUserId() {
		return userId;
	}

	public void setUserId( final Long userId ) {
		this.userId = userId;
	}

	@Column(columnDefinition = "TINYINT")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	public Boolean getActive() {
		return active;
	}

	public void setActive( final Boolean active ) {
		this.active = active;
	}

	@Transient
	public Set<Category> getCategories() {
		return this.itemCategories.stream()
				.map( relationship -> relationship.getCategory() )
				.collect( Collectors.toSet() );
	}
}
