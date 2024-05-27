package co.empresa.bancoBBVA.dao;

import java.sql.SQLException;
import java.util.List;

import co.empresa.bancoBBVA.modelo.Usuario;

public interface UsuarioDao {

	public Usuario login(String username, String pass) throws SQLException;

	public List<Usuario> selectAll();
}
