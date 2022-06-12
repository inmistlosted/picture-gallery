package com.podvazhuk.gallery.dao;

import java.util.List;

import com.podvazhuk.gallery.domain.Painting;

public interface PaintingDao {

   void addPainting(Painting painting);

   void deletePainting(String id);

	void updatePainting(Painting painting);
	
	int getTotalRecordsNum();
	
	List<Painting> findPagePaintings(int startIndex, int size);

	int getTotalRecordsNum(String categoryId);

	List<Painting> findPagePaintings(int startIndex, int pageSize, String categoryId);

	Painting findOne(String paintingId);

	int getTotalRecordsNumOfArtist(String artistId);

	int getTotalRecordsNumOfWay(String wayId);

	List<Painting> findPagePaintingsOfArtist(int startIndex, int pageSize, String artistId);

	List<Painting> findPagePaintingsOfWay(int startIndex, int pageSize, String wayId);

	List<Painting> findPagePaintingsOfName(int startIndex, int pageSize, String name);

	List<Painting> findPagePaintingsSorted(int startIndex, int pageSize, String sortType);

	int getTotalRecordsNumOfName(String name);
	

}
