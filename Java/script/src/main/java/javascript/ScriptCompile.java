package javascript;

import javax.script.*;

public class ScriptCompile {
	public CompiledScript compile(String script) throws ScriptException {

		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("JavaScript");

		return ((Compilable) engine).compile(script);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ScriptCompile sc = new ScriptCompile();
		try {

			CompiledScript script = sc.compile("print('Helloworld!!!');");

			for (int i = 0; i < 100; i++) {
				script.eval();
			}

		} catch (ScriptException ex) {
			ex.printStackTrace();
		}
	}

}
