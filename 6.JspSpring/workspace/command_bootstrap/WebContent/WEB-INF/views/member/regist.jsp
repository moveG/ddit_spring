<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<title>회원등록</title>

<body>

	<!-- Content Wrapper. Contains page content -->
<div>
  	 <section class="content-header">
	  	<div class="container-fluid">
	  		<div class="row md-2">
	  			<div class="col-sm-6">
	  				<h1>회원등록</h1>  				
	  			</div>
	  			<div class="col-sm-6">
	  				<ol class="breadcrumb float-sm-right">
				        <li class="breadcrumb-item">
				        	<a href="#">
					        	<i class="fa fa-dashboard">회원관리</i>
					        </a>
				        </li>
				        <li class="breadcrumb-item active">
				        	등록
				        </li>		        
	    	  		</ol>
	  			</div>
	  		</div>
	  	</div>
  	</section>
	<!-- Main content -->
	<section class="content register-page" style="justify-content:start;">
		<div class="register-box">
			<div class="login-logo">
    			<a href=""><b>회원 등록</b></a>
  			</div>
			<!-- form start -->
			<div class="card">				
				<div class="register-card-body">
					<form role="form" class="form-horizontal" action="regist.do" method="post">						
						<input type="hidden" name="picture" />
						<div class="input-group mb-3">
							<div class="mailbox-attachments clearfix" style="text-align: center;">
								<div class="mailbox-attachment-icon has-img" id="pictureView" style="border: 1px solid green; height: 200px; width: 140px; margin: 0 auto;"></div>
								<div class="mailbox-attachment-info">
									<div class="input-group input-group-sm">
										<label for="inputFile" class=" btn btn-warning btn-sm btn-flat input-group-addon">파일선택</label>
										<input type="text" id="inputFileName" class="form-control" name="tempPicture" disabled/>
										<span class="input-group-append-sm">											
											<button type="button" class="btn btn-info btn-sm btn-append" onclick="upload_go();">업로드</button>											
										</span>
									</div>
								</div>
							</div>
							<br>
						  </div>	
						  <div class="form-group row">
							 <label for="id" class="col-sm-3" style="font-size:0.9em;" >
							 	<span style="color:red;font-weight:bold;">*</span>아이디</label>	
							<div class="col-sm-9 input-group input-group-sm">
								<input name="id" type="text" class="form-control" id="id" placeholder="13글자 영문자,숫자 조합">
								<span class="input-group-append-sm">	
									<button type="button" onclick="idCheck_go();" class="btn btn-info btn-sm btn-append">중복확인</button>
								</span>								
							</div>								
						</div>
						<div class="form-group row">
							<label for="pwd" class="col-sm-3" style="font-size:0.9em;">
								<span style="color:red;font-weight:bold;">*</span>패스워드</label>
							<div class="col-sm-9 input-group-sm">								
								<input class="form-control" name="pwd" type="password" class="form-control" id="pwd"
										placeholder="20글자 영문자,숫자,특수문자 조합" />
							</div>
							
						</div>
						<div class="form-group row">
							<label for="name" class="col-sm-3" style="font-size:0.9em;">
								<span style="color:red;font-weight:bold;">*</span>이 름</label>
							<div class="col-sm-9 input-group-sm">								
								<input class="form-control" name="name" type="text" class="form-control" id="name"
										placeholder="이름을 입력하세요"
										onkeyup="this.value=this.value.trim();" />
							</div>
							
						</div>		
						<div class="form-group row">
							<label for="authority" class="col-sm-3" style="font-size:0.9em;" >권 한</label>
							<div class="col-sm-9">
								<select name="authority" class="form-control" style="font-size:0.9em;">
									<option value="ROLE_USER">사용자</option>
									<option value="ROLE_MANAGER">운영자</option>
									<option value="ROLE_ADMIN">관리자</option>
								</select>
							</div>
						</div>					
						<div class="form-group row">
							<label for="email" class="col-sm-3" style="font-size:0.9em;">이메일</label>
							<div class="col-sm-9 input-group-sm">
								<input name="email" type="email" class="form-control" id="email"
										placeholder="example@naver.com">
							</div>
						</div>
						<div class="form-group row">
							<label for="phone" class="col-sm-3 control-label">전화번호</label>
							<div class="col-sm-9">	
								<div class="input-group-sm">
									<select style="width:75px;" name="phone" id="phone" class="form-control float-left">
										<option value="">-선택-</option>
										<option value="010">010</option>
										<option value="011">011</option>
										<option value="017">017</option>
										<option value="018">018</option>
									</select>							
									<label class="float-left" style="padding: 0; text-align: center;">&nbsp;-&nbsp;</label>										
									<input style="width:68px;" name="phone" type="text" class="form-control float-left" />
									<label class="float-left" style="padding: 0; text-align: center;">&nbsp;-</label>
									<input style="width:68px;" name="phone" type="text" class="form-control float-right" />						
								</div>
							</div>
						</div>
						
						<div class="card-footer">
							<div class="row">								
								<div class="col-sm-6">
									<button type="button" id="registBtn" onclick="regist_go();return false;" class="btn btn-info">등록</button>
							 	</div>
							 	<div class="col-sm-6">
									<button type="button" id="cancelBtn" onclick="CloseWindow();"
										class="btn btn-secondary float-right">취소</button>
								</div>
							</div>
						</div>
					</form>					
				</div><!-- register-card-body -->
			</div>
		</div>
	</section>		<!-- /.content -->
</div>
<!-- /.content-wrapper -->
<form role="imageForm" method="post" enctype="multipart/form-data">
	<input type="file" name="pictureFile" id="inputFile" class="form-control" onchange="picture_go();" style="display:none;">
	<input type="hidden" name="oldPicture" id="oldFile" value="">
	<input type="hidden" name="checkUpload" value="0">
</form>

<script>
	var formData = "";

	function picture_go() {
		//alert("file change!");
		
		//파일을 가져오려면  순수 DOM을 꺼내야 하므로 JQUERY를 자바스크립트로 변경
		//JQUERY의 0번째 주소에 자바스크립트 순수 객체가 들어있음 
		formData = new FormData($('form[role="imageForm"]')[0]);
		var form = $('form[role="imageForm"]');
		var picture = form.find('[name=pictureFile]')[0];
		
		//여기서부터 계속 자바스크립트 사용
		
		//alert(picture.value);	//파일경로까지 가져옮
		//파일의 메타데이터는 0번지에 저장됨
		//alert(picture.files[0].name);	//파일명만 가져옮
		//alert(picture.files[0].size);	//파일사이즈만 가져옮
		
		//이미지 확장자 jpg 확인
		var fileFormat = picture.value.substr(picture.value.lastIndexOf(".") + 1).toUpperCase();
		if(!(fileFormat == "JPG" || fileFormat == "JPEG")){
			alert("이미지는 jpg/jpeg 형식만 가능합니다.");
			picture.value = "";
			return;
		}
		
		//이미지 파일 용량 체크
		if(picture.files[0].size > 1024*1024*1){
			alert("사진의 용량은 1MB 이하만 가능합니다.");
			picture.value="";
			return;
		}
		
		//업로드 확인 변수 초기화(사진 변경)
		form.find('[name="checkUpload"]').val(0);
		
		document.getElementById('inputFileName').value = picture.files[0].name;
		
		if(picture.files && picture.files[0]){
			var reader = new FileReader();
			reader.onload = function(e){
				$('div#pictureView').css({
					'background-image'    : 'url(' + e.target.result + ')',
					'background-position' : 'center',
					'background-size'     : 'cover',
					'background-repeat'   : 'no-repeat'
				});
			}
			reader.readAsDataURL(picture.files[0]);
		}
	}
	
	function upload_go(){
		//alert("upload btn click!");
		
		//파일 유무 확인
		if(!$('input[name="pictureFile"]').val()){
			alert("사진을 선택하세요.");
			$('input[name="pictureFile"]').click();
			return;
		}
		
		//업로드 유무 확인
		if($('input[name="checkUpload"]').val() == 1){
			alert("이미 업로드된 사진입니다.");
			return;
		}
		
		//이미지 업로드
		$.ajax({
			url : "picture.do",
			data : formData,
			type : "post",
			processData : false,
			contentType : false,
			success : function(data){
				//업로드 확인 변수 세팅
				$('input[name=checkUpload]').val(1);
				//저장된 파일명 저장
				$('input#oldFile').val(data);	//변경시 삭제될 파일명
				$('form[role="form"] input[name="picture"]').val(data);

				alert("사진 업로드가 완료되었습니다.");
			},
			error : function(error){
				alert("현재 사진 업로드가 불가능합니다.\n관리자에게 연락해주세요.");
			}
		});
	}
	
	var checkedID = "";
	function idCheck_go(){
		//alert("idCheck btn click!");
		var input_ID = $('input[name="id"]');
		
		if(!input_ID.val()){
			alert("아이디를 입력하시오.");
			input_ID.focus();
			return;
		}else{
			//아이디는 4~12자의 영문자와 숫자로만 입력
			var reqID = /^[a-z]{1}[a-zA-Z0-9]{3,12}$/;
			if(!reqID.test($('input[name="id"]').val())){
				alert("아이디의 첫글자는 영소문자이며,\n4~13자의 영문자와 숫자로만 입력해야 합니다.");
				$('input[name="id"]').focus();
				return;
			}
		}
		
		$.ajax({
			url : "idCheck.do?id=" + input_ID.val().trim(),
			type : "get",
			success : function(result){
				if(result == "DUPLICATED"){
					alert("중복된 아이디입니다.");
					$('input[name="id"]').focus();
				}else{
					alert("사용가능한 아이디입니다.");
					checkedID = input_ID.val().trim();
					$('input[name="id"]').val(input_ID.val().trim());
				}
			},
			error : function(error){
				alert("시스템 장애로 가입이 불가능합니다.");
			}
		});
	}
	
	function regist_go(){
		//alert("regist btn click!");
		var uploadCheck = $('input[name="checkUpload"]').val();
		if(uploadCheck == "0"){
			alert("사진 업로드는 필수입니다.");
			return;
		}
		
		if(!$('input[name="id"]').val()){
			alert("아이디는 필수입니다.");
			return;
		}
		
		if($('input[name="id"]').val() != checkedID){
			alert("아이디 중복확인이 필요합니다.");
			return;
		}
		
		if(!$('input[name="pwd"]').val()){
			alert("패스워드는 필수입니다.");
			return;
		}
		
		if(!$('input[name="email"]').val()){
			alert("이메일은 필수입니다.");
			return;
		}
		
		if(!$('input[name="name"]').val()){
			alert("이름은 필수입니다.");
			return;
		}
		
		var form = $('form[role="form"]');
		form.submit();
	}
</script>
</body>