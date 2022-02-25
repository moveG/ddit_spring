<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<title>회원상세보기</title>

<body>

  <!-- Content Wrapper. Contains page content -->
  <div >
  	 <section class="content-header">
	  	<div class="container-fluid">
	  		<div class="row md-2">
	  			<div class="col-sm-6">
	  				<h1>상세페이지</h1>  				
	  			</div>
	  			<div class="col-sm-6">
	  				<ol class="breadcrumb float-sm-right">
			        <li class="breadcrumb-item">
			        	<a href="#">
				        	<i class="fa fa-dashboard">회원관리</i>
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
    <section class="content register-page">       
		<div class="register-box">
	    	<form role="form" class="form-horizontal"  method="post">
	    		<div class="register-card-header" >
	    			<h1 class="text-center">회원 상세보기</h1>
	    		</div>
	        	<div class="register-card-body" >
	            	<div class="row"  style="height:200px;">
						<div class="mailbox-attachments clearfix col-md-12" style="text-align: center;">							
							<div id="pictureView" data-id="${member.id}" style="border:1px solid green; height: 200px; width: 140px; margin: 0 auto;"></div>														
						</div>
					</div>
					<br />
	                <div class="form-group row" >
	                  <label for="inputEmail3" class="col-sm-3 control-label text-right">아이디</label>
	
	                  <div class="col-sm-9">
	                    <input name="id" type="text" class="form-control" id="inputEmail3"  value="${member.id}" readonly>
	                  </div>
	                </div>	               
	                <div class="form-group row">
	                  <label for="inputPassword3" class="col-sm-3 control-label text-right">이  름</label>
	
	                  <div class="col-sm-9">
	                    <input name="pwd" type="text" class="form-control" id="inputPassword3" value="${member.name}" readonly>
	                  </div>
	                </div>
	                 <div class="form-group row">
	                  <label for="inputPassword3" class="col-sm-3 control-label text-right">이메일</label>
	
	                  <div class="col-sm-9">
	                    <input name="email" type="email" class="form-control" id="inputPassword3" value="${member.email}" readonly>
	                  </div>
	                </div>
	                 <div class="form-group row">
	                  <label for="inputPassword3" class="col-sm-3 control-label text-right">전화번호</label>
	                  <div class="col-sm-9">   
	                  	<input name="phone" type="text" class="form-control" id="inputPassword3" value="${member.phone}" readonly>	                
	                  </div>                  
	                </div>               
	              </div>  
		          <div class="card-footer" style="padding:5px 0;" >
		          		<div class="row">
		          	<%-- 	<c:if test="${loginUser.id eq member.id }"> --%>
			          		<div class="col-sm-3 text-center">
			          			<button type="button" onclick="location.href='modifyForm.do?id=${member.id}';" id="modifyBtn" class="btn btn-warning ">수 정</button>
			          			<%-- <c:if test="${sessionScope.loginUser.enabled=='1'}">
			          			<button type="button" onclick="location.href='modify?id=${member.id}';" id="modifyBtn" class="btn btn-warning ">수 정</button>
			          			</c:if>
			          			<c:if test="${sessionScope.loginUser.enabled!='1'}">
			          			<button type="button" id="modifyBtn" class="btn btn-warning disabled">수 정</button>
			          			</c:if> --%>
			          			<%-- style="display:${loginUser.id eq member.id ? 'visible' : 'none'};" --%>
			          		</div>
		          		
			          		<div class="col-sm-3 text-center">
			          			<button type="button" onclick="location.href='remove.do?id=${member.id}';" 
			          			id="deleteBtn" class="btn btn-danger" >삭 제</button>
			          		</div>
		          			
			          		<div class="col-sm-3 text-center">
		          				<button type="button" onclick="location.href='enabled.do?id=${member.id}&enabled=0';" id="stopBtn" class="btn btn-info" style="display:${member.enabled ne 0 ? 'visible' : 'none'};">비활성</button>
								<button type="button" onclick="location.href='enabled.do?id=${member.id}&enabled=1';" id="activeBtn" class="btn btn-info" style="display:${member.enabled eq 0 ? 'visible' : 'none'};">활&nbsp;&nbsp;성</button>
			          		</div>
		          		<%-- </c:if> --%>
			          		<div class="col-sm-3 text-center">
			            		<button type="button" id="listBtn" onclick="CloseWindow();" class="btn btn-primary pull-right">닫 기</button>
			            	</div>
		          	    </div>  	
		          </div>
	      	  </form>
      	  </div>
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

<script>
	window.onload=function(){
	    MemberPictureThumb(document.querySelector('[data-id="${member.id}"]'),'${member.picture}','<%=request.getContextPath()%>');
	    
	    if(${!empty parentReload && parentReload}) {
	    	if(confirm('로그인 사용자의 정보가 수정되었습니다.\n현재화면을 닫고 새로고침 하시겠습니까?')) {
	    		window.opener.parent.location.reload(true);
	    		window.close();
	    	}
	    }
	    
	    if(${param.from eq 'enabled'}) {
			alert("${id} 님의 상태가 변경되었습니다.");
			location.href="detail.do?id=${id}";
		}
	    
	    if(${param.from eq 'remove'}) {
			alert("${removeMember.name} 님의 정보가 삭제되었습니다.");
			if(${empty loginUser}) {
				window.opener.parent.location.href="<%=request.getContextPath()%>";
			}
			window.close();
		}
	}
</script>
</body>