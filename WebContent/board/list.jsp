<%@page import="board.BoardVO"%>
<%@page import="java.util.List"%> 
 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
 <link href="${pageContext.request.contextPath}/css/default.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/subpage.css" rel="stylesheet" type="text/css">
</head>
<%
int count=((Integer)request.getAttribute("count")).intValue();
List<BoardVO> boardList=(List<BoardVO>)request.getAttribute("boardList");
String pageNum=(String)request.getAttribute("pageNum");
int pageCount=((Integer)request.getAttribute("pageCount")).intValue();
int pageBlock=((Integer)request.getAttribute("pageBlock")).intValue();
int startPage=((Integer)request.getAttribute("startPage")).intValue();
int endPage=((Integer)request.getAttribute("endPage")).intValue();

%>
<body>
	<center>
		<!-- 게시판 -->
		<article>
		<h1>[전체글개수:<%=count %>]</h1>
		<table id="notice">
		<tr><th class="tno">No.</th>
		    <th class="ttitle">Title</th>
		    <th class="twrite">Writer</th>
		    <th class="tdate">Date</th>
		    <th class="tread">Read</th>
		</tr>
		    <%
		    if(count!=0){
		    	for(int i=0;i<boardList.size();i++){
		    		BoardVO bvo=boardList.get(i);
		    		%>
		 <tr onclick="location.href='${pageContext.request.contextPath}/pboard/BoardContent.do?num=<%=bvo.getNum()%>&pageNum=<%=pageNum%>'">
		 	<td><%=bvo.getNum() %></td>		 
			 <td class="left">
			 <%
			 int wid=0;
			 if(bvo.getRe_lev()>0){
				 wid=bvo.getRe_lev()*10;
			 %>
				 <img src="${pageContext.request.contextPath}/img/level.gif" width="<%=wid%>">
				 <img src="${pageContext.request.contextPath}/img/re.gif">
			 <%
			 }
			 %>
			 	<%=bvo.getSubject() %>
			 </td>
		    <td><%=bvo.getName() %></td>
		    <td><%=bvo.getDate() %></td>
		    <td><%=bvo.getReadcount() %></td>
		  </tr>   		
		     <%
		    	}
		    }
		    %>
		</table>
		<%
		String id=(String)session.getAttribute("id");
		if(id!=null){
			%>
		<div id="table_search">
		<input type="button" value="글쓰기" class="btn" onclick="location.href='${pageContext.request.contextPath}/pboard/boardWrite.do'">
		</div>	
			<%
		}
		%>
		<div id="table_search">
			<form action="${pageContext.request.contextPath}/pboard/boardListSearch.do">
				<input type="text" name="search" class="input_box">
				<input type="submit" value="검색" class="btn">
			</form>
		</div>
		<div class="clear"></div>
		<div id="page_control">
		<%
		if(count!=0){
			if(startPage > pageBlock){
		%>
			<a href="${pageContext.request.contextPath}/pboard/BoardList.do?pageNum=<%=startPage-pageBlock%>">Prev</a>
		<%
			}
			for(int i=startPage;i<=endPage;i++){
		%>
			<a href="${pageContext.request.contextPath}/pboard/BoardList.do?pageNum=<%=i%>"><%=i %></a>
		<%
			}
			if(endPage < pageCount){
		%>
			<a href="${pageContext.request.contextPath}/pboard/BoardList.do?pageNum=<%=startPage+pageBlock%>">Next</a>
		<%
			}
		}
		%>
		</div>
		</article>
		<!-- 게시판 -->
	</center>
</body>
</html>