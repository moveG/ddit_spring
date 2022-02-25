package kr.co.ddit.core;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QRCodeTest {

	public static void main(String[] args) throws WriterException {

		String myCodeText = "뇌를 자극 하는 대덕";
		String filePath = "/Temp/Crunchify.com-QRCode1.png";
		int size = 512;

		String crunchifyFileType = "png";
		File crunchifyFile = new File(filePath);

		try {

			Map<EncodeHintType, Object> crunchifyHintType = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
			crunchifyHintType.put(EncodeHintType.CHARACTER_SET, "UTF-8");

			crunchifyHintType.put(EncodeHintType.MARGIN, 1); /* default = 4 */
			Object put = crunchifyHintType.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);

			QRCodeWriter mYQRCodeWriter = new QRCodeWriter(); // throws com.google.zxing.WriterException
			BitMatrix crunchifyBitMatrix = mYQRCodeWriter.encode(myCodeText, BarcodeFormat.QR_CODE, size, size, crunchifyHintType);

			int CrunchifyWidth = crunchifyBitMatrix.getWidth();

			BufferedImage crunchifyImage = new BufferedImage(CrunchifyWidth, CrunchifyWidth, BufferedImage.TYPE_INT_RGB);

			crunchifyImage.createGraphics();

			Graphics2D crunchifyGraphics = (Graphics2D) crunchifyImage.getGraphics();

			crunchifyGraphics.setColor(Color.white);

			crunchifyGraphics.fillRect(0, 0, CrunchifyWidth, CrunchifyWidth);

			crunchifyGraphics.setColor(Color.BLUE);

			for (int i = 0; i < CrunchifyWidth; i++) {

				for (int j = 0; j < CrunchifyWidth; j++) {

					if (crunchifyBitMatrix.get(i, j)) {
						crunchifyGraphics.fillRect(i, j, 1, 1);
					}

				}

			}

			// 이미지 생성
			ImageIO.write(crunchifyImage, crunchifyFileType, crunchifyFile);

			System.out.println("\nCongratulation.. You have successfully created QR Code.. \n" + "Check your code here: " + filePath);

		} catch (WriterException e) {
			System.out.println("\nSorry.. Something went wrong...\n");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
