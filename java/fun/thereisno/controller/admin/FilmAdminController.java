package fun.thereisno.controller.admin;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import fun.thereisno.entity.Film;
import fun.thereisno.entity.PageBean;
import fun.thereisno.run.InitRun;
import fun.thereisno.service.FilmService;

@RestController
@RequestMapping("admin/film")
public class FilmAdminController {

	@Value("${imagePath}")
	private String imagePath;
	
	@Resource
	private FilmService filmService;
	
	@Resource
	private InitRun initRun;
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}
	
	@RequestMapping("upload")
	public String upload(MultipartFile upload, String CKEditorFuncNum){
		String filePath = new SimpleDateFormat("yyyy/MM/ddHHmmss").format(new Date()) + "." + upload.getOriginalFilename().split("\\.")[1];
		String storePath = imagePath + filePath;
		try {
			FileUtils.copyInputStreamToFile(upload.getInputStream(), new File(storePath));
		} catch (IOException e) {}
		StringBuilder sb = new StringBuilder(300);
		sb.append("<script type=\"text/javascript\">")
		  .append("window.parent.CKEDITOR.tools.callFunction(" + CKEditorFuncNum + ",'/static/uploadImages/" + filePath + "',''" + ")")
		  .append("</script>");
		return sb.toString();
	}
	
	@GetMapping("check")
	public String check(String name){
		if(filmService.existName(name))
			return "unsuccess";
		return "success";
	}
	
	@PostMapping
	public String save(MultipartFile imageFile, Film film){
		if(!imageFile.isEmpty()){
			String filePath = new SimpleDateFormat("yyyy/MM/ddHHmmss").format(new Date()) + "." + imageFile.getOriginalFilename().split("\\.")[1];
			String storePath = imagePath + filePath;
			try {
				FileUtils.copyInputStreamToFile(imageFile.getInputStream(), new File(storePath));
			} catch (IOException e) {}
			film.setImageName(filePath);
		}
		film.setPublishDate(new Date());
		filmService.save(film);
		try {
			initRun.run("");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	@GetMapping
	public Film get(Integer id){
		return filmService.get(id);
	}
	
	@PostMapping("list")
	public Map<String, Object> list(Film film, PageBean pageBean, Date s_b_date, Date s_e_date){
		Map<String, Object> map = new HashMap<>();
		map.put("total", filmService.count(film, s_b_date, s_e_date));
		map.put("rows", filmService.list(film, pageBean, s_b_date, s_e_date));
//		JsonConfig config = new JsonConfig();
//		config.setExcludes(new String[]{"content"});
//		config.registerJsonValueProcessor(Date.class, new JsonUtil());
//		config.registerJsonValueProcessor(WebSite.class, new JsonObjectUtil(new String[]{"name"},WebSite.class));
//		return JSONObject.fromObject(map, config);
		return map;
	}
	
	@GetMapping("delete")
	public String delete(String ids){
		String[] idArr = ids.split(",");
		for (String id : idArr) 
			filmService.delete(Integer.parseInt(id));
		try {
			initRun.run(new String[0]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	
}
