package cn.netkiller.gson;

import com.google.gson.Gson;

public class GsonExampleFromJson {
	public static void main(String[] args) {

		Personal obj = new Personal();
		Gson gson = new Gson();

		// convert the json string back to object
		obj = gson.fromJson("{\"age\":30,\"name\":\"neo\",\"telphone\":[\"13113668890\",\"13322993040\",\"29812080\"]}", Personal.class);

		System.out.println(obj);
	}
}
