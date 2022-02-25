<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>

<script>
	alert("${memberVO.name}님의 정보가 수정되었습니다.");
	location.href='detail.do?id=${memberVO.id}';
</script>  