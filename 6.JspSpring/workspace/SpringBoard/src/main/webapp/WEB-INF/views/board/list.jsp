<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="pageMaker" value="${dataMap.pageMaker}"/>
<c:set var="cri" value="${dataMap.pageMaker.cri}"/>
<c:set var="boardList" value="${dataMap.boardList}"/>

<title>BoardList</title>

<head></head>

<body>
<script>
	if(${from eq 'regist'}){
		alert("등록되었습니다.");
		window.opener.location.reload();
		window.close();
	}
</script>

<div class="content-wrapper">
	<!-- Main content -->
	<section class="content-header">
		<div class="container-fluid">
			<div class="row md-2">
				<div class="col-sm-6">
					<h1 class="m-0">BoardList</h1>			
				</div>
				<div class="col-sm-6">
					<ol class="breadcrumb float-sm-right">
						<li class="breadcrumb-item">
							<a href="list.do">
								<i class="fa fa-dashboard"></i>BoardList
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
							<button type="button" class="btn btn-sm btn-primary" onclick="OpenWindow('registForm.do', 'BoardRegist', 822, 1000);">등록</button>
						</div>
						<div class="col-md-7">
							<div id="keyword" class="card-tools" style="width:546px;">
								<div class="input-group row float-right">
									<!-- search bar -->
									<!-- sort num -->
									<select class="form-control form-control-sm col-md-3" name="perPageNum" id="perPageNum" onchange="list_go(1);">
										<option value="10">Sort</option>
										<option value="20" ${pageMaker.cri.perPageNum == 20 ? 'selected' : ''}>20</option>
										<option value="50" ${pageMaker.cri.perPageNum == 50 ? 'selected' : ''}>50</option>
										<option value="100" ${pageMaker.cri.perPageNum == 100 ? 'selected' : ''}>100</option>
									</select>
									
									<!-- search bar -->
									<select class="form-control form-control-sm col-md-3" name="searchType" id="searchType">
										<option value="" ${pageMaker.cri.searchType eq '' ? 'selected' : ''}>SearchType</option>
										<option value="t" ${pageMaker.cri.searchType eq 't' ? 'selected' : ''}>Title</option>
										<option value="c" ${pageMaker.cri.searchType eq 'c' ? 'selected' : ''}>Content</option>
										<option value="w" ${pageMaker.cri.searchType eq 'w' ? 'selected' : ''}>Writer</option>
										<option value="tc" ${pageMaker.cri.searchType eq 'tc' ? 'selected' : ''}>Title + Content</option>
										<option value="cw" ${pageMaker.cri.searchType eq 'cw' ? 'selected' : ''}>Content + Writer</option>
										<option value="tcw" ${pageMaker.cri.searchType eq 'tcw' ? 'selected' : ''}>Title + Content + Writer</option>
									</select>
									<!-- keyword -->
									<input class="form-control form-control-sm" type="text" name="searchWord" placeholder="SearchWord" value="${pageMaker.cri.searchWord}"
										   onKeypress="javascript:if(event.keyCode==13) {list_go(1);}"/>
									<span class="input-group-append">
										<button class="btn btn-sm btn-success" type="button" id="searchBtn" data-card-widget="search" onclick="list_go(1);">
											<i class="fa fa-fw fa-search"></i>
										</button>
									</span>
								<!-- end : search bar -->		
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
										<th style="width:120px;">Writer</th>
										<th style="width:80px;">Views</th>
										<th style="width:120px;">RegDate</th>
									</tr>
								</thead>
								<tbody>
									<c:if test="${empty boardList}">
										<tr>
											<td colspan="5" class="text-center">
												해당 내용이 없습니다.
											</td>
										</tr>
									</c:if>
									<c:if test="${!empty boardList}">
										<c:forEach items="${boardList}" var="board">
										<tr style="cursor:pointer;border-bottom:1px solid #dee2e6;" onclick="OpenWindow('detail.do?bno=${board.bno}&from=list', 'BoardDetail', 900, 1000);">
											<td>${board.bno}</td>
											<td>
												<div style="overflow:hidden;white-space:nowrap;text-overflow:ellipsis;text-align:left;">
													<span class="col-sm-12">
														${board.title}
														<c:if test="${board.replyCnt ne 0}">		
															<span class="nav-item">															
															&nbsp;&nbsp;<i class="fa fa-comment" style="color:#007bff;"></i>
															<span class="badge badge-warning navbar-badge">${board.replyCnt}</span>
															</span>
														</c:if>
													</span>
												</div>
											</td>
											<td>${board.writer}</td>
											<td>${board.viewCnt}</td>
											<td>
												<fmt:formatDate value="${board.regDate}" pattern="yyyy-MM-dd"/>
											</td>
										</tr>
										</c:forEach>
									</c:if>
								</tbody>
							</table>
						</div> <!-- col-sm-12 -->
					</div> <!-- row -->
				</div> <!-- card-body -->
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
</body>
