<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>    

<title>MainPage</title>

<head></head>

<body>
	<div class="content-wrapper" style="background:#f4f6f9;">
		<iframe name="ifr" src="" frameborder="0" style="width:100%;height:86vh;">
		</iframe>
	</div>
	
	<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.7.7/handlebars.min.js"></script>
	<script type="text/x-handlebars-template" id="subMenu-list-template">
		{{#each .}}
			<li class="nav-item subMenu">
				<a href="javascript:goPage('<%=request.getContextPath()%>{{murl}}', '{{mcode}}');" class="nav-link">
					<i class="{{micon}}"></i>
					<p>{{mname}}</p>
				</a>
			</li>
		{{/each}}
	</script>
	
	<script>
		function goPage(url, mCode){
			document.querySelector('iframe[name="ifr"]').src = url;
			
			//HTML5 지원 브라우저에서 사용 가능
			if(typeof(history.pushState) == 'function'){
				//현재 주소를 가져온다.
				var renewURL = location.href;
				
				//현재 주소에서 .do 뒷부분이 있다면 날린다.
				renewURL = renewURL.substring(0, renewURL.indexOf(".do") + 3);
				if(mCode != "M000000"){
					renewURL += "?mCode=" + mCode;
				}
				
				//페이지를 리로드하지 않고, 페이지 주소만 변경할 때 사용한다.
				history.pushState(mCode, null, renewURL);
			}else{
				location.hash = "#" + mCode;
			}
		}
		
		function subMenu_go(mCode){
			if(mCode != "M000000"){
				//ajax 변형상태 - error페이지는 없음
				$.getJSON("<%=request.getContextPath()%>/subMenu.do?mCode=" + mCode, function(data){
					//printData(데이터, 타겟, 템플릿, 제거클래스);
					printData(data, $('.subMenuList'), $('#subMenu-list-template'), '.subMenu');
				});
			}else{
				$('.subMenuList').html("");
			}
		}
		
		//handlebars printElement(args : data Array, append target, handlebar template, remove class)
		function printData(dataArr, target, templateObject, removeClass){
			//컴파일
			var template = Handlebars.compile(templateObject.html());
			
			//실행
			var html = template(dataArr);
			
			//먼저 것 삭제
			$(removeClass).remove();//JQUERY 방식
			
			//새로 만든 것 삽입
			target.append(html);
		}
		
		window.onload = function(){
			goPage('<%=request.getContextPath()%>${menu.murl}', '${menu.mcode}');
			subMenu_go('${menu.mcode}'.substring(0,3) + "0000");
		}
	</script>
</body>
