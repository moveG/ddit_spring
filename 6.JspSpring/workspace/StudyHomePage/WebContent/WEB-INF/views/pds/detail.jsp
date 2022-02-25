<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<title>PdsDetail</title>

<head></head>

<body>
<div class="content-wrapper">
	<!-- Main content -->
	<section class="content-header">
		<div class="container-fluid">
			<div class="row md-2">
				<div class="col-sm-6">
					<h1 class="m-0">PdsDetail</h1>			
				</div>
				<div class="col-sm-6">
					<ol class="breadcrumb float-sm-right">
						<li class="breadcrumb-item">
							<a href="detail.do?pno=${pdsVO.pno}">
								<i class="fa fa-dashboard"></i>PdsDetail
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
								<button type="button" id="removeBtn" class="btn btn-danger" onclick="submit_go('remove.do','${pdsVO.pno}');">삭제</button>
								<button type="button" id="modifyBtn" class="btn btn-primary" onclick="submit_go('modifyForm.do','${pdsVO.pno}');">수정</button>
								<button type="button" id="listBtn" class="btn btn-secondary" onclick="CloseWindow();">닫기</button>
							</div>
						</div>
					</div>
				</div>
				<div class="card-body">
					<div class="row">
						<div class="col-md-12">	
							<table class="table">
								<tr>
									<th style="width:130px;">제목</th>
									<td>${pdsVO.title}</td>
								</tr>
								<tr>
									<th style="width:130px;">작성자</th>
									<td>${pdsVO.writer}</td>
								</tr>
								<tr>
									<th style="width:130px;">등록일</th>
									<td>
										<fmt:formatDate value="${pdsVO.regDate}" pattern="yyyy-MM-dd"/>
									</td>
								</tr>
								<tr>
									<th style="width:130px;">조회수</th>
									<td>${pdsVO.viewCnt}</td>
								</tr>
								<tr>
									<td colspan="2" style="height:250px;">${pdsVO.content}</td>
								</tr>
								
							</table>
						</div> <!-- col-sm-12 -->
					</div> <!-- row -->
				</div> <!-- card-body -->
				<div class="card-footer" style="padding-bottom:0px;">
					<div class="row">
						<c:forEach items="${pdsVO.attachList}" var="attach">
							<div class="col-md-4" style="cursor:pointer;" onclick="location.href='<%=request.getContextPath()%>/pds/getFile.do?ano=${attach.ano}';">
								<div class="info-box">
									<span class="info-box-icon bg-warning"><i class="fas fa-paperclip"></i></span>
									<div class="info-box-content">
										<span class="info-box-text">
											<fmt:formatDate value="${attach.regDate}" pattern="yy-MM-dd"/>
										</span>
										<span class="info-box-number">
											${attach.fileName}
										</span>
									</div>
								</div>
							</div>	
						</c:forEach>
					</div>	
				</div>
			</div>
		</div>
	</section>
</div>

<script>
	function submit_go(url, pno){
		location.href = url + "?pno=" + pno;
	}
</script>
</body>