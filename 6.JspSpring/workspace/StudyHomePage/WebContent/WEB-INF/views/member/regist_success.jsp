<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>    

<script>
	alert("등록 성공!");

	window.opener.location.href="<%= request.getContextPath() %>/member/list.do";
	window.close();
</script>