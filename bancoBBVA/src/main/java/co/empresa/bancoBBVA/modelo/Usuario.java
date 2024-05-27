package co.empresa.bancoBBVA.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
	private Integer id;
	private String username;
	private String pass;
	private String email;
	
	public Usuario(String username, String pass) {
		this.username = username;
		this.pass = pass;
	}
	
	
}
