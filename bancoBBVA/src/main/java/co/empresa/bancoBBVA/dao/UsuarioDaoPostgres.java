package co.empresa.bancoBBVA.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.empresa.bancoBBVA.modelo.Usuario;
import co.empresa.bancoBBVA.util.Conexion;

public class UsuarioDaoPostgres implements UsuarioDao{

	private Conexion conexion;
	
	private static final String SELECT_USUARIO_SQL_BY_CREDENTIAL = "SELECT * FROM users WHERE username = ? AND pass = ?;";
	private static final String SELECT_ALL_USUARIO_SQL = "SELECT * FROM users;";
	
	public UsuarioDaoPostgres() {
		this.conexion = Conexion.getConexion();
	}
	
	
	@Override
	public Usuario login(String username, String pass) throws SQLException {
		Usuario usuario = null;
		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(SELECT_USUARIO_SQL_BY_CREDENTIAL);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, pass);
			
			ResultSet rs = conexion.query();
			
			while(rs.next()) {
				usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setUsername(rs.getString("username"));
				usuario.setPass(rs.getString("pass"));
			}
		} catch (SQLException e) {
			
		}		
		return usuario;
	}


	@Override
	public List<Usuario> selectAll() {
		List<Usuario> usuarios = new ArrayList<>();

		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(SELECT_ALL_USUARIO_SQL);
			ResultSet rs = conexion.query();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String nombre = rs.getString("username");
				String pass = rs.getString("pass");
				String email = rs.getString("email");
				usuarios.add(new Usuario(id, nombre, pass, email));
			}
					
		}catch(SQLException e) {
			
		}
		
		
		return usuarios;

	}
	
}
