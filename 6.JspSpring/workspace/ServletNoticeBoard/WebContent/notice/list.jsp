<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="noticeList" value="${dataMap.noticeList}"/>
<c:set var="pageMaker" value="${dataMap.pageMaker}"/>

<title>NoticeList</title>

<head></head>

<body>
<div class="content-wrapper">
	<!-- Main content -->
	<section class="content-header">
		<div class="container-fluid">
			<div class="row md-2">
				<div class="col-sm-6">
					<h1 class="m-0">NoticeList</h1>			
				</div>
				<div class="col-sm-6">
					<ol class="breadcrumb float-sm-right">
						<li class="breadcrumb-item">
							<a href="list">
								<i class="fa fa-dashboard"></i>NoticeList
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
							<button type="button" class="btn btn-sm btn-primary" onclick="OpenWindow('regist', 'NoticeRegist', 800, 1000);">등록</button>
						</div>
						<div class="col-md-7">
							<div id="keyword" class="card-tools" style="width:546px;">
								<div class="input-group row float-right">
									<!-- search bar -->
									<!-- sort num -->
									<select class="form-control form-control-sm col-md-3" name="perPageNum" id="perPageNum" onchange="list_go(1);">
										<option value="10">Sort</option>
										<option value="3" ${pageMaker.cri.perPageNum == 3 ? 'selected' : ''}>3</option>
										<option value="5" ${pageMaker.cri.perPageNum == 5 ? 'selected' : ''}>5</option>
										<option value="7" ${pageMaker.cri.perPageNum == 7 ? 'selected' : ''}>7</option>
									</select>
									
									<!-- search bar -->
									<select class="form-control form-control-sm col-md-3" name="searchType" id="searchType">
										<option value="" ${pageMaker.cri.searchType eq '' ? 'selected' : ''}>SearchType</option>
										<option value="title" ${pageMaker.cri.searchType eq 'title' ? 'selected' : ''}>Title</option>
										<option value="content" ${pageMaker.cri.searchType eq 'content' ? 'selected' : ''}>Content</option>
										<option value="writer" ${pageMaker.cri.searchType eq 'writer' ? 'selected' : ''}>Writer</option>
										<option value="titleAndContent" ${pageMaker.cri.searchType eq 'titleAndContent' ? 'selected' : ''}>Title + Content</option>
										<option value="titleAndWriter" ${pageMaker.cri.searchType eq 'titleAndWriter' ? 'selected' : ''}>Title + Writer</option>
										<option value="titleAndContentAndWriter" ${pageMaker.cri.searchType eq 'titleAndContentAndWriter' ? 'selected' : ''}>Title + Content + Writer</option>
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
									<c:if test="${empty noticeList}">
										<tr>
											<td colspan="5" class="text-center">
												해당 내용이 없습니다.
											</td>
										</tr>
									</c:if>
									<c:if test="${!empty pointList}">
										<c:forEach items="${pointList}" var="point">
										<tr style="cursor:pointer;" onclick="location.href='detail?nNo=${point.nNo}'">
											<td style="background:#dfe3e8;border-top:1px solid white;"><i class="fas fa-star" style="color:#dc3545;"></i></td>
											<td style="background:#dfe3e8;border-top:1px solid white;text-align:left;">
												<div style="overflow:hidden;white-space:nowrap;text-overflow:ellipsis;text-align:left;">
													${point.title}
												</div>
											</td>
											<td style="background:#dfe3e8;border-top:1px solid white;">${point.writer}</td>
											<td style="background:#dfe3e8;border-top:1px solid white;">${point.viewCnt}</td>
											<td style="background:#dfe3e8;border-top:1px solid white;">
												<fmt:formatDate value="${point.regDate}" pattern="yyyy-MM-dd"/>
											</td>
										</tr>
										</c:forEach>
									</c:if>
									<c:if test="${!empty noticeList}">
										<c:forEach items="${noticeList}" var="notice">
										<tr style="cursor:pointer;border-bottom:1px solid #dee2e6;" onclick="location.href='detail?nNo=${notice.nNo}'">
											<td>${notice.nNo}</td>
											<td>
												<div style="overflow:hidden;white-space:nowrap;text-overflow:ellipsis;text-align:left;">
													${notice.title}
												</div>
											</td>
											<td>${notice.writer}</td>
											<td>${notice.viewCnt}</td>
											<td>
												<fmt:formatDate value="${notice.regDate}" pattern="yyyy-MM-dd"/>
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
							<%@ include file="/common/pagination.jsp" %>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</div>
</body>