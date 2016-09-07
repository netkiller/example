package javascript;

import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ContextVariable {

	public static void main(String[] args) throws ScriptException {
		// TODO Auto-generated method stub
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("JavaScript");
		ScriptContext context = engine.getContext();  
	    context.setAttribute("name", "Netkiller", ScriptContext.GLOBAL_SCOPE);  
	    context.setAttribute("name", "Neo", ScriptContext.ENGINE_SCOPE);  
	    //context.getAttribute("name"); 
	    
	    engine.eval("print('I am ' + name);", context);
	    
	    ScriptEngine engine1 = manager.getEngineByName("JavaScript");
	    ScriptContext context1 = engine1.getContext();  
	    engine.eval("print('I am ' + name);", context1);
	    
	}

}
