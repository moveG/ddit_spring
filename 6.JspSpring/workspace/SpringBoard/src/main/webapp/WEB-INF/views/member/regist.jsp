<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<title>MemberRegist</title>

<head>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<style>
	.form-group{
		margin-bottom : 10px;
	}
	.star{
	color : #dc3545;
	}
</style>
</head>

<body>
<div class="content-wrapper">
	<!-- Main content -->
	<section class="content-header"">
		<div class="container-fluid">
			<div class="row md-2">
				<div class="col-sm-6">
					<h1 class="m-0">MemberRegist</h1>			
				</div>
				<div class="col-sm-6">
					<ol class="breadcrumb float-sm-right">
						<li class="breadcrumb-item">
							<a href="registForm.do">
								<i class="fa fa-dashboard"></i>MemberRegist
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
		<div class="row">
			<div class="col-sm-2"></div>
			<div class="card col-sm-8">
				<form role="form" class="regist-form" action="regist.do" method="post">
					<div class="card-body">
						<div class="row">
							<div class="col-sm-12">	
								<input type="hidden" name="picture">
								<div id="pictureView" style="border:1px solid gray;height:170px;width:132px;margin:0px auto 12px;"></div>
								<div class="form-group input-group" style="margin-bottom:0px;">
									<span class="input-group-prepend">
										<label for="inputFile" class="btn btn-info" style="font-weight:400;">파일선택</label>
									</span>
									<input type="text" id="inputFileName" class="form-control" name="tempPicture" readOnly>
									<span class="input-group-append" style="height:38px;">											
										<button type="button" class="btn btn-info" onClick="upload_go();">업로드</button>											
									</span>
								</div>
								<label for="id"><span class="star">*</span>아이디:</label>&nbsp;&nbsp;<span id="idspan"></span>
								<div class="form-group input-group">
									<input type="text" class="form-control" id="id" placeholder="Enter ID" name="id" onKeyUp="idCheck_go();">
									<span class="input-group-append">
										<button type="button" id="idcheck" class="btn btn-success" onClick="idCheckButton_go();">중복검사</button>
									</span>
								</div>
								<div class="form-group">
									<label for="pwd"><span class="star">*</span>패스워드:</label>&nbsp;&nbsp;<span id='pwdspan'></span>
									<input type="password" class="form-control" id="pwd" placeholder="Enter password" name="pwd" onKeyUp="pwdCheck_go();" disabled>
								</div>
								<div class="form-group">
									<label for="email"><span class="star">*</span>이메일:</label>&nbsp;&nbsp;<span id='emailspan'></span>
									<input type="text" class="form-control" id="email" placeholder="Enter email" name="email" onKeyUp="emailCheck_go();" disabled>
								</div>
								<div class="form-group">
									<label for="name"><span class="star">*</span>이름:</label>
									<input type="text" class="form-control" id="name" placeholder="Enter name" name="name" onKeyUp="nameCheck_go();">
								</div>
								<label for="phone">연락처:</label>
								<div class="form-group input-group">
									<select name="phone" id="phone" class="form-control float-left">
										<option value="">Select</option>
										<option value="010">010</option>
										<option value="011">011</option>
										<option value="017">017</option>
										<option value="018">018</option>
									</select>
									<label class="float-left" style="padding:0;margin-top:4px;margin-bottom:0px;text-align:center;">&nbsp;-&nbsp;</label>										
									<input name="phone" type="text" class="form-control float-left">
									<label class="float-left" style="padding:0;margin-top:4px;margin-bottom:0px;text-align:center;">&nbsp;-&nbsp;</label>
									<input name="phone" type="text" class="form-control float-right">
									<!-- <input type="text" class="form-control" id="phone" placeholder="Enter phone-number" name="phone" onKeyUp="phoneCheck_go();"> -->
								</div>
								<label for="address">주소:</label>
								<div class="form-group input-group">
									<input type="text" class="form-control" id="address" placeholder="Enter address" name="address" readOnly>
									<span class="input-group-append">
										<button type="button" class="btn btn-success btn-append" onClick="address_go()">주소검색</button>
									</span>
								</div>
								<!-- <div class="form-group">
									<label for="regdate">입사일:</label>
									<input style="cursor:pointer;" type="date" class="form-control" id="regDate" placeholder="Enter regDate" name="regDate">
								</div> -->
								<div class="form-group">
									<label for="authority">권한:</label>
									<select name="authority" class="form-control">
										<option value="ROLE_USER">사용자</option>
										<option value="ROLE_MANAGER">운영자</option>
										<option value="ROLE_ADMIN">관리자</option>
									</select>
									<!-- <label for="register">등록인:</label>
									<input type="text" class="form-control" id="register" placeholder="Enter register" name="register"> -->	
								</div>
							</div> <!-- col-sm-12 -->
						</div> <!-- row -->
					</div> <!-- card-body -->
					<div class="card-footer">
						<div class="row">
							<div class="col-sm-12">
								<div class="float-right">
									<button type="button" class="btn btn-primary" id="regist" onclick="regist_go();return false;" disabled>등록</button>
									<button type="button" class="btn btn-secondary" id="cancel" onClick="CloseWindow();">취소</button>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
			<div class="col-sm-2"></div>
		</div>
	</section>
</div>

<form role="imageForm" action="upload/picture.do" method="post" enctype="multipart/form-data">
	<input type="file" name="pictureFile" id="inputFile" class="form-control" onchange="picture_go();" style="display:none;">
	<input type="hidden" name="oldPicture" id="oldFile" value="">
	<input type="hidden" name="checkUpload" value="0">
</form>

<script>
	var formData = "";
	
	function picture_go() {
		formData = new FormData($('form[role="imageForm"]')[0]);
		var form = $('form[role="imageForm"]');
		var picture = form.find('[name=pictureFile]')[0];
		
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
					'background-repeat'   : 'no-repeat',
					'border'              : 'none'
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
				//alert("현재 사진 업로드가 불가능합니다.\n관리자에게 연락해주세요.");
				AjaxErrorSecurityRedirectHandler(error.status);
			}
		});
	}
	
	var checkedId = "";
	
	var idReg = /^[a-z]{1}[a-zA-Z0-9]{3,12}$/;
	var pwdReg = /^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[#$@!]).{8,20}$/;
	var emailReg = /^[a-zA-Z0-9-_]+@[a-zA-Z]+(\.[a-zA-Z]+){1,2}$/;
	var nameReg = /^[가-힣]{2,10}$/;
	var phoneReg = /^\d{3}-\d{3,4}-\d{4}$/;
	
	var inputId = $('input[name="id"]');
	var inputPwd = $('input[name="pwd"]');
	var inputEmail = $('input[name="email"]');
	var inputPhone = $('input[name="phone"]');
	var inputName = $('input[name="name"]');
	
	//아이디 중복검사
	function idCheckButton_go(){
		//공백 체크
		if(!inputId.val()){
			$('#idspan').html("아이디를 입력하세요.").css('color', '#dc3545');
			return false;
		}else{
			$.ajax({
				url : "idCheck.do?id=" + inputId.val().trim(),
				type : "get",
				success : function(result){
					if(result != "DUPLICATED" && idReg.test(inputId.val().trim())){
						$('#idspan').html("사용할 수 있는 아이디입니다.").css('color', '#28a745');
						$('#id').addClass('is-valid');
						$('#id').removeClass('is-invalid');
						
						$('#email').prop("disabled", false);
						$('#pwd').prop("disabled", false);
						
						checkedID = inputId.val().trim();
						$('input[name="id"]').val(inputId.val().trim());
					}else{
						$('#idspan').html("사용할 수 없는 ID입니다.").css('color', '#dc3545');
						$('#id').addClass('is-invalid');				
						$('#id').removeClass('is-valid');
						
						$('#email').prop("disabled", true);
						$('#pwd').prop("disabled", true);
						$('#regist').prop("disabled", true);

						$('input[name="id"]').focus();
					}
				},
				error : function(error){
					//alert("시스템 장애로 가입이 불가능합니다.");
					AjaxErrorSecurityRedirectHandler(error.status);
				}
			});
		}
	}
	
	//아이디 정규식
	function idCheck_go(){
		if(idReg.test(inputId.val().trim())){
			inputId.addClass('is-valid');
			inputId.removeClass('is-invalid');
			$('#idspan').html("중복검사를 해주세요.").css('color', '#28a745');
			
			$('#email').prop("disabled", true);
			$('#pwd').prop("disabled", true);
			$('#regist').prop("disabled", true);
			
			if(pwdReg.test(inputPwd.val().trim()) && emailReg.test(inputEmail.val().trim()) && $('#idspan').text() != "중복검사를 해주세요."){
				$('#regist').prop("disabled", false);
			}
		}else{
			inputId.addClass('is-invalid');
			inputId.removeClass('is-valid');
			$('#idspan').html("");
			
			$('#email').prop("disabled", true);
			$('#pwd').prop("disabled", true);
			$('#regist').prop("disabled", true);
			
			$('#regist').prop('disabled', true);
		}
	}
	
	//비밀번호 정규식
	function pwdCheck_go(){
		if(pwdReg.test(inputPwd.val().trim())){
			inputPwd.addClass('is-valid');
			inputPwd.removeClass('is-invalid');
			$('#pwdspan').html("사용할 수 있는 패스워드입니다.").css('color', '#28a745');
			
			if(idReg.test(inputId.val().trim()) && emailReg.test(inputEmail.val().trim())){
				$('#regist').prop("disabled", false);
			}
		}else{
			inputPwd.addClass('is-invalid');				
			inputPwd.removeClass('is-valid');
			$('#pwdspan').html("대소문자, 숫자, 특수문자로, 8 ~ 20자까지 가능합니다.").css('color', '#dc3545');
			
			$('#regist').prop("disabled", true);
		}
	}
	
	//이메일 정규식
	function emailCheck_go(){
		if(emailReg.test(inputEmail.val().trim())){
			inputEmail.addClass('is-valid');
			inputEmail.removeClass('is-invalid');
			$('#emailspan').html("사용할 수 있는 이메일입니다.").css('color', '#28a745');
			
			if(idReg.test(inputId.val().trim()) && pwdReg.test(inputPwd.val().trim())){
				$('#regist').prop("disabled", false);
			}
		}else{
			inputEmail.addClass('is-invalid');				
			inputEmail.removeClass('is-valid');
			$('#emailspan').html("사용할 수 없는 이메일입니다.").css('color', '#dc3545');
			
			$('#regist').prop("disabled", true);
		}
	}

	//이름 정규식
	function nameCheck_go(){
		if(nameReg.test(inputName.val().trim())){
			inputName.addClass('is-valid');
			inputName.removeClass('is-invalid');
		}else{
			inputName.addClass('is-invalid');				
			inputName.removeClass('is-valid');
		}
	}
	
	/* //전화번호 정규식
	function phoneCheck_go(){
		if(phoneReg.test(inputPhone.val().trim())){
			inputPhone.addClass('is-valid');
			inputPhone.removeClass('is-invalid');
		}else{
			inputPhone.addClass('is-invalid');				
			inputPhone.removeClass('is-valid');
		}
	} */
	
	//회원 등록
	function regist_go(){
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
		
		var flag = confirm('등록하시겠습니까?'); 
		if(flag){
			var form = $('form[role="form"]');
			form.submit();
		}else{
			return;
		}
	}
	
	//주소
	function address_go() {
		new daum.Postcode({
			oncomplete: function(data) {
				// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
				
				// 각 주소의 노출 규칙에 따라 주소를 조합한다.
				// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
				var addr = ''; // 주소 변수
				var extraAddr = ''; // 참고항목 변수
				
				//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
				if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
				    addr = data.roadAddress;
				} else { // 사용자가 지번 주소를 선택했을 경우(J)
				    addr = data.jibunAddress;
				}
				
				// 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
				if(data.userSelectedType === 'R'){
				    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
				    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
				    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
				        extraAddr += data.bname;
				    }
				    // 건물명이 있고, 공동주택일 경우 추가한다.
				    if(data.buildingName !== '' && data.apartment === 'Y'){
				        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
				    }
				    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
				    if(extraAddr !== ''){
				        extraAddr = ' (' + extraAddr + ')';
				    }
				    // 조합된 참고항목을 해당 필드에 넣는다.
				    document.getElementById("address").value = "(" + data.zonecode + ") " + addr + " " + extraAddr + " ";
				
				} else {
					document.getElementById("address").value = "(" + data.zonecode + ") " + addr + " ";
				}
				
				// readOnly 해제
				document.getElementById("address").readOnly = false;
				
				// 커서를 상세주소 필드로 이동한다.
				document.getElementById("address").focus();
			}
		}).open();
	}
</script>
</body>