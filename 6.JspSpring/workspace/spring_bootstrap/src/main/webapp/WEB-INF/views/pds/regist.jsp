<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>    

<title>자료등록</title>

<head></head>

<body>
  <!-- Content Header (Page header) -->
    <section class="content-header">
    	<div class="container-fluid">
    		<div class="row mb-2">
    			<div class="col-sm-6">
	      			<h1>자료실</h1>
	      		</div>	      		
	    	
	       		
	       		<div class="col-sm-6">
			      <ol class="breadcrumb float-sm-right">
			        <li class="breadcrumb-item"><a href="list.do"><i class="fa fa-dashboard"></i>자료실</a></li>
			        <li class="breadcrumb-item active">자료등록</li>		        
			      </ol>
		      	</div>
	     	</div>	     	
      	</div>
    </section>

    <section class="content container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="card card-outline card-info">
					<div class="card-header">
						<div class="row">
							<div class="col-md-12">
								<div class="float-right">
									<!-- <h4>글등록</h4> -->
									<button type="button" class="btn btn-primary" id="registBtn" onclick="regist_go();">등 록</button>
									<button type="button" class="btn btn-secondary" id="cancelBtn" onclick="CloseWindow();">취 소</button>
								</div>
							</div>
						</div>
					</div><!--end card-header  -->
					<div class="card-body">
						<form enctype="multipart/form-data" role="form" method="post" action="regist.do" name="registForm">
							<div class="form-group">
								<label for="writer">작성자</label> 
								<input type="text" id="writer" readonly
									name="writer" class="form-control" value="${loginUser.id }">
							</div>
							<div class="form-group">
								<label for="title">제 목</label> 
								<input type="text" id="title"
									name='title' class="form-control" placeholder="제목을 쓰세요">
							</div>
							<div class="form-group">
								<label for="content">내 용</label>
								<textarea class="form-control" name="content" id="content" rows="5"
									placeholder="1000자 내외로 작성하세요."></textarea>
							</div>
							<div class="form-group">								
								<div class="card card-outline card-success">
									<div class="card-header">
										<h5 style="display:inline;line-height:40px;">첨부파일 : </h5>
										&nbsp;&nbsp;<button class="btn btn-xs btn-primary"
										onclick="addFile_go();"	type="button" id="addFileBtn">Add File</button>
									</div>									
									<div class="card-footer fileInput">
									</div>
								</div>
							</div>
						</form>
					</div><!--end card-body  -->
					<div class="card-footer">
					</div><!--end card-footer  -->
				</div><!-- end card -->				
			</div><!-- end col-md-12 -->
		</div><!-- end row -->
    </section>
    
	<%@ include file="/WEB-INF/views/common/summernote.jsp" %>
    <script>
    	window.onload = function(){	//js를 아직 못 읽기 때문에 window.onload를 해줌
    		summernote_go($('textarea#content'));
			
    		//첨부파일버튼 삭제 - 이벤트핸들러 방식 
    		/* $('div.fileInput').on('click', 'div.inputRow > button', function(event){
	    		$(this).parent('div.inputRow').remove();
	    	}); */
	    	
	    	//파일은 밖에서 다룰 수 없기 때문에 펑션콜 방식보다는 이벤트핸들러 방식으로 다뤄야한다.
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
    	
    	//첨부파일 버튼추가(5개까지 제한)
    	function addFile_go(){
    		//alert("add file");
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
    				alert("파일을 선택하세요.");
    				file.focus();
    				file.click();
    				return;
    			}
    		}
    		
    		if($("input[name='title']").val() == ""){	//form.title.value
    			alert("제목은 필수입니다.");
    			$("input[name='title']").focus();
    			return;
    		}
    		document.registForm.submit();
    	}
    </script>
</body>