package com.shubh.blog.config;

import java.util.Arrays;
import java.util.List;

public class AppConstants {

	public static final String PAGE_NUMBER = "0";
	public static final String PAGE_SIZE = "5";
	public static final String SORT_BY = "postId";
	public static final String SORT_DIR = "asc";
	public static final List<String> ALLOWED_FILES = Arrays.asList("png","jpg","jpeg");
	public static final String SECRET = "jwtTokenKey";
	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
	public static final Integer NORMAL_USER = 502;
	public static final Integer ADMIN_USER = 501;
}
