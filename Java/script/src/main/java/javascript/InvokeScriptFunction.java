package javascript;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class InvokeScriptFunction {

	public static void main(String[] args) throws ScriptException, NoSuchMethodException {
		// TODO Auto-generated method stub
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("JavaScript");

		// JavaScript code in a String
		String script = "function hello(name) { print('Hello, ' + name); }";
		// evaluate script
		engine.eval(script);

		// javax.script.Invocable is an optional interface.
		// Check whether your script engine implements or not!
		// Note that the JavaScript engine implements Invocable interface.
		Invocable inv = (Invocable) engine;

		// invoke the global function named "hello"
		inv.invokeFunction("hello", "Scripting!!");

		// JavaScript code in a String. This code defines a script object 'obj'
		// with one method called 'hello'.
		script = "var obj = new Object(); obj.hello = function(name) { print('Hello, ' + name); }";
		// evaluate script
		engine.eval(script);
		// get script object on which we want to call the method
		Object obj = engine.get("obj");

		// invoke the method named "hello" on the script object "obj"
		inv.invokeMethod(obj, "hello", "Script Method !!");
	}

}
