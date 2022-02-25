<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<title>공지 상세</title>
    
<head></head>

    
<body>
  <!-- Content Wrapper. Contains page content -->
  <div >
	<section class="content-header">
	  	<div class="container-fluid">
	  		<div class="row md-2">
	  			<div class="col-sm-6">
	  				<h1>상세보기</h1>  				
	  			</div>
	  			<div class="col-sm-6">
	  				<ol class="breadcrumb float-sm-right">
			        <li class="breadcrumb-item">
			        	<a href="list.do">
				        	<i class="fa fa-dashboard"></i>공지사항
				        </a>
			        </li>
			        <li class="breadcrumb-item active">
			        	상세보기
			        </li>		        
	    	  </ol>
	  			</div>
	  		</div>
	  	</div>
	</section>
	 
   
      <!-- Main content -->
    <section class="content container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="card card-outline card-info">
					<div class="card-header">
						<h3 class="card-title">상세보기</h3>
						<div class="card-tools">
							<button type="button" id="modifyBtn" class="btn btn-warning" onclick="modify_go();">MODIFY</button>						
						    <button type="button" id="removeBtn" class="btn btn-danger" onclick="remove_go();">REMOVE</button>
						    <button type="button" id="listBtn" class="btn btn-primary" onclick="CloseWindow();">CLOSE</button>
					    </div>
					</div>
					<div class="card-body">
						<div class="form-group col-sm-12">
							<label for="title">제 목</label>
							<input type="text" class="form-control" id="title" readonly disabled value="${notice.title}" />							
						</div>
						<div class="row">	
							<div class="form-group col-sm-4" >
								<label for="writer">작성자</label>
								<input type="text" class="form-control" id="writer" readonly value="${notice.writer}"/>
							</div>		
							
							<div class="form-group col-sm-4" >
								<label for="regDate">작성일</label>
								<input type="text" class="form-control" id="regDate" readonly 
									value="<fmt:formatDate value="${notice.regDate}" pattern="yyyy-MM-dd" />" />
							
							</div>
							<div class="form-group col-sm-4" >
								<label for="viewcnt">조회수</label>
								<input type="text" class="form-control" id="viewcnt" readonly value="${notice.viewcnt}"/>
							</div>
						</div>		
						<div class="form-group col-sm-12">
							<label for="content">내 용</label>
							<div id="content">${notice.content}</div>
							<!-- <textArea cols="80" rows="50"> </textArea> -->	
						</div>
												
					</div>													
				</div><!-- end card -->				
			</div><!-- end col-md-12 -->
		</div><!-- end row  -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

<form role="form" method="post">
	<input type="hidden" name="nno" value="${notice.nno}" />
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
		var answer = confirm("정말 삭제하시겠습니까?");
		if(answer) formObj.attr('action', 'remove.do').submit();
	}
	
	if(${param.from eq 'modify'}) {
		alert("수정되었습니다.");
		window.opener.location.reload();
	}
	
	if(${from eq 'remove'}) {
		alert("삭제되었습니다.");
		window.opener.location.reload();
		window.close();
	}
</script>
</body>