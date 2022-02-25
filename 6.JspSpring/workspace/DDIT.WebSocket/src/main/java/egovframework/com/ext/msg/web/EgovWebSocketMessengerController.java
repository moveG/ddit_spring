/*
 * eGovFrame Web Messager
 * Copyright The eGovFrame Open Community (http://open.egovframe.go.kr)).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @author 이영지(슈퍼개발자K3)
 */
package egovframework.com.ext.msg.web;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.com.cmm.LoginVO;

@Controller
public class EgovWebSocketMessengerController {

	/**
	 * 웹소켓 메신저 접속화면으로 이동한다.
	 * 
	 * @param session 사용자세션
	 * @param model   모델
	 * @return view name
	 */
	@RequestMapping(value = "/cop/msg/websocketMessengerView.do")
	public String websocketMessengerView(HttpSession session, ModelMap model) {
		model.addAttribute("loginVO", session.getAttribute("loginVO"));
		return "egovframework/com/ext/msg/EgovMessenger";
	}

	/**
	 * 웹 소켓 메신저 메인화면(대화상대 리스트화면)으로 이동한다.
	 * 
	 * @param session 사용자세션
	 * @param model   모델
	 * @return view name
	 */
	@RequestMapping(value = "/cop/msg/websocketMessengerMain.do")
	public String websocketMessengerMain(HttpSession session, ModelMap model) {
		model.addAttribute("loginVO", session.getAttribute("loginVO"));
		return "egovframework/com/ext/msg/EgovMessengerMain";
	}

	/**
	 * 대화창을 새로 띄운다.
	 * 
	 * @param roomId   대화창 아이디
	 * @param username 대화상대 이름
	 * @param session  사용자세션
	 * @param model    모델
	 * @return view name
	 */
	@RequestMapping(value = "/cop/msg/websocketMessengePopup.do")
	public String websocketMessengePopup(@RequestParam(value = "roomId") String roomId, @RequestParam(value = "username") String username, HttpSession session, ModelMap model) {
		model.addAttribute("loginVO", session.getAttribute("loginVO"));
		model.addAttribute("roomId", roomId);
		model.addAttribute("username", username);
		return "egovframework/com/ext/msg/popup/chatPopupBubble";
	}

	@RequestMapping(value = "/cop/msg/session.do")
	public String websocketSession(

			HttpSession session, ModelMap model

	) {

		LoginVO loginVO = new LoginVO();
		loginVO.setId(String.format("%s", RandomUtils.nextLong(100, 20000)));
		loginVO.setPassword("raHLBnHFcunwNzcDcfad4PhD11hHgXSUr7fc1Jk9uoQ=");
		loginVO.setUserSe("USR");
		loginVO.setEmail("egovframe@nia.or.kr");
		loginVO.setIhidNum("");
		loginVO.setName(String.format("쳇_%s", loginVO.getId()));
		loginVO.setOrgnztId("ORGNZT_0000000000000");
		loginVO.setUniqId("USRCNFRM_00000000000");

		session.setAttribute("loginVO", loginVO);

		return "blank";
	}

}
