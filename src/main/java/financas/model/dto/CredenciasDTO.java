package financas.model.dto;

import java.io.Serializable;

public class CredenciasDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String login;
	private String senha;

	public CredenciasDTO() {
	}

	public CredenciasDTO(String login, String senha) {
		this.login = login;
		this.senha = senha;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
