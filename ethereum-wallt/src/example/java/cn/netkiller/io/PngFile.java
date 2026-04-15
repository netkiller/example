package cn.netkiller.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class PngFile {

	private static InputStream inputStream;

	public PngFile() {
	}

	public static void main(String[] args) throws FileNotFoundException {

		int[] pngSignature = { 137, 80, 78, 71, 13, 10, 26, 10 };

		try {
			inputStream = new FileInputStream("/Users/neo/Downloads/NBRC.png");

			byte[] headerBytes = new byte[8];
			inputStream.read(headerBytes);

			// for (byte b : headerBytes) {
			// System.out.println(b);
			// }

			int[] pngHeader = new int[8];

			for (int i = 0; i < headerBytes.length; i++) {
				pngHeader[i] = (int) headerBytes[i] & 0xff;
			}

			if (Arrays.equals(pngHeader, pngSignature)) {
				System.out.println("This is a PNG file! ");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
