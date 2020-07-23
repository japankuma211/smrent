package carList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import carOrder.CarConfirmVO;
import carOrder.CarOrderVO;

public class CarListDAO {
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
	
	public Vector<CarListVO> getAllCarlist() {
		
		Vector<CarListVO> v = new Vector();
		
		

		try {
			
			con=getConnection();
			
			String sql = "select * from carlist";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
		
			while (rs.next()) {
				
				carlistvo = new CarListVO();
				
				carlistvo.setCarno(rs.getInt("carno")); 
				carlistvo.setCarname(rs.getString("carname")); 
				carlistvo.setCarcompany(rs.getString("carcompany"));
				carlistvo.setCarprice(rs.getInt("carprice"));
				carlistvo.setCarusepeople(rs.getInt("carusepeople"));
				carlistvo.setCarinfo(rs.getString("carinfo"));
				carlistvo.setCarimg(rs.getString("carimg"));
				carlistvo.setCarcategory(rs.getString("carcategory"));
				
				v.add(carlistvo);
			}
			
			con.close();
		} catch (Exception e) {
			System.out.println("getAllCarlist오류"+e.getMessage());
		}
		return v; 
	}// getAllCarlist()메소드 끝
    
	public Vector<CarListVO> getCategoryCarlist(String carcategory){
		Vector<CarListVO> v = new Vector<CarListVO>();
		try {
			con=getConnection();
			
			sql = "select * from carlist where carcategory=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, carcategory);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				carlistvo = new CarListVO();
				
				carlistvo.setCarno(rs.getInt("carno"));
				carlistvo.setCarname(rs.getString("carname")); 
				carlistvo.setCarcompany(rs.getString("carcompany"));
				carlistvo.setCarprice(rs.getInt("carprice"));
				carlistvo.setCarusepeople(rs.getInt("carusepeople"));
				carlistvo.setCarinfo(rs.getString("carinfo"));
				carlistvo.setCarimg(rs.getString("carimg"));
				carlistvo.setCarcategory(rs.getString("carcategory"));
				
				v.add(carlistvo);
			}
		} catch (Exception e) {
			System.out.println("getCategoryCarlist에서 오류"+e.getMessage());
		}finally {
			freeResource();
		}
		return v;
	}//getCategoryCarlist
	
	public CarListVO getOneCar(int carno) {
		carlistvo = new CarListVO();
		try {
			con= getConnection();
			
			sql = "select * from carlist where carno=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, carno);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				carlistvo.setCarno(rs.getInt("carno"));
				carlistvo.setCarname(rs.getString("carname")); 
				carlistvo.setCarcompany(rs.getString("carcompany"));
				carlistvo.setCarprice(rs.getInt("carprice"));
				carlistvo.setCarusepeople(rs.getInt("carusepeople"));
				carlistvo.setCarinfo(rs.getString("carinfo"));
				carlistvo.setCarimg(rs.getString("carimg"));
				carlistvo.setCarcategory(rs.getString("carcategory"));
			}
		} catch (Exception e) {
			System.out.println("getOneCar에서 오류"+e.getMessage());
		}finally {
			freeResource();
		}
		
		return carlistvo;
	}//getOneCar끝
	
	
	public void insertCarOrder(CarOrderVO ordervo) {
		int orderid=0;
		try {
			con=getConnection();
			
			
			
			sql="select max(orderid) from carorder";
			
			pstmt = con.prepareStatement(sql);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				orderid=rs.getInt(1)+1;
				
			}else {
				orderid=1;
			}
			
			sql = "insert into carorder(carno,carqty,carreserveday,"
			+ "carbegindate,carins,carwifi,carnave,carbabyseat,memberphone,memberpass,orderid) "
			+ "values(?,?,?,?,?,?,?,?,?,?,?)";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, ordervo.getCarno());
			pstmt.setInt(2, ordervo.getCarqty());
			pstmt.setInt(3, ordervo.getCarreserveday());
			pstmt.setString(4, ordervo.getCarbegindate());
			pstmt.setInt(5, ordervo.getCarins());
			pstmt.setInt(6, ordervo.getCarwifi());
			pstmt.setInt(7, ordervo.getCarnave());
			pstmt.setInt(8, ordervo.getCarbabyseat());
			pstmt.setString(9, ordervo.getMemberphone());
			pstmt.setString(10, ordervo.getMemberpass());
			pstmt.setInt(11, orderid);
			
			pstmt.executeUpdate();
			
			
			
		} catch (Exception e) {
			System.out.println("insertCarOrder에서 오류"+e.getMessage());
		}finally {
			freeResource();
		}
	}//insertCarOrder메소드 끝
	
	
	public List<CarConfirmVO> getAllCarOrder(String memberphone, String memberpass){
		System.out.println("dao도착");
		List<CarConfirmVO> v = new ArrayList();
		
		
		try {
			 con= getConnection();
			 
			sql = "select * from carorder natural join carlist where "
					+ "now() < str_to_date(carbegindate ,'%Y-%m-%d') and "
					+ "memberphone=? and memberpass=?";
			System.out.println("sql시작");
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberphone);
			pstmt.setString(2, memberpass);
			
			rs = pstmt.executeQuery();
		   System.out.println(rs);
		   
			while(rs.next()){
				CarConfirmVO vo = new CarConfirmVO();
				
				System.out.println(rs.getString("carname"));
				vo.setOrderid(rs.getInt("orderid"));
				vo.setCarqty(rs.getInt("carqty"));
				vo.setCarreserveday(rs.getInt("carreserveday"));
				vo.setCarbegindate(rs.getString("carbegindate"));
				vo.setCarins(rs.getInt("carins"));
				vo.setCarwifi(rs.getInt("carwifi"));
				vo.setCarnave(rs.getInt("carnave"));
				vo.setCarbabyseat(rs.getInt("carbabyseat"));
				vo.setCarname(rs.getString("carname"));
				vo.setCarprice(rs.getInt("carprice"));
				vo.setCarimg(rs.getString("carimg"));
				
				v.add(vo);
				
			}
			System.out.println(v);
	}catch (Exception e) {
		System.out.println("getAllCarOrder에서오류"+e.getMessage());
	}finally {
		freeResource();
	}
		return v;
}
	
	public CarConfirmVO getOneOrder(int orderid) {
		CarConfirmVO confirmvo=new CarConfirmVO();
		try {
			con= getConnection();
			
			sql="select * from carorder where orderid=?";
			
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, orderid);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				
				confirmvo.setOrderid(orderid);
				confirmvo.setCarbegindate(rs.getString("carbegindate"));
				confirmvo.setCarreserveday(rs.getInt("carreserveday"));
				confirmvo.setCarins(rs.getInt("carins"));
				confirmvo.setCarwifi(rs.getInt("carwifi"));
				confirmvo.setCarnave(rs.getInt("carnave"));
				confirmvo.setCarbabyseat(rs.getInt("carbabyseat"));
			}
		} catch (Exception e) {
			
		}finally {
			freeResource();
		}
		
		
		return confirmvo;
	}//getOneOrder끝

	public String selectMemberPass(int orderid) {
		    String memberpass=null;
		    String memberphone=null;
		    Map<String, String> memberMap=new HashMap();
		try {
			con=getConnection();
			
			sql="select memberpass from carorder where orderid=?";
			
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, orderid);
			
			rs=pstmt.executeQuery();
			if(rs.next()) {
				memberpass=rs.getString(1);
				
				
				
			}
			
		} catch (Exception e) {
			System.out.println("selectMEmberPass에서오류"+e.getMessage());
		}finally {
			freeResource();
		}
		return memberpass;
	}
	
	public void carOrderUpdate(CarOrderVO vo) {
		try {
			con=getConnection();
		String sql ="update carorder set carbegindate=? , carreserveday=? , carqty=?"
					+ ", carins=? , carwifi=? , carbabyseat=? where orderid=? "
					+ "and memberpass=?";
			//쿼리 실행할 객체 생성
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, vo.getCarbegindate());
			pstmt.setInt(2, vo.getCarreserveday());
			pstmt.setInt(3, vo.getCarqty());
			pstmt.setInt(4, vo.getCarins());
			pstmt.setInt(5, vo.getCarwifi());
			pstmt.setInt(6, vo.getCarbabyseat());
			pstmt.setInt(7, vo.getOrderid());
			pstmt.setString(8, vo.getMemberpass());
			pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			System.out.println("carOrderUpdate에서 오류"+e.getMessage());
		}finally {
			freeResource();
		}
		
	}

	//하나의 주문 정보를 삭제 하는 메소드
	public int carOrderDelete(int orderid, String memberpass) {
		int result=0;
		try {
			con=getConnection();
			
		    sql ="delete from carorder where orderid=? and memberpass=?";
		    
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, orderid);
			
			pstmt.setString(2, memberpass);
			
			result = pstmt.executeUpdate();//쿼리가 실행되지 않았다면 0값이 리턴 실행이 되면 1이 리턴
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			freeResource();
		}		
		
		return result;
	}//carOrderDelete끝
	
	
	
}
