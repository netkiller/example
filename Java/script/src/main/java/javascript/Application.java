package javascript;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("JavaScript");

		System.out.println(engine.getClass().getName());
		try {
			System.out.println("Result:" + engine.eval("function f() { return 1; }; f() + 1;"));
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
