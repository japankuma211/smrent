package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import carList.CarListVO;

public class BoardDAO {
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	CarListVO carlistvo = null;
	String sql ="";
	
	private Connection getConnection() throws Exception{
				
				
				Context init=new InitialContext();
				
				DataSource ds=(DataSource)init.lookup("java:comp/env/jdbc/jspbeginner");
				
				Connection con =ds.getConnection();
			return con;
		}//getConnection끝
		
		public void freeResource() {
			try {
				
				if(rs !=null) { rs.close();}
				if(pstmt !=null) { pstmt.close();}
				if(con !=null) { con.close();}
			} catch (Exception e) {
				System.out.println("freeResource에서 오류"+e.getMessage());
			}
		}//freeResource끝;
		
	public void insertBoard(BoardVO bvo){
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="";
		ResultSet rs=null;
		int num=0;
		
		try {
			
			
			con=getConnection();

			sql="select max(num) from board2";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()){
				num=rs.getInt(1)+1; 
			}
			System.out.println("num="+num);
			
			sql="insert into board2(num,name,pass,subject,content,re_ref,re_lev,re_seq,readcount,date,ip) values(?,?,?,?,?,?,?,?,?,now(),?)";
			pstmt=con.prepareStatement(sql);
			
			pstmt.setInt(1, num); 
			pstmt.setString(2, bvo.getName());
			pstmt.setString(3, bvo.getPass());
			pstmt.setString(4, bvo.getSubject());
			pstmt.setString(5, bvo.getContent());
			pstmt.setInt(6, num); 
			pstmt.setInt(7, 0);  
			pstmt.setInt(8, 0);  
			pstmt.setInt(9, 0); 
			pstmt.setString(10, bvo.getIp());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
            System.out.println("insertBoard에서오류"+e.getMessage());
		}finally{
			
			freeResource();
		}
	}// insertBoard끝
	
	public int getBoardCount(){
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="";
		ResultSet rs=null;
		int count=0;
		try {
			
			con=getConnection();
			
			sql="select count(*) from board2";
			pstmt=con.prepareStatement(sql);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				count=rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			freeResource();
		}
		return count;
	}
	
	public int getBoardCount(String search){
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="";
		ResultSet rs=null;
		int count=0;
		try {
			
			con=getConnection();
			
			sql="select count(*) from board2 where subject like ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, "%"+search+"%");
			
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				count=rs.getInt(1);
			}
		} catch (Exception e) {
			   System.out.println("getBoardCount에서오류"+e.getMessage());
		}finally{
			freeResource();
		}
		return count;
	}
	
	public List<BoardVO> getBoardList(int startRow,int pageSize){
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="";
		ResultSet rs=null;
		List<BoardVO> boardList=new ArrayList<>();
		try{
			
			con=getConnection();
			
			sql="select * from board2 order by re_ref desc, re_seq asc limit ?,?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, startRow-1);
			pstmt.setInt(2, pageSize);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				BoardVO bvo=new BoardVO();
				bvo.setContent(rs.getString("content"));
				bvo.setDate(rs.getDate("date"));	
				bvo.setIp(rs.getString("ip"));
				bvo.setName(rs.getString("name"));
				bvo.setNum(rs.getInt("num"));
				bvo.setPass(rs.getString("pass"));
				bvo.setRe_lev(rs.getInt("re_lev"));
				bvo.setRe_ref(rs.getInt("re_ref"));
				bvo.setRe_seq(rs.getInt("re_seq"));
				bvo.setReadcount(rs.getInt("readcount"));
				bvo.setSubject(rs.getString("subject"));
				
				boardList.add(bvo);
			}
		}catch(Exception e){
			System.out.println("getBoardList에서오류"+e.getMessage());
		}finally {
			freeResource();
		}
		return boardList;
	}
	
	public List<BoardVO> getBoardList(int startRow,int pageSize,String search){
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="";
		ResultSet rs=null;
		List<BoardVO> boardList=new ArrayList<>();
		try{
			
			con=getConnection();
			
			sql="select * from board2 where subject like ? order by re_ref desc, re_seq asc limit ?,?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, "%"+search+"%");
			pstmt.setInt(2, startRow-1);
			pstmt.setInt(3, pageSize);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				BoardVO bvo=new BoardVO();
				bvo.setContent(rs.getString("content"));
				bvo.setDate(rs.getDate("date"));
			
				bvo.setIp(rs.getString("ip"));
				bvo.setName(rs.getString("name"));
				bvo.setNum(rs.getInt("num"));
				bvo.setPass(rs.getString("pass"));
				bvo.setRe_lev(rs.getInt("re_lev"));
				bvo.setRe_ref(rs.getInt("re_ref"));
				bvo.setRe_seq(rs.getInt("re_seq"));
				bvo.setReadcount(rs.getInt("readcount"));
				bvo.setSubject(rs.getString("subject"));
				boardList.add(bvo);
			}
		}catch(Exception e){
			System.out.println("getBoardList에서오류+스트링"+e.getMessage());
	    }finally {
			freeResource();
		}
		return boardList;
	}
	
	public BoardVO getBoard(int num){
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="";
		ResultSet rs=null;
		BoardVO bvo=null;
		try {
			
			con=getConnection();
			
			sql="select * from board2 where num=?";
			
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				bvo=new BoardVO();
				
				bvo.setContent(rs.getString("content"));
				bvo.setDate(rs.getDate("date"));
				bvo.setIp(rs.getString("ip"));
				bvo.setName(rs.getString("name"));
				bvo.setNum(rs.getInt("num"));
				bvo.setPass(rs.getString("pass"));
				bvo.setRe_lev(rs.getInt("re_lev"));
				bvo.setRe_ref(rs.getInt("re_ref"));
				bvo.setRe_seq(rs.getInt("re_seq"));
				bvo.setReadcount(rs.getInt("readcount"));
				bvo.setSubject(rs.getString("subject"));
			}
		} catch (Exception e) {
			System.out.println("getBoard에서오류"+e.getMessage());
		}finally{
			freeResource();
		}
		return bvo;
	}//getBoard끝
	
	public void updateReadcount(int num){
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="";
		ResultSet rs=null;
		try {
			
			con=getConnection();
			
			sql="update board2 set readcount=readcount+1 where num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			freeResource();
		}
	}
	
	public int updateBoard(BoardVO bvo){
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="";
		ResultSet rs=null;
		int check=-1;
		try {
			
			con=getConnection();
			
			sql="select pass from board2 where num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, bvo.getNum());
			
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				if(bvo.getPass().equals(rs.getString("pass"))){
					check=1;
					
	sql="update board2 set name=?,subject=?,content=? where num=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setString(1, bvo.getName());
					pstmt.setString(2, bvo.getSubject());
					pstmt.setString(3, bvo.getContent());
					pstmt.setInt(4, bvo.getNum());
					
					pstmt.executeUpdate();
				}else{
					check=0;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			freeResource();
		}
		return check;
	}
	
	public int deleteBoard(int num,String pass){
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="";
		ResultSet rs=null;
		int check=-1;
		try {
			
			con=getConnection();
			
			sql="select pass from board2 where num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				if(pass.equals(rs.getString("pass"))){
					check=1;
					
					sql="delete from board2 where num=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setInt(1, num);
					
					pstmt.executeUpdate();
				}else{
					check=0;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			freeResource();
		}
		return check;
	}
	
	public void reInsertBoard(BoardVO bvo){
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="";
		ResultSet rs=null;
		int num=0;
		try {
			con=getConnection();
		
			sql="select max(num) from board2";
			pstmt=con.prepareStatement(sql);
			
			rs=pstmt.executeQuery();
			if(rs.next()){
				num=rs.getInt(1)+1;
			}
			
			sql="update board2 set re_seq=re_seq+1 where re_ref= ? and re_seq > ?";
			
			pstmt=con.prepareStatement(sql);
			
			pstmt.setInt(1, bvo.getRe_ref());
			pstmt.setInt(2, bvo.getRe_seq());
			pstmt.executeUpdate();
		
			sql="insert into board2(num,name,pass,subject,content,re_ref,re_lev,re_seq,readcount,date,ip) values(?,?,?,?,?,?,?,?,?,now(),?)";
			
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num); //num
			pstmt.setString(2, bvo.getName());
			pstmt.setString(3, bvo.getPass());
			pstmt.setString(4, bvo.getSubject());
			pstmt.setString(5, bvo.getContent());
			pstmt.setInt(6, bvo.getRe_ref()); 
			pstmt.setInt(7, bvo.getRe_lev()+1);  
			pstmt.setInt(8, bvo.getRe_seq()+1);  
			pstmt.setInt(9, 0); 
			pstmt.setString(10, bvo.getIp());
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("reInsertBoard에서오류"+e.getMessage());
		}finally{
			freeResource();
		}
	}
	
}//클래스
