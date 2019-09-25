package org.di.ebay.ebay.bid;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityNotFoundException;

import org.di.ebay.ebay.BidTransformer;
import org.di.ebay.ebay.transferables.BidDTO;
import org.di.ebay.entities.ebay.Bid;
import org.di.ebay.exceptions.http.InvalidDtoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class BidServiceImpl implements BidService {

	@Autowired
	private BidDAO bidDAO;
	@Autowired
	private BidTransformer bidTransformer;

	@Override
	public List<BidDTO> getAll() {
		return bidTransformer.transformToDtos( bidDAO.getAll() );
	}

	@Override
	public Long create( final BidDTO bidDTO ) {
		Optional<Bid> entity = bidTransformer.transformToEntity( bidDTO );
		if ( !entity.isPresent() ) {
			throw new InvalidDtoException( "Invalid bid fields." );
		}
		try {
			return bidDAO.save( entity.get() ).getId();
		} catch ( Exception e ) {
			return null;
		}
	}

	@Override
	public void update( final Long bidId, final BidDTO bidDto ) {
		Optional<Bid> entity = bidTransformer.transformToEntity( bidDto );
		if ( !entity.isPresent() ) {
			throw new InvalidDtoException( "Invalid bid." );
		}
		Optional<Bid> bid = bidDAO.get( bidId );
		if ( !bid.isPresent() ) {
			throw new EntityNotFoundException( "No bid found with the provided id" );
		}
		bidDAO.update( bidId, entity.get() );
	}

	@Override
	public BidDTO get( final Long bidId ) {
		Optional<Bid> bid = bidDAO.get( bidId );
		if ( !bid.isPresent() ) {
			throw new EntityNotFoundException( "No bid found with the provided id" );
		}
		return bidTransformer.transformToDto( bid.get() ).get();
	}

	@Override
	public List<BidDTO> getMany( final List<Long> bidIds ) {
		return bidTransformer.transformToDtos( bidDAO.getMany( bidIds ) );
	}

	@Override
	public void delete( final Long bidId, final Long userId ) {
		Optional<Bid> bid = bidDAO.get( bidId );
		if ( !bid.isPresent() ) {
			throw new EntityNotFoundException( "No bid found with the provided id" );
		}
		bidDAO.delete( bidId );
	}

	@Override
	public List<BidDTO> getBidsByAuctionItemId( final long auctionItemId ) {
		return bidTransformer.transformToDtos( bidDAO.getBidsByAuctionItemId( auctionItemId ) );
	}

	@Override
	public List<BidDTO> getBidsByUserId( final long auctionItemId ) {
		return bidTransformer.transformToDtos( bidDAO.getBidsByUserId( auctionItemId ) );
	}

	@Override
	public List<BidDTO> getBidsByAuctionItemIdAndUserId( final long auctionItemId,
			final long userId ) {
		return bidTransformer.transformToDtos(
				bidDAO.getBidsByAuctionItemIdAndUserId( auctionItemId, userId ) );
	}

	@Override
	public List<BidDTO> getBids( final Set<Long> bidIds ) {
		List<Bid> bids = bidDAO.getBids( bidIds );
		return bidTransformer.transformToDtos( bids );
	}

	@Override
	public Optional<BidDTO> getWinningBidForAuction( final long auctionItemId ) {
		Optional<Bid> bid = bidDAO.getWinningBidForAuction( auctionItemId );
		return bid.isPresent() ? bidTransformer.transformToDto( bid.get() ) : Optional.empty();
	}

	@Override
	public List<Long> getAuctionsWhereUserBidded( final long userId ) {
		return bidDAO.getAuctionsWhereUserBidded( userId );
	}
}
