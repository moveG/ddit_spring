<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>    

<script>
	alert("등록 성공!");
	
	<%-- window.opener.location.href="<%= request.getContextPath() %>/notice/list.do"; --%>
	window.opener.location.reload();
	window.close();
</script>