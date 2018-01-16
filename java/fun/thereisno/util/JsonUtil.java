package fun.thereisno.util;

import java.text.SimpleDateFormat;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class JsonUtil implements JsonValueProcessor{

	private String format = "yyyy-MM-dd HH:mm:ss";
	
	
	
	public JsonUtil(String format) {
		super();
		this.format = format;
	}

	public JsonUtil() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object processArrayValue(Object arg0, JsonConfig arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object processObjectValue(String key, Object value, JsonConfig config) {
		if(value == null){
			return "";
		}
		if(value instanceof java.util.Date){
			return new SimpleDateFormat(format).format((java.util.Date)value);
		}
		return value.toString();
	}

	
}
