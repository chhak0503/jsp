package controller.user1;

import java.io.IOException;

import dto.User1DTO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.User1Service;

@WebServlet("/user1/modify.do")
public class ModifyController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private User1Service service = User1Service.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 데이터 수신
		String uid = req.getParameter("uid");
		
		// 데이터 조회
		User1DTO user = service.selectUser1(uid);
		
		// 데이터 공유
		req.setAttribute("user", user);
		
		// View 포워드
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/user1/modify.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 데이터 수신
		String uid = req.getParameter("uid");
		String name = req.getParameter("name");
		String birth = req.getParameter("birth");
		String hp = req.getParameter("hp");
		String age = req.getParameter("age");

		// DTO 생성(DTO - 데이터 전송 객체, 컴포넌트간 데이터 교환을 위한 객체)
		User1DTO dto = new User1DTO();
		dto.setUid(uid);
		dto.setName(name);
		dto.setBirth(birth);
		dto.setHp(hp);
		dto.setAge(age);
		
		// 데이터 수정 요청
		service.updateUser1(dto);
		
		// 리다이렉트
		resp.sendRedirect("/ch10/user1/list.do");
	}
}








