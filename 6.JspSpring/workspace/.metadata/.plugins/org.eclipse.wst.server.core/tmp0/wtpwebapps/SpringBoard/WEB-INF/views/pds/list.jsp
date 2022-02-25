<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="pageMaker" value="${dataMap.pageMaker}"/>
<c:set var="cri" value="${pageMaker.cri}"/>
<c:set var="pdsList" value="${dataMap.pdsList}"/>

<title>PdsList</title>

<head></head>

<body>
<div class="content-wrapper">
	<section class="content-header">
		<div class="container-fluid">
			<div class="row md-2">
				<div class="col-sm-6">
					<h1 class="m-0">PdsList</h1>			
				</div>
				<div class="col-sm-6">
					<ol class="breadcrumb float-sm-right">
						<li class="breadcrumb-item">
							<a href="list.do">
								<i class="fa fa-dashboard"></i>PdsList
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
				<div class="card-header with-border">
					<div class="row">
						<div class="col-md-5">
							<button type="button" id="registBtn" class="btn btn-sm btn-primary" onclick="OpenWindow('registForm.do', 'PdsRegist', 822, 1000);">등록</button>
						</div>
						<div class="col-md-7">
							<div id="keyword" class="card-tools" style="width:546px;">
								<div class="input-group row float-right">
									<select class="form-control form-control-sm col-md-3" name="perPageNum" id="perPageNum" onchange="list_go(1);">
										<option value="10" ${cri.perPageNum == 10 ? 'selected' : ''}>Sort</option>
										<option value="20" ${cri.perPageNum == 20 ? 'selected' : ''}>20</option>
										<option value="30" ${cri.perPageNum == 30 ? 'selected' : ''}>30</option>
										<option value="50" ${cri.perPageNum == 50 ? 'selected' : ''}>50</option>
									</select>
							
									<select class="form-control form-control-sm col-md-3" name="searchType" id="searchType">
										<option value="" ${cri.searchType eq '' ? 'selected' : ''}>SearchType</option>
										<option value="t" ${cri.searchType eq 't' ? 'selected' : ''}>Title</option>
										<option value="c" ${cri.searchType eq 'c' ? 'selected' : ''}>Content</option>
										<option value="w" ${cri.searchType eq 'w' ? 'selected' : ''}>Writer</option>
										<option value="tc" ${cri.searchType eq 'tc' ? 'selected' : ''}>Title + Content</option>
										<option value="cw" ${cri.searchType eq 'cw' ? 'selected' : ''}>Content + Writer</option>
									</select>
									
									<input class="form-control form-control-sm" type="text" name="searchWord" placeholder="SearchWord" value="${cri.searchWord}"
										   onKeypress="javascript:if(event.keyCode==13) {list_go(1);}"/>
									<span class="input-group-append">
										<button class="btn btn-sm btn-success" type="button" id="searchBtn" data-card-widget="search" onclick="list_go(1);">
											<i class="fa fa-fw fa-search"></i>
										</button>
									</span>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="card-body" style="text-align:center;">
					<div class="row">
						<div class="col-sm-12">
							<table class="table table-sm table-hover" style="table-layout:fixed;margin-bottom:0px;">
								<thead style="background:#343a40;color:white;">
									<tr>
										<th style="width:70px;">No</th>
										<th>Title</th>
										<th style="width:80px;">Attach</th>
										<th style="width:120px;">Writer</th>
										<th style="width:80px;">Views</th>
										<th style="width:120px;">RegDate</th>
									</tr>
								</thead>
								<tbody>
									<c:if test="${empty pdsList}">
										<tr>
											<td colspan="6" class="text-center">
												해당 내용이 없습니다.
											</td>
										</tr>
									</c:if>
									<c:if test="${!empty pdsList}">
										<c:forEach items="${pdsList}" var="pds">
										<tr style="cursor:pointer;border-bottom:1px solid #dee2e6;" onclick="OpenWindow('detail.do?pno=${pds.pno}', 'PdsDetail', 900, 1000);">
											<td>${pds.pno}</td>
											<td>
												<div style="overflow:hidden;white-space:nowrap;text-overflow:ellipsis;text-align:left;">
													${pds.title}
												</div>
											</td>
											<td>
												<c:if test="${!empty pds.attachList }">
													<i class="nav-icon fas fa-paperclip"></i>
												</c:if>
											</td>
											<td>${pds.writer}</td>
											<td>${pds.viewCnt}</td>
											<td>
												<fmt:formatDate value="${pds.regDate}" pattern="yyyy-MM-dd"/>
											</td>
										</tr>
										</c:forEach>
									</c:if>
								</tbody>
							</table>
						</div>
					</div>	
				</div>
				<div class="card-footer">
					<div class="row">
						<div class="col-sm-12">
							<%@ include file="/WEB-INF/views/common/pagination.jsp" %>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</div>

<script>
	if(${from eq 'regist'}){
		alert("등록되었습니다.");
		window.opener.location.reload();
		window.close();
	}
</script>
</body>