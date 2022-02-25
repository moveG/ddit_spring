package egovframework.example.sample.interceptor;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import egovframework.example.sample.service.LoginVo;

@Controller
//@RequestMapping(value = {"/com", "/com2"})
public class LoginController {
	
	//request 요청시 post, get, service 등 모든 요청을 전부 받아서 처리함
	@RequestMapping(value = {"/login.do", "/loginFront.do"})
	
	//request 요청시 get 방식만 허용하고 나머지는 에러처리
	@GetMapping(value = {"/login.do", "/loginFront.do"})

	//request 요청시 post 방식만 허용하고 나머지는 에러처리(ex: 서브 및 버튼 등)
	@PostMapping(value = {"/login.do", "/loginFront.do"})
	public String loginFront() {
		/*QRCodeWriter qrCodeWriter = new QRCodeWriter(); // QR 코드
		MultiFormatWriter multiFormatWriter = new MultiFormatWriter(); // 바코드
		
		try {
			//String text = "1234123";
			//String text = "http://127.0.0.1:8787/login.do?key=랜덤 생성..";
			String text = "http://192.168.143.11:8787/updateSampleView.do?key=랜덤샌성";
			text = new String(text.getBytes("UTF-8"), "ISO-8859-1");
	 
			//zxing에서 스트림에 파일을 뿌릴수있도록 메소드를 지원함. QR코드
			OutputStream out = new FileOutputStream("/Temp/Crunchify.com-QRCode2.png");
			BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 500, 200);
			MatrixToImageWriter.writeToStream(bitMatrix, "png", out);
		} catch (WriterException | IOException e) {
			e.printStackTrace();
		}*/
		
		return "loginFront";
	}
	
	@GetMapping(value = {"/loginProc.do"})
	public String loginProc(HttpServletRequest req, HttpSession sessions) {
		
		HttpSession session =  req.getSession();

		LoginVo loginInfo = new LoginVo();
		{//DB에서 조회된 데이터
			loginInfo.setId("heebang");
			loginInfo.setName("오니짱");
			loginInfo.setAge("55");
			loginInfo.setLogin(true);
		}
		{
			sessions.setAttribute("loginInfo", loginInfo);			
		}
		return "loginFront";
	}
}
