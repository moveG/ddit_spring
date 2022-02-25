<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<title>NoticeDetail</title>

<head></head>

<body>
<div class="content-wrapper">
	<!-- Main content -->
	<section class="content-header">
		<div class="container-fluid">
			<div class="row md-2">
				<div class="col-sm-6">
					<h1 class="m-0">NoticeDetail</h1>			
				</div>
				<div class="col-sm-6">
					<ol class="breadcrumb float-sm-right">
						<li class="breadcrumb-item">
							<a href="detail.do?nno=${noticeVO.nno}">
								<i class="fa fa-dashboard"></i>NoticeDetail
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
		<div class="container-fluid">
			<div class="card">
				<div class="card-header with-border" style="background:#f7f7f7;border-bottom:none;">
					<div class="row">
						<div class="col-sm-12">
							<div class="float-right">
								<button type="button" class="btn btn-danger" onclick="remove_go();">삭제</button>
								<button type="button" class="btn btn-primary" onclick="modify_go();">수정</button>
								<button type="button" class="btn btn-secondary" onclick="CloseWindow();">닫기</button>
							</div>
						</div>
					</div>
				</div>
				<div class="card-body">
					<div class="row">
						<div class="col-md-12">	
							<table class="table">
								<tr>
									<th style="width:130px;">제목</th>
									<td>${noticeVO.title}</td>
								</tr>
								<tr>
									<th style="width:130px;">작성자</th>
									<td>${noticeVO.writer}</td>
								</tr>
								<tr>
									<th style="width:130px;">등록일</th>
									<td>
										<fmt:formatDate value="${noticeVO.regDate}" pattern="yyyy-MM-dd"/>
									</td>
								</tr>
								<tr>
									<th style="width:130px;">조회수</th>
									<td>${noticeVO.viewCnt}</td>
								</tr>
								<tr>
									<td colspan="2" style="height:350px;">${noticeVO.content}</td>
								</tr>
								
							</table>
						</div> <!-- col-sm-12 -->
					</div> <!-- row -->
				</div> <!-- card-body -->
				<div class="card-footer">
				</div>
			</div>
		</div>
	</section>
</div>

<form role="form">
	<input type="hidden" name="nno" value="${noticeVO.nno}">
</form>

<script>
	var formObj = "";
	window.onload = function(){
		formObj = $("form[role='form']");
	}
	
	function modify_go(){
		formObj.attr('action', 'modifyForm.do').submit();
	}

	function remove_go(){
		var flag = confirm('삭제하시겠습니까?'); 
		if(flag){
			formObj.attr('action', 'remove.do').submit();
		}else{
			return;
		}
	}
</script>
</body>