package com.podvazhuk.gallery.bean;

import java.io.Serializable;

import com.podvazhuk.gallery.domain.Painting;

public class CartItem implements Serializable{
	
	private Painting painting;
	private float price;
	private int number;
	
	public CartItem(Painting painting){
		this.painting = painting;
	}
	
	public Painting getPainting() {
		return painting;
	}
	public void setPainting(Painting painting) {
		this.painting = painting;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
	public float getPrice() {
		return painting.getPrice()*number;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	

}
