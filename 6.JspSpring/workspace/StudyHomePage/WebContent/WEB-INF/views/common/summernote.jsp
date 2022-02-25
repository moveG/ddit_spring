<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>    

<script>
	function summernote_go(target){
		target.summernote({//JSON형태로 각각의 설정이 있음, 키값으로 정해져있음, CSS는 불가능
			placeholder : 'Enter Content',
			lang : 'ko-KR',
			height : '200px',
			disableResizeEditor : false,
			callbacks : {
				onImageUpload : function(files, editor, welEditable){//files : 자바스크립트 file 객체, 배열, 순서X
					for(var file of files){
						/* if(file.name.substring(file.name.lastIndexOf(".") + 1).toUpperCase() != "JPG"){
							alert("JPG 형식의 이미지만 가능합니다.");
							return;
						} */
						if(file.size > 1024 * 1024 * 5){
							alert("5MB 미만의 이미지만 가능합니다.");
							return;
						}
					}
					for(var file of files){
						sendFile(file, this);
					}
				},
				onMediaDelete : function(target){
					deleteFile(target[0].src);
				}
			}
		});
	}
	
	function deleteFile(src){
		//교육적인 차원에서 json 사용함
		var splitSrc = src.split("=");
		var fileName = splitSrc[splitSrc.length - 1];
		
		var fileData = {fileName:fileName};	//object mapper로 받으려함, command 객체 필요
		//연습을 위한 json형태 데이터, url주소 형식이 정해져있기 때문에 json형태의 데이터는 get으로 못보내고 post로 보냄
		//json데이터도 String 타입으로 넘어감
		
		$.ajax({
			//data가 String화시킨 json데이터라면, contentType에 반드시 json형태임을 명싷해줘야함
			url : "<%=request.getContextPath()%>/deleteImg.do",
			data : JSON.stringify(fileData),	//json을 String형태로 변환
			type : "post",
			contentType : "application/json",	//데이터가 json형태임을 명시
			success : function(res){
				console.log(res);	
			},
			error : function(){
				alert("이미지 삭제가 불가능합니다.");
			}
		});
	}
	
	function sendFile(file, el){//el : textarea#content(텍스트에어리어)을 의미함
		var form_data = new FormData();
		form_data.append("file", file);
		
		$.ajax({
			data : form_data,
			type : "POST",
			url : '<%=request.getContextPath()%>/uploadImg.do',
			cache : false,
			contentType : false,
			processData : false,
			success : function(img_url){
				$(el).summernote('editor.insertImage', img_url);
			},
			error : function(){
				alert(file.name + " 업로드에 실패했습니다.");
			}
		});
	}
</script>