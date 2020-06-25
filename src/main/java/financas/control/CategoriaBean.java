package financas.control;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import financas.model.Categoria;
import financas.rest.client.CategoriaRESTClient;

@ManagedBean
@SessionScoped
public class CategoriaBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Categoria categoria;
	private List<Categoria> categorias;
	private String consulta;
	
	public CategoriaBean()  {		
		CategoriaRESTClient rest = new CategoriaRESTClient();
		categorias = rest.findAll();
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}		
	
	public String getConsulta() {
		return consulta;
	}

	public void setConsulta(String consulta) {
		this.consulta = consulta;
	}

	public String pagPrincipal() {
		CategoriaRESTClient rest = new CategoriaRESTClient();
		categorias = rest.findAll();
		return "/protected/categoria?faces-redirect=true";		
	}
	
	public String pagCategoria() {
		this.categoria = new Categoria();
		return "/protected/pag_categoria?faces-redirect=true";
	}
	
	public String pagCategoria(Categoria categoria) {
		this.categoria = categoria;
		return "/protected/pag_categoria?faces-redirect=true";
	}
	
	public String consultar() {
		CategoriaRESTClient rest = new CategoriaRESTClient();
		if (consulta != null && !consulta.trim().isEmpty()) {
			this.categorias = rest.findByName(consulta);
		}
		else {
			this.categorias = rest.findAll();
		}
		return null;
	}
	
	public String gravar() {
		CategoriaRESTClient rest = new CategoriaRESTClient();
		if (categoria.getId() == null) {
			rest.create(categoria);
			categoria = new Categoria();			
		}
		else {
			categoria = rest.edit(categoria);
		}
		return null;		
	}
	
	public String excluir(Categoria c) {
		CategoriaRESTClient rest = new CategoriaRESTClient();
		if (!rest.delete(c.getId())) {
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage msg = new FacesMessage("Não foi possível excluir a categoria " + c.getNome());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, msg);
		}
		else {
			categorias.remove(c);
		}
		return null;
	}
}
