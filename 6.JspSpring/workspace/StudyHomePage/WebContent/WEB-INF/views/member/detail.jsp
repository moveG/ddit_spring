<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  

<title>MemberDetail</title>

<head>
<style>
	.table{
		margin-bottom : 0px;
	}
	.table td{
		vertical-align : middle;
		height : 49px;
	}
	.item{
		width : 150px;
	}
</style>
</head>

<body>
<div class="content-wrapper">
	<!-- Main content -->
	<section class="content-header">
		<div class="container-fluid">
			<div class="row md-2">
				<div class="col-sm-6">
					<h1 class="m-0">MemberDetail</h1>			
				</div>
				<div class="col-sm-6">
					<ol class="breadcrumb float-sm-right">
						<li class="breadcrumb-item">
							<a href="detail.do?id=${memberVO.id}">
								<i class="fa fa-dashboard"></i>MemberDetail
							</a>
						</li>
						<li class="breadcrumb-item active">
							Home
						</li>		        
					</ol>
				</div>
			</div>
		</div>
	</section>
	
	<section class="content">
		<div class="row">
			<div class="col-sm-2"></div>
			<div class="card col-sm-8">
				<div class="card-body" style="padding-top:0px;padding-bottom:0px;">
					<div class="row">
						<div class="col-sm-12">	
							<table class="table table-hover">
								<tr>
									<td class="item" style="border-top:0px;"></td>
									<td class="content" style="border-top:0px;">
										<div id="pictureView" data-id="${memberVO.id}" style="border:1px solid gray;height:170px;width:132px;margin:0px;"></div>
									</td>
								</tr>
								
								<tr>
									<td class="item">아이디</td>
									<td class="content">${memberVO.id}</td>
								</tr>
								<tr>
									<td class="item">패스워드</td>
									<td class="content">${memberVO.pwd}</td>
								</tr>
								<tr>
									<td class="item">이름</td>
									<td class="content">${memberVO.name}</td>
								</tr>
								<tr>
									<td class="item">이메일</td>
									<td class="content">${memberVO.email}</td>
								</tr>
								<tr>
									<td class="item">연락처</td>
									<td class="content">
										<c:set var="phone" value="${memberVO.phone.replace('-', '')}"/>
										${phone.substring(0, 3)}-${phone.substring(3, 7)}-${phone.substring(7)}
									</td>
								</tr>
								<tr>
									<td class="item">주소</td>
									<td class="content">${memberVO.address}</td>
								</tr>
								<tr>
									<td class="item">입사일</td>
									<td class="content">
										<fmt:formatDate value="${memberVO.regDate}" pattern="yyyy-MM-dd"/>
									</td>
								</tr>
								<tr>
									<td class="item">권한</td>
									<td class="content">
										<c:if test="${memberVO.authority eq 'ROLE_USER'}">
											사용자
										</c:if>
										<c:if test="${memberVO.authority eq 'ROLE_MANAGER'}">
											운영자
										</c:if>
										<c:if test="${memberVO.authority eq 'ROLE_ADMIN'}">
											관리자
										</c:if>
									</td>
								</tr>
								<tr>
									<td class="item">상태</td>
									<td class="content">
										<c:if test="${memberVO.enabled eq 0}">
											퇴사
										</c:if>
										<c:if test="${memberVO.enabled eq 1}">
											재직
										</c:if>
										<c:if test="${memberVO.enabled eq 2}">
											휴직
										</c:if>
									</td>
								</tr>
								<tr>
									<td class="item">등록자</td>
									<td class="content">${memberVO.register}</td>
								</tr>	
							</table>
						</div> <!-- col-sm-12 -->
					</div> <!-- row -->
				</div> <!-- card-body -->
				<div class="card-footer">
					<div class="row">
						<div class="col-sm-12">
							<div class="float-right">
				          		<button type="button" class="btn btn-dark" onclick="location.href='enabled.do?id=${memberVO.id}&enabled=0';" id="stopBtn" style="display:${memberVO.enabled ne 0 ? 'visible' : 'none'};">비활성</button>
		          				<button type="button" class="btn btn-success" onclick="location.href='enabled.do?id=${memberVO.id}&enabled=1';" id="activeBtn" style="display:${memberVO.enabled eq 0 ? 'visible' : 'none'};">활성</button>
								<button type="button" class="btn btn-danger" onclick="location.href='remove.do?id=${memberVO.id}';">삭제</button>
								<button type="button" class="btn btn-primary" onclick="location.href='modifyForm.do?id=${memberVO.id}';">수정</button>
								<button type="button" class="btn btn-secondary" onclick="CloseWindow();">닫기</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-2"></div>
		</div>
	</section>
</div>

<script>
	window.onload = function(){
		MemberPictureThumb(document.querySelector('[data-id="${memberVO.id}"]'), '${memberVO.picture}', '<%=request.getContextPath()%>');
	}
</script>
</body>