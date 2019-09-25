package org.di.ebay.ebay.bid;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.di.ebay.CrudService;
import org.di.ebay.ebay.transferables.BidDTO;

public interface BidService extends CrudService<Long, BidDTO> {

	List<BidDTO> getBidsByAuctionItemId( final long auctionItemId );

	List<BidDTO> getBidsByUserId( final long userId );

	List<BidDTO> getBidsByAuctionItemIdAndUserId( final long auctionItemId, final long userId );

	Optional<BidDTO> getWinningBidForAuction( final long auctionItemId );

	List<BidDTO> getBids( final Set<Long> bidIds );

	List<Long> getAuctionsWhereUserBidded( final long userId );

}
