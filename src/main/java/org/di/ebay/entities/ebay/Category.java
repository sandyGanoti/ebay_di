package org.di.ebay.entities.ebay;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "category")
public class Category {

	@Size(max = 60)
	@NotNull
	private String name;
	private Set<ItemCategory> itemCategories;

	@Id
	public String getName() {
		return name;
	}

	public void setName( final String name ) {
		this.name = name;
	}

	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	public Set<ItemCategory> getItemCategories() {
		return itemCategories;
	}

	public void setItemCategories( final Set<ItemCategory> itemCategories ) {
		this.itemCategories = itemCategories;
	}
}