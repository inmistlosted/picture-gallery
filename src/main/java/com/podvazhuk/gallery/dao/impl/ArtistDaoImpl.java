package com.podvazhuk.gallery.dao.impl;

import com.podvazhuk.gallery.utils.C3P0Util;
import com.podvazhuk.gallery.dao.ArtistDao;
import com.podvazhuk.gallery.domain.Artist;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class ArtistDaoImpl implements ArtistDao {
    private QueryRunner qr=new QueryRunner(C3P0Util.getDataSource());

    @Override
    public void save(Artist artist) {
        try {
            qr.update("insert into artists(id ,name,country) values(?,?,?)",
                    artist.getId(),artist.getName(),artist.getCountry());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(Artist artist) {
        try {
            qr.update("update artists set name=?, country=? where id=?",
                    artist.getName(),artist.getCountry(),artist.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Artist> findAll() {
        try {
            return	qr.query("select * from artists", new BeanListHandler<Artist>(Artist.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public Artist findByName(String artistName) {

        try {
            return 	qr.query("select * from artists  where name=? ", new BeanHandler<Artist>(Artist.class),artistName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public  void deleteByName(String artistName) {

        try {
            qr.update("delete from artists where name=? ", artistName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Artist findOne(String artistId) {

        try {
            return 	qr.query("select * from artists  where id=? ", new BeanHandler<Artist>(Artist.class),artistId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
