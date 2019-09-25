package org.di.ebay.entities.ebay;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bid")
public class Bid implements Serializable {

	private long id;
	private BigDecimal amount;
	private Instant time;
	private AuctionItem auctionItem;
	private Long userId;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}

	public void setId( final long id ) {
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

	@ManyToOne()
	@JoinColumn(unique = true, name = "auction_item_id")
	public AuctionItem getAuctionItem() {
		return auctionItem;
	}

	public void setAuctionItem( final AuctionItem auctionItem ) {
		this.auctionItem = auctionItem;
	}

	@Column(name = "user_id")
	public Long getUserId() {
		return userId;
	}

	public void setUserId( final Long userId ) {
		this.userId = userId;
	}
}
