<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>    

<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.7.7/handlebars.min.js"></script>
<script type="text/x-handlebars-template"  id="reply-list-template" >
{{#each .}}
<div class="replyLi">
	<div class="user-block">
		<img src="<%=request.getContextPath()%>/member/getPictureById.do/{{replyer}}" class="img-circle img-bordered-sm"/>
    </div>
	
 	<div class="timeline-item">
  		<span class="time">
    		<i class="fa fa-clock"></i>{{prettifyDate regdate}}
	 		<a class="btn btn-primary btn-xs {{rno}}-a" id="modifyReplyBtn" data-rno={{rno}}
				onclick="replyModifyModal_go('{{rno}}');"				
				style="display:{{VisibleByLoginCheck replyer}};"
	    		data-replyer={{replyer}} data-toggle="modal" data-target="#modifyModal">Modify</a>
  		</span>
	
  		<h3 class="timeline-header"><strong style="display:none;">{{rno}}</strong>{{replyer}}</h3>
  		<div class="timeline-body" id="{{rno}}-replytext">{{replytext}} </div>
	</div>
</div>
{{/each}}
</script>

<script type="text/x-handlebars-template"  id="reply-pagination-template" >
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
<li class="paginate_button page-item {{signActive this}} ">
	<a href="{{this}}" aria-controls="example2" data-dt-idx="1" tabindex="0" class="page-link">
		{{this}}
	</a>
</li>
{{/each}}

<li class="paginate_button page-item ">
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
	getPage("<%=request.getContextPath()%>/replies/${board.bno}/" + replyPage);
	//$('ul.pagination').on('click', function(event){		//ul의 어디를 눌러도 전부 event가 실행됨
	//타고 가는 것                                       나오는 것
	//li a태그를 직접 element로 잡아서 function을 먹여도 작동이 안 됨
	$('ul.pagination').on('click', 'li a', function(event){	//이벤트가 'li a'를 클릭했을 때만 나오도록 제한됨.
		//alert("ul click");
		if($(this).attr("href")){
			replyPage = $(this).attr("href");
			getPage("<%=request.getContextPath()%>/replies/${board.bno}/" + replyPage);
		}
	
		return false;
	});
}

var replyPage = 1;

//handlebar가 jquery를 기준으로 만든 것이라, jquery를 쓰는 것이 적절함
function printData(replyArr, target, templateObject){
	//replyArr(action에서 날아올 데이터), target(댓글리스트를 넣을 위치기준을 설정), templateObject(handlebars template)
	var template = Handlebars.compile(templateObject.html());
	//html은 임의로 붙인게 아니라 jquery의 function호출 용도로 정해져 있는 기능이다.
	//메모리에 펑션이 남아있고, var template가 이 펑션의 주소를 가리키는 것이다.
	//compile하면 리턴타입이 펑션이 되어서 var template에 이 펑션이 들어가서 사용할수 있게 된다.
	//$('#asdf').html
	//document.getElementById('asdf').innerHTML
	var html = template(replyArr);
	$('.replyLi').remove();	//앞의 리플들을 전부 삭제하고
	target.after(html);//새롭게 전부를 불러와 붙여준다.
}

function printPagination(pageMaker, target, templateObject){
	var pageNum = new Array(pageMaker.endPage - pageMaker.startPage + 1);
	//한 페이지에 보여줄 페이지네이션 숫자 개수(10개  - 배열로 표현(0~9))만큼 배열을 만듦
	
	for(var i = 0; i < pageMaker.endPage - pageMaker.startPage + 1; i++){
		pageNum[i] = pageMaker.startPage + i;
		//만들어진 배열에 숫자를 삽입
	}
	
	pageMaker.pageNum = pageNum;
	pageMaker.prevPageNum = pageMaker.startPage - 1;//<를 누를 경우 갈 페이지 번호
	pageMaker.nextPageNum = pageMaker.endPage + 1;	//>를 누를 경우 갈 페이지 번호
	
	var template = Handlebars.compile(templateObject.html());
	var html = template(pageMaker);
	target.html("").html(html);
}

function getPage(pageInfo){
	$.ajax({
		url : pageInfo,
		type : "get",
		success : function(data){
			printData(data.replyList, $('#repliesDiv'), $('#reply-list-template'));
			printPagination(data.pageMaker, $('ul#pagination'), $('#reply-pagination-template'));
		},
		error : function(error){
			AjaxErrorSecurityRedirectHandler(error.status);
		}
	});
	
	/* $.getJSON(pageInfo, function(data){
		printData(data.replyList, $('#repliesDiv'), $('#reply-list-template'));
		printPagination(data.pageMaker, $('ul#pagination'), $('#reply-pagination-template'));
	}); */
}

Handlebars.registerHelper({
	"prettifyDate" : function(timeValue){	//Handlebars에 날짜출력 함수 등록
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

//댓글 등록
function replyRegist_go(){
	//alert("add reply");
	
	var replytext = $('#newReplyText').val();
	var bno = $('input[name="bno"]').val();
	
	if(!replytext){
		alert("내용은 필수입니다.");
		return;
	}
	
	var data = {
		"bno"       : "" + bno + "",
		"replyer"   : "${loginUser.id}",
		"replytext" : replytext
	}
	//"" + bno + "" : bno를 String화 시키기 위한 용도, 없어도 ""가 씌워져서 날라감
	
	$.ajax({
		url : "<%=request.getContextPath()%>/replies",
		type : "post",
		data : JSON.stringify(data),
		contentType : 'application/json',
		success : function(data){
			alert("댓글이 등록되었습니다.\n마지막 페이지로 이동합니다.");
			replyPage = data;	//페이지 이동
			
			getPage("<%=request.getContextPath()%>/replies/" + bno + "/" + data);
			//&page= + data의 data는 위의 var data가 아니라 success : function(data)의 data이다. ReplyRegistAction의 realEndPage가 온다.
			$('#newReplyText').val("");
		},
		error : function(error){
			//alert("댓글 등록이 실패했습니다.");
			AjaxErrorSecurityRedirectHandler(error.status);
		}
	});
}

//모달창 설정
function replyModifyModal_go(rno){
	$('div#modifyModal div.modal-body #replytext').val($('div#' + rno + '-replytext').text());
	$('div#modifyModal div.modal-header h4.modal-title').text(rno);
}

//댓글수정
function replyModify_go(){
	//alert("replyModify_go");
	
	var rno = $('.modal-title').text();
	var replytext = $('#replytext').val();
	
	var sendData = {
		rno       : rno,
		replytext : replytext
	}
	
	$.ajax({
		url : "<%=request.getContextPath()%>/replies/" + rno,
		type : 'put',
		data : JSON.stringify(sendData),
		headers : {"X-HTTP-Method-Override" : "PUT"},
		contentType : "application/json",
		success : function(result){
			alert("수정되었습니다.");
			getPage("<%=request.getContextPath()%>/replies/${board.bno}/" + replyPage);
		},
		error : function(error){
			//alert("수정 실패했습니다.");
			AjaxErrorSecurityRedirectHandler(error.status);
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
		url : "<%=request.getContextPath()%>/replies/${board.bno}/" + rno + "/" + replyPage,
		type : "delete",
		headers : {"X-HTTP-Method-Override" : "DELETE"},
		success : function(page){
			alert("삭제되었습니다.");
			getPage("<%=request.getContextPath()%>/replies/${board.bno}/" + page);
			replyPage = page;
		},
		error : function(error){
			//alert("삭제 실패했습니다.");
			AjaxErrorSecurityRedirectHandler(error.status);
		},
		complete : function(){//success, error 상관없이 무조건 한번 실행함
			$('#modifyModal').modal('hide');
		}
	});
}
</script>