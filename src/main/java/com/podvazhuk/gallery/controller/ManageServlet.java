package com.podvazhuk.gallery.controller;

import com.podvazhuk.gallery.commons.Page;
import com.podvazhuk.gallery.domain.Category;
import com.podvazhuk.gallery.domain.Painting;
import com.podvazhuk.gallery.domain.Way;
import com.podvazhuk.gallery.service.BusinessService;
import com.podvazhuk.gallery.service.impl.BusinessServiceImpl;
import com.podvazhuk.gallery.utils.WebUtil;
import com.podvazhuk.gallery.domain.Artist;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ManageServlet extends HttpServlet {

	private BusinessService s=new BusinessServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
		String op=request.getParameter("op");
		if("checkCategory".equals(op)){
			checkCategory(request,response);
		}else if("addCategory".equals(op)){
			addCategory(request,response);
		}else if("listCategories".equals(op)){
			listCategories(request,response);
		}else if("addPaintingUI".equals(op)){
			addPaintingUI(request,response);
		}else if("delCategory".equals(op)){
			delCategory(request,response);
		}else if("checkArtist".equals(op)){
            checkArtist(request,response);
        }else if("addArtist".equals(op)){
            addArtist(request,response);
        }else if("listArtists".equals(op)){
            listArtists(request,response);
        }else if("delArtist".equals(op)){
            delArtist(request,response);
        }else if("checkWay".equals(op)){
            checkWay(request,response);
        }else if("addWay".equals(op)){
            addWay(request,response);
        }else if("listWays".equals(op)){
            listWays(request,response);
        }else if("delWay".equals(op)){
            delWay(request,response);
        }else if("addPainting".equals(op)){
			try {
				addPainting(request,response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("listPaintings".equals(op)){
			listPaintings(request,response);
		}else if("editCategoryUI".equals(op)){
			editCategoryUI(request, response);
		}else if("editCategory".equals(op)){
			editCategory(request, response);
		}else if("editArtistUI".equals(op)){
			editArtistUI(request, response);
		}else if("editArtist".equals(op)){
			editArtist(request, response);
		}else if("editWayUI".equals(op)){
			editWayUI(request, response);
		}else if("editWay".equals(op)){
			editWay(request, response);
		}else if("delPainting".equals(op)){
			delPainting(request, response);
		}else if("editPaintingUI".equals(op)){
			editPaintingUI(request, response);
		}else if("editPainting".equals(op)){
			try {
				editPainting(request, response);
			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
	}

	private void listPaintings(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String num=request.getParameter("num");
		Page page=s.findPage(num);
		page.setUrl("/servlet/ManageServlet?op=listPaintings");
		request.setAttribute("page", page);
		request.getRequestDispatcher("/manage/listPaintings.jsp").forward(request, response);
	}

	private void addPainting(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		boolean isMultipart=ServletFileUpload.isMultipartContent(request);
		if(!isMultipart){
			throw new RuntimeException();
		}
		
		DiskFileItemFactory factory=new DiskFileItemFactory();
		ServletFileUpload sfu=new ServletFileUpload(factory);

		List<FileItem>  items=sfu.parseRequest(request);
		Painting painting =new Painting();
		for (FileItem item : items) {
			if(item.isFormField()){
				String fieldName=item.getFieldName();
				String fieldValue=item.getString("UTF-8");

				BeanUtils.setProperty(painting, fieldName, fieldValue);

				if("categoryId".equals(fieldName)){
					Category category = s.findCategoryById(fieldValue);
					painting.setCategory(category);
				}

				if ("wayId".equals(fieldName)){
					Way way = s.findWayById(fieldValue);
					painting.setWay(way);
				}

				if ("artistId".equals(fieldName)){
					Artist artist = s.findArtistById(fieldValue);
					painting.setArtist(artist);
				}
			} else {
				String filename = item.getName();

				painting.setFilename(filename);

				String rootDirectory = getServletContext().getRealPath("/res/shop_assets/images/book");
				String childPath = getStoreDirecotry(rootDirectory);
				painting.setPath(childPath);

				item.write(new File(rootDirectory+File.separator+childPath+File.separator+filename));
			}
		}
		
		s.addPainting(painting);
		request.setAttribute("message", "Успішно додано");
		request.getRequestDispatcher("/manage/message.jsp").forward(request, response);
	}

	private void editPaintingUI(HttpServletRequest request,
								HttpServletResponse response) throws ServletException, IOException {

		String id=request.getParameter("paintingId");
		Painting painting = s.findPaintingById(id);
		List<Category> cs=s.findAllCategories();
		List<Artist> as = s.findAllArtists();
		List<Way> ws = s.findAllWays();
		request.setAttribute("cs", cs);
		request.setAttribute("as", as);
		request.setAttribute("ws", ws);
		request.setAttribute("p", painting);
		request.getRequestDispatcher("/manage/editPainting.jsp").forward(request, response);
	}

	private void editPainting(HttpServletRequest request,
							  HttpServletResponse response) throws IOException, FileUploadException, InvocationTargetException, IllegalAccessException, ServletException {

		boolean isMultipart=ServletFileUpload.isMultipartContent(request);
		if(!isMultipart){
			throw new RuntimeException();
		}

		DiskFileItemFactory factory=new DiskFileItemFactory();
		ServletFileUpload sfu=new ServletFileUpload(factory);

		List<FileItem>  items =sfu.parseRequest(request);
		Painting painting =new Painting();
		for (FileItem item : items) {
			if(item.isFormField()){
				String fieldName=item.getFieldName();
				String fieldValue=item.getString("UTF-8");

				BeanUtils.setProperty(painting, fieldName, fieldValue);

				if("categoryId".equals(fieldName)){
					Category category = s.findCategoryById(fieldValue);
					painting.setCategory(category);
				}

				if ("wayId".equals(fieldName)){
					Way way = s.findWayById(fieldValue);
					painting.setWay(way);
				}

				if ("artistId".equals(fieldName)){
					Artist artist = s.findArtistById(fieldValue);
					painting.setArtist(artist);
				}
			}
		}

		s.updatePainting(painting);
		request.setAttribute("message", "Успішно змінено");
		request.getRequestDispatcher("/manage/message.jsp").forward(request, response);
	}

	private void delPainting(HttpServletRequest request,
						   HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");

		s.delPainting(id);

		request.setAttribute("message", "Успішно видалено");
		request.getRequestDispatcher("/manage/message.jsp?d="+new Date().getTime()).forward(request, response);
	}


	private String getStoreDirecotry(String rootDirectory) {
		Date now = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String path = df.format(now);
		File file = new File(rootDirectory,path);
		if(!file.exists()){
			file.mkdirs();
		}
		return path;
	}

	private void addPaintingUI(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Category> cs=s.findAllCategories();
		List<Artist> as = s.findAllArtists();
		List<Way> ws = s.findAllWays();
		request.setAttribute("cs", cs);
        request.setAttribute("as", as);
		request.setAttribute("ws", ws);
		request.getRequestDispatcher("/manage/addPainting.jsp").forward(request, response);
	}

	private void listCategories(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Category> cs=s.findAllCategories();
		request.setAttribute("cs", cs);
		request.getRequestDispatcher("/manage/listCategories.jsp").forward(request, response);
	}

	private void addCategory(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Category category= WebUtil.fillBean(request, Category.class);
		s.addCategory(category);
		
		request.setAttribute("message", "Успішно додано");
		request.getRequestDispatcher("/manage/message.jsp").forward(request, response);
	}


	private void editCategoryUI(HttpServletRequest request,
								HttpServletResponse response) throws ServletException, IOException {

		String id=request.getParameter("categoryId");
		Category category = s.findCategoryById(id);
		request.setAttribute("c", category);
		request.getRequestDispatcher("/manage/editCategory.jsp").forward(request, response);
	}

	private void editCategory(HttpServletRequest request,
							 HttpServletResponse response) throws ServletException, IOException {

		Category category=WebUtil.fillBean(request, Category.class);
		s.updateCategory(category);

		request.setAttribute("message", "Успішно модифіковано");
		request.getRequestDispatcher("/manage/message.jsp").forward(request, response);
	}



	private void checkCategory(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out=response.getWriter();
		
		String name=request.getParameter("name");
		boolean exists=s.isCategoryExists(name);
		if(exists){
			out.write("<font color='red'>Ця категорія уже існує.</font>");
		}else{
			out.write("<font color='green'>&#10003;</font>");
		}
	}

    private void delCategory(HttpServletRequest request,
                             HttpServletResponse response) throws ServletException, IOException {
        String name=request.getParameter("name");

        s.delCategory(name);

        request.setAttribute("message", "Успішно видалено");
        request.getRequestDispatcher("/manage/message.jsp?d="+new Date().getTime()).forward(request, response);
    }

    private void listArtists(HttpServletRequest request,
                                HttpServletResponse response) throws ServletException, IOException {
        List<Artist> as=s.findAllArtists();
        request.setAttribute("as", as);
        request.getRequestDispatcher("/manage/listArtists.jsp").forward(request, response);
    }

    private void addArtist(HttpServletRequest request,
                             HttpServletResponse response) throws ServletException, IOException {
        Artist artist=WebUtil.fillBean(request, Artist.class);
        s.addArtist(artist);

        request.setAttribute("message", "Успішно додано");
        request.getRequestDispatcher("/manage/message.jsp").forward(request, response);
    }

	private void editArtistUI(HttpServletRequest request,
								HttpServletResponse response) throws ServletException, IOException {

		String id=request.getParameter("artistId");
		Artist artist = s.findArtistById(id);
		request.setAttribute("a", artist);
		request.getRequestDispatcher("/manage/editArtist.jsp").forward(request, response);
	}

	private void editArtist(HttpServletRequest request,
							  HttpServletResponse response) throws ServletException, IOException {

		Artist artist=WebUtil.fillBean(request, Artist.class);
		s.updateArtist(artist);
		request.setAttribute("message", "Успішно модифіковано");
		request.getRequestDispatcher("/manage/message.jsp").forward(request, response);
	}


    private void checkArtist(HttpServletRequest request,
                               HttpServletResponse response) throws IOException {
        PrintWriter out=response.getWriter();

        String name=request.getParameter("name");
        boolean exists=s.isArtistExists(name);
        if(exists){
            out.write("<font color='red'>Цей художник уже існує.</font>");
        }else{
            out.write("<font color='green'>&#10003;</font>");
        }
    }

    private void delArtist(HttpServletRequest request,
                             HttpServletResponse response) throws ServletException, IOException {
        String name=request.getParameter("name");

        s.delArtist(name);

        request.setAttribute("message", "Успішно видалено");
        request.getRequestDispatcher("/manage/message.jsp?d="+new Date().getTime()).forward(request, response);
    }

    private void listWays(HttpServletRequest request,
                             HttpServletResponse response) throws ServletException, IOException {

        List<Way> ws=s.findAllWays();
        request.setAttribute("ws", ws);
        request.getRequestDispatcher("/manage/listWays.jsp").forward(request, response);
    }

    private void addWay(HttpServletRequest request,
                           HttpServletResponse response) throws ServletException, IOException {
        Way way=WebUtil.fillBean(request, Way.class);
        s.addWay(way);

        request.setAttribute("message", "Успішно додано");
        request.getRequestDispatcher("/manage/message.jsp").forward(request, response);
    }

	private void editWayUI(HttpServletRequest request,
							  HttpServletResponse response) throws ServletException, IOException {

		String id=request.getParameter("wayId");
		Way way = s.findWayById(id);
		request.setAttribute("w", way);
		request.getRequestDispatcher("/manage/editWay.jsp").forward(request, response);
	}

	private void editWay(HttpServletRequest request,
							HttpServletResponse response) throws ServletException, IOException {

		Way way=WebUtil.fillBean(request, Way.class);
		s.updateWay(way);
		request.setAttribute("message", "Успішно модифіковано");
		request.getRequestDispatcher("/manage/message.jsp").forward(request, response);
	}


    private void checkWay(HttpServletRequest request,
                             HttpServletResponse response) throws IOException {
        PrintWriter out=response.getWriter();

        String name=request.getParameter("name");
        boolean exists=s.isWayExists(name);
        if(exists){
            out.write("<font color='red'>Цей художник уже існує.</font>");
        }else{
            out.write("<font color='green'>&#10003;</font>");
        }
    }

    private void delWay(HttpServletRequest request,
                           HttpServletResponse response) throws ServletException, IOException {
        String name=request.getParameter("name");

        s.delWay(name);

        request.setAttribute("message", "Успішно видалено");
        request.getRequestDispatcher("/manage/message.jsp?d="+new Date().getTime()).forward(request, response);

    }

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}
}
