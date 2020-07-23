package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class MemberDAO {
	
	

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
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
	
	public boolean insertMember(MemberVO memberVO){
		int result=0;
		try {
			System.out.println("DAO insertMember시작");
			con=getConnection();
			
			sql="insert into member2(id,pass,name,joinDate,email,address,phone,mobile) values(?,?,?,now(),?,?,?,?)";
			
			pstmt =con.prepareStatement(sql);
			
			pstmt.setString(1,memberVO.getId()); 
			pstmt.setString(2,memberVO.getPass()); 
			pstmt.setString(3,memberVO.getName()); 
			pstmt.setString(4,memberVO.getEmail());
			pstmt.setString(5,memberVO.getAddress());
			pstmt.setString(6,memberVO.getPhone());
			pstmt.setString(7,memberVO.getMobile());
		
		    result= pstmt.executeUpdate();
		 	
		    if(result !=0) {
		    	return true;
		    }
		 
		} catch (Exception e) {
			System.out.println("insertMember에서 오류"+e.getMessage());
		}finally {
			freeResource();
		}
		
		return false;
	}//insertMember끝
	
	
	public int userCheck(String id,String pass){
		
		int check=-1;
		try {
			con=getConnection();
			
			sql="select pass from member2 where id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				if(pass.equals(rs.getString("pass"))) {
					check=1;
				}else {
					check=0;
				}
			}else {
				check=-1;
			}
		} catch (Exception e) {
			System.out.println("userCheck에서 오류"+e.getMessage());
		}finally {
			freeResource();
		}
		
		return check;
	}//userCheck 끝
	
	public MemberVO getMember(String id){
		int check=-1;
	    MemberVO memberVO=new MemberVO();
		
	    try {
			con=getConnection();
			
			sql="select * from member2 where id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs=pstmt.executeQuery();
			if(rs.next()) {
				
				memberVO.setId(rs.getString("id"));
				memberVO.setPass(rs.getString("pass"));
				memberVO.setName(rs.getString("name"));
				memberVO.setJoinDate(rs.getDate("joinDate"));
			}
		} catch (Exception e) {
			System.out.println("getMember에서 오류"+e.getMessage());
			
		}finally {
			freeResource();
		}
	    return memberVO;
		
	}//getMember끝

	public List getMemberList(){
		
		List memberList=new ArrayList();
		
		try {
			con=getConnection();
			
			sql="select * from member2";
			
			pstmt=con.prepareStatement(sql);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				MemberVO memberVO=new MemberVO();
				memberVO.setId(rs.getString("id"));
				memberVO.setPass(rs.getString("pass"));
				memberVO.setName(rs.getString("name"));
				memberVO.setJoinDate(rs.getDate("joinDate"));
				memberList.add(memberVO);
			}
		} catch (Exception e) {
			System.out.println("getMemberList에서 오류"+e.getMessage());
		}finally {
			freeResource();
		}
		
		return memberList;
	}


}
