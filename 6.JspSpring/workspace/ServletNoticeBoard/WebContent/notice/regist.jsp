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
							<a href="regist">
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
				<div class="card-body">
					<div class="row">
						<div class="col-md-12">	
							<form role="form" class="regist-form" action="regist" method="post">
								<table class="table">
									<tr class="form-group">
										<th style="width:100px;vertical-align:middle;">Title</th>
										<td>
											<input type="text" class="form-control" placeholder="Enter Title" name="title">
										</td>
									</tr>
									<tr class="form-group">
										<th style="width:100px;vertical-align:middle;">Writer</th>
										<td>
											<select name="writer" class="form-control">
												<c:forEach items="${idList}" var="id">
													<option value="${id}">${id}</option>
												</c:forEach>
											</select>
										</td>
									</tr>
									<tr class="form-group">
										<th style="width:100px;vertical-align:middle;">Content</th>
										<td>
											<textarea rows="15" cols="50" class="form-control" placeholder="Enter Content" name="content"></textarea>
										</td>
									</tr>
									<tr class="form-group">
										<th style="width:100px;vertical-align:middle;">Point</th>
										<td>
											<select name="point" class="form-control">
												<option value="0">Low</option>
												<option value="1">High</option>
											</select>
										</td>
									</tr>
									<tr class="form-group">
										<th style="width:100px;vertical-align:middle;">StartDate</th>
										<td>
											<input type="date" class="form-control" id="startDate" name="startDate">
										</td>
									</tr>
									<tr class="form-group">
										<th style="width:100px;vertical-align:middle;">EndDate</th>
										<td>
											<input type="date" class="form-control" id="endDate" name="endDate">
										</td>
									</tr>
									<tr class="form-group">
										<th style="width:100px;vertical-align:middle;">AllDate</th>
										<td>
											<div class="custom-control custom-checkbox">
												<input class="custom-control-input" type="checkbox" id="customCheckbox1" value="" onchange="check_go();">
												<label for="customCheckbox1" class="custom-control-label">Select</label>
											</div>
										</td>
									</tr>
								</table>
							</form>
						</div> <!-- col-sm-12 -->
					</div> <!-- row -->
				</div> <!-- card-body -->
				<div class="card-footer">
					<div class="row">
						<div class="col-sm-12">
							<div class="float-right">
								<button type="button" class="btn btn-primary" onclick="regist_go();return false;">등록</button>
								<button type="button" class="btn btn-secondary" onclick="CloseWindow();">취소</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</div>

<script>
	function regist_go(){
		if(!$('input[name="title"]').val()){
			alert("제목 입력은 필수입니다.");
			return;
		}
		
		if(!$('textarea[name="content"]').val()){
			alert("내용 입력은 필수입니다.");
			return;
		}

		if(!$('input[name="startDate"]').val()){
			alert("시작일을 입력해주세요.");
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