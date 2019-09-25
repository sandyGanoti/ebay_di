package org.di.ebay.ebay.transferables;

public class CategoryDTO {

	private String name;

	public static Builder builder() {
		return new Builder();
	}

	public String getName() {
		return name;
	}

	public void setName( final String name ) {
		this.name = name;
	}

	public static class Builder {
		private CategoryDTO tmp = new CategoryDTO();

		public Builder name( final String name ) {
			tmp.setName( name );
			return this;
		}

		public CategoryDTO build() {
			CategoryDTO categoryDTO = new CategoryDTO();
			categoryDTO.setName( tmp.getName() );

			return categoryDTO;
		}
	}

}
