package javascript;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleBindings;

public class ScriptVars {

	ScriptEngine engine = null;

	public ScriptVars() {
		ScriptEngineManager manager = new ScriptEngineManager();
		engine = manager.getEngineByMimeType("text/javascript");
	}

	public void variable() throws ScriptException {
		engine.put("name", "Neo");
		engine.eval("var message = 'Hello, ' + name;");
		engine.eval("print(message);");
		Object obj = engine.get("message");
		System.out.println(obj);
	}

	public void simpleBindings() throws ScriptException {
		Bindings bindings = new SimpleBindings();
		bindings.put("name", "Neo");
		engine.eval("print('I am ' + name);", bindings);
	}

	public void function() throws ScriptException {
		engine.put("a", 4);
		engine.put("b", 6);
		Object maxNum = engine.eval("function max_num(a,b){return (a>b)?a:b;}max_num(a,b);");
		System.out.println("max_num:" + maxNum + ", (class = " + maxNum.getClass() + ")");

		Map<String, Integer> m = new HashMap<String, Integer>();
		m.put("c", 10);
		engine.put("m", m);
		engine.eval("var x= max_num(a,m.get('c'));");
		System.out.println("max_num:" + engine.get("x"));
	}

	public void object() throws ScriptException {
		File f = new File("test.txt");
		// expose File object as variable to script
		engine.put("file", f);

		// evaluate a script string. The script accesses "file"
		// variable and calls method on it
		engine.eval("print(file.getAbsolutePath())");
	}

	public void outputToFile() throws IOException, ScriptException {
		ScriptContext context = engine.getContext();
		context.setWriter(new FileWriter("script.log"));
		engine.eval("print('Hello World!');");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			ScriptVars sv = new ScriptVars();
			sv.variable();
			sv.simpleBindings();
			sv.outputToFile();
			sv.function();
			sv.object();
			sv.outputToFile();

		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
