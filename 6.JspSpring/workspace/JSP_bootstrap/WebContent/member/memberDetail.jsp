<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<title>MemberDetail</title>

<head>
<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/memberDetail.css">

<script>
	$(function(){
		$('#delete').on('click', function(){
			var flag = confirm('삭제하시겠습니까?'); 
			if(flag){
				$('#btnbox').submit();
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
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<div class="content-header">
		<div class="container-fluid">
			<div class="row mb-2">
				<div class="col-sm-6">
					<h1 class="m-0" style="cursor:pointer;" onclick="location.href='MemberDetail.do?id=${vo.id}'">MemberDetail</h1>
				</div><!-- /.col -->
				<div class="col-sm-6">
					<ol class="breadcrumb float-sm-right">
						<li class="breadcrumb-item"><a href="#">Home</a></li>
						<li class="breadcrumb-item active">MemberDetail</li>
					</ol>
				</div><!-- /.col -->
			</div><!-- /.row -->
		</div><!-- /.container-fluid -->
	</div>
	<!-- /.content-header -->
   
	<!-- Main content -->
	<div class="content">
		<div class="container-fluid">
			<div class="row">
				<div class="col-2"></div>
				<div class="col-8">
					<div class="card">
						<!-- /.card-header -->
						<div class="card-body table-responsive p-0">
							<form id="btnbox" action="MemberDelete.do" method="post">
								<table class="table table-hover text-nowrap">
									<thead class="table-dark">
			                    		<tr>
											<th class="item">항목</th>
											<th>내용</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td class="item">사진</td>
											<td>
												<img id="pic" class="pic" alt="" src="/JSP_bootstrap/picture/${vo.picture}">
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
								<div id="buttonbox">
									<input type="hidden" name="id" value="${vo.id}">
									<button type="button" id="delete" class="btn btn-dark">삭제</button>
									<button type="button" class="btn btn-primary" onclick="location.href='MemberUpdate.do?id=${vo.id}'">수정</button>
									<button type="button" class="btn btn-primary" onclick="location.href='MemberList.do'">목록</button>
								</div>
							</form>
						</div>
						<br>
						<!-- /.card-body -->
					</div>
					<!-- /.card -->
				</div>
				<div class="col-2"></div>
			</div>
		</div>
	</div>
</div>
</body>
