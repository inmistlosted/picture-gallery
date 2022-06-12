package com.podvazhuk.gallery.dao;

import com.podvazhuk.gallery.domain.Artist;

import java.util.List;

public interface ArtistDao {
    void save(Artist artist);

    void update(Artist artist);

    List<Artist> findAll();

    Artist findByName(String artistName);

    void deleteByName(String artistName);

    Artist findOne(String artistId);
}
