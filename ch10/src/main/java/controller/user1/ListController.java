package controller.user1;

import java.io.IOException;
import java.util.List;

import dto.User1DTO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.User1Service;

@WebServlet("/user1/list.do")
public class ListController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private User1Service service = User1Service.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 데이터 조회하기
		List<User1DTO> users = service.selectUser1s();
		
		// 데이터 공유(View에서 데이터를 출력하기 위해 같은 request scope 사용)
		req.setAttribute("users", users);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/user1/list.jsp");
		dispatcher.forward(req, resp);
	}
}










