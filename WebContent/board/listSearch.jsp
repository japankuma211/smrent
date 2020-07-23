<%@page import="board.BoardVO"%>
<%@page import="java.util.List"%> 

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%String contextPath=request.getContextPath(); %>
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
 <link href="<%=contextPath %>/css/default.css" rel="stylesheet" type="text/css">
<link href="<%=contextPath %>/css/subpage.css" rel="stylesheet" type="text/css">
</head>
<%
// request.setAttribute("count", count); //모든속성저장 Integer -> Object형저장
// request.setAttribute("boardList", boardList); // List -> Object 저장
// request.setAttribute("pageNum", pageNum); //String -> Object 저장
// request.setAttribute("pageCount", pageCount);
// request.setAttribute("pageBlock", pageBlock);
// request.setAttribute("startPage", startPage);
// request.setAttribute("endPage", endPage);
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
		    		BoardVO vo=boardList.get(i);
		    		%>
		 <tr onclick="location.href='<%=contextPath%>/pboard/BoardContent.do?num=<%=vo.getNum()%>&pageNum=<%=pageNum%>'">
		 	<td><%=vo.getNum() %></td>		 
			 <td class="left">
			 <%
			 int wid=0;
			 if(vo.getRe_lev()>0){
				 wid=vo.getRe_lev()*10;
			 %>
				 <img src="<%=contextPath %>/img/level.gif" width="<%=wid%>">
				 <img src="<%=contextPath %>/img/re.gif">
			 <%
			 }
			 %>
			 	<%=vo.getSubject() %>
			 </td>
		    <td><%=vo.getName() %></td>
		    <td><%=vo.getDate() %></td>
		    <td><%=vo.getReadcount() %></td>
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
		<input type="button" value="글쓰기" class="btn" onclick="location.href='<%=contextPath%>/pboard/BoardWrite.do'">
		</div>	
			<%
		}
		%>
		<div id="table_search">
			<form action="<%=contextPath%>/pboard/BoardListSearch.do">
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
			<a href="<%=contextPath%>/pboard/BoardList.do?pageNum=<%=startPage-pageBlock%>">Prev</a>
		<%
			}
			for(int i=startPage;i<=endPage;i++){
		%>
			<a href="<%=contextPath%>/pboard/BoardList.do?pageNum=<%=i%>"><%=i %></a>
		<%
			}
			if(endPage < pageCount){
		%>
			<a href="<%=contextPath%>/pboard/BoardList.do?pageNum=<%=startPage+pageBlock%>">Next</a>
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