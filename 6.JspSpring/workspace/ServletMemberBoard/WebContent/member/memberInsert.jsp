<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
		<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/memberInsert.css">
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
		
		<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
		
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>회원 등록 페이지</title>
		
		<script>
			$(function(){
				idvalue = "";
				idreg = /^[a-z][a-zA-Z0-9]{3,11}$/;
				pwdvalue = "";
				pwdreg = /^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[#$@!]).{8,20}$/;
				emailvalue = "";
				emailreg = /^[a-zA-Z0-9-_]+@[a-zA-Z]+(\.[a-zA-Z]+){1,2}$/;
				
				//아이디 정규식
				$('#id').on('keyup', function(){
					idvalue= $(this).val();
										
					if(idreg.test(idvalue) == true){
						$(this).css('border', '2px solid blue');
						
						if(pwdreg.test(pwdvalue) == true && emailreg.test(emailvalue) == true){
							$('#insert').prop("disabled", false);
						}
					}else if(idreg.test(idvalue) == false){
						$(this).css('border', '2px solid red');
						
						$('#insert').prop('disabled', true);
					}
				})
				
				//아이디 공백 체크
				$('#idcheck').on('click', function(){
					idvalue = $('#id').val();
					
					//공백 체크
					if(idvalue.length < 1){
						$('#idspan').html("아이디를 입력하세요.").css('color', 'red');
						return false;
					}else{
						$.ajax({
					         url : "/ServletMemberBoard/IDCheck.do",
					         type : 'get',
					         data : {'id' : idvalue},
					         dataType : 'json',
					         success : function(res){
					            if(res.flag == "사용" && idreg.test(idvalue) == true){
									$('#idspan').html("사용할 수 있는 아이디입니다.").css('color', 'blue');
									$('#id').css('border', '2px solid blue');
									
									$('#email').prop("disabled", false);
									$('#pwd').prop("disabled", false);
					            }else{
									$('#idspan').html("사용할 수 없는 ID입니다.").css('color', 'red');
									$('#id').css('border', '2px solid red');
									
									$('#email').prop("disabled", true);
									$('#pwd').prop("disabled", true);
									$('#insert').prop("disabled", true);
					            }
					         },
					         error : function(xhr){
					            alert('상태 : ' + xhr.status);
					         }
					      })
					}
				})
				
				//이름 정규식
				$('#name').on('keyup', function(){
					namevalue = $(this).val().trim();
					namereg = /^[가-힣]{2,10}$/;
					
					if(namereg.test(namevalue)){
						$(this).css('border', '2px solid blue');
					}else{
						$(this).css('border', '2px solid red');
					}
				})
				
				//비밀번호 정규식
				$('#pwd').on('keyup', function(){
					pwdvalue = $(this).val().trim();
					
					if(pwdreg.test(pwdvalue) == true){
						$(this).css('border', '2px solid blue');
						$('#pwdspan').html("사용할 수 있는 패스워드입니다.").css('color', 'blue');
						
						if(idreg.test(idvalue) == true && emailreg.test(emailvalue) == true){
							$('#insert').prop("disabled", false);
						}
					}else if(pwdreg.test(pwdvalue) == false){
						$(this).css('border', '2px solid red');
						$('#pwdspan').html("패스워드는 대문자, 소문자, 숫자, 특수문자를 조합하여, 8 ~ 20자까지 가능합니다.").css('color', 'red');
						
						$('#insert').prop("disabled", true);
					}
				})
				
				//전화번호 정규식
				$('#phone').on('keyup', function(){
					hpvalue = $(this).val().trim();

					
					if(hpreg.test(hpvalue)){
						$(this).css('border', '2px solid blue');
					}else{
						$(this).css('border', '2px solid red');
					}
				})
								
				//이메일 정규식
				$('#email').on('keyup', function(){
					emailvalue = $(this).val().trim();
					
					if(emailreg.test(emailvalue) == true){
						$(this).css('border', '2px solid blue');
						$('#emailspan').html("사용할 수 있는 이메일입니다.").css('color', 'blue');
						
						if(idreg.test(idvalue) == true && pwdreg.test(pwdvalue) == true){
							$('#insert').prop("disabled", false);
						}
					}else if(emailreg.test(emailvalue) == false){
						$(this).css('border', '2px solid red');
						$('#emailspan').html("사용할 수 없는 이메일입니다.").css('color', 'red');
						
						$('#insert').prop("disabled", true);
					}
				})
				
				$('#insert').on('click', function(){
					var flag = confirm('등록하시겠습니까?'); 
					if(flag){
						$('#insertform').submit();
					}else{
						return;
					}
				})
			})
		</script>
	</head>
	<body id="body">
		<div class="container">
			<h2 style="cursor:pointer;" onclick="location.href='MemberInsert.do'">회원 등록 페이지</h2>
			<br>
			<form id="insertform" action="MemberInsert.do" method="post">
				<div class="form-group">
					<label for="id"><span class="star">*</span>아이디:</label>
					<br>
					<input type="text" class="form-control" id="id" placeholder="Enter ID" name="id">
					&nbsp;&nbsp;
					<button id="idcheck" type="button" class="btn btn-success">중복검사</button>
					&nbsp;&nbsp;<span id="idspan"></span>
				</div>
				
				<div class="form-group">
					<label for="email"><span class="star">*</span>이메일:</label>
					<br>
					<input type="text" class="form-control" id="email" placeholder="Enter email" name="email" disabled>
					&nbsp;&nbsp;
					<span id='emailspan'></span>
				</div>
				<div class="form-group">
					<label for="pwd"><span class="star">*</span>패스워드:</label>
					<br>
					<input type="password" class="form-control" id="pwd" placeholder="Enter password" name="pwd" disabled>
					&nbsp;&nbsp;
					<span id='pwdspan'></span>
				</div>
				<div class="form-group">
					<label for="name">이름:</label>
					<br>
					<input type="text" class="form-control" id="name" placeholder="Enter name" name="name">
				</div>
				<div class="form-group">
					<label for="phone">연락처:</label>
					<br>
					<input type="text" class="form-control" id="phone" placeholder="Enter phone-number" name="phone">
				</div>
				<div class="form-group">
					<label for="address">주소:</label>
					<br>
					<input type="text" class="form-control" id="address" placeholder="Enter address" name="address" readOnly>
					&nbsp;&nbsp;
					<button type="button" class="btn btn-success" onclick="sample6_execDaumPostcode()">주소검색</button>
				</div>
				<div class="form-group">
					<label for="regdate">입사일:</label>
					<br>
					<input style="cursor:pointer;" type="date" class="form-control" id="regdate" placeholder="Enter regdate" name="regdate">
				</div>
				<div class="form-group">
					<label for="picture">사진:</label>
					<br>
					<div class="custom-file mb-3">
						<input style="cursor:pointer;" type="file" class="form-control custom-file-input" id="picture" placeholder="Enter picture" name="picture">
						<label class="custom-file-label" for="picture">Choose file</label>
					</div>
				</div>
				<div class="form-group">
					<label for="register">등록자:</label>
					<br>
					<input type="text" class="form-control" id="register" placeholder="Enter register" name="register">
				</div>
				<button id="insert" type="button" class="btn btn-primary" disabled>등록</button>
				<button type="button" class="btn btn-primary" onclick="location.href='MemberList.do'">취소</button>
			</form>
		</div>
		
		<script>
			$(".custom-file-input").on("change", function() {
				var picture = $(this).val().split("\\").pop();
				$(this).siblings(".custom-file-label").addClass("selected").html(picture);
			});
		</script>
		
		<script>
			function sample6_execDaumPostcode() {
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
	</body>
</html>