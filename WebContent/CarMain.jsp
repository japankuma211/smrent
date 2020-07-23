<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%--jstl 라이브러리 사용을 위한 선언 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>


 <c:set var="center" value="${param.center}"/>
 
 <!-- 처음 CarMain.jsp페이지를 실행하면 당연히..  param.값을 받아올수 없기에  조건 주기-->
 <c:if test="${center == null}"> <!-- 넘겨받은 center값이 없으면.. 이동할  Center.jsp주소를 center변수에 저장 -->
 	<c:set var="center" value="Center.jsp"/>
 </c:if>
 
 <center>
 	<table width="1000" height="700">
 		<tr align="center">
 			<td>
 			<!--로그인 처리시... session값 이동 경로
 			login.jsp-> MemberFrontController.java-> CarMain.jsp-> Top.jsp 
 			-->
 				<jsp:include page="${contextPath}/Top.jsp"/>
 			</td> 		
 		</tr>
 		<tr>
 			<td height="500">
 				<jsp:include page="${center}"/>
 			</td> 		
 		</tr>
 		<tr>
 			<td>
 				<jsp:include page="${contextPath}/Bottom.jsp"/>
 			</td> 		
 		</tr>
 	</table>
 </center>
 
</body>
</html>