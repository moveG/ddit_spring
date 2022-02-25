<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<title>PdsModify</title>

<head>
	<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/bootstrap/plugins/summernote/summernote-bs4.css">
</head>

<body>
<div class="content-wrapper">
	<section class="content-header">
		<div class="container-fluid">
			<div class="row md-2">
				<div class="col-sm-6">
					<h1 class="m-0">PdsModify</h1>			
				</div>
				<div class="col-sm-6">
					<ol class="breadcrumb float-sm-right">
						<li class="breadcrumb-item">
							<a href="modifyForm.do?pno=${pdsVO.pno}">
								<i class="fa fa-dashboard"></i>PdsModify
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
								<button type="button" class="btn btn-primary" id="modifyBtn" onclick="modify_go();">수정</button>
								<button type="button" class="btn btn-secondary" id="cancelBtn" onclick="history.go(-1);">취소</button>
							</div>
						</div>
					</div>
				</div>
				
				<div class="card-body">
					<form role="form" name="modifyForm" id="modifyForm" method="post" action="modify.do" enctype="multipart/form-data">
						<input type="hidden" name="pno" value="${pdsVO.pno}"/>
						<div class="row">
							<div class="col-md-12">
								<table class="table">
									<tr class="form-group">
										<th style="width:130px;padding:0px auto;">제목</th>
										<td style="padding:0px;vertical-align:middle;">
											<input type="text" id="title" class="form-control form-control-sm" name="title" placeholder="Enter Title" value="${pdsVO.title}">
										</td>
									</tr>
									<tr class="form-group">
										<th style="width:130px;padding:0px auto;">작성자</th>
										<td style="padding:0px;vertical-align:middle;">
											<input type="text" id="writer" class="form-control form-control-sm" name="writer" value="${pdsVO.writer}" style="background:white;" readonly>
										</td>
									</tr>
									<tr class="form-group">
										<td colspan="2">
											<textarea id="content" class="form-control form-control-sm" name="content" id="content" placeholder="Enter Content">${pdsVO.content}</textarea>
										</td>
									</tr>
								</table>
							</div>
						</div>	
						
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<div class="card">
										<div class="card-header" style="background:#f7f7f7;border-bottom:none;">
											<button class="btn btn-sm btn-primary" onclick="addFile_go();" type="button" id="addFileBtn">파일첨부</button>
										</div>
										<div class="card-body fileInput">	
											<ul class="mailbox-attachments d-flex align-items-stretch clearfix">
											<c:forEach items="${pdsVO.attachList}" var="attach">
												<li class="attach-item thumb${attach.ano}" file-name="${attach.fileName}" target-ano="${attach.ano}">
													<div class="mailbox-attachment-info">
														<div class="row">
															<div class="col-md-12">
																<a class="mailbox-attachment-name" name="attachedFile" attach-fileName="${attach.fileName}" attach-no="${attach.ano}" href="<%=request.getContextPath()%>/pds/getFile.do?ano=${attach.ano}">
																	<i class="fas fa-paperclip"></i>
																	${attach.fileName}
																</a>
																<button type="button" onclick="removeFile_go('thumb${attach.ano}');return false;" style="border:0;outline:0;" class="btn btn-danger btn-xs btn-success float-right">X</button>
															</div>
														</div>
													</div>
												</li>
											</c:forEach>
											</ul>
										</div>
									</div>
								</div>
							</div>
						</div>
					</form>
				</div>
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
</script>

<script>
	function removeFile_go(className){
		var li = $('li.' + className);
		if(!confirm("" + " 파일을 정말 삭제하시겠습니까?")){
			return;
		}
		li.remove();
		
		var input = $('<input>').attr({
			"type" : "hidden",
			"name" : "deleteFile",
			"value" : li.attr("target-ano")
		});
		
		$('form[role="form"]').prepend(input);
	}
	
	var dataNum = 0;
	
	function addFile_go(){
		var attachedFile = $('a[name="attachedFile"]').length;	//기존파일 개수
		var inputFiles = $('input[name="uploadFile"]').length;	//추가파일 개수
		var attachCount = attachedFile + inputFiles;			//전체파일 개수
		if(attachCount >= 5){
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
	
	function modify_go(){
		var form = document.modifyForm;
		
		//제목 유효성 확인
		if($("input[name='title']").val() == ""){
			alert("제목을 입력해주세요.");
			$("input[name='title']").focus();
			return;
		}
		
		//내용 유효성 확인
		if($("textarea[name='content']").val() == ""){
			alert("내용을 입력해주세요.");
			$("textarea[name='content']").focus();
			return;
		}
		
		//파일첨부 확인
		var files = $('input[name="uploadFile"]');
		for(var file of files){
			console.log(file.name + " : " + file.value);
			if(file.value == ""){
				alert("파일을 선택해주세요.");
				file.focus();
				return false;
			}
		}
		form.submit();
	}
</script>
</body>
