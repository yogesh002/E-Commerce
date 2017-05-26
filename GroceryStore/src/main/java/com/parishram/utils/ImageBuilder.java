package com.parishram.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

public class ImageBuilder {
	private static Logger LOGGER = Logger.getLogger(ImageBuilder.class);
	private static List<String> uploadedImageList;
	private static List<HashMap<String, String>> paramNameValueMapList;

	public static List<String> getImages() {
		return uploadedImageList;
	}

	public static List<HashMap<String, String>> getParamNameValue() {
		return paramNameValueMapList;
	}

	public static void handleMultipartFormUpload(HttpServletRequest request, List<String> inputParams)
			throws FileUploadException, IOException {
		if (ServletFileUpload.isMultipartContent(request)) {
			ServletFileUpload fileUpload = new ServletFileUpload();
			uploadedImageList = new ArrayList<String>();
			paramNameValueMapList = new ArrayList<HashMap<String, String>>();
			FileItemIterator items = fileUpload.getItemIterator(request);
			while (items.hasNext()) {
				FileItemStream fileItem = items.next();
				InputStream is = fileItem.openStream();
				if (!fileItem.isFormField()) {
					byte[] byteArray = IOUtils.toByteArray(is);
					if (byteArray.length != 0) {
						String uploadedImage = ImageBuilder.buildImage(byteArray);
						uploadedImageList.add(uploadedImage);
					}
				} else if (fileItem.isFormField()) {
					for (String param : inputParams) {
						if (fileItem.getFieldName().equals(param)) {
							byte[] byteArray = new byte[is.available()];
							is.read(byteArray);
							String value = new String(byteArray, "UTF8");
							Map<String, String> paramNameValueMap = new HashMap<String, String>();
							paramNameValueMap.put(param, value);
							paramNameValueMapList.add((HashMap<String, String>) paramNameValueMap);
							break;
						}
					}

				}
			}

		}

	}

	public static String buildImage(byte[] imageUploaded) {
		String image = new String(Base64.getEncoder().encode(imageUploaded));
		StringBuilder imageBuilder = new StringBuilder();
		imageBuilder.append("data:image/jpeg;charset=utf-8;base64,");
		imageBuilder.append(image.trim());
		return imageBuilder.toString();
	}
}
