package member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.crypto.provider.RSACipher;
import com.sun.org.apache.xml.internal.security.Init;

@WebServlet("/member/*")
public class MemberController extends HttpServlet{
	MemberDAO dao;
	MemberService memberservice;
	
	public void init() throws ServletException{
		 dao=new MemberDAO();
		 memberservice=new MemberService();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doHandle(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doHandle(request, response);
	}
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charaset=utf-8");
		
		HttpSession session = request.getSession();
		
		String action=request.getPathInfo();
		System.out.println("action:"+action);
		
		String nextPage=null;
		
		if(action.equals("/MemberJoin.me")) {
			
			nextPage="/CarMain.jsp?center=Member/join.jsp";
		
		} else if(action.equals("/MemberJoinAction.me")) {
			
			
			
			System.out.println(request.getParameter("id"));
			System.out.println(request.getParameter("pass"));
			System.out.println(request.getParameter("name"));
			System.out.println(request.getParameter("email"));
			System.out.println(request.getParameter("address"));
			System.out.println(request.getParameter("phone"));
			System.out.println(request.getParameter("mobile"));
			
			MemberVO memberVO=new MemberVO(
					request.getParameter("id"),
					request.getParameter("pass"),
					request.getParameter("name"),
					request.getParameter("email"),
					request.getParameter("address"),
					request.getParameter("phone"),
					request.getParameter("mobile"));
			 
		     	
		     	 
		  boolean result=memberservice.insertMember(memberVO);
			System.out.println(result);
			
			if(result == false) {
				System.out.println("회원가입실패");
				nextPage="/member/MemberJoin.me";
			
			}else {
				nextPage="/member/MemberLogin.me";
			}
	      System.out.println(nextPage);
		}else if(action.equals("/MemberLoginAction.me")) {
			
			int check=memberservice.userCheck(request.getParameter("id"), request.getParameter("pass"));
		     if(check == 1 ) {
		    	session.setAttribute("id",request.getParameter("id") );
		    	
		    	nextPage="/CarMain.jsp";
		     }
		}else if(action.equals("/MemberLogin.me")) {
			
			nextPage="/CarMain.jsp?center=Member/login.jsp";
		}else if(action.equals("/MemberLogout.me")) {
			
			session.invalidate();
			
			nextPage="/CarMain.jsp";
		}else if(action.equals("/Main.me")) {
			nextPage="/CarMain.jsp";
		}
		
		if(!nextPage.equals("")) {
			RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
			dispatch.forward(request, response);
		}
		
	}//dohandle끝

}
