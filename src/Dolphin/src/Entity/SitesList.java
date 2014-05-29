package Dolphin.src.Entity;

import java.util.ArrayList;

/** Contains getter and setter method for varialbles  */
public class SitesList {

	/** Variables */
	private ArrayList<String> name = new ArrayList<String>();
	private ArrayList<String> category = new ArrayList<String>();
	private ArrayList<String> brand = new ArrayList<String>();

	
	/** In Setter method default it will return arraylist 
	 *  change that to add  */
	
	public ArrayList<String> getName() {
		return name;
	}

	public void setName(String name) {
		this.name.add(name);
	}

	public ArrayList<String> getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category.add(category);
	}
	
	public ArrayList<String> getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand.add(brand);
	}

}
