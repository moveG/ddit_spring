/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.72
 * Generated at: 2022-01-27 02:43:52 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.board;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class modify_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

private static org.apache.jasper.runtime.ProtectedFunctionMapper _jspx_fnmap_0;

static {
  _jspx_fnmap_0= org.apache.jasper.runtime.ProtectedFunctionMapper.getMapForFunction("fn:escapeXml", org.apache.taglibs.standard.functions.Functions.class, "escapeXml", new Class[] {java.lang.String.class});
}

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(3);
    _jspx_dependants.put("/WEB-INF/views/common/summernote.jsp", Long.valueOf(1639050644628L));
    _jspx_dependants.put("/WEB-INF/lib/jstl-1.2.jar", Long.valueOf(1640308347111L));
    _jspx_dependants.put("jar:file:/D:/A_TeachingMaterial/6.JspSpring/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/SpringBoard/WEB-INF/lib/jstl-1.2.jar!/META-INF/fn.tld", Long.valueOf(1153352682000L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<title>BoardModify</title>\r\n");
      out.write("\r\n");
      out.write("<head></head>\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("<div class=\"content-wrapper\">\r\n");
      out.write("	<!-- Main content -->\r\n");
      out.write("	<section class=\"content-header\">\r\n");
      out.write("		<div class=\"container-fluid\">\r\n");
      out.write("			<div class=\"row md-2\">\r\n");
      out.write("				<div class=\"col-sm-6\">\r\n");
      out.write("					<h1 class=\"m-0\">BoardModify</h1>			\r\n");
      out.write("				</div>\r\n");
      out.write("				<div class=\"col-sm-6\">\r\n");
      out.write("					<ol class=\"breadcrumb float-sm-right\">\r\n");
      out.write("						<li class=\"breadcrumb-item\">\r\n");
      out.write("							<a href=\"modify.do?bno=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${boardVO.bno}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\">\r\n");
      out.write("								<i class=\"fa fa-dashboard\"></i>BoardModify\r\n");
      out.write("							</a>\r\n");
      out.write("						</li>\r\n");
      out.write("						<li class=\"breadcrumb-item active\">\r\n");
      out.write("							Home\r\n");
      out.write("						</li>		        \r\n");
      out.write("					</ol>\r\n");
      out.write("				</div>\r\n");
      out.write("			</div>\r\n");
      out.write("		</div>\r\n");
      out.write("	</section>\r\n");
      out.write("\r\n");
      out.write("	<section class=\"content\">\r\n");
      out.write("		<div class=\"container-fluid\">\r\n");
      out.write("			<div class=\"card\">\r\n");
      out.write("				<div class=\"card-header with-border\" style=\"background:#f7f7f7;border-bottom:none;\">\r\n");
      out.write("					<div class=\"row\">\r\n");
      out.write("						<div class=\"col-sm-12\">\r\n");
      out.write("							<div class=\"float-right\">\r\n");
      out.write("								<button type=\"button\" class=\"btn btn-primary\" id=\"modifyBtn\" onclick=\"modify_go();return false;\">수정</button>\r\n");
      out.write("								<button type=\"button\" class=\"btn btn-secondary\" id=\"cancelBtn\" onclick=\"history.go(-1);\">취소</button>\r\n");
      out.write("							</div>\r\n");
      out.write("						</div>\r\n");
      out.write("					</div>\r\n");
      out.write("				</div>\r\n");
      out.write("				<div class=\"card-body\">\r\n");
      out.write("					<div class=\"row\">\r\n");
      out.write("						<div class=\"col-md-12\">	\r\n");
      out.write("							<form role=\"form\" method=\"post\" action=\"modify.do\" name=\"modifyForm\">\r\n");
      out.write("								<input type=\"hidden\" name=\"bno\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${boardVO.bno}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\"/>\r\n");
      out.write("								<table class=\"table\">\r\n");
      out.write("									<tr class=\"form-group\">\r\n");
      out.write("										<th style=\"width:130px;padding:0px auto;\">제목</th>\r\n");
      out.write("										<td style=\"padding:0px;vertical-align:middle;\">\r\n");
      out.write("											<input type=\"text\" id=\"title\" class=\"form-control form-control-sm\" name=\"title\" placeholder=\"Enter Title\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${boardVO.title}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\">\r\n");
      out.write("										</td>\r\n");
      out.write("									</tr>\r\n");
      out.write("									<tr class=\"form-group\">\r\n");
      out.write("										<th style=\"width:130px;padding:0px auto;\">작성자</th>\r\n");
      out.write("										<td style=\"padding:0px;vertical-align:middle;\">\r\n");
      out.write("											<input type=\"text\" id=\"writer\" class=\"form-control form-control-sm\" name=\"writer\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${boardVO.writer}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\" style=\"background:white;\" readonly>\r\n");
      out.write("										</td>\r\n");
      out.write("									</tr>\r\n");
      out.write("									<tr class=\"form-group\">\r\n");
      out.write("										<td colspan=\"2\">\r\n");
      out.write("											<textarea rows=\"3\" id=\"content\" class=\"form-control form-control-sm\" name=\"content\" id=\"content\" placeholder=\"Enter Content\">\r\n");
      out.write("												");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${fn:escapeXml(boardVO.content)}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, _jspx_fnmap_0));
      out.write("</textarea>\r\n");
      out.write("										</td>\r\n");
      out.write("									</tr>\r\n");
      out.write("								</table>\r\n");
      out.write("							</form>\r\n");
      out.write("						</div> <!-- col-sm-12 -->\r\n");
      out.write("					</div> <!-- row -->\r\n");
      out.write("				</div> <!-- card-body -->\r\n");
      out.write("				<div class=\"card-footer\">\r\n");
      out.write("				</div>\r\n");
      out.write("			</div>\r\n");
      out.write("		</div>\r\n");
      out.write("	</section>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<script>\r\n");
      out.write("	function summernote_go(target){\r\n");
      out.write("		target.summernote({//JSON형태로 각각의 설정이 있음, 키값으로 정해져있음, CSS는 불가능\r\n");
      out.write("			placeholder : 'Enter Content',\r\n");
      out.write("			lang : 'ko-KR',\r\n");
      out.write("			height : '200px',\r\n");
      out.write("			disableResizeEditor : false,\r\n");
      out.write("			callbacks : {\r\n");
      out.write("				onImageUpload : function(files, editor, welEditable){//files : 자바스크립트 file 객체, 배열, 순서X\r\n");
      out.write("					for(var file of files){\r\n");
      out.write("						/* if(file.name.substring(file.name.lastIndexOf(\".\") + 1).toUpperCase() != \"JPG\"){\r\n");
      out.write("							alert(\"JPG 형식의 이미지만 가능합니다.\");\r\n");
      out.write("							return;\r\n");
      out.write("						} */\r\n");
      out.write("						if(file.size > 1024 * 1024 * 5){\r\n");
      out.write("							alert(\"5MB 미만의 이미지만 가능합니다.\");\r\n");
      out.write("							return;\r\n");
      out.write("						}\r\n");
      out.write("					}\r\n");
      out.write("					for(var file of files){\r\n");
      out.write("						sendFile(file, this);\r\n");
      out.write("					}\r\n");
      out.write("				},\r\n");
      out.write("				onMediaDelete : function(target){\r\n");
      out.write("					deleteFile(target[0].src);\r\n");
      out.write("				}\r\n");
      out.write("			}\r\n");
      out.write("		});\r\n");
      out.write("	}\r\n");
      out.write("	\r\n");
      out.write("	function deleteFile(src){\r\n");
      out.write("		//교육적인 차원에서 json 사용함\r\n");
      out.write("		var splitSrc = src.split(\"=\");\r\n");
      out.write("		var fileName = splitSrc[splitSrc.length - 1];\r\n");
      out.write("		\r\n");
      out.write("		var fileData = {fileName:fileName};	//object mapper로 받으려함, command 객체 필요\r\n");
      out.write("		//연습을 위한 json형태 데이터, url주소 형식이 정해져있기 때문에 json형태의 데이터는 get으로 못보내고 post로 보냄\r\n");
      out.write("		//json데이터도 String 타입으로 넘어감\r\n");
      out.write("		\r\n");
      out.write("		$.ajax({\r\n");
      out.write("			//data가 String화시킨 json데이터라면, contentType에 반드시 json형태임을 명싷해줘야함\r\n");
      out.write("			url : \"");
      out.print(request.getContextPath());
      out.write("/deleteImg.do\",\r\n");
      out.write("			data : JSON.stringify(fileData),	//json을 String형태로 변환\r\n");
      out.write("			type : \"post\",\r\n");
      out.write("			contentType : \"application/json\",	//데이터가 json형태임을 명시\r\n");
      out.write("			success : function(res){\r\n");
      out.write("				console.log(res);	\r\n");
      out.write("			},\r\n");
      out.write("			error : function(){\r\n");
      out.write("				alert(\"이미지 삭제가 불가능합니다.\");\r\n");
      out.write("			}\r\n");
      out.write("		});\r\n");
      out.write("	}\r\n");
      out.write("	\r\n");
      out.write("	function sendFile(file, el){//el : textarea#content(텍스트에어리어)을 의미함\r\n");
      out.write("		var form_data = new FormData();\r\n");
      out.write("		form_data.append(\"file\", file);\r\n");
      out.write("		\r\n");
      out.write("		$.ajax({\r\n");
      out.write("			data : form_data,\r\n");
      out.write("			type : \"POST\",\r\n");
      out.write("			url : '");
      out.print(request.getContextPath());
      out.write("/uploadImg.do',\r\n");
      out.write("			cache : false,\r\n");
      out.write("			contentType : false,\r\n");
      out.write("			processData : false,\r\n");
      out.write("			success : function(img_url){\r\n");
      out.write("				$(el).summernote('editor.insertImage', img_url);\r\n");
      out.write("			},\r\n");
      out.write("			error : function(){\r\n");
      out.write("				alert(file.name + \" 업로드에 실패했습니다.\");\r\n");
      out.write("			}\r\n");
      out.write("		});\r\n");
      out.write("	}\r\n");
      out.write("</script>");
      out.write("<script>\r\n");
      out.write("	window.onload = function(){\r\n");
      out.write("		summernote_go($('textarea#content'));\r\n");
      out.write("	}	\r\n");
      out.write("\r\n");
      out.write("	function modify_go(){\r\n");
      out.write("		var formObj = document.modifyForm;\r\n");
      out.write("		\r\n");
      out.write("		if(formObj.title.value == \"\"){\r\n");
      out.write("			alert(\"제목을 입력해주세요.\");\r\n");
      out.write("			return;\r\n");
      out.write("		}\r\n");
      out.write("		if(formObj.content.value == \"\"){\r\n");
      out.write("			alert(\"내용을 입력해주세요.\");\r\n");
      out.write("			return;\r\n");
      out.write("		}\r\n");
      out.write("		formObj.submit();\r\n");
      out.write("	}\r\n");
      out.write("</script>\r\n");
      out.write("    \r\n");
      out.write("</body>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
