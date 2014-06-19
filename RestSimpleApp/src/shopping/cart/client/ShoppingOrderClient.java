package shopping.cart.client;



import java.net.URI;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.representation.Form;

import shopping.cart.om.Order;
import javax.ws.rs.core.UriInfo;

public class ShoppingOrderClient {
	public static void main(String[] args) {
		
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource service = client.resource(UriBuilder.fromUri("http://localhost:8080/RestSimpleApp").build());
		//Create one shopping order
	//	Form form = new Form();
		
	//	form.add("description", "this is the Third order");
	//	form.add("id","3");
		
		Order order = new Order ( "3","3rd Order");
		order.setDescription("This is Third Order");
		ClientResponse response = service.path("rest").path("orders").path(order.getId()).accept(MediaType.APPLICATION_XML).post(ClientResponse.class,order);
		// Return code should be 201 == created resource
		// Get the Order
		// System.out.println("Form response " + response.getEntity(String.class));
		
	    System.out.println(service.path("rest").path("orders")
	        .accept(MediaType.APPLICATION_XML).get(String.class));
	 //   System.out.println(service.path("rest").path("orders")
	//	        .accept(MediaType.APPLICATION_JSON).get(String.class));
	
	  //  response = service.path("rest").path("orders")
	  //          .type(MediaType.APPLICATION_FORM_URLENCODED)
	  //          .post(ClientResponse.class, form);
	  //      System.out.println("Form response " + response.getEntity(String.class));
	}

}
