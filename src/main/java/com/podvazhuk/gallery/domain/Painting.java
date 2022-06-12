package com.podvazhuk.gallery.domain;

import java.io.Serializable;

public class Painting implements Serializable {

	private String id;
	private String name;
	private float price;
	private String path; 
	private String filename; 
	private String des;
	
	private  Category category;
	private Artist artist;
	private Way way;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public Way getWay() {
		return way;
	}

	public void setWay(Way way) {
		this.way = way;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Painting(Category category) {
		this.category = category;
	}

	public Painting() {
		super();
	}

	public static boolean isSubString(String string, String substring){
		String[] s1 = string.split("");
		String[] s2 = substring.split("");
		boolean isSubstring = false;
		for (int i = 0; i < s1.length-s2.length+1; i++) {
			for (int j = 0; j < s2.length; j++) {
				if (!s1[i + j].toLowerCase().equals(s2[j].toLowerCase())){
					break;
				}

				if (s1[i + j].toLowerCase().equals(s2[j].toLowerCase()) && j == s2.length - 1) {
					isSubstring = true;
					break;
				}
			}
		}
		return isSubstring;
	}

}
