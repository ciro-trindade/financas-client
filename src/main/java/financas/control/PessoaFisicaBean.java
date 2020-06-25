package financas.control;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import financas.model.PessoaFisica;
import financas.rest.client.PessoaFisicaRESTClient;

@ManagedBean
@RequestScoped
public class PessoaFisicaBean {
	private PessoaFisica pessoaFisica = new PessoaFisica();

	public PessoaFisicaBean() {
	}
	
	public String gravar() {
		PessoaFisicaRESTClient client = new PessoaFisicaRESTClient();
		if (client.create(pessoaFisica) == null) {
			FacesMessage msg = new FacesMessage("Já existe um usuário cadastrado com este login (" + pessoaFisica.getLogin() + ")!");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage("frm:email", msg);
			return null;
		}
		return "/index?faces-redirect=true";
	}
	
	public String pag_usuario() {
		pessoaFisica = new PessoaFisica();
		return "/pag_usuario?faces-redirect=true";
	}
		
}
