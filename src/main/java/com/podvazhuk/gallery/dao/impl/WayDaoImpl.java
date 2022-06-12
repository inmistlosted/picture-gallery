package com.podvazhuk.gallery.dao.impl;

import com.podvazhuk.gallery.utils.C3P0Util;
import com.podvazhuk.gallery.dao.WayDao;
import com.podvazhuk.gallery.domain.Way;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class WayDaoImpl implements WayDao {
    private QueryRunner qr=new QueryRunner(C3P0Util.getDataSource());


    @Override
    public void save(Way way) {
        try {
            qr.update("insert into way(id,name) values(?,?)",
                    way.getId(),way.getName());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Way way) {
        try {
            qr.update("update way set name=? where id=?",
                    way.getName(),way.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Way> findAll() {
        try {
            return	qr.query("select * from way", new BeanListHandler<Way>(Way.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Way findByName(String wayName) {
        try {
            return 	qr.query("select * from way  where name=? ", new BeanHandler<Way>(Way.class),wayName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteByName(String wayName) {
        try {
            qr.update("delete from way where name=? ", wayName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Way findOne(String wayId) {
        try {
            return 	qr.query("select * from way where id=? ", new BeanHandler<Way>(Way.class),wayId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
