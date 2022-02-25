<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>    
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>

<%-- <jsp:include page="/include/header.jsp"/>
<jsp:include page="/include/left.jsp"/> 
이건 변수공유가 안됨
--%>
<%@ include file="/include/header.jsp" %>
<%@ include file="/include/left.jsp" %>

<decorator:body/>

<%-- <jsp:include page="/include/footer.jsp"/> --%>
<%@ include file="/include/footer.jsp" %>