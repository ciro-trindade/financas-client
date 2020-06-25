package financas.rest.client;

import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import financas.model.PessoaFisica;
import financas.util.SessionContext;

public class PessoaFisicaRESTClient implements RESTClientInterface<PessoaFisica> {

	private Response response;
	
	private String token = (String) SessionContext.getInstance().getAttribute("token");

	@Override
	public List<PessoaFisica> findAll() {
		this.response = ClientBuilder.newClient()
				.target(REST_WEBSERVICE_URL + REST_PESSOAFISICA_URL)
				.request(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, token)
	    		.get();
		List<PessoaFisica> pf = this.response
				.readEntity(new GenericType<List<PessoaFisica>>() {});
		return pf;
	}

	@Override
	public PessoaFisica find(Long id) {
		this.response = ClientBuilder.newClient()				
				//.register(AuthenticationFeature.getFeature())
				.target(REST_WEBSERVICE_URL + REST_PESSOAFISICA_URL + id)
				.request(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, token)
				.get();
		if (response.getStatus() == STATUS_OK) {
			PessoaFisica usuario = this.response.readEntity(PessoaFisica.class);
			return usuario;
		}
		return null;
	}

	
	@Override
	public PessoaFisica create(PessoaFisica obj) {
	    this.response = ClientBuilder.newClient()
	    		.target(REST_WEBSERVICE_URL + REST_PESSOAFISICA_URL)
	    		.queryParam("pessoaFisica", obj)
	    		.request(MediaType.APPLICATION_JSON)
	    		.header(HttpHeaders.AUTHORIZATION, token)
	    		.post(Entity.entity(obj, MediaType.APPLICATION_JSON));
	    if (response.getStatus() == STATUS_NOT_ACCEPTABLE) {
	    	return null;
	    }
	    PessoaFisica usuario = this.response.readEntity(PessoaFisica.class);		
		return usuario;		
	}

	@Override
	public PessoaFisica edit(PessoaFisica obj) {
		this.response = ClientBuilder.newClient()
				.target(REST_WEBSERVICE_URL + REST_PESSOAFISICA_URL)
				.queryParam("categoria", obj)
				.request(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, token)
				.put(Entity.entity(obj, MediaType.APPLICATION_JSON));
		if (this.response.getStatus() == STATUS_OK) {
			PessoaFisica usuario = this.response.readEntity(PessoaFisica.class);
			return usuario;
		}
		return null;
	}

	@Override
	public boolean delete(Long id) {
		this.response = ClientBuilder.newClient()
				.target(REST_WEBSERVICE_URL + REST_PESSOAFISICA_URL + id)
				.request(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, token)
				.delete();
		return this.response.getStatus() == STATUS_OK;
	}

}
