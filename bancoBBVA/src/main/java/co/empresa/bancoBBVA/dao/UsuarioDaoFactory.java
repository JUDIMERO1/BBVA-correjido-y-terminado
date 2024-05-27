package co.empresa.bancoBBVA.dao;


public class UsuarioDaoFactory {

	public static UsuarioDao getUsuarioDao(String type) {
		switch(type) {
		case "postgresql":
			return new UsuarioDaoPostgres();
		default:
			return new UsuarioDaoPostgres();
		}
	}
}
