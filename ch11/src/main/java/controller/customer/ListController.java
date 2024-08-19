package controller.customer;

import java.io.IOException;
import java.util.List;

import dao.CustomerDAO;
import dto.CustomerDTO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.CustomerService;

@WebServlet("/customer/list.do")
public class ListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CustomerService service = CustomerService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 데이터 조회
		List<CustomerDTO> customers = service.selectCustomers();
		
		// 데이터 공유 참조
		req.setAttribute("customers", customers);
		
		// 포워드
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/customer/list.jsp");
		dispatcher.forward(req, resp);
	}
	
}









