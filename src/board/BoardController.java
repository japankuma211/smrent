package board;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;






@WebServlet("/pboard/*")
public class BoardController extends HttpServlet {
	

	@Override
	public void init() throws ServletException {
		
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		doHandle(request,response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		doHandle(request,response);
	}
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		String nextPage = "";
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		BoardService service=new BoardService();
		String action = request.getPathInfo();
		System.out.println("action: " + action);
		
		try {
			if(action.equals("/BoardList.do")) {
			
				int count=service.getBoardCount();
				int pageSize=5;
				
				String pageNum=request.getParameter("pageNum");
				System.out.println(pageNum);
				
				if(pageNum == null){
					pageNum="1";
				}
				
				int currentPage=Integer.parseInt(pageNum);
				int startRow=(currentPage-1)*pageSize+1;
				
				int endRow=currentPage*pageSize;
				List<BoardVO> boardList=null;
				if(count!=0){
					boardList=service.getBoardList(startRow, pageSize);
					
				}
				
				int pageCount =count/pageSize+(count%pageSize==0?0:1);
				int pageBlock=3;
				int startPage=((currentPage-1)/pageBlock)*pageBlock+1;
				int endPage=startPage+pageBlock-1;
				if(endPage > pageCount){
					endPage = pageCount;
				}
				
				request.setAttribute("count", count); 
				request.setAttribute("boardList", boardList); 
				request.setAttribute("pageNum", pageNum); 
				request.setAttribute("pageCount", pageCount);
				request.setAttribute("pageBlock", pageBlock);
				request.setAttribute("startPage", startPage);
				request.setAttribute("endPage", endPage);
				
				nextPage="/CarMain.jsp?center=board/list.jsp";
				
			}else if(action.equals("/boardWrite.do")) {
				
				nextPage="/CarMain.jsp?center=board/write.jsp";
				System.out.println(nextPage);
				
			}else if(action.equals("/BoardContent.do")) {
				
				int num=Integer.parseInt(request.getParameter("num"));
				String pageNum=request.getParameter("pageNum");
			   service.updateReadcount(num);
			   BoardVO vo=service.getBoard(num);
			   
			   request.setAttribute("vo", vo);
			   request.setAttribute("pageNum", pageNum);
			
			    nextPage="/CarMain.jsp?center=board/content.jsp";
			    
			}else if(action.equals("/boardUpdate.do")) {
				
				int num=Integer.parseInt(request.getParameter("num"));
				String pageNum=request.getParameter("pageNum");
				
				BoardVO vo=service.getBoard(num);
				request.setAttribute("vo", vo);
				request.setAttribute("pageNum", pageNum);
				
				nextPage="/CarMain.jsp?center=board/update.jsp";
				
			}else if(action.equals("/BoardUpdatePro.do")) {
				
				String pageNum=request.getParameter("pageNum");
				BoardVO vo=new BoardVO();
				vo.setContent(request.getParameter("content"));
				vo.setName(request.getParameter("name"));
				vo.setPass(request.getParameter("pass"));
				vo.setSubject(request.getParameter("subject"));
				vo.setNum(Integer.parseInt(request.getParameter("num")));
				
				int check=service.updateBoard(vo);
				if(check==0){
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out=response.getWriter();
					out.println("<script>");
					out.println("alert('비밀번호틀림');");
					out.println("history.back();");
					out.println("</script>");
					out.close();
					
				}else if(check==-1){
					response.setContentType("text/html; charset=UTF-8");

					PrintWriter out=response.getWriter();
					out.println("<script>");
					out.println("alert('num없음');");
					out.println("history.back();");
					out.println("</script>");
					out.close();
					
				}else if(check == 1) {
					response.setContentType("text/html; charset=UTF-8");

					PrintWriter out=response.getWriter();
					out.println("<script>");
					out.println("alert('수정성공');");
					out.println("location.href='"+request.getContextPath()+"/pboard/BoardList.do?pageNum="+pageNum+"';");
					out.println("</script>");
					out.close();
					
				}
				return ;
			}else if(action.equals("/boardDelete.do")) {
				String pageNum=request.getParameter("pageNum");
				String num=request.getParameter("num");
				nextPage="/CarMain.jsp?center=board/delete.jsp?pageNum="+pageNum+"&num="+num;
				
			}else if(action.equals("/boardDeletePro.do")) {
				
				String pageNum=request.getParameter("pageNum");
				int num=Integer.parseInt(request.getParameter("num"));
				String pass=request.getParameter("pass");
				
				int check=service.deleteBoard(num, pass);
				
				if(check==0){
					response.setContentType("text/html; charset=UTF-8");

					PrintWriter out=response.getWriter();
					out.println("<script>");
					out.println("alert('비밀번호틀림');");
					out.println("history.back();");
					out.println("</script>");
					out.close();
					
				}else if(check==-1){
					response.setContentType("text/html; charset=UTF-8");

					PrintWriter out=response.getWriter();
					out.println("<script>");
					out.println("alert('num없음');");
					out.println("history.back();");
					out.println("</script>");
					out.close();
					
				}else if(check == 1) {
					
					response.setContentType("text/html; charset=UTF-8");

					PrintWriter out=response.getWriter();
					out.println("<script>");
					out.println("alert('삭제성공');");
					out.println("location.href='"+request.getContextPath()+"/pboard/BoardList.do?pageNum="+pageNum+"';");
					out.println("</script>");
					out.close();
				}
				return;
				
			}else if(action.equals("/boardWritePro.do")) {
				
				BoardVO vo=new BoardVO();
				
				vo.setName(request.getParameter("name"));
				vo.setPass(request.getParameter("pass"));
				vo.setSubject(request.getParameter("subject"));
				vo.setContent(request.getParameter("content"));
				vo.setIp(request.getParameter("ip"));
				System.out.println(request.getParameter("ip"));
				service.insertBoard(vo);
				
				nextPage="/pboard/BoardList.do";
				
			}else if(action.equals("/boardReWrite.do")) {
				
				nextPage="/CarMain.jsp?center=board/reWrite.jsp";
				
			} else if(action.equals("/boardReWritePro.do")) {
				
				BoardVO vo=new BoardVO();
				
				vo.setNum(Integer.parseInt(request.getParameter("num")));
				vo.setRe_ref(Integer.parseInt(request.getParameter("re_ref")));
				vo.setRe_lev(Integer.parseInt(request.getParameter("re_lev")));
				vo.setRe_seq(Integer.parseInt(request.getParameter("re_seq")));
				vo.setName(request.getParameter("name"));
				vo.setPass(request.getParameter("pass"));
				vo.setSubject(request.getParameter("subject"));
				vo.setContent(request.getParameter("content"));
				vo.setIp(request.getParameter("ip"));
				System.out.println(request.getParameter("ip"));
				
				service.reInsertBoard(vo);
				
				nextPage="/pboard/BoardList.do";
				
			}else if(action.equals("/boardListSearch.do")) {
				
				int count=service.getBoardCount(request.getParameter("search"));
				
				int pageSize=5;
				
				String pageNum=request.getParameter("pageNum");
				if(pageNum==null){
					pageNum="1";
				}
				int currentPage=Integer.parseInt(pageNum);
				int startRow=(currentPage-1)*pageSize+1;
		
				int endRow=currentPage*pageSize;
				List<BoardVO> boardList=null;
				if(count!=0){
					boardList=service.getBoardList(startRow, pageSize, request.getParameter("search"));
				
				}
				
				int pageCount =count/pageSize+(count%pageSize==0?0:1);
				int pageBlock=3;
				int startPage=((currentPage-1)/pageBlock)*pageBlock+1;
				int endPage=startPage+pageBlock-1;
				
				if(endPage > pageCount){
					endPage = pageCount;
				}
				request.setAttribute("count", count); 
				request.setAttribute("boardList", boardList); 
				request.setAttribute("pageNum", pageNum);
				request.setAttribute("pageCount", pageCount);
				request.setAttribute("pageBlock", pageBlock);
				request.setAttribute("startPage", startPage);
				request.setAttribute("endPage", endPage);
				
				nextPage="/CarMain.jsp?center=board/listSearch.jsp";
			}else if(action.equals("/boardListSearch.do")) {
				   String search=request.getParameter("serch");
				 
				   int count=service.getBoardCount(search);
				  
				  int pageSize=5;
				  
				  String pageNum=request.getParameter("pageNum");
					if(pageNum==null){
						pageNum="1";
					}
					int currentPage=Integer.parseInt(pageNum);
					int startRow=(currentPage-1)*pageSize+1;
					int endRow=currentPage*pageSize;
					List<BoardVO> boardList=null;
					if(count!=0){
						boardList=service.getBoardList(startRow, pageSize, search);
					}
					int pageCount =count/pageSize+(count%pageSize==0?0:1);

					int pageBlock=3;
					int startPage=((currentPage-1)/pageBlock)*pageBlock+1;
					int endPage=startPage+pageBlock-1;
					if(endPage > pageCount){
						endPage = pageCount;
					}
					request.setAttribute("count", count); 
					request.setAttribute("boardList", boardList); 
					request.setAttribute("pageNum", pageNum); 
					request.setAttribute("pageCount", pageCount);
					request.setAttribute("pageBlock", pageBlock);
					request.setAttribute("startPage", startPage);
					request.setAttribute("endPage", endPage);
			
			       nextPage="/CarMain.jsp?center=board/listSearch.jsp";
			}
			
			
			
			 if(!nextPage.equals("")) {
				 
				 
					RequestDispatcher dispatch = 
							request.getRequestDispatcher(nextPage);
					dispatch.forward(request, response);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}

}
