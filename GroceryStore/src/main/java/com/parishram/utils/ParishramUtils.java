package com.parishram.utils;

import org.springframework.web.servlet.ModelAndView;

import com.mysql.jdbc.StringUtils;

public class ParishramUtils {
	private static ModelAndView modelAndView;

	public static ModelAndView getModelAndView(String viewName) {
		if (!StringUtils.isNullOrEmpty(viewName)) {
			modelAndView = new ModelAndView(viewName);
		}
		return modelAndView;
	}

}
