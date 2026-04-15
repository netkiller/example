package cn.netkiller.etl;

import cn.netkiller.ipo.Input;
import cn.netkiller.ipo.InputProcessOutput;
import cn.netkiller.ipo.Output;
import cn.netkiller.ipo.Process;
import cn.netkiller.ipo.input.FileInput;
import cn.netkiller.ipo.output.StdoutOutput;
import cn.netkiller.ipo.process.Replace;

public class Application {
	public static void main(String[] args) {
		System.out.println("Hello World!");

		// FileInput fi = ;
		// System.out.print(fi.readLine());

		Input input = new Input();
		input.add(new FileInput("/tmp/adobegc.log"));
		// input.read();

		Output output = new Output();
		output.add(new StdoutOutput());

		Process process = new Process();
		process.add(new Replace("Hello", "Netkiller "));
		process.add(new Replace("Neo", "<Neo>"));
		process.add(new Replace("Tom", "[Tom]"));

		InputProcessOutput ipo = new InputProcessOutput();
		ipo.setInput(input);
		ipo.setProcess(process);
		ipo.setOutput(output);

		ipo.launch();
		ipo.shutdown();

	}
}
