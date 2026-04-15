package cn.netkiller.wallet.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/captcha")
public class CaptchaController {

	public String captcha = "captcha";

	public CaptchaController() {
		// HttpServletRequest request = null;
		// HttpSession session = request.getSession();
		// session.setAttribute(this.captcha, "酸扥空单反哈扫江防刷夜穿刷远处我");
	}

	public String test() {
		return captcha;
	}

	// @RequestMapping("/set")
	// public String test(HttpServletRequest request, HttpServletResponse response) {
	// HttpSession session = request.getSession();
	// session.setAttribute(this.captcha, "酸扥空单反哈扫江防刷夜穿刷远处我");
	// return "OK";
	// }

	@RequestMapping("/images")
	public void image(HttpServletRequest request, HttpServletResponse response) throws IOException {

		HttpSession session = request.getSession();

		response.setDateHeader("Expires", 0);

		// Set standard HTTP/1.1 no-cache headers.
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");

		// Set IE extended HTTP/1.1 no-cache headers (use addHeader).
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");

		// Set standard HTTP/1.0 no-cache header.
		response.setHeader("Pragma", "no-cache");

		// return a jpeg
		response.setContentType("image/jpeg");

		// store the text in the session

		ServletOutputStream out = response.getOutputStream();
		String code = (String) session.getAttribute(captcha);
		if (code == null) {
			code = " ";
		}

		System.out.println("******************验证码是: " + code + "******************");

		outputImage2(800, 80, out, code);

		// write the data out
		try {
			out.flush();
		} finally {
			out.close();
		}

		// return null;
	}

	private static Random random = new Random();

	/**
	 * 生成指定验证码图像文件
	 * 
	 * @param w
	 * @param h
	 * @param outputFile
	 * @param code
	 * @throws IOException
	 */
	public static void outputImage(int w, int h, File outputFile, String code) throws IOException {
		if (outputFile == null) {
			return;
		}
		File dir = outputFile.getParentFile();
		if (!dir.exists()) {
			dir.mkdirs();
		}
		try {
			outputFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(outputFile);
			outputImage2(w, h, fos, code);
			fos.close();
		} catch (IOException e) {
			throw e;
		}
	}

	private static Color getRandColor(int fc, int bc) {
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	private static int getRandomIntColor() {
		int[] rgb = getRandomRgb();
		int color = 0;
		for (int c : rgb) {
			color = color << 8;
			color = color | c;
		}
		return color;
	}

	private static int[] getRandomRgb() {
		int[] rgb = new int[3];
		for (int i = 0; i < 3; i++) {
			rgb[i] = random.nextInt(255);
		}
		return rgb;
	}

	public static void outputImage2(int w, int h, OutputStream os, String code) throws IOException {
		int verifySize = code.length();
		BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Random rand = new Random();
		Graphics2D g2 = image.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Color[] colors = new Color[5];
		Color[] colorSpaces = new Color[] { Color.WHITE, Color.CYAN, Color.GRAY, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.YELLOW, Color.BLACK, Color.BLUE, Color.DARK_GRAY, Color.GREEN, Color.ORANGE, Color.RED };
		float[] fractions = new float[colors.length];
		for (int i = 0; i < colors.length; i++) {
			colors[i] = colorSpaces[rand.nextInt(colorSpaces.length)];
			fractions[i] = rand.nextFloat();
		}
		Arrays.sort(fractions);

		// g2.setColor(getRandColor(10, 250));// 设置边框色
		//

		Color c = getRandColor(100, 250);
		g2.setColor(c);// 设置背景色
		g2.fillRect(0, 0, w, h);
		// g2.fillRect(0, 1, w - 1, h - 1);

		// 绘制干扰线
		Random random = new Random();

		for (int i = 0; i < 50; i++) {
			int x = random.nextInt(w - 1);
			int y = random.nextInt(h - 1);
			int xl = random.nextInt(w / 2) - 1;
			int yl = random.nextInt(h) - 1;
			g2.setColor(getRandColor(100, 250));// 设置线条的颜色
			g2.drawLine(x, y, x + xl, yl);
		}

		// shear1(g2, w / 2, h, c);// 使图片扭曲
		String[] fontName = new String[] { "黑体", "宋体", "楷体" };

		char[] chars = code.toCharArray();
		for (int i = 0; i < verifySize; i++) {

			g2.setColor(getRandColor(50, 200));
			int fontSize = h - random.nextInt(50);

			// Font font = new Font("Algerian", Font.ITALIC, fontSize);
			Font font = new Font(fontName[rand.nextInt(fontName.length)], Font.ITALIC, fontSize);
			g2.setFont(font);

			AffineTransform affine = new AffineTransform();
			// affine.setToRotation(Math.PI / 4 * rand.nextDouble() * (rand.nextBoolean() ? 1 : -1), (w / verifySize) * i + fontSize / 2, h / 2);
			affine.setToRotation(Math.PI * rand.nextDouble() * (rand.nextBoolean() ? 1 : -1), (w / verifySize) * i + fontSize / 2, h / 2);
			g2.setTransform(affine);
			g2.drawChars(chars, i, 1, ((w - 10) / verifySize) * i + 5, h / 2 + fontSize / 2 - 10);
		}

		// 添加噪点
		float yawpRate = 0.08f;// 噪声率
		int area = (int) (yawpRate * w * h);
		for (int i = 0; i < area; i++) {
			int x = random.nextInt(w);
			int y = random.nextInt(h);
			int rgb = getRandomIntColor();
			image.setRGB(x, y, rgb);
		}

		g2.dispose();
		ImageIO.write(image, "jpg", os);
	}

}
