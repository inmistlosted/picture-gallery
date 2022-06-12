package com.podvazhuk.gallery.utils;

import com.podvazhuk.gallery.domain.Category;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;

public class WebUtil {
	public static <T>  T fillBean(HttpServletRequest request,Class<T> clazz){
		try {
			T bean=clazz.newInstance();
			BeanUtils.populate(bean, request.getParameterMap());
			return bean;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static Category fillBean(String name, Class<Category> class1) {
		return null;
	}
}
