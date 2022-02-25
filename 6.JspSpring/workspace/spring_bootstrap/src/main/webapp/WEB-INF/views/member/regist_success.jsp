<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>    

<script>
	alert("등록 성공!");
	
	//window.opener.location.href = "list?page=1&perPageNum=10";
	//window.opener.location.href = "list?page=1;
	//window.opener.list_go(1);
	//window.close();
	/* window.onload = function(){
		CloseWindow();
	} */
	<%-- window.opener.location.href="<%= request.getContextPath() %>/member/list";
	window.close(); --%>
	window.opener.location.href="<%= request.getContextPath() %>/member/list.do";
	window.close();
</script>