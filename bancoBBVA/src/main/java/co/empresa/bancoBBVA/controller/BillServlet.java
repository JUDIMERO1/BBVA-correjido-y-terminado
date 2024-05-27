package co.empresa.bancoBBVA.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.empresa.bancoBBVA.dao.BillDao;
import co.empresa.bancoBBVA.dao.BillDaoFactory;
import co.empresa.bancoBBVA.modelo.Bill;

/**
 * Servlet implementation class BillServlet
 */
@WebServlet(name = "BillServlet", urlPatterns = { "/BillServlet" })
public class BillServlet extends HttpServlet {
	private BillDao billDao;
	private List<Bill> listMostrar = new ArrayList<>();
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BillServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() {
		String type = getServletContext().getInitParameter("type");
		this.billDao = BillDaoFactory.getBillDao(type);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		try {
			switch (action) {
			case "new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertarBill(request, response);
				break;
			case "delete":
				eliminarBill(request, response);
				break;
			case "/listar":
				listBills(request, response);
				break;
			case "logout":
				cerrarSesion(request, response);
				break;
			default:
				listBills(request, response);
				break;
			}
		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}

	private void cerrarSesion(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.getSession().invalidate();
		listMostrar.clear();
		response.sendRedirect("login.jsp");
	}

	private void listBills(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Bill> listBills = billDao.selectAll();
		listMostrar.clear();
		int user_id = (int) request.getSession().getAttribute("user_id");
		listBills.forEach((billes) -> {
			if (billes.getUser_Id() == user_id)
				listMostrar.add(billes);
		});
		
		request.getSession().setAttribute("listBills", listMostrar);
		RequestDispatcher dispatcher = request.getRequestDispatcher("billlist.jsp");
		dispatcher.forward(request, response);
	}

	private void eliminarBill(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		billDao.delete(id);
		request.getRequestDispatcher("BillServlet?action=listar").forward(request, response);
	}

	private void insertarBill(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		Date date = new Date();
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		String observation = request.getParameter("observation");
		int type = Integer.parseInt(request.getParameter("movimiento"));
		int value = Integer.parseInt(request.getParameter("value"));
		int user_id = (int) request.getSession().getAttribute("user_id");

		Bill bill = new Bill(sqlDate, user_id, value, type, observation);

		billDao.insert(bill);
		
		request.getRequestDispatcher("BillServlet?action=listar").forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("bill.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
