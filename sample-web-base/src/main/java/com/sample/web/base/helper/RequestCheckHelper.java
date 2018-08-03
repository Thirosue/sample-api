package com.sample.web.base.helper;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

public class RequestCheckHelper {

	public static boolean isUpdateRequest(HttpServletRequest request) {
		return !StringUtils.contains(request.getRequestURI(), "/error/") &&
				!StringUtils.contains(request.getRequestURI(), "/auth") &&
				Arrays.asList("POST", "PUT", "DELETE").stream().anyMatch(method->StringUtils.equalsIgnoreCase(request.getMethod(), method));
	}
}
