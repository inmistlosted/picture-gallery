package com.podvazhuk.gallery.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.podvazhuk.gallery.domain.Painting;

public class Cart implements Serializable{
	
	private Map<String, CartItem> items = new HashMap<String, CartItem>();
	private float price;
	private int number;
	public Map<String, CartItem> getItems() {
		return items;
	}
	public void addPainting2Items(Painting painting){
		if(items.containsKey(painting.getId())){
			CartItem item = items.get(painting.getId());
			item.setNumber(item.getNumber()+1);
		}else{
			CartItem item = new CartItem(painting);
			item.setNumber(1);
			items.put(painting.getId(), item);
		}
	}
	
	public float getPrice() {
		price = 0;
		for(Map.Entry<String, CartItem> me:items.entrySet()){
			price+=me.getValue().getPrice();
		}
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getNumber() {
		number = 0;
		for(Map.Entry<String, CartItem> me:items.entrySet()){
			number+=me.getValue().getNumber();
		}
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	
}
