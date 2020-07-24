<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%--jstl 라이브러리 사용을 위한 선언 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>      
 <c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${contextPath}/css/product.css">
<title>Insert title here</title>
</head>
<body>
	<center>
		<img alt="" src="${contextPath }/img/cis.jpg" border="0">
		<!-- 자동차 예약에 시 옵션 선택하기 버튼을 눌렀을떄... 옵션 선택 페이지로  요청 -->
		<form action="${contextPath}/product/carOption.do" method="post">
				<table width="1000" border="0">
					<tr align="center">
						<td rowspan="6" width="600">
							<img alt="" src="${contextPath }/img/${vo.carimg}" width="500" border="0">
						</td>
						<td align="center" width="200">	차량이름</td>
						<td align="center" width="200">	${vo.carname}</td>
					</tr>
					<tr>
						<td align="center" width="200">	대여수량</td>
						<td align="center" width="200">	
							<select name="carqty">
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
							</select>
						</td>
					</tr>
					<tr>
						<td align="center" width="200">	차량분류</td>
						<td align="center" width="200">	
							${requestScope.vo.carcategory }
						</td>
					</tr>
					<tr>
						<td align="center" width="200">	대여금액</td>
						<td align="center" width="200">	
							${vo.carprice }
						</td>
					</tr>
					<tr>
						<td align="center" width="200">	제조회사</td>
						<td align="center" width="200">	
							${vo.carcompany }
						</td>
					</tr>
					<tr>
						<td align="center" width="200">
							<!-- 옵션선택 페이지로 예약할 차번호, 이미지이름, 차가격 넘기기-->
							<input type="hidden" name="carno" value="${vo.carno }">
							<input type="hidden" name="carimg" value="${vo.carimg }">
							<input type="hidden" name="carprice" value="${vo.carprice }">
							<!-- CarList.jsp로 이동 -->
							<input type="button" value="이전" 
							onclick="location.href='${contextPath }/product/CarListController.do'">
						</td>
						<td align="center" width="200">	
							<input type="submit" value="옵션선택하기">
						</td>
					</tr>
				</table>
		</form>
		<p>
		<b>차량 정보 상세 보기</b><p>
		${vo.carinfo }
		
	</center>

<script src="${contextPath}/js/jquery-3.2.1.min.js"></script>
<script src="${contextPath}/js/popper.js"></script>
<script src="${contextPath}/js/bootstrap.min.js"></script>
<script src="${contextPath}/js/easing.js"></script>
<script src="${contextPath}/js/parallax.min.js"></script>
<script src="${contextPath}/js/custom.js"></script>
<script src="${contextPath}/js/product_custom.js"></script>
<script>
   //후기
$(document).ready(function(){
	reviewList();
});
   
   //리뷰목록 보여주기
   
   function reviewList(){
	   
	   var url1='${contextPath}/reviewServlet/listReview.do';
	   var reviewListInfo1='{"carno":"'+${vo.carno}+'"}';
	   var carno = ${vo.carno};
	   
	   $.ajax({
		   url :url,
		   type : 'post',
		   data : {revewListInfo : reviewListInfo1},
		   success : function(data){
			   var a='';
			   var b='';
			   
			   if(data == null || data == ''){
				   a += '<li class=" review clearfix">';
	       			a += '<p>등록된 리뷰가 없습니다.</p>';
	       			a += '</li>';
	       			$(".reviews_container ul").html(a);	
	       			return;
			   }
			   var jsonInfo = JSON.parse(data);
		       	
	        	$.each(jsonInfo, function(index, entry){
	        	
		        	
	        		$.each(entry, function(key, value){
	        			
	        			var no = value.reviewNo;
			    	    var name = value.userName;
	 			       	var content = value.reviewContent;
		        	   	var date = value.reviewDate;	
		           		var id = value.userId;
						var rating = value.starRating;
		           		var num = value.reviewNum;
		           		
				   a += '<li class=" review clearfix" id="review'+no+'">';
					a += '<div class="review_image"><img src="${contextPath}/images/review_1.jpg" alt=""></div>';
					a += '<div class="review_content">';
					a += '<div class="review_name"><b>'+name+'</b></div>';
					a += '<div class="review_date">'+date+'</div>'
					a += '<div class="rating rating_'+rating+' review_rating" data-rating="'+rating+'">';
					a += '<i class="fa fa-star"></i>';
					a += '<i class="fa fa-star"></i>';
					a += '<i class="fa fa-star"></i>';
					a += '<i class="fa fa-star"></i>';
					a += '<i class="fa fa-star"></i>';
					a += '</div>';
					a += '<div class="review_text">';
					a += '<p>'+content+'</p>';
					if('${userId}' == id || '${userId}' == 'admin'){
						a += '<button type="button" class="btn btn-sm btn-danger mt-2" onclick="reviewDelete('+no+')">삭제</button>';	
		   }
					a += '</div>';
					a += '</div>';
					a += '</li>';
					
			$(".reviews_container ul").html(a);	
	   });var rCount = entry.length;
		$("#rCount").html(rCount);
		$("#rCount2").html(rCount);
  	});
},
error: function(err) {
   alert("ajax 통신에러");
}
});
	        		
   }
</script>

</body>
</html>