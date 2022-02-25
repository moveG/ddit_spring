<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<title>BoardDetail</title>

<head></head>

<body>
<div class="content-wrapper">
	<!-- Main content -->
	<section class="content-header">
		<div class="container-fluid">
			<div class="row md-2">
				<div class="col-sm-6">
					<h1 class="m-0">BoardDetail</h1>			
				</div>
				<div class="col-sm-6">
					<ol class="breadcrumb float-sm-right">
						<li class="breadcrumb-item">
							<a href="detail.do?bno=${boardVO.bno}">
								<i class="fa fa-dashboard"></i>BoardDetail
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
									<td>${boardVO.title}</td>
								</tr>
								<tr>
									<th style="width:130px;">작성자</th>
									<td>${boardVO.writer}</td>
								</tr>
								<tr>
									<th style="width:130px;">등록일</th>
									<td>
										<fmt:formatDate value="${boardVO.regDate}" pattern="yyyy-MM-dd"/>
									</td>
								</tr>
								<tr>
									<th style="width:130px;">조회수</th>
									<td>${boardVO.viewCnt}</td>
								</tr>
								<tr>
									<td colspan="2" style="height:250px;">${boardVO.content}</td>
								</tr>
								
							</table>
						</div> <!-- col-sm-12 -->
					</div> <!-- row -->
				</div> <!-- card-body -->
			</div>
		</div>
	</section>
	
	<section class="content">
		<div class="container-fluid">
			<div class="card">
				<div class="card-header" style="background:#f7f7f7;padding-top:15px;padding-bottom:0px;border-bottom:none;">
					<div class="row">
						<div class="col-md-12">
							<div class="form-group input-group">
								<input type="text" class="form-control" placeholder="Enter Reply" id="newreplyText" style="display:inline-block;">
								<span class="input-group-append">
									<button type="button" class="btn btn-primary btn-append" id="replyAddBtn" onclick="replyRegist_go();">등록</button>						
								</span>
							</div>
						</div>
					</div>
				</div>
				<div class="card-body" style="padding-right:0px;">
					<div class="row">
						<div class="col-md-12">	
							<div class="timeline">
								<div class="time-label" id="repliesDiv">
								</div>
							</div>
						</div> <!-- col-sm-12 -->
					</div> <!-- row -->
				</div> <!-- card-body -->
				<div class="card-footer" style="background:#f7f7f7;">
					<div class="row">
						<div class="col-md-12">
							<div class='text-center'>
								<ul id="pagination" class="pagination pagination-sm justify-content-center m-0"></ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</div>

<div id="modifyModal" class="modal modal-default fade" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" style="display:none;"></h4>
				<button type="button" class="close" data-dismiss="modal">&times;</button>        
			</div>
			<div class="modal-body" data-rno>
				<p><input type="text" id="replyText" class="form-control"></p>
			</div>
			<div class="modal-footer" style="background:#f7f7f7;">
				<button type="button" class="btn btn-danger" id="replyDelBtn" onclick="replyRemove_go();">삭제</button>
				<button type="button" class="btn btn-primary" id="replyModBtn" onclick="replyModify_go();">수정</button>
				<button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
			</div>
		</div>
	</div>
</div>

<form role="form">
	<input type="hidden" name="bno" value="${boardVO.bno}">
</form>

<script>
	var formObj = "";
	window.onload = function(){
		formObj = $("form[role='form']");
	}
	
	function modify_go(){
		var formObj = $("form[role='form']");
		formObj.attr({
			'action' : 'modifyForm.do',
			'method' : 'post'	
		});
		formObj.submit();
		
	}
	
	function remove_go(){
		var formObj = $("form[role='form']");
		var answer = confirm("삭제하시겠습니까?");
		if(answer) {
			formObj.attr('action', 'remove.do');
			formObj.attr('method', 'post');
			formObj.submit();
		}
	}
</script>
<%@ include file="./reply_js.jsp" %>
</body>