<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>    

<script>
	alert("등록 성공!");
	
	window.close();
	window.opener.parent.location.href="<%=request.getContextPath()%>/index.do?mCode=M040100";
</script>