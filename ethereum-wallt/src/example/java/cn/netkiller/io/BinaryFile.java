package cn.netkiller.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import scala.Char;

public class BinaryFile {

	public BinaryFile() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * java.io包中的OutputStream及其子类专门用于写二进制数据。 FileOutputStream是其子类，可用于将二进制数据写入文件。 DataOutputStream是OutputStream的另一个子类，它可以 连接到一个FileOutputStream上，便于写各种基本数据类型的数据。
	 */
	public void writeMethod1() {
		String fileName = "test1.dat";
		int value0 = 255;
		int value1 = 0;
		int value2 = -1;
		try {
			// 将DataOutputStream与FileOutputStream连接可输出不同类型的数据
			// FileOutputStream类的构造函数负责打开文件kuka.dat，如果文件不存在，
			// 则创建一个新的文件，如果文件已存在则用新创建的文件代替。然后FileOutputStream
			// 类的对象与一个DataOutputStream对象连接，DataOutputStream类具有写
			// 各种数据类型的方法。
			DataOutputStream out = new DataOutputStream(new FileOutputStream(fileName));
			out.writeInt(value0);
			out.writeInt(value1);
			out.writeInt(value2);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 对于大量数据的写入，使用缓冲流BufferedOutputStream类可以提高效率
	public void writeMethod2() {
		String fileName = "test2.dat";
		try {
			DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));
			out.writeInt(10);
			System.out.println(out.size() + " bytes have been written.");
			out.writeDouble(31.2);
			System.out.println(out.size() + " bytes have been written.");
			out.writeBytes("JAVA");
			System.out.println(out.size() + " bytes have been written.");
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 对二进制文件比较常见的类有FileInputStream，DataInputStream BufferedInputStream等。类似于DataOutputStream，DataInputStream 也提供了很多方法用于读入布尔型、字节、字符、整形、长整形、短整形、 单精度、双精度等数据。
	 */
	public void readMethod1() {
		String fileName = "test1.dat";
		int sum = 0;
		try {
			DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(fileName)));
			sum += in.readInt();
			sum += in.readInt();
			sum += in.readInt();
			System.out.println("The sum is:" + sum);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void readMethod2() {
		try {
			FileInputStream stream = new FileInputStream("test2.dat");
			int c;
			while ((c = stream.read()) != -1) {
				System.out.println(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		BinaryFile bin = new BinaryFile();
		// bin.writeMethod1();
		// bin.writeMethod2();
		// bin.readMethod1();
		// bin.readMethod2();

		String filename = "netkiller.bin";
		DataOutputStream out = new DataOutputStream(new FileOutputStream(filename));
		out.writeInt(1024);
		out.writeShort(255);
		out.writeLong(100000000000L);
		out.writeFloat(3.14f);
		out.writeDouble(3.141592653579d);
		out.writeBoolean(true);
		out.writeChar(165);
		out.writeChars("陈景峰");
		out.writeUTF("Netkiller Java 手札 - http://www.netkiller.cn");
		out.writeUTF("这是最后一行\r\n");
		out.flush();
		out.close();

		DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(filename)));

		System.out.println(in.readInt());
		System.out.println(in.readUnsignedShort());
		System.out.println(in.readLong());
		System.out.println(in.readFloat());
		System.out.println(in.readDouble());
		System.out.println(in.readBoolean());
		System.out.println(in.readChar());

		int i = 0;
		String name = "";
		while (i < 3) {
			try {
				char c = in.readChar();
				name += c;
			} catch (EOFException e) {
				break;
			}
			i++;
		}

		System.out.println(name);
		System.out.println(in.readUTF());
		System.out.println(in.readUTF());
		System.out.println("--------");

		double d = 0d;
		while (true) {
			try {
				d = in.readDouble();
				System.out.println(d);

			} catch (EOFException e) {
				System.out.println();
				break;
			}
		}

		// try {
		// int i = in.readInt();
		// System.out.println(i);

		// boolean bool = in.readBoolean();
		// System.out.println(bool);

		// long l = in.readLong();
		// System.out.println(l);

		// char c = in.readChar();
		// System.out.println(c);

		// char c = ' ';
		// while (true) {
		// try {
		// c = in.readChar();
		// System.out.print(c);
		//
		// } catch (EOFException e) {
		// System.out.println();
		// break;
		// }
		// }
		// System.out.println(new String(in.readAllBytes()));
		// System.out.println(in.readUTF());
		// System.out.println(in.readShort());
		// System.out.println(in.readUnsignedShort());
		// System.out.println(in.readShort());
		// System.out.println(in.readUnsignedShort());

		// } catch (EOFException e) {
		// e.printStackTrace();
		// }

		System.out.println("--------");
		// FileInputStream stream = new FileInputStream(filename);
		//
		// byte[] buffer = new byte[8];
		//
		// while (stream.read(buffer) != -1) {
		// ByteBuffer byteBuffer = ByteBuffer.wrap(buffer);
		// System.out.println(byteBuffer.getInt());
		// }

		// int c;
		// while ((c = stream.read()) != -1) {
		// System.out.println(c);
		// }

		// String binStr=Integer.toBinaryString(1);
		// System.out.println(binStr.)

	}
}
