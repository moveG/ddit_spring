<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  

<title>MemberModify</title>

<head>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<style>
	.table{
	margin-bottom : 0px;
	}
	.table td{
		vertical-align : middle;
		height : 49px;
	}
	.item{
		width : 150px;
	}
</style>
</head>

<body>
<div class="content-wrapper">
	<!-- Main content -->
	<section class="content-header">
		<div class="container-fluid">
			<div class="row md-2">
				<div class="col-sm-6">
					<h1 class="m-0">MemberModify</h1>			
				</div>
				<div class="col-sm-6">
					<ol class="breadcrumb float-sm-right">
						<li class="breadcrumb-item">
							<a href="modifyForm.do?id=${memberVO.id}">
								<i class="fa fa-dashboard"></i>MemberModify
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
				<form role="form" class="modify-form" action="modify.do" method="post" enctype="multipart/form-data">
					<div class="card-body" style="padding-top:0px;padding-bottom:0px;">
						<div class="row">
							<input type="hidden" name="oldPicture" value="${memberVO.picture}">
							<input type="file" id="inputFile" onchange="changePicture_go();" name="picture" style="display:none">

							<div class="col-sm-12">	
								<table class="table table-hover">
									<tr>
										<td class="item" style="height:194.5px;border-top:0px;"></td>
										<td class="content row" style="border-top:0px;">
											<div class="col-sm-5">
												<div id="pictureView" style="border:1px solid gray;height:170px;width:132px;margin:0px;"></div>
											</div>
											<div class="col-sm-7" style="display:flex;align-items:bottom;">
												&nbsp;&nbsp;
												<div>
													<label for="inputFile" class="btn btn-success" style="cursor:pointer;margin-bottom:0px;font-weight:400;">파일선택</label>
													<input type="hidden" id="inputFileName" class="form-control" name="tempPicture" value="">
													<input type="hidden" id="picture" class="form-control" name="uploadPicture">	
												</div>
											</div>											
										</td>
									</tr>
									<tr>
										<td class="item">아이디</td>
										<td class="content" style="padding:0px 12px;">
											${memberVO.id}
											<input type="hidden" id="id" name="id" value="${memberVO.id}">
										</td>
									</tr>
									<tr>
										<td class="item">패스워드<span style="color:#dc3545;">*</span></td>
										<td class="content" style="padding:0px 12px;">
											<input class="form-control" type="password" id="pwd" placeholder="Enter password" name="pwd" value="${memberVO.pwd}" onKeyUp="pwdCheck_go();">
										</td>
									</tr>
									<tr>
										<td class="item">이름<span style="color:#dc3545;">*</span></td>
										<td class="content" style="padding:0px 12px;">
											<input class="form-control" type="text" id="name" placeholder="Enter name" name="name" value="${memberVO.name}" onKeyUp="nameCheck_go();">
										</td>
									</tr>
									<tr>
										<td class="item">이메일<span style="color:#dc3545;">*</span></td>
										<td class="content" style="padding:0px 12px;">
											<input class="form-control" type="text" id="email" placeholder="Enter email" name="email" value="${memberVO.email}" onKeyUp="emailCheck_go();">
										</td>
									</tr>
									<tr>
										<td class="item">연락처</td>
										<td class="content" style="padding:0px 12px;">
											<input class="form-control" type="text" id="phone" placeholder="Enter phoneNumber" name="phone" value="${memberVO.phone}" onKeyUp="phoneCheck_go();">
										</td>
									</tr>
									<tr>
										<td class="item">주소</td>
										<td class="content" style="padding:0px 12px;">
											<div class="form-group input-group" style="margin:0px;">
												<input type="text" id="address" class="form-control" placeholder="Enter address" name="address" value="${memberVO.address}" readonly>
												<span class="input-group-append">
													<button type="button" class="btn btn-success btn-append" onclick="address_go()">주소검색</button>
												</span>
											</div>
										</td>
									</tr>
									<tr>
										<td class="item">입사일</td>
										<td class="content" style="padding:0px 12px;">
											<fmt:formatDate value="${memberVO.regDate}" pattern="yyyy-MM-dd"/>
										</td>
									</tr>
									<tr>
										<td class="item">권한</td>
										<td class="content" style="padding:0px 12px;">
											<select name="authority" class="form-control">
												<option value="ROLE_USER" ${memberVO.authority eq 'ROLE_USER' ? 'selected' : ''}>사용자</option>
												<option value="ROLE_MANAGER" ${memberVO.authority eq 'ROLE_MANAGER' ? 'selected' : ''}>운영자</option>
												<option value="ROLE_ADMIN" ${memberVO.authority eq 'ROLE_ADMIN' ? 'selected' : ''}>관리자</option>
											</select>
										</td>
									</tr>
									<tr>
										<td class="item">상태</td>
										<td class="content" style="padding:0px 12px;">
											<select name="enabled" class="form-control">
												<option value="0" ${memberVO.enabled eq 0 ? 'selected' : ''}>퇴사</option>
												<option value="1" ${memberVO.enabled eq 1 ? 'selected' : ''}>재직</option>
												<option value="2" ${memberVO.enabled eq 2 ? 'selected' : ''}>휴직</option>
											</select>
										</td>
									</tr>
									<tr>
										<td class="item">등록자</td>
										<td class="content" style="padding:0px 12px;">${memberVO.register}</td>
									</tr>
								</table>
							</div> <!-- col-sm-12 -->
						</div> <!-- row -->
					</div> <!-- card-body -->
					<div class="card-footer">
						<div class="row">
							<div class="col-sm-12">
								<div class="float-right">
									<button type="button" id="modify" class="btn btn-primary" onclick="modify_go();">수정</button>
									<button type="button" class="btn btn-secondary" onclick="history.go(-1);">취소</button>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
			<div class="col-sm-2"></div>
		</div>
	</section>
	
	<script>
		window.onload = function(){
			MemberPictureThumb($('#pictureView')[0], '${memberVO.picture}', '<%=request.getContextPath()%>');
		}
		
		//이미지 업로드
		function changePicture_go(){
			var picture = $('input#inputFile')[0];
			
			//이미지 확장자 jpg 확인
			var fileFormat = picture.value.substr(picture.value.lastIndexOf(".") + 1).toUpperCase();
			if(!(fileFormat == "JPG" || fileFormat == "JPEG")){
				alert("이미지는 jpg/jpeg 형식만 가능합니다.");
				return;
			}
			
			//이미지 파일 용량 체크
			if(picture.files[0].size > 1024*1024*1){
				alert("사진의 용량은 1MB 이하만 가능합니다.");
				return;
			}
			
			//업로드 확인 변수 초기화(사진 변경)
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
			
			//이미지 변경 확인
			$('input[name="uploadPicture"]').val(picture.files[0].name);
		}
		
		var pwdReg = /^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[#$@!]).{8,20}$/;
		var emailReg = /^[a-zA-Z0-9-_]+@[a-zA-Z]+(\.[a-zA-Z]+){1,2}$/;
		var nameReg = /^[가-힣]{2,10}$/;
		var phoneReg = /^[0-9]{10,11}$/;
		
		var inputPwd = $('input[name="pwd"]');
		var inputEmail = $('input[name="email"]');
		var inputName = $('input[name="name"]');
		var inputPhone = $('input[name="phone"]');
		
		//패스워드 정규식
		function pwdCheck_go(){
			if(pwdReg.test(inputPwd.val().trim())){
				inputPwd.addClass('is-valid');
				inputPwd.removeClass('is-invalid');
				
				if(emailReg.test(inputEmail.val().trim())){
					$('#modify').prop("disabled", false);
				}
			}else{
				inputPwd.addClass('is-invalid');				
				inputPwd.removeClass('is-valid');
				
				$('#modify').prop("disabled", true);
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
		
		//이메일 정규식
		function emailCheck_go(){
			if(emailReg.test(inputEmail.val().trim())){
				inputEmail.addClass('is-valid');
				inputEmail.removeClass('is-invalid');
				
				if(pwdReg.test(inputPwd.val().trim())){
					$('#modify').prop("disabled", false);
				}
			}else{
				inputEmail.addClass('is-invalid');				
				inputEmail.removeClass('is-valid');
				
				$('#modify').prop("disabled", true);
			}
		}

		//전화번호 정규식
		function phoneCheck_go(){
			if(phoneReg.test(inputPhone.val().trim())){
				inputPhone.addClass('is-valid');
				inputPhone.removeClass('is-invalid');
			}else{
				inputPhone.addClass('is-invalid');				
				inputPhone.removeClass('is-valid');
			}
		}
		
		//수정 버튼
		function modify_go(){
			var flag = confirm('수정하시겠습니까?'); 
			if(flag){
				var form = $('form[role="form"]');
				
				form.submit();
			}else{
				return;
			}
		}
		
		//주소 버튼
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
					
					// readonly 해제
					document.getElementById("address").readOnly = false;
					
					// 커서를 상세주소 필드로 이동한다.
					document.getElementById("address").focus();
				}
			}).open();
		}
	</script>
</div>
</body>