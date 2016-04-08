package cn.netkiller.gson;

import com.google.gson.Gson;

public class GsonExampleToJson {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Personal obj = new Personal();
		Gson gson = new Gson();

		// convert java object to JSON format, and returned as JSON formatted string
		String json = gson.toJson(obj);
		System.out.println(json);
	}

}
