package application;

import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;

public class Sandwich {
	
	int mSandType;
	String mBreadType;
	double sandPrice;
	String sandName;
	String breadName;
	ArrayList<String> ingredients = new ArrayList<String>();
	SimpleStringProperty nameAndIngredients;
	SimpleStringProperty strPrice;
	
	public Sandwich(int sandType, String breadType, boolean containsLettuce, 
					boolean containsTomato, boolean containsBacon, boolean containsMustard,
					boolean containsMayo, boolean containsPickles) {
		mSandType = sandType;
		mBreadType = breadType;
		nameAndIngredients = new SimpleStringProperty();
		strPrice = new SimpleStringProperty();
		
		switch(mSandType) {
			case 0:
				sandName = "Turkey Club";
				sandPrice = 8;
			  break;
			case 1:
				sandName = "Ham Club";
				sandPrice = 9;
			  break;
			case 2:
				sandName = "Chicken Club";
				sandPrice = 7;
			  break;
			case 3:
				sandName = "Cheddar Grilled Cheese";
				sandPrice = 4;
			  break;
			case 4:
				sandName = "Swiss Grilled Cheese";
				sandPrice = 5;
			  break;
			case 5:
				sandName = "Mozzarella Grilled Cheese";
				sandPrice = 6;
			  break;
			case 6:
				sandName = "Turkey Panini";
				sandPrice = 8;
			  break;
			case 7:
				sandName = "Ham Panini";
				sandPrice = 9;
			  break;
			case 8:
				sandName = "Chicken Panini";
				sandPrice = 7;
			  break;
			case 9:
				sandName = "Scrambled Egg Sandwich";
				sandPrice = 4;
			  break;
			case 10:
				sandName = "Over-easy Egg Sandwich";
				sandPrice = 5;
			  break;
			case 11:
				sandName = "Over-hard Egg Sandwich";
				sandPrice = 6;
			  break;
		}
		
		if (containsLettuce == true) {
			ingredients.add("Lettuce");
		}
		if (containsTomato == true) {
			ingredients.add("Tomato");
		}
		if (containsBacon == true) {
			ingredients.add("Bacon");
		}
		if (containsMustard == true) {
			ingredients.add("Mustard");
		}
		if (containsMayo == true) {
			ingredients.add("Mayo");
		}
		if (containsPickles == true) {
			ingredients.add("Pickles");
		}
		
		 nameAndIngredients.set(sandName + " on " + mBreadType + " with " + ingredients);
		 strPrice.set(String.valueOf(sandPrice) + "0");
	}
	
	 public String getNameAndIngredients() {
	        return nameAndIngredients.get();
	 }
	    public void setNameAndIngredients(String name) {
	        nameAndIngredients.set(name);
	 }
	    
	 public SimpleStringProperty  nameAndIngredientsProperty() {
		 return nameAndIngredients;
	 }
	 
	 
	 public String getStrPrice() {
	        return strPrice.get();
	 }
	    public void setStrPrice(String name) {
	    	strPrice.set(name);
	 }
	    
	 public SimpleStringProperty  strPriceProperty() {
		 return strPrice;
	 }
	
}
