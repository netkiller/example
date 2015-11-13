package netkiller.json;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
 
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
 
public final class Reader {
 
    public static final String JSON_FILE="json.txt";
     
    public static void main(String[] args) throws IOException {
        InputStream fis = new FileInputStream(JSON_FILE);
        //create JsonReader object
        JsonReader jsonReader = Json.createReader(fis);

        //get JsonObject from JsonReader
        JsonObject jsonObject = jsonReader.readObject();
         
        //we can close IO resource and JsonReader now
        jsonReader.close();
        fis.close();
         
        System.out.printf("nickname: %s \n", jsonObject.getString("nickname"));
        System.out.printf("name: %s \n", jsonObject.getString("name"));
        System.out.printf("department: %s \n", jsonObject.getString("department"));
        System.out.printf("role: %s \n", jsonObject.getString("role"));
        JsonArray jsonArray = jsonObject.getJsonArray("phone");
        
        //long[] numbers = new long[jsonArray.size()];
        int index = 0;
        for(JsonValue value : jsonArray){
            //numbers[index++] = Long.parseLong(value.toString());
        	System.out.printf("phone[%d]: %s \n", index++, value.toString());
        }

        //reading inner object from json object
        JsonObject innerJsonObject = jsonObject.getJsonObject("address");
        
        System.out.printf("address: %s, %s, %d \n", innerJsonObject.getString("street"), innerJsonObject.getString("city"), innerJsonObject.getInt("zipcode"));
         
    }
 
}