<%@page import="java.util.List"%> 

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!--[if lt IE 9]>
<script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/IE9.js" type="text/javascript"></script>
<script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/ie7-squish.js" type="text/javascript"></script>
<script src="http://html5shim.googlecode.com/svn/trunk/html5.js" type="text/javascript"></script>
<![endif]-->
<!--[if IE 6]>
 <script src="../script/DD_belatedPNG_0.0.8a.js"></script>
 <script>
   /* EXAMPLE */
   DD_belatedPNG.fix('#wrap');
   DD_belatedPNG.fix('#main_img');   

 </script>
 <![endif]-->
 <link href="${contextPath }/css/default.css" rel="stylesheet" type="text/css">
<link href="${contextPath }/css/subpage.css" rel="stylesheet" type="text/css">
</head>

<%
//세션값 가져오기
String id=(String)session.getAttribute("id");
//세션값이 없으면  ./MemberLogin.me
if(id==null){
	response.sendRedirect(request.getPathInfo()+"/MemberLogin.me");
}
int num=Integer.parseInt(request.getParameter("num"));
int re_ref=Integer.parseInt(request.getParameter("re_ref"));
int re_lev=Integer.parseInt(request.getParameter("re_lev"));
int re_seq=Integer.parseInt(request.getParameter("re_seq"));
%>
<body>
<!-- 게시판(답글쓰기)-->
	<center>
		<article>
		<h1>ReWrite</h1>
		<form action="${contextPath}/pboard/boardReWritePro.do" method="post">
			<input type="hidden" name="num" value="<%=num%>">
			<input type="hidden" name="re_ref" value="<%=re_ref%>">
			<input type="hidden" name="re_lev" value="<%=re_lev%>">
			<input type="hidden" name="re_seq" value="<%=re_seq%>">
				<input type="hidden" name="ip" value="<%request.getRemoteAddr();%>">
			<table id="notice">
				<tr>
					<td>이름</td>
					<td><input type="text" name="name" value="<%=id %>" readonly></td>
				</tr>
				<tr>
					<td>비밀번호</td><td>
					<input type="password" name="pass"></td>
				</tr>
				<tr>
					<td>제목</td>
					<td><input type="text" name="subject" value="[답글]"></td>
				</tr>
				<tr>
					<td>내용</td>
					<td>
						<textarea rows="13" cols="40" name="content"></textarea>
					</td>
				</tr>
			</table>
			<div id="table_search">
				<input type="submit" value="답글쓰기" class="btn">
				<input type="reset" value="다시작성" class="btn">
				<input type="button" value="목록보기" class="btn" 
				   onclick="location.href='${pageContext.request.contextPath}/pboard/BoardList.do'">
			</div>
		</form>
		<div class="clear"></div>
		<div id="page_control">
		</div>
		</article>
	</center>
	<!-- 게시판(답글쓰기)-->
</body>
</html>