<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>    

<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.7.7/handlebars.min.js"></script>
<script type="text/x-handlebars-template"  id="reply-list-template" >
{{#each .}}
<div class="replyLi">
	<div class="user-block">
		<img src="<%=request.getContextPath()%>/member/getPicture.do?picture={{picture}}" class="img-circle"/>
    </div>
	
 	<div class="timeline-item">
  		<span class="time">
    		<i class="fa fa-clock"></i>{{prettifyDate regDate}}
	 		<a class="btn btn-primary btn-xs {{rno}}-a" id="modifyReplyBtn" data-rno={{rno}}
				onclick="replyModifyModal_go('{{rno}}');"				
				style="display:{{VisibleByLoginCheck replyer}};"
	    		data-replyer={{replyer}} data-toggle="modal" data-target="#modifyModal">수정</a>
  		</span>
	
  		<h3 class="timeline-header"><strong style="display:none;">{{rno}}</strong>{{replyer}}</h3>
  		<div class="timeline-body" id="{{rno}}-replyText">{{replyText}} </div>
	</div>
</div>
{{/each}}
</script>

<script type="text/x-handlebars-template" id="reply-pagination-template">
<li class="paginate_button page-item">
	<a href="1" aria-controls="example2" data-dt-idx="1" tabindex="0" class="page-link">
		<i class='fas fa-angle-double-left'></i>
	</a>
</li>
<li class="paginate_button page-item">
	<a href="{{#if prev}}{{prevPageNum}}{{/if}}" aria-controls="example2" data-dt-idx="1" tabindex="0" class="page-link">
		<i class='fas fa-angle-left'></i>
	</a>
</li>
{{#each pageNum}}
<li class="paginate_button page-item {{signActive this}}">
	<a href="{{this}}" aria-controls="example2" data-dt-idx="1" tabindex="0" class="page-link">
		{{this}}
	</a>
</li>
{{/each}}

<li class="paginate_button page-item">
	<a href="{{#if next}}{{nextPageNum}}{{/if}}" aria-controls="example2" data-dt-idx="1" tabindex="0" class="page-link">
		<i class='fas fa-angle-right'></i>
	</a>
</li>
<li class="paginate_button page-item">
	<a href="{{realEndPage}}" aria-controls="example2" data-dt-idx="1" tabindex="0" class="page-link">
		<i class='fas fa-angle-double-right'></i>
	</a>
</li>	
</script>

<script>
window.onload = function(){
	getPage("<%=request.getContextPath()%>/reply/list.do?bno=${boardVO.bno}&page=" + replyPage);
	
	$('ul.pagination').on('click', 'li a', function(event){
		if($(this).attr("href")){
			replyPage = $(this).attr("href");
			getPage("<%=request.getContextPath()%>/reply/list.do?bno=${boardVO.bno}&page=" 
					+ replyPage);
		}
		return false;
	});
}

var replyPage = 1;

function printData(replyArr, target, templateObject){
	var template = Handlebars.compile(templateObject.html());
	
	var html = template(replyArr);
	$('.replyLi').remove();
	target.after(html);
}

function printPagination(pageMaker, target, templateObject){
	var pageNum = new Array(pageMaker.endPage - pageMaker.startPage + 1);
	
	for(var i = 0; i < pageMaker.endPage - pageMaker.startPage + 1; i++){
		pageNum[i] = pageMaker.startPage + i;
	}
	
	pageMaker.pageNum = pageNum;
	pageMaker.prevPageNum = pageMaker.startPage - 1;
	pageMaker.nextPageNum = pageMaker.endPage + 1;
	
	var template = Handlebars.compile(templateObject.html());
	var html = template(pageMaker);
	target.html("").html(html);
}

function getPage(pageInfo){
	$.getJSON(pageInfo, function(data){
		printData(data.replyList, $('#repliesDiv'), $('#reply-list-template'));
		printPagination(data.pageMaker, $('ul#pagination'), $('#reply-pagination-template'));
	});
}

Handlebars.registerHelper({
	"prettifyDate" : function(timeValue){
		var dateObj = new Date(timeValue);
		var year = dateObj.getFullYear();
		var month = dateObj.getMonth() + 1;
		var date = dateObj.getDate();
		
		return year + "/" + month + "/" + date;
	},
	
	"VisibleByLoginCheck" : function(replyer){
		var result = "none";
		if(replyer == "${loginUser.id}") result = "visible";
		
		return result;
	},
	
	"signActive" : function(pageNum){
		if(pageNum == replyPage) return 'active';
	}
});

function replyRegist_go(){
	var replyText = $('#newreplyText').val();
	var bno = $('input[name="bno"]').val();
	
	if(!replyText){
		alert("내용을 입력해주세요.");
		return;
	}
	
	var data = {
		"bno"       : "" + bno + "",
		"replyer"   : "${loginUser.id}",
		"replyText" : replyText
	}
	//"" + bno + "" : bno를 String화 시키기 위한 용도, 없어도 ""가 씌워져서 날라감
	
	$.ajax({
		url : "<%=request.getContextPath()%>/reply/regist.do",
		type : "post",
		data : JSON.stringify(data),
		contentType : 'application/json',
		success : function(data){
			alert("댓글이 등록되었습니다.");
			replyPage = data;	//페이지 이동
			
			getPage("<%=request.getContextPath()%>/reply/list.do?bno=" + bno + "&page=" + data);
			//&page= + data의 data는 위의 var data가 아니라 success : function(data)의 data이다. ReplyRegistAction의 realEndPage가 온다.
			$('#newreplyText').val("");
		},
		error : function(){
			alert("댓글 등록이 실패했습니다.");
		}
	});
}

//댓글등록
function replyModifyModal_go(rno){
	$('div#modifyModal div.modal-body #replyText').val($('div#' + rno + '-replyText').text());
	$('div#modifyModal div.modal-header h4.modal-title').text(rno);
}

//댓글수정
function replyModify_go(){
	//alert("replyModify_go");
	
	var rno = $('.modal-title').text();
	var replyText = $('#replyText').val();
	
	var sendData = {
		rno       : rno,
		replyText : replyText
	}
	
	$.ajax({
		url : "<%=request.getContextPath()%>/reply/modify.do",
		type : 'post',
		data : JSON.stringify(sendData),
		contentType : "application/json",
		success : function(result){
			alert("수정되었습니다.");
			getPage("<%=request.getContextPath()%>/reply/list.do?bno=${boardVO.bno}&page=" + replyPage);
		},
		error : function(){
			alert("수정 실패했습니다.");
		},
		complete : function(){//success, error 상관없이 무조건 한번 실행함
			$('#modifyModal').modal('hide');
		}
	});
}

//댓글 삭제
function replyRemove_go(){
	//alert("Delete Reply");
	var rno = $('.modal-title').text();
	
	$.ajax({
		url : "<%=request.getContextPath()%>/reply/remove.do?rno=" + rno + "&page=" + replyPage + "&bno=${boardVO.bno}",
		type : "get",
		success : function(page){
			alert("삭제되었습니다.");
			getPage("<%=request.getContextPath()%>/reply/list.do?bno=${boardVO.bno}&page=" + page);
			replyPage = page;
		},
		error : function(){
			alert("삭제 실패했습니다.");
		},
		complete : function(){//success, error 상관없이 무조건 한번 실행함
			$('#modifyModal').modal('hide');
		}
	});
}
</script>