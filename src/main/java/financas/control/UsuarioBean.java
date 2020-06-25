package financas.control;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import financas.model.dto.CredenciasDTO;
import financas.rest.client.UsuarioRESTClient;
import financas.util.SessionContext;

@ManagedBean
@RequestScoped
public class UsuarioBean {
	private CredenciasDTO usuario = new CredenciasDTO();

	public UsuarioBean() {
	}
	
	public CredenciasDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(CredenciasDTO usuario) {
		this.usuario = usuario;
	}

	public String autenticar() {
		UsuarioRESTClient client = new UsuarioRESTClient();
		if (client.authenticate(usuario)) {
			return "/protected/categoria?faces-redirect=true";
		}
		FacesMessage msg = new FacesMessage("Login/senha inválidos");
		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return null;
	}
		
	public String logout() {
		SessionContext.getInstance().encerrarSessao();
		return "/index?faces-redirect=true";
	}
	
}
