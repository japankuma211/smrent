package board;

import java.util.List;

public class BoardService {
	
	BoardDAO dao=new BoardDAO();
	
	public void insertBoard(BoardVO bvo) {
		dao.insertBoard(bvo);
	}
	
	public int getBoardCount() {
		return dao.getBoardCount();
	}
	
	public int getBoardCount(String search){
		
		return dao.getBoardCount(search);
	}
	
	public List<BoardVO> getBoardList(int startRow,int pageSize){
		return dao.getBoardList(startRow, pageSize);
	}
	
	public List<BoardVO> getBoardList(int startRow,int pageSize,String search){
		
		return dao.getBoardList(startRow, pageSize, search);
	}
	
	public BoardVO getBoard(int num) {
		return dao.getBoard(num);
	}
	
	public void updateReadcount(int num){
		dao.updateReadcount(num);
	}
	
	public int updateBoard(BoardVO bvo){
		return dao.updateBoard(bvo);
	}
	
	public int deleteBoard(int num,String pass){
		return dao.deleteBoard(num, pass);
	}
	
	public void reInsertBoard(BoardVO bvo){
		dao.reInsertBoard(bvo);
	}

}
