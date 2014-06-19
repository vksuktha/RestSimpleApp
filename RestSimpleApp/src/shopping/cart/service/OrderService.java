package shopping.cart.service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.representation.Form;

import javax.ws.rs.core.MediaType;
 



import shopping.cart.dao.OrderDao;
import shopping.cart.om.Order;

//maps this resource to the URL orders
@Path("/orders")
public class OrderService {
	// Allows to insert contextual objects into the class
    @Context
    UriInfo uriInfo;
    @Context
    Request request;
   
    // Return the list of orders for applications with json or xml formats
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Order> getOrders() {
      List<Order> orders = new ArrayList<Order>();
      
      orders.addAll(OrderDao.instance.getModel().values());
      return orders;
            		
    }
     	
    @POST
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)    
    public void newOrder(@FormParam("id") String id,
            @FormParam("summary") String summary,
            @FormParam("description") String description,
            @Context HttpServletResponse servletResponse) throws IOException {
          Order order = new Order(id,summary);
                   
          if (description!=null){
            order.setDescription(description);
          }
          
          
          OrderDao.instance.getModel().put(id, order);         
        }
    	
}