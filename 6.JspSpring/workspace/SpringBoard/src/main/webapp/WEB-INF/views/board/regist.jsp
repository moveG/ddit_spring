<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>    

<title>BoardRegist</title>

<head></head>

<body>
<div class="content-wrapper">
	<!-- Main content -->
	<section class="content-header">
		<div class="container-fluid">
			<div class="row md-2">
				<div class="col-sm-6">
					<h1 class="m-0">BoardRegist</h1>			
				</div>
				<div class="col-sm-6">
					<ol class="breadcrumb float-sm-right">
						<li class="breadcrumb-item">
							<a href="registForm.do">
								<i class="fa fa-dashboard"></i>BoardRegist
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
								<button type="button" id="registBtn" class="btn btn-primary" onclick="regist_go();return false;">등록</button>
								<button type="button" id="cancelBtn" class="btn btn-secondary" onclick="CloseWindow();">취소</button>
							</div>
						</div>
					</div>
				</div>
				<div class="card-body">
					<div class="row">
						<div class="col-md-12">	
							<form role="form" method="post" action="regist.do" name="registForm">
								<label for="title">제목:</label>
								<div class="form-group input-group">
									<input type="text" id="title" name="title" class="form-control form-control-sm" placeholder="Enter Title">
								</div>
								<label for="writer">작성자:</label>
								<div class="form-group input-group">
									<input type="text" id="writer" name="writer" class="form-control form-control-sm" placeholder="Enter Writer" value="${loginUser.id}" readonly style="background:white;">
								</div>
								<label for="content">내용:</label>
								<div class="form-group input-group" style="margin-bottom:0px;">
									<textarea id="content" name="content" class="form-control form-control-sm textarea" placeholder="Enter Content" placeholder="1~1000자 내외로 작성하세요." rows="15" style="display:none;"></textarea>
								</div>
								
								
							</form>
						</div> <!-- col-sm-12 -->
					</div> <!-- row -->
				</div> <!-- card-body -->
				<div class="card-footer"></div>
			</div>
		</div>
	</section>
</div>

<%@ include file="/WEB-INF/views/common/summernote.jsp" %>

<script>
	window.onload = function(){
   		summernote_go($('textarea#content'));
   	}	
  	
	function regist_go(){
		var form = document.registForm;
		
		if(form.title.value == ""){
			alert("제목을 입력해주세요.");
			return;
		}
		if(form.content.value == ""){
			alert("내용을 입력해주세요.");
			return;
		}
		form.submit();
	}
</script>
</body>