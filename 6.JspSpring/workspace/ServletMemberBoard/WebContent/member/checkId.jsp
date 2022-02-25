<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="cnt" value="${requestScope.cnt}"/>
<c:choose>
	<c:when test="${cnt gt 0}">
		{"flag" : "중복"}
	</c:when>
	<c:otherwise>
		{"flag" : "사용"}
	</c:otherwise>
</c:choose>