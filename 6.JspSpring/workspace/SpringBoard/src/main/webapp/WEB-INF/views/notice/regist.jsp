<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<title>NoticeRegist</title>

<head></head>

<body>
<div class="content-wrapper">
	<!-- Main content -->
	<section class="content-header">
		<div class="container-fluid">
			<div class="row md-2">
				<div class="col-sm-6">
					<h1 class="m-0">NoticeRegist</h1>			
				</div>
				<div class="col-sm-6">
					<ol class="breadcrumb float-sm-right">
						<li class="breadcrumb-item">
							<a href="registForm.do">
								<i class="fa fa-dashboard"></i>NoticeRegist
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
								<button type="button" class="btn btn-primary" onclick="regist_go();return false;">등록</button>
								<button type="button" class="btn btn-secondary" onclick="CloseWindow();">취소</button>
							</div>
						</div>
					</div>
				</div>
				<div class="card-body">
					<div class="row">
						<div class="col-md-12">	
							<form role="form" class="regist-form" action="regist.do" method="post" name="registForm">
								<label for="title">제목:</label>&nbsp;&nbsp;<span id="id-span"></span>
								<div class="form-group input-group">
									<input type="text" id="title" name="title" class="form-control form-control-sm" placeholder="Enter Title">
								</div>
								<label for="writer">작성자:</label>&nbsp;&nbsp;<span id="writer-span"></span>
								<div class="form-group input-group">
									<input type="text" id="writer" name="writer" class="form-control form-control-sm" placeholder="Enter Writer" value="${loginUser.id}" readonly style="background:white;">
								</div>
								<label for="content">내용:</label>
								<div class="form-group input-group" style="margin-bottom:0px;">
									<textarea id="content" name="content" class="form-control form-control-sm textarea" placeholder="Enter Content" placeholder="1~1000자 내외로 작성하세요." rows="15" style="display:none;"></textarea>
								</div>
								<label for="point">중요도:</label>
								<div class="form-group input-group">
									<select name="point" class="form-control form-control-sm">
										<option value="0">Low</option>
										<option value="1">High</option>
									</select>
								</div>
								<label for="startDate">게시 시작일:</label>
								<div class="form-group input-group">
									<input type="date" class="form-control form-control-sm" id="startDate" name="startDate">
								</div>
								<label for="endDate">게시 종료일:</label>
								<div class="form-group input-group">
									<input type="date" class="form-control form-control-sm" id="endDate" name="endDate">
								</div>
								<div class="form-group input-group">
									<div class="custom-control custom-checkbox">
										<input class="custom-control-input" type="checkbox" id="customCheckbox1" value="" onchange="check_go();">
										<label for="customCheckbox1" class="custom-control-label">영구게시 여부</label>
									</div>
								</div>
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
	window.onload = function(){	//js를 아직 못 읽기 때문에 window.onload를 해줌
		summernote_go($('textarea#content'));
	}	

	function regist_go(){
		if(!$('input[name="title"]').val()){
			alert("제목을 입력해주세요.");
			return;
		}
		
		if(!$('textarea[name="content"]').val()){
			alert("내용을 입력해주세요.");
			return;
		}

		if(!$('input[name="startDate"]').val()){
			alert("시작일을 입력해주세요.");
			return;
		}
		
		var flag = confirm('등록하시겠습니까?'); 
		if(flag){
			var form = document.registForm;
			form.submit();
		}else{
			return;
		}
	}
	
	var endDate = "";
	function check_go(){
		if($('#customCheckbox1').prop('checked')){
			endDate = $('input[name="endDate"]').val();
			$('input[name="endDate"]').val("");
			$('input[name="endDate"]').prop('readonly', true);
        }else{
        	$('input[name="endDate"]').val(endDate);
        	endDate = "";
        	$('input[name="endDate"]').prop('readonly', false);
        }
	}
</script>
</body>