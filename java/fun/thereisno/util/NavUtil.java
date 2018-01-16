package fun.thereisno.util;

import fun.thereisno.entity.WebSite;

public class NavUtil {
	
	public static String genNav(WebSite type, String name){
		StringBuilder sb =  new StringBuilder("<ol class=\"breadcrumb\">当前位置：");
		if(name != null){
			sb.append("<li><a href='/site/" + type.getId() + "'>" + type.getName() + "</a></li>");
			sb.append("<li class='active'>" + name + "</li></ol>");
		}else
			sb.append("<li classs='active'>" + type.getName() + "</li></ol>");
		return sb.toString();
	}
}
