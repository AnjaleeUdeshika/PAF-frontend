package com;

import model.application;

//for REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//for JSON
import com.google.gson.*;

//for XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Application")

public class applicationService {
	
	application applicationObj = new application();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readApplication() {
		
		return applicationObj.readApplication();
		
	}
	
	
	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_FORM_URLENCODED)
	public String insertApplication(@FormParam("id") String id,
							@FormParam("name") String name,
							@FormParam("nic") String nic,
							@FormParam("address") String address,
							@FormParam("phone") String phone,
							@FormParam("email") String email,
							@FormParam("area") String area,
							@FormParam("service_center") String service_center,
							@FormParam("solar_panel") String solar_panel)
	{
		
		String output = applicationObj.insertApplication(id, name, nic, address, phone, email, area, service_center, solar_panel);
		return output;
		
	}
	
	//PUT
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateApplication(String applicationData)
	{
		//Convert input string to a JSON object
		JsonObject applicationObject = new JsonParser().parse(applicationData).getAsJsonObject();
		
		//Read values from JSON object
		String id = applicationObject.get("id").getAsString();
		String name = applicationObject.get("name").getAsString();
		String nic = applicationObject.get("nic").getAsString();
		String address = applicationObject.get("address").getAsString();
		String phone = applicationObject.get("phone").getAsString();
		String email = applicationObject.get("email").getAsString();
		String area = applicationObject.get("area").getAsString();
		String service_center = applicationObject.get("service_center").getAsString();
		String solar_panel = applicationObject.get("solar_panel").getAsString();
		
		
		String output = applicationObj.updateApplication(id, name, nic, address, phone, email, area, service_center, solar_panel);
		return output;
		
	}
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteApplication(String applicationData)
	{
		//Convert input string to a JSON object
		Document doc = Jsoup.parse(applicationData, "", Parser.xmlParser());
		
		//Read values from JSON object
		String applicationID = doc.select("applicationID").text();
		
		String output = applicationObj.deleteApplication(applicationID);
		return output;
		
	}

}
