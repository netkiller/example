package cn.netkiller.security;

import java.nio.ByteBuffer;
import java.util.zip.CRC32;

public class CRC {
	/*
	 * private static long getCRC(final File file) throws IOException { final FileInputStream fis = new FileInputStream(file); final byte[] data = new byte[(int) file.length()]; fis.read(data); fis.close(); final CRC32 crc32 = new CRC32(); crc32.update(data); return crc32.getValue(); }
	 */
	public static void main(String[] args) {

		final CRC32 crc32 = new CRC32();
		ByteBuffer data = ByteBuffer.wrap("http://www.netkiller.cn".getBytes());
		crc32.update(data);
		System.out.println(crc32.getValue());

	}

}
