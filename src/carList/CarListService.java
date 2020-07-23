package carList;

import java.util.List;
import java.util.Vector;

import carOrder.CarConfirmVO;
import carOrder.CarOrderVO;




public class CarListService {
	CarListDAO dao =new CarListDAO();
	
	public Vector<CarListVO> getAllCarlist(){
		return dao.getAllCarlist();
	}
	
	public Vector<CarListVO> getCategoryCarlist(String carcategory){
		
		return dao.getCategoryCarlist(carcategory);
	}
	
	public CarListVO getOneCar(int carno) {
		
		return dao.getOneCar(carno);
	
	}
	
	public void insertCarOrder(CarOrderVO ordervo) {
		dao.insertCarOrder(ordervo);
	}
	
	public  List<CarConfirmVO> getAllCarOrder(String memberphone, String memberpass){
		System.out.println("서비스도착");
		return dao.getAllCarOrder(memberphone, memberpass);
				
	}
	
	public CarConfirmVO getOneOrder(int orderid) {
		return dao.getOneOrder(orderid);
	}
	
	public void carOrderUpdate(CarOrderVO vo) {
		dao.carOrderUpdate(vo);
	}
	
	public int carOrderDelete(int orderid, String memberpass) {
		return dao.carOrderDelete(orderid, memberpass);
	}
	public String selectMemberPass(int orderid) {
		return dao.selectMemberPass(orderid);
	}

}
