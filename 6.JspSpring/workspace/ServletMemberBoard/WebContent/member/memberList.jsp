<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css">
 		<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/memberList.css">
 		
 		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"></script>
 		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
 		
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>회원 관리 페이지</title>
		
		<script>
			$(function(){
				var searchKey = "${pagingVO.searchKey}";
				
				if(searchKey == "id"){
					$('#searchId').prop('selected', true);
				}else if(searchKey == "name"){
					$('#searchName').prop('selected', true);
				}else if(searchKey == "email"){
					$('#searchEmail').prop('selected', true);
				}else if(searchKey == "phone"){
					$('#searchPhone').prop('selected', true);
				}
			})
		</script>
	</head>
	<body id="body">
		<div class="container mt-3">
			<h2 style="cursor:pointer;" onclick="location.href='MemberList.do'">회원 관리 페이지</h2>
			<br>
			<form action="MemberList.do" method="get">
				<div class="input-group form-group mb-3" id="searchbox">
					<select style="cursor:pointer;" name="searchKey"  class="form-control" id="sel1">
						<option id="searchId" value="id">ID</option>
						<option id="searchName" value="name">Name</option>
						<option id="searchEmail" value="email">Email</option>
						<option id="searchPhone" value="phone">Tel</option>
					</select>
					<input type="text" class="form-control" placeholder="Search" name="searchWord" value="${pagingVO.searchWord}">
					<button class="btn btn-success" type="submit">검색</button>
				</div>
				<div id="insertbox">
					<button type="button" class="btn btn-primary" onclick="location.href='MemberInsert.do'">등록</button>
				</div>
			</form>
			<table class="table table-hover" id="memberList">
				<thead class="table-dark">
					<tr>
						<th id="id" class="id">ID</th>
						<th id="name" class="name">Name</th>
						<th id="email" class="email">Email</th>
						<th id="tel" class="tel">Tel</th>
						<th id="state" class="state">State</th>
					</tr>
				</thead>
				<tbody>
					<c:set var="listSize" value="${requestScope.listSize}"/>
					<c:if test="${listSize gt 0}">
						<c:forEach items="${list}" var="vo">
							<tr style="cursor:pointer;" onclick="location.href='MemberDetail.do?id=${vo.id}'">
								<td class="id">${vo.id}</td>
								<td class="name">${vo.name}</td>
								<td class="email">${vo.email}</td>
								<td class="tel">${vo.phone}</td>
								<td class="state">
									<c:set var="enabled" value="${vo.enabled}"/>
									<c:if test="${enabled eq 0}">
										퇴사
									</c:if>
									<c:if test="${enabled eq 1}">
										재직
									</c:if>
									<c:if test="${enabled eq 2}">
										휴직
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${listSize eq 0}">
						<tr>
							<c:if test="${pagingVO.searchKey ne ''}">
								<td colspan="5" align="center">검색결과가 존재하지 않습니다.</td>
							</c:if>
							<c:if test="${pagingVO.searchKey eq ''}">
								<td colspan="5" align="center">회원이 존재하지 않습니다.</td>
							</c:if>
						</tr>
					</c:if>
				</tbody>
			</table>
			
			<c:set var="pagingVO" value="${requestScope.pagingVO}"/>
			<c:if test="${pagingVO.totalCount gt 0}">
				<ul class="pagination justify-content-center pagingbtn" style="margin:20px 0">
					<c:if test="${pagingVO.firstPageNo gt pagingVO.pageSize}">
						<li class="page-item">
							<a class="page-link" href="MemberList.do?searchKey=${pagingVO.searchKey}&searchWord=${pagingVO.searchWord}&pageNo=${pagingVO.firstPageNo - pagingVO.pageSize}">이전</a>
						</li>
					</c:if>	
					<c:forEach var="pNo" begin="${pagingVO.firstPageNo}" end="${pagingVO.lastPageNo}" step="1" varStatus="status">
						<li class="page-item
							<c:if test="${pNo eq pagingVO.currentPageNo}">
								active
							</c:if>	
								">
							<a class="page-link" href="MemberList.do?searchKey=${pagingVO.searchKey}&searchWord=${pagingVO.searchWord}&pageNo=${pNo}">${pNo}</a>
						</li>
					</c:forEach>
					<c:if test="${pagingVO.lastPageNo lt pagingVO.totalPageCount}">
						<li class="page-item">
							<a class="page-link" href="MemberList.do?searchKey=${pagingVO.searchKey}&searchWord=${pagingVO.searchWord}&pageNo=${pagingVO.firstPageNo + pagingVO.pageSize}">다음</a>
						</li>
					</c:if>	
				</ul>
			</c:if>		
		</div>
	</body>
</html>