package carList;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import carOrder.CarConfirmVO;
import carOrder.CarOrderVO;

@WebServlet("/product/*")
public class CarListController extends HttpServlet {
	
	CarListService carlistservice;
	
	public void init() throws ServletException{
		 
		 carlistservice= new CarListService();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doHendle(request,response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doHendle(request,response);
	}
	
	
	private void doHendle(HttpServletRequest request,HttpServletResponse response)
					throws ServletException, IOException  {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charaset=utf-8");
		
		String action=request.getPathInfo();
		System.out.println("action"+action);
		
		String nextPage=null;
		
		try {
			if(action.equals("/CarcategoryController.do")) {
				
				String carcategory=request.getParameter("carcategory");
				
				Vector<CarListVO> v=carlistservice.getCategoryCarlist(carcategory);
				
				request.setAttribute("v", v);
				
				nextPage="/CarMain.jsp?center=CarList.jsp";
				
			}else if(action.equals("/CarReservation.do")) {
				
				nextPage="/CarMain.jsp?center=CarReservation.jsp";
				
			}else if(action.equals("/CarInfoController.do")) {
				
				int carno=Integer.parseInt(request.getParameter("carno"));
				CarListVO vo=carlistservice.getOneCar(carno);
				
				request.setAttribute("vo", vo);
				nextPage="/CarMain.jsp?center=CarInfo.jsp";
				
			}else if(action.equals("/CarListController.do")) {
				
				Vector<CarListVO> v=carlistservice.getAllCarlist();
				
				request.setAttribute("v", v);
				
				nextPage="/CarMain.jsp?center=CarList.jsp";
				
			}else if(action.equals("/carOption.do")) {
				
				nextPage="/CarMain.jsp?center=CarOption.jsp";
				
			}else if(action.equals("/CarOptionController.do")) {
				
				int carqty = Integer.parseInt(request.getParameter("carqty"));
				int carprice = Integer.parseInt(request.getParameter("carprice"));
				int carreserveday = Integer.parseInt(request.getParameter("carreserveday"));
				int carins = Integer.parseInt(request.getParameter("carins"));
				int carwifi = Integer.parseInt(request.getParameter("carwifi"));
				int carnave = Integer.parseInt(request.getParameter("carnave"));       
				int carbabyseat = Integer.parseInt(request.getParameter("carbabyseat"));
				int totalreserve = carqty * carreserveday * carprice;
				int totaloption =((carins * carreserveday) + (carwifi * carreserveday) + (carbabyseat*carreserveday)) * 10000 * carqty;
				
				CarOrderVO vo= new CarOrderVO();
				
				vo.setCarno(Integer.parseInt(request.getParameter("carno")));
				vo.setCarqty(carqty);
				vo.setCarreserveday(carreserveday);
				vo.setCarins(carins);
				vo.setCarnave(carnave);
				vo.setCarbabyseat(carbabyseat);
				vo.setCarwifi(carwifi);
				vo.setCarbegindate(request.getParameter("carbegindate"));
				
				request.setAttribute("vo", vo);//자바빈객체 저장
				request.setAttribute("totalreserve", totalreserve);
				request.setAttribute("totaloption", totaloption);
				
				nextPage="/CarMain.jsp?center=CarOrder.jsp";
				
			}else if(action.equals("/CarOrderController.do")) {
				
				CarOrderVO vo=new CarOrderVO();
				
				vo.setCarno(Integer.parseInt(request.getParameter("carno")));
				vo.setCarqty(Integer.parseInt(request.getParameter("carqty")));
				vo.setCarreserveday(Integer.parseInt(request.getParameter("carreserveday")));
				vo.setCarbegindate(request.getParameter("carbegindate"));
				vo.setCarins(Integer.parseInt(request.getParameter("carins")));
				vo.setCarwifi(Integer.parseInt(request.getParameter("carwifi")));
				vo.setCarnave(Integer.parseInt(request.getParameter("carnave")));
				vo.setCarbabyseat(Integer.parseInt(request.getParameter("carbabyseat")));
				vo.setMemberphone(request.getParameter("memberphone"));
				vo.setMemberpass(request.getParameter("memberpass"));
				
				carlistservice.insertCarOrder(vo);
				
				nextPage="/product/CarListController.do";
				
			}else if(action.equals("/CarConfirm.do")) {

				nextPage="/CarMain.jsp?center=CarReserveConfirm.jsp";
				
				System.out.println(nextPage);
				
			}else if(action.equals("/CarReserveConfirmController.do")) {
				
			     String memberphone=request.getParameter("memberphone");
			     String memberpass=request.getParameter("memberpass");
			     
				List<CarConfirmVO> v=new ArrayList<CarConfirmVO>();
				
				v=carlistservice.getAllCarOrder(memberphone, memberpass);
				
				if(!v.isEmpty()) {
				      request.setAttribute("v", v);
				   }
				
				nextPage="/CarMain.jsp?center=CarReserveResult.jsp";
			
			}else if(action.equals("/CarConfirmUpdateController.do")) {
				
				String carimg = request.getParameter("carimg");
				int orderid = Integer.parseInt(request.getParameter("orderid"));
				CarConfirmVO vo=carlistservice.getOneOrder(orderid);
				vo.setCarimg(carimg);
				
				request.setAttribute("vo", vo);
				System.out.println(vo.getOrderid());
				System.out.println(vo.getCarwifi());
				nextPage="/CarMain.jsp?center=CarConfirmUpdate.jsp";
			 
			}else if(action.equals("/CarConfirmUpdateProcController.do")) {
				//집에서 체
			  String parammemberpass=request.getParameter("memberpass");
				int orderid = Integer.parseInt(request.getParameter("orderid"));
				int carreserveday = Integer.parseInt(request.getParameter("carreserveday"));
				int carqty = Integer.parseInt(request.getParameter("carqty"));
				int carins = Integer.parseInt(request.getParameter("carins"));
				int carwifi = Integer.parseInt(request.getParameter("carwifi"));
				int carbabyseat = Integer.parseInt(request.getParameter("carbabyseat"));
				String carbegindate = request.getParameter("carbegindate");
				//String memberpass = request.getParameter("memberpass");
				System.out.println(orderid);
				System.out.println(carreserveday);
				System.out.println(carqty);
				System.out.println(carins);
				System.out.println(carwifi);
				System.out.println(carbabyseat);
				System.out.println(carbegindate);
			    String dbMemberPass=carlistservice.selectMemberPass(orderid);
				if(dbMemberPass.equals(parammemberpass)) {
					CarOrderVO vo= new CarOrderVO();
				    
				    vo.setOrderid(orderid);
				    vo.setCarreserveday(carreserveday);
				    vo.setCarbabyseat(carbabyseat);
				    vo.setCarbegindate(carbegindate);
				    vo.setCarqty(carqty);
				    vo.setCarins(carins);
				    vo.setCarwifi(carwifi);
				    vo.setMemberpass(parammemberpass);
				    
				    carlistservice.carOrderUpdate(vo);
				    
					nextPage="/product/CarListController.do";
				}else {
					
					
					response.setContentType("text/html; charset=UTF-8");
					
					PrintWriter out=response.getWriter();
					out.println("<script>");
					out.println("alert('비밀번호가 일치 하지 않습니다');");
					out.println("history.back();");
					out.println("</script>");
					out.close();
					
					return;
				}
				
			    
				
			}else if(action.equals("/CarConfirmDelete.do")) {
				request.setAttribute("orderid", request.getParameter("orderid"));
				nextPage="/CarMain.jsp?center=CarConfirmDelete.jsp";
			}else if(action.equals("/CarConfirmDeleteController.do")) {
				
				int orderid= Integer.parseInt(request.getParameter("orderid"));
				String memberpass = request.getParameter("memberpass");
				
				int result=carlistservice.carOrderDelete(orderid, memberpass);
				if(result!=0){
					 
					 response.setContentType("text/html;charset=utf-8"); 
		             PrintWriter out = response.getWriter(); 
		             out.println("<script>"); 
		             out.println("alert('렌트카 예약정보를 삭제 하였습니다.');");      
		             out.println("</script>"); 
		             nextPage="/product/CarListController.do";
		
				}else{
					
					request.setAttribute("result", result);
			        nextPage="/product/CarConfirmDelete.do";
				}
			}
			if(!nextPage.equals("")) {
				RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
				dispatch.forward(request, response);
			     }
             
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}//finally
  	 
   }//doHandle끝
}//클래스끝
