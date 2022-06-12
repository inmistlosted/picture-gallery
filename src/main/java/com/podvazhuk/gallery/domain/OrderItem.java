package com.podvazhuk.gallery.domain;

import java.io.Serializable;

public class OrderItem implements Serializable {
	
	private String id;
	private int number;
	private float price;
	private Painting painting;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public Painting getPainting() {
		return painting;
	}
	public void setPainting(Painting painting) {
		this.painting = painting;
	}
}
