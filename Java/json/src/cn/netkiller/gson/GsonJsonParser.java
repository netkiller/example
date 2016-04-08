package cn.netkiller.gson;

import java.util.Map.Entry;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public class GsonJsonParser {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String jsonString = "{\"age\":30,\"name\":\"neo\",\"telphone\":[\"13113668890\",\"13322993040\",\"29812080\"],\"address\":{\"province\":\"Guangdong\",\"city\":\"Shenzhen\"}}";
		// Read the JSON file
		// new FileReader("/path/to/the/json/file/in/your/file/system.json")
		JsonElement root = new JsonParser().parse(jsonString);
		System.out.println(root.toString());
		System.out.println(root.getAsJsonObject().get("age").getAsInt());
		System.out.println(root.getAsJsonObject().get("name").getAsString());

		// Get the content of the first map
		JsonArray jsonArray = root.getAsJsonObject().get("telphone").getAsJsonArray();

		for (JsonElement tel : jsonArray) {
			System.out.println(tel);
		}

		JsonObject object = root.getAsJsonObject().get("address").getAsJsonObject();
		for (Entry<String, JsonElement> entry : object.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
	}

}
