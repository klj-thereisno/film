package fun.thereisno.util;

import java.util.List;

import fun.thereisno.entity.Film;

public class UpDownUtil {

	public static String genFooter(List<Film> list){
		StringBuilder sb = new StringBuilder();
		Film blog = new Film();
		if((blog = list.get(0)) != null)
			sb.append("<p>上一部：<a href='" + blog.getId() + "'>" + blog.getTitle() + "</a></p>");
		else
			sb.append("<p>上一部：没有了</p>");
		if((blog = list.get(1)) != null)
			sb.append("<p>下一部：<a href='" + blog.getId() + "'>" + blog.getTitle() + "</a></p>");
		else
			sb.append("<p>下一部：没有了</p>");
		return sb.toString();
		
	}
}
