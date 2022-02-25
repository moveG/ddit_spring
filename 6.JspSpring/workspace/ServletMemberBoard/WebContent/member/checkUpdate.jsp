<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="cnt" value="${requestScope.cnt}"/>
<c:choose>
	<c:when test="${cnt gt 0}">
		<script>
			alert("수정 성공");
			location.replace('MemberDetail.do?id=${requestScope.id}');	
		</script>
	</c:when>
	<c:otherwise>
		<script>
			alert("수정 실패");
			history.go(-1);
		</script>
	</c:otherwise>
</c:choose>