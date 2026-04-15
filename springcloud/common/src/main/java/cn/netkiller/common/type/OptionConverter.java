package cn.netkiller.common.type;

import java.util.Map;
import javax.persistence.AttributeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class OptionConverter implements AttributeConverter<Map<String, String>, String> {

	Gson json = new Gson();

	@Override
	public String convertToDatabaseColumn(Map<String, String> items) {
		return json.toJson(items, new TypeToken<Map<String, String>>() {
		}.getType());
	}

	@Override
	public Map<String, String> convertToEntityAttribute(String str) {
		return json.fromJson(str, new TypeToken<Map<String, String>>() {
		}.getType());
	}
}
