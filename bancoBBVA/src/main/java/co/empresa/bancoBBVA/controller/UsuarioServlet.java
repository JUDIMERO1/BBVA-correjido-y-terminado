package co.empresa.bancoBBVA.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.parser.Cookie;

import co.empresa.bancoBBVA.dao.UsuarioDao;
import co.empresa.bancoBBVA.dao.UsuarioDaoFactory;
import co.empresa.bancoBBVA.modelo.Usuario;

/**
 * Servlet implementation class UsuarioServlet
 */
@WebServlet("/")
public class UsuarioServlet extends HttpServlet {
	private UsuarioDao usuarioDao;

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UsuarioServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() {
		String type = getServletContext().getInitParameter("type");
		this.usuarioDao = UsuarioDaoFactory.getUsuarioDao(type);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/login":
				showLogin(request, response);
				break;
			case "/loged":
				logearUsuario(request, response);
				break;
			default:
				listUsuario(request, response);
				break;
			}
		} catch (SQLException e) {

		}
	}

	private void listUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Usuario> listUsuarios = usuarioDao.selectAll();
		request.setAttribute("listUsuario", listUsuarios);

		RequestDispatcher dispatcher = request.getRequestDispatcher("usuariolist.jsp");
		dispatcher.forward(request, response);

	}

	private void logearUsuario(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {

		String username = request.getParameter("username");
		String pass = request.getParameter("pass");

		Usuario usuario = usuarioDao.login(username, pass);

		if (usuario == null) {
			request.setAttribute("mensaje", "Error en nombre de usuario o contraseña");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} else {
			request.getSession().setAttribute("user_id", usuario.getId());
			response.sendRedirect("BillServlet?action=/listar");
		}

	}

	private void showLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
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
