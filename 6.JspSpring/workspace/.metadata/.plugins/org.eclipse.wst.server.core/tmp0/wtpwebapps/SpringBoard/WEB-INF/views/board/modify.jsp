<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<title>BoardModify</title>

<head></head>

<body>
<div class="content-wrapper">
	<!-- Main content -->
	<section class="content-header">
		<div class="container-fluid">
			<div class="row md-2">
				<div class="col-sm-6">
					<h1 class="m-0">BoardModify</h1>			
				</div>
				<div class="col-sm-6">
					<ol class="breadcrumb float-sm-right">
						<li class="breadcrumb-item">
							<a href="modify.do?bno=${boardVO.bno}">
								<i class="fa fa-dashboard"></i>BoardModify
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
								<button type="button" class="btn btn-primary" id="modifyBtn" onclick="modify_go();return false;">수정</button>
								<button type="button" class="btn btn-secondary" id="cancelBtn" onclick="history.go(-1);">취소</button>
							</div>
						</div>
					</div>
				</div>
				<div class="card-body">
					<div class="row">
						<div class="col-md-12">	
							<form role="form" method="post" action="modify.do" name="modifyForm">
								<input type="hidden" name="bno" value="${boardVO.bno}"/>
								<table class="table">
									<tr class="form-group">
										<th style="width:130px;padding:0px auto;">제목</th>
										<td style="padding:0px;vertical-align:middle;">
											<input type="text" id="title" class="form-control form-control-sm" name="title" placeholder="Enter Title" value="${boardVO.title}">
										</td>
									</tr>
									<tr class="form-group">
										<th style="width:130px;padding:0px auto;">작성자</th>
										<td style="padding:0px;vertical-align:middle;">
											<input type="text" id="writer" class="form-control form-control-sm" name="writer" value="${boardVO.writer}" style="background:white;" readonly>
										</td>
									</tr>
									<tr class="form-group">
										<td colspan="2">
											<textarea rows="3" id="content" class="form-control form-control-sm" name="content" id="content" placeholder="Enter Content">
												${fn:escapeXml(boardVO.content)}
											</textarea>
										</td>
									</tr>
								</table>
							</form>
						</div> <!-- col-sm-12 -->
					</div> <!-- row -->
				</div> <!-- card-body -->
				<div class="card-footer">
				</div>
			</div>
		</div>
	</section>
</div>

<%@ include file="/WEB-INF/views/common/summernote.jsp" %>

<script>
	window.onload = function(){
		summernote_go($('textarea#content'));
	}	

	function modify_go(){
		var formObj = document.modifyForm;
		
		if(formObj.title.value == ""){
			alert("제목을 입력해주세요.");
			return;
		}
		if(formObj.content.value == ""){
			alert("내용을 입력해주세요.");
			return;
		}
		formObj.submit();
	}
</script>
    
</body>