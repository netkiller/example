package netkiller.json;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.json.*;

public final class Writer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JsonObjectBuilder jsonBuilder = Json.createObjectBuilder();
		JsonObjectBuilder addressBuilder = Json.createObjectBuilder();
		JsonArrayBuilder phoneNumBuilder = Json.createArrayBuilder();

		phoneNumBuilder.add("+8612355566688").add("0755-2222-3333");

		addressBuilder.add("street", "Longhua").add("city", "Shenzhen").add("zipcode", 518000);

		jsonBuilder.add("nickname", "netkiller").add("name", "Neo").add("department", "IT").add("role", "Admin");

		jsonBuilder.add("phone", phoneNumBuilder);
		jsonBuilder.add("address", addressBuilder);

		JsonObject jsonObject = jsonBuilder.build();

		System.out.println(jsonObject);

		try {
			// write to file
			File file = new File("json.txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			OutputStream os = null;
			os = new FileOutputStream(file);
			JsonWriter jsonWriter = Json.createWriter(os);
			jsonWriter.writeObject(jsonObject);
			jsonWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
