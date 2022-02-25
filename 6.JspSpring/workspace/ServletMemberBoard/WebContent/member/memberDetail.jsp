<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css">
 		<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/memberDetail.css">
 		
 		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"></script>
 		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>회원 상세 페이지</title>
		
		<script>
			$(function(){
				$('#delete').on('click', function(){
					var flag = confirm('삭제하시겠습니까?'); 
					if(flag){
						$('#buttonbox').submit();
					}else{
						return;
					}
				})
				
				if("${vo.picture}" == null){
					$('#pic').attr('alt', '등록된 사진이 없습니다');  
				}else{
					$('#pic').attr('alt', '${vo.picture}');
				}
			})
		</script>
	</head>
	<body>
		<div class="container mt-3">
			<h2 style="cursor:pointer;" onclick="location.href='MemberDetail.do?id=${vo.id}'">회원 상세 페이지</h2>            
			<table class="table table-hover">
				<thead>
					<tr>
						<th class="item">항목</th>
						<th>내용</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td class="item">사진</td>
						<td>
							<img id="pic" class="pic" alt="" src="/ServletMemberBoard/picture/${vo.picture}">
						</td>
					</tr>
					<tr>
						<td class="item">이름</td>
						<td>${vo.name}</td>
					</tr>
					<tr>
						<td class="item">아이디</td>
						<td>${vo.id}</td>
					</tr>
					<tr>
						<td class="item">패스워드</td>
						<td>${vo.pwd}</td>
					</tr>
					<tr>
						<td class="item">이메일</td>
						<td>${vo.email}</td>
					</tr>
					<tr>
						<td class="item">연락처</td>
						<td>${vo.phone}</td>
					</tr>
					<tr>
						<td class="item">주소</td>
						<td>
							<c:if test="${vo.address ne null}">
								${vo.address}
							</c:if>
							<c:if test="${vo.address eq null}">
								미등록
							</c:if>
						</td>
					</tr>
					<tr>
						<td class="item">입사일</td>
						<td>
							<c:if test="${vo.regdate ne null}">
								<fmt:formatDate value="${vo.regdate}" pattern="yyyy-MM-dd"/>
							</c:if>
							<c:if test="${vo.regdate eq null}">
								미등록
							</c:if>
						</td>
					</tr>
					<tr>
						<td class="item">권한</td>
						<td>${vo.authority}</td>
					</tr>
					<tr>
						<td class="item">상태</td>
						<td>${requestScope.enabled}</td>
					</tr>
					<tr>
						<td class="item">등록자</td>
						<td>${vo.register}</td>
					</tr>
				</tbody>
			</table>
			
			<form id="buttonbox" action="MemberDelete.do" method="post">
				<input type="hidden" name="id" value="${vo.id}">
				<button type="button" id="delete" class="btn btn-dark">삭제</button>
				<button type="button" class="btn btn-primary" onclick="location.href='MemberUpdate.do?id=${vo.id}'">수정</button>
				<button type="button" class="btn btn-primary" onclick="location.href='MemberList.do'">목록</button>
			</form>
		</div>
	</body>
</html>