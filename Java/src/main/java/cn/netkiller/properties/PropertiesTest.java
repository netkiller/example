package cn.netkiller.properties;

import java.util.Enumeration;
import java.util.Map.Entry;
import java.util.Properties;

public class PropertiesTest {

	public PropertiesTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Properties properties = new Properties();

		properties.put("K1", "V1");
		properties.put("K2", "V2");

		for (Entry<Object, Object> x : properties.entrySet()) {
			System.out.println(x.getKey() + " " + x.getValue());
		}
		
		Enumeration<?> e = properties.propertyNames();
		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			String value = properties.getProperty(key);
			System.out.println(key + ": " + value);
		}
	}

}
