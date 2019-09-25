package org.di.ebay.ebay.transferables;

import java.math.BigDecimal;
import java.time.Instant;

public class BidDTO {

	private Long id;
	private BigDecimal amount;
	private Instant time;
	private Long auctionItemId;
	private Long userId;
	private String bidderUsername;

	public static Builder builder() {
		return new Builder();
	}

	public Long getId() {
		return id;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount( final BigDecimal amount ) {
		this.amount = amount;
	}

	public Instant getTime() {
		return time;
	}

	public void setTime( final Instant time ) {
		this.time = time;
	}

	public Long getAuctionItemId() {
		return auctionItemId;
	}

	public void setAuctionItemId( final Long auctionItemId ) {
		this.auctionItemId = auctionItemId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId( final Long userId ) {
		this.userId = userId;
	}

	public String getBidderUsername() {
		return bidderUsername;
	}

	public void setBidderUsername( final String bidderUsername ) {
		this.bidderUsername = bidderUsername;
	}

	public static class Builder {
		private BidDTO tmp = new BidDTO();

		public Builder id( Long id ) {
			tmp.setId( id );
			return this;
		}

		public Builder amount( BigDecimal amount ) {
			tmp.setAmount( amount );
			return this;
		}

		public Builder time( Instant time ) {
			tmp.setTime( time );
			return this;
		}

		public Builder auctionItemId( Long auctionItemId ) {
			tmp.setAuctionItemId( auctionItemId );
			return this;
		}

		public Builder userId( Long userId ) {
			tmp.setUserId( userId );
			return this;
		}

		public Builder bidderUsername( String bidderUsername ) {
			tmp.setBidderUsername( bidderUsername );
			return this;
		}

		public BidDTO build() {
			BidDTO bidDTO = new BidDTO();
			bidDTO.setId( tmp.getId() );
			bidDTO.setAmount( tmp.getAmount() );
			bidDTO.setTime( tmp.getTime() );
			bidDTO.setAuctionItemId( tmp.getAuctionItemId() );
			bidDTO.setUserId( tmp.getUserId() );
			bidDTO.setBidderUsername( tmp.getBidderUsername() );
			return bidDTO;
		}
	}

}
