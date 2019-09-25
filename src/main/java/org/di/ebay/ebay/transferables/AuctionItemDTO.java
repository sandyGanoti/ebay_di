package org.di.ebay.ebay.transferables;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collections;
import java.util.Set;

public class AuctionItemDTO {

	private Long id;
	private String name;
	private Set<CategoryDTO> categories;
	private String description;
	private String location;
	private String country;
	private BigDecimal firstBid;
	private Instant startedAt;
	private Instant endsAt;
	private Set<BidDTO> bids;
	private BigDecimal currently;
	private Long userId;
	private Boolean active;

	public static Builder builder() {
		return new Builder();
	}

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

	public Set<CategoryDTO> getCategories() {
		return categories;
	}

	public void setCategories( final Set<CategoryDTO> categories ) {
		this.categories = categories;
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

	public BigDecimal getFirstBid() {
		return firstBid;
	}

	public void setFirstBid( final BigDecimal firstBid ) {
		this.firstBid = firstBid;
	}

	public Instant getStartedAt() {
		return startedAt;
	}

	public void setStartedAt( final Instant startedAt ) {
		this.startedAt = startedAt;
	}

	public Instant getEndsAt() {
		return endsAt;
	}

	public void setEndsAt( final Instant endsAt ) {
		this.endsAt = endsAt;
	}

	public Set<BidDTO> getBids() {
		return bids;
	}

	public void setBids( final Set<BidDTO> bids ) {
		this.bids = bids;
	}

	public BigDecimal getCurrently() {
		return currently;
	}

	public void setCurrently( final BigDecimal currently ) {
		this.currently = currently;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId( final Long userId ) {
		this.userId = userId;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive( final Boolean active ) {
		this.active = active;
	}

	public boolean canBeEntered() {
		/* startedAt should be at the future */
		Instant now = Instant.now();
		return this.active && this.endsAt.compareTo( now ) < 0;
	}

	public boolean timesCorrect() {
		return this.startedAt != null && this.endsAt != null && this.startedAt.compareTo(
				this.endsAt ) < 0;
	}

	public boolean canBeDeleted() {
		return !this.active && bids.isEmpty();
	}

	public boolean ownedByUser( final long userId ) {
		return this.userId.equals( userId );
	}

	public static class Builder {
		private AuctionItemDTO tmp = new AuctionItemDTO();

		public Builder id( final Long id ) {
			tmp.setId( id );
			return this;
		}

		public Builder name( final String name ) {
			tmp.setName( name );
			return this;
		}

		public Builder categories( final Set categories ) {
			tmp.setCategories( categories );
			return this;
		}

		public Builder description( final String description ) {
			tmp.setDescription( description );
			return this;
		}

		public Builder location( final String location ) {
			tmp.setLocation( location );
			return this;
		}

		public Builder country( final String country ) {
			tmp.setCountry( country );
			return this;
		}

		public Builder firstBid( BigDecimal firstBid ) {
			tmp.setFirstBid( firstBid );
			return this;
		}

		public Builder startedAt( Instant startedAt ) {
			tmp.setStartedAt( startedAt );
			return this;
		}

		public Builder endsAt( Instant endsAt ) {
			tmp.setEndsAt( endsAt );
			return this;
		}

		public Builder bids( Set bids ) {
			tmp.setBids( bids );
			return this;
		}

		public Builder currently( BigDecimal currently ) {
			tmp.setCurrently( currently );
			return this;
		}

		public Builder userId( Long userId ) {
			tmp.setUserId( userId );
			return this;
		}

		public Builder active( Boolean active ) {
			tmp.setActive( active );
			return this;
		}

		public AuctionItemDTO build() {
			AuctionItemDTO auctionItemDTO = new AuctionItemDTO();
			auctionItemDTO.setId( tmp.getId() );
			auctionItemDTO.setName( tmp.getName() );
			auctionItemDTO.setCategories(
					tmp.getCategories() != null ? tmp.getCategories() : Collections.emptySet() );
			auctionItemDTO.setDescription( tmp.getDescription() );
			auctionItemDTO.setLocation( tmp.getLocation() );
			auctionItemDTO.setCountry( tmp.getCountry() );
			auctionItemDTO.setFirstBid( tmp.getFirstBid() );
			auctionItemDTO.setStartedAt( tmp.getStartedAt() );
			auctionItemDTO.setEndsAt( tmp.getEndsAt() );
			auctionItemDTO.setCurrently( tmp.getCurrently() );
			auctionItemDTO.setBids(
					tmp.getBids() != null ? tmp.getBids() : Collections.emptySet() );
			auctionItemDTO.setUserId( tmp.getUserId() );
			auctionItemDTO.setActive( tmp.getActive() );
			return auctionItemDTO;
		}
	}

}
