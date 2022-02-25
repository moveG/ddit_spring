<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="memberList" value="${dataMap.memberList}"/>
<c:set var="pageMaker" value="${dataMap.pageMaker}"/>

<title>MemberList</title>

<head>
<style>
	.id{
	width : 121px;
	}
	.name{
		width : 157px;
	}
	.email{
		width : 290px;
	}
	.phone{
		width : 216px;
	}
	.regDate{
		width : 173px;
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
					<h1 class="m-0">MemberList</h1>			
				</div>
				<div class="col-sm-6">
					<ol class="breadcrumb float-sm-right">
						<li class="breadcrumb-item">
							<a href="list.do">
								<i class="fa fa-dashboard"></i>MemberList
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
							<button type="button" class="btn btn-sm btn-primary" onclick="OpenWindow('registForm.do', 'MemberRegist', 800, 1000);">등록</button>
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
										<option value="i" ${pageMaker.cri.searchType eq 'i' ? 'selected' : ''}>ID</option>
										<option value="n" ${pageMaker.cri.searchType eq 'n' ? 'selected' : ''}>Name</option>
										<option value="e" ${pageMaker.cri.searchType eq 'e' ? 'selected' : ''}>Email</option>
										<option value="p" ${pageMaker.cri.searchType eq 'p' ? 'selected' : ''}>Tel</option>
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
								<thead style="background:#343a40; color:white;">
									<tr>
										<th>ID</th>
										<th>Name</th>
										<th>Email</th>
										<th>Tel</th>
										<th>RegDate</th>
									</tr>
								</thead>
								<tbody>
									<c:if test="${empty memberList}">
										<tr>
											<td colspan="5" class="text-center">
												해당 내용이 없습니다.
											</td>
										</tr>
									</c:if>
									<c:if test="${!empty memberList}">
										<c:forEach items="${memberList}" var="member">
										<tr style="cursor:pointer;border-bottom:1px solid #dee2e6;" onclick="OpenWindow('detail.do?id=${member.id}', '', '800', '900');">
											<td class="id">${member.id}</td>
											<td class="name">${member.name}</td>
											<td class="email">${member.email}</td>
											<td class="phone">
												<c:set var="phone" value="${member.phone.replace('-', '')}"/>
												${phone.substring(0, 3)}-${phone.substring(3, 7)}-${phone.substring(7)}
												<!-- ${member.phone} -->
											</td>
											<td class="regDate">
												<fmt:formatDate value="${member.regDate}" pattern="yyyy-MM-dd"/>
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