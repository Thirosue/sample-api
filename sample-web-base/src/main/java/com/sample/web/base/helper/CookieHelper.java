package com.sample.web.base.helper;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Optional;

public class CookieHelper {

	/**
	 * Cookieを取得する
	 *
	 * @return
	 */
	public static Optional<Cookie> getCookie(HttpServletRequest request, String name) {
		return request.getCookies() == null ? Optional.empty() :
				Arrays.stream(request.getCookies()).filter(c->name.equals(c.getName())).findFirst();
	}
}
