package org.di.ebay.entities.ebay;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "item_category")
public class ItemCategory implements Serializable {

	private AuctionItem auctionItem;
	private Category category;

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "auction_item")
	public AuctionItem getAuctionItem() {
		return auctionItem;
	}

	public void setAuctionItem( AuctionItem auctionItem ) {
		this.auctionItem = auctionItem;
	}

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	public Category getCategory() {
		return category;
	}

	public void setCategory( Category category ) {
		this.category = category;
	}

	@Override
	public boolean equals( final Object o ) {
		if ( this == o ) {
			return true;
		}
		if ( o == null || getClass() != o.getClass() ) {
			return false;
		}
		final ItemCategory that = (ItemCategory) o;
		return Objects.equals( auctionItem, that.auctionItem ) && Objects.equals( category,
				that.category );
	}

	@Override
	public int hashCode() {
		return Objects.hash( auctionItem, category );
	}
}