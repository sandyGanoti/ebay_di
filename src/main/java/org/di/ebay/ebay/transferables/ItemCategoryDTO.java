package org.di.ebay.ebay.transferables;


public class ItemCategoryDTO {
	public CategoryDTO categoryDTO;
	public AuctionItemDTO auctionItemDTO;

	public ItemCategoryDTO(CategoryDTO categoryDTO, AuctionItemDTO auctionItemIdDTO) {
		this.categoryDTO = categoryDTO;
		this.auctionItemDTO = auctionItemIdDTO;
	}

	public CategoryDTO getCategoryDTO() {
		return categoryDTO;
	}

	public void setCategoryDTO( final CategoryDTO categoryDTO ) {
		this.categoryDTO= categoryDTO;
	}

	public AuctionItemDTO getAuctionItemDTO() {
		return auctionItemDTO;
	}

	public void setAuctionItemDTO( final AuctionItemDTO auctionItemDTO ) {
		this.auctionItemDTO = auctionItemDTO;
	}
}
