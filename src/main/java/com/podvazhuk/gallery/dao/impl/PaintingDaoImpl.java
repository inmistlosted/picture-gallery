package com.podvazhuk.gallery.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.podvazhuk.gallery.domain.Artist;
import com.podvazhuk.gallery.domain.Category;
import com.podvazhuk.gallery.domain.Painting;
import com.podvazhuk.gallery.domain.Way;
import com.podvazhuk.gallery.utils.C3P0Util;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.podvazhuk.gallery.dao.PaintingDao;

public class PaintingDaoImpl implements PaintingDao {

	private QueryRunner qr=new QueryRunner(C3P0Util.getDataSource());
	@Override
	public void addPainting(Painting painting) {
		try {
			qr.update("insert into paintings(id,id_artist,id_category,name,price,path,filename,des,id_way) values (?,?,?,?,?,?,?,?,?)",
					painting.getId(), painting.getArtist()==null?null: painting.getArtist().getId(), painting.getCategory()==null?null: painting.getCategory().getId(),
                    painting.getName(), painting.getPrice(), painting.getPath(), painting.getFilename(), painting.getDes(),
                    painting.getWay()==null?null: painting.getWay().getId());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public void updatePainting(Painting painting) {
		try {
			qr.update("update paintings set name=?, price=?, id_artist=?, id_way=?, des=?, id_category=? where id=?",
					painting.getName(),painting.getPrice(),painting.getArtist()==null?null: painting.getArtist().getId(),
					painting.getWay()==null?null: painting.getWay().getId(), painting.getDes(),
					painting.getCategory()==null?null: painting.getCategory().getId(), painting.getId());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

    @Override
    public void deletePainting(String id) {
        try {
            qr.update("delete from paintings where id=? ", id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
	@Override
	public int getTotalRecordsNum() {
		
		try {
			Long num=(Long) qr.query("select count(id) from paintings ",new ScalarHandler(1));
			return num.intValue();
		
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public List<Painting> findPagePaintings(int startIndex, int size) {
	
		try {
			List<Painting> paintings =qr.query("select * from paintings limit ?,? ",new BeanListHandler<Painting>(Painting.class),startIndex,size);
			if(paintings !=null){
				for (Painting painting : paintings) {
					String sql = "select * from categorys where id=(select id_category from paintings where id=?)";
					Category category = qr.query(sql, new BeanHandler<Category>(Category.class), painting.getId());
					painting.setCategory(category);
                    sql = "select * from artists where id=(select id_artist from paintings where id=?)";
                    Artist artist = qr.query(sql, new BeanHandler<Artist>(Artist.class), painting.getId());
                    painting.setArtist(artist);
                    sql = "select * from way where id=(select id_way from paintings where id=?)";
                    Way way = qr.query(sql, new BeanHandler<Way>(Way.class), painting.getId());
                    painting.setWay(way);
				}
			}
			return paintings;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	@Override
	public int getTotalRecordsNum(String categoryId) {
		try {
			Long num=(Long) qr.query("select count(id) from paintings where id_category=? ",new ScalarHandler(1),categoryId);
			return num.intValue();
		
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public int getTotalRecordsNumOfName(String name) {
		try {
			List<Painting> allPaintings =qr.query("select * from paintings",new BeanListHandler<Painting>(Painting.class));
			List<Painting> paintings = new ArrayList<Painting>();
			for (Painting painting : allPaintings){
				if (isSubString(painting.getName(), name)){
					paintings.add(painting);
				}
			}
			return paintings.size();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public boolean isSubString(String string, String substring){
		String[] s1 = string.split("");
		String[] s2 = substring.split("");
		for (int i = 0; i < s1.length-s2.length+1; i++) {
			for (int j = 0; j < s2.length; j++) {
				if (!s1[i + j].toLowerCase().equals(s2[j].toLowerCase())){
					break;
				}

				if (s1[i + j].toLowerCase().equals(s2[j].toLowerCase()) && j == s2.length-1){
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public int getTotalRecordsNumOfArtist(String artistId) {
		try {
			Long num=(Long) qr.query("select count(id) from paintings where id_artist=? ",new ScalarHandler(1),artistId);
			return num.intValue();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

    @Override
    public int getTotalRecordsNumOfWay(String wayId) {
        try {
            Long num=(Long) qr.query("select count(id) from paintings where id_way=? ",new ScalarHandler(1),wayId);
            return num.intValue();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


	@Override
	public List<Painting> findPagePaintings(int startIndex, int pageSize, String categoryId) {
		
		try {
			List<Painting> paintings =qr.query("select * from paintings  where id_category=?  limit  ?,? ",new BeanListHandler<Painting>(Painting.class),categoryId,startIndex,pageSize);
			if(paintings !=null){
				for (Painting painting : paintings) {
					String sql = "select * from categorys where id=?";
					Category category = qr.query(sql, new BeanHandler<Category>(Category.class),categoryId);
					painting.setCategory(category);
				}
			}
			return paintings;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Painting> findPagePaintingsOfArtist(int startIndex, int pageSize, String artistId) {

		try {
			List<Painting> paintings =qr.query("select * from paintings  where id_artist=?  limit  ?,? ",new BeanListHandler<Painting>(Painting.class),artistId,startIndex,pageSize);
			if(paintings !=null){
				for (Painting painting : paintings) {
					String sql = "select * from artists where id=?";
					Artist artist = qr.query(sql, new BeanHandler<Artist>(Artist.class),artistId);
					painting.setArtist(artist);
				}
			}
			return paintings;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

    @Override
    public List<Painting> findPagePaintingsOfWay(int startIndex, int pageSize, String wayId) {

        try {
            List<Painting> paintings =qr.query("select * from paintings  where id_way=?  limit  ?,? ",new BeanListHandler<Painting>(Painting.class),wayId,startIndex,pageSize);
            if(paintings !=null){
                for (Painting painting : paintings) {
                    String sql = "select * from way where id=?";
                    Way way = qr.query(sql, new BeanHandler<Way>(Way.class),wayId);
                    painting.setWay(way);
                }
            }
            return paintings;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

	@Override
	public List<Painting> findPagePaintingsSorted(int startIndex, int pageSize, final String sortType) {
		class PriceComparator implements Comparator<Painting>{

			@Override
			public int compare(Painting b1, Painting b2) {
				if (sortType.equals("1")){
					return (int)(b1.getPrice() - b2.getPrice());
				} else {
					return (int)(b2.getPrice() - b1.getPrice());
				}
			}
		}

		try {
			List<Painting> allPaintings =qr.query("select * from paintings",new BeanListHandler<Painting>(Painting.class));
			if (startIndex == 3){
				int t = 0;
				t++;
			}
			Collections.sort(allPaintings, new PriceComparator());
			List<Painting> paintings = new ArrayList<Painting>();
			for (int i = startIndex; i < startIndex + pageSize; i++) {
				if (i == allPaintings.size()) break;
				paintings.add(allPaintings.get(i));
			}
			return paintings;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Painting> findPagePaintingsOfName(int startIndex, int pageSize, String name) {

		try {
			List<Painting> allPaintings =qr.query("select * from paintings",new BeanListHandler<Painting>(Painting.class));
			List<Painting> paintsOfName = new ArrayList<Painting>();
			for (Painting painting : allPaintings){
				if (isSubString(painting.getName(), name)){
					paintsOfName.add(painting);
				}
			}

			List<Painting> paintings = new ArrayList<Painting>();
			for (int i = startIndex; i < startIndex + pageSize; i++) {
				if (i == paintsOfName.size()) break;
				paintings.add(paintsOfName.get(i));
			}
			return paintings;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	@Override
	public Painting findOne(String paintingId) {
		
		try {
			Painting painting = qr.query("select * from paintings where id=?", new BeanHandler<Painting>(Painting.class), paintingId);
			if(painting !=null){
				String sql = "select * from categorys where id=(select id_category from paintings where id=?)";
				Category category = qr.query(sql, new BeanHandler<Category>(Category.class), painting.getId());
				painting.setCategory(category);
				sql = "select * from artists where id=(select id_artist from paintings where id=?)";
				Artist artist = qr.query(sql, new BeanHandler<Artist>(Artist.class), painting.getId());
				painting.setArtist(artist);
				sql = "select * from way where id=(select id_way from paintings where id=?)";
				Way way = qr.query(sql, new BeanHandler<Way>(Way.class), painting.getId());
				painting.setWay(way);
			}
			return painting;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
