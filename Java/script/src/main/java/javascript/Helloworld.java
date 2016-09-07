package javascript;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Helloworld {

	public Helloworld() {
		ScriptEngineManager manager = new ScriptEngineManager();
		List<ScriptEngineFactory> factories = manager.getEngineFactories();
		for (ScriptEngineFactory f : factories) {
			System.out.println("egine name:" + f.getEngineName());
			System.out.println("engine version:" + f.getEngineVersion());
			System.out.println("language name:" + f.getLanguageName());
			System.out.println("language version:" + f.getLanguageVersion());
			System.out.println("names:" + f.getNames());
			System.out.println("mime:" + f.getMimeTypes());
			System.out.println("extension:" + f.getExtensions());
			System.out.println("-----------------------------------------------");
		}
	}

	public void hello() throws ScriptException {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("JavaScript");
		// ScriptEngine engine = manager.getEngineByExtension("js");
		// ScriptEngine engine = manager.getEngineByMimeType("text/javascript");
		engine.eval("print('Hello, World')");
	}

	public static void main(String[] args) {
		try {
			new Helloworld().hello();
		} catch (ScriptException ex) {
			Logger.getLogger(Helloworld.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}