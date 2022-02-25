package kr.co.ddit.core;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class QRCode3Test {

	public static void main(String[] args) throws WriterException, IOException {

		String filePath = "/Temp/Crunchify.com-QRCode3.png";

		QRCodeWriter qrCodeWriter = new QRCodeWriter(); // QR 코드

		String text = "뇌를 자극 하는 대덕";
		text = new String(text.getBytes("UTF-8"), "ISO-8859-1");

		BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 500, 500);

		System.out.println(Color.BLACK.getTransparency());

		MatrixToImageConfig config = new MatrixToImageConfig(Color.RED.hashCode(), Color.WHITE.hashCode());
		System.out.println(config.getPixelOffColor());
		System.out.println(config.getPixelOnColor());

		Path file = Paths.get(filePath);

		MatrixToImageWriter.writeToPath(bitMatrix, "png", file, config);

		BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix, config);
//		ImageIO.write(qrImage, "jpg", new File("/Temp/Crunchify.com-QRCode.jpg"));

	}
}
