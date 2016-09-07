package javascript;

import java.io.FileNotFoundException;
import java.net.URL;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class EvalFile {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByExtension("js");
		// ScriptEngine engine = manager.getEngineByMimeType("text/javascript");

		try {

			URL location = EvalFile.class.getProtectionDomain().getCodeSource().getLocation();
			String file = location.getFile() + "test.js";
			System.out.println(file);

			engine.eval(new java.io.FileReader(file));

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
