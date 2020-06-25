package financas.rest.client;

import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import financas.model.Categoria;
import financas.util.SessionContext;

public class CategoriaRESTClient implements RESTClientInterface<Categoria> {
	private Response response;	
	private String token = (String) SessionContext.getInstance().getAttribute("token");

	@Override
	public List<Categoria> findAll() {
		this.response = ClientBuilder.newClient()
                 .target(REST_WEBSERVICE_URL + REST_CATEGORIA_URL)
                 .request(MediaType.APPLICATION_JSON)
				 .header(HttpHeaders.AUTHORIZATION, this.token)
				 .get();

		List<Categoria> categorias = this.response
				.readEntity(new GenericType<List<Categoria>>() {});
		return categorias;
	}

	@Override
	public Categoria find(Long id) {
		this.response = ClientBuilder.newClient()
				.target(REST_WEBSERVICE_URL + REST_CATEGORIA_URL + id)
				.request(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, this.token)
				.get();
		if (response.getStatus() == STATUS_OK) {
			return this.response.readEntity(Categoria.class);
		}
		return null;
	}

	public List<Categoria> findByName(String nome) {
		this.response = ClientBuilder.newClient()
				.target(REST_WEBSERVICE_URL + REST_CATEGORIA_URL + "nome/" + nome)
				.request(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, this.token)
				.get();
		List<Categoria> categorias = this.response.readEntity(new GenericType<List<Categoria>>() {
		});

		return categorias;
	}

	@Override
	public Categoria create(Categoria obj) {
		this.response = ClientBuilder.newClient()
				.target(REST_WEBSERVICE_URL + REST_CATEGORIA_URL)
				.queryParam("categoria", obj)
				.request(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, this.token)
				.post(Entity.entity(obj, MediaType.APPLICATION_JSON));
		Categoria categoria = this.response.readEntity(Categoria.class);

		return categoria;
	}

	@Override
	public Categoria edit(Categoria obj) {
		this.response = ClientBuilder.newClient()
				.target(REST_WEBSERVICE_URL + REST_CATEGORIA_URL)
				.queryParam("categoria", obj)
				.request(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, this.token)
				.put(Entity.entity(obj, MediaType.APPLICATION_JSON));
		if (this.response.getStatus() == STATUS_OK) {
			return this.response.readEntity(Categoria.class);
		}
		return null;
	}

	@Override
	public boolean delete(Long id) {
		this.response = ClientBuilder.newClient()
				.target(REST_WEBSERVICE_URL + REST_CATEGORIA_URL + id)
				.request(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, this.token)
				.delete();
		return this.response.getStatus() == STATUS_OK;
	}

}
