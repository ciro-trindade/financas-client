package financas.rest.client;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import financas.model.dto.CredenciasDTO;
import financas.util.SessionContext;

public class UsuarioRESTClient {

	private static final String REST_WEBSERVICE_URL = "http://localhost:8081";

	private static final String REST_LOGIN_URL = "/login";

	private Response response;
	
	public boolean authenticate(CredenciasDTO cred) { 
		response = ClientBuilder.newClient()
				.target(REST_WEBSERVICE_URL + REST_LOGIN_URL)
	    		.queryParam("cred", cred)
	    		.request(MediaType.APPLICATION_JSON)
	    		.post(Entity.entity(cred, MediaType.APPLICATION_JSON));
		if (response.getStatus() == Response.Status.OK.getStatusCode()) {
			String token = response.getHeaderString("Authentication");
			SessionContext.getInstance().setAttribute("usuario", cred);
			SessionContext.getInstance().setAttribute("token", token);			
			return true;
		}	    
		return false;
	}

}
