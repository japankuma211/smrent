package member;

import java.util.List;

public class MemberService {
	MemberDAO dao = new MemberDAO();
	
	public boolean insertMember(MemberVO memberVO) {
		
		return dao.insertMember(memberVO);
	}
	
	public int userCheck(String id,String pass) {
		return dao.userCheck(id, pass);
	}
	
	public MemberVO getMember(String id) {
		return dao.getMember(id);
	}
	
	public List<MemberVO> getMemberList() {
		return dao.getMemberList();
	}

}
