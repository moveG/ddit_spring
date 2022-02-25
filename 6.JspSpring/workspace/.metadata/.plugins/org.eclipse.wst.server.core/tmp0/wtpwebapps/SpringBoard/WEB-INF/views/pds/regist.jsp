<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>    

<title>PdsRegist</title>

<head></head>

<body>
<div class="content-wrapper">
	<!-- Main content -->
	<section class="content-header">
		<div class="container-fluid">
			<div class="row md-2">
				<div class="col-sm-6">
					<h1 class="m-0">PdsRegist</h1>			
				</div>
				<div class="col-sm-6">
					<ol class="breadcrumb float-sm-right">
						<li class="breadcrumb-item">
							<a href="registForm.do">
								<i class="fa fa-dashboard"></i>PdsRegist
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
							<form enctype="multipart/form-data" role="form" method="post" action="regist.do" name="registForm">
								<div class="form-group">
									<label for="title">제목:</label>
									<div class="form-group input-group">
										<input type="text" id="title" name="title" class="form-control form-control-sm" placeholder="Enter Title">
									</div>
								</div>
								<div class="form-group">
									<label for="writer">작성자:</label>
									<div class="form-group input-group">
										<input type="text" id="writer" name="writer" class="form-control form-control-sm" placeholder="Enter Writer" value="${loginUser.id}" readonly style="background:white;">
									</div>
								</div>
								<div class="form-group">
									<label for="content">내용:</label>
									<div class="form-group input-group" style="margin-bottom:0px;">
										<textarea id="content" name="content" class="form-control form-control-sm textarea" placeholder="Enter Content" placeholder="1~1000자 내외로 작성하세요." rows="15" style="display:none;"></textarea>
									</div>
								</div>
								<div class="form-group">
									<div class="card">
										<div class="card-header" style="background:#f7f7f7;border-bottom:none;">
											<button class="btn btn-sm btn-primary" onclick="addFile_go();" type="button" id="addFileBtn">파일첨부</button>
										</div>
										<div class="card-body fileInput"></div>
										<div class="card-footer"></div>
									</div>
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
   		
   		$('.fileInput').on('change', 'input[type="file"]', function(event){
    		if(this.files[0].size > 1024 * 1024 * 40){
    			alert("파일 용량은 40MB 이하만 가능합니다.");
    			this.value="";
    			$(this).click();
    			return;
    		}
    	});
   	}	
</script>

<script>
	var dataNum = 0;
	
	function addFile_go(){
		if($('input[name="uploadFile"]').length >= 5){
			alert("파일 추가는 5개 까지만 가능합니다.");
			return;
		}
		
		var div = $('<div>').addClass("inputRow").attr("data-no", dataNum);
		var input = $('<input>').attr({"type":"file", "name":"uploadFile"}).css("display", "inline");
		
		div.append(input).append("<button type='button' class='badge bg-red' onclick='remove_go(" + dataNum + ")' style='border:0;outline:0;'>X</button>");
		$('.fileInput').append(div);
		
		dataNum++;
	}
	
	function remove_go(dataNum){
		$('div[data-no="' + dataNum + '"]').remove();
	}
	
	function regist_go(){
		var files = $('input[name="uploadFile"]');
		for(var file of files){
			console.log(file.name + " : " + file.value);
			if(file.value == ""){
				alert("파일을 선택해주세요.");
				file.focus();
				file.click();
				return;
			}
		}
		
		if($("input[name='title']").val() == ""){
			alert("제목을 입력해주세요.");
			$("input[name='title']").focus();
			return;
		}
		if($("textarea[name='content']").val() == ""){
			alert("내용을 입력해주세요.");
			$("textarea[name='content']").focus();
			return;
		}
		document.registForm.submit();
	}
</script>
</body>