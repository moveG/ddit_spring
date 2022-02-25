<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>    

<% response.setStatus(302); %>
<!-- rest에서 에러로 가게하려고 error를 설정 -->

<script>
	alert("${message}");
	if(window.opener) {
		window.opener.parent.location.reload();
	}else {
		window.location.href="<%=request.getContextPath()%>";
	}
	window.close();
</script>