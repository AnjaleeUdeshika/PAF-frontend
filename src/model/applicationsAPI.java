package model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class applicationsAPI
 */
@WebServlet("/applicationsAPI")
public class applicationsAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public applicationsAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		application applicationObj = new application();
		
		String output = applicationObj.insertApplication(request.getParameter("id"),
				 request.getParameter("name"),
				request.getParameter("nic"),
				request.getParameter("address"),
				request.getParameter("phone"),
				request.getParameter("email"),
				request.getParameter("area"),
				request.getParameter("service_center"),
				request.getParameter("solar_panel"));
		
		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		application applicationObj = new application();
		
		Map paras = getParasMap(request);
		String output = applicationObj.updateApplication(paras.get("hidapplicationIDSave").toString(),
											paras.get("id").toString(),
											paras.get("name").toString(),
											paras.get("nic").toString(),
											paras.get("address").toString(),
											paras.get("phone").toString(),
											paras.get("area").toString(),
											paras.get("service_center").toString(),
											paras.get("solar_panel").toString());
		
		
		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		application applicationObj = new application();
		
		Map paras = getParasMap(request);
		String output = applicationObj.deleteApplication(paras.get("applicationID").toString());
		
		response.getWriter().write(output); 
	}
	
	// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request)
	{
		Map<String, String> map = new HashMap<String, String>();
		try
		{
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ?
					scanner.useDelimiter("\\A").next() : "";
			scanner.close();
			
			String[] params = queryString.split("&");
			for (String param : params)
			{
				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
		 }
		catch (Exception e)
		 {
			
		 }
		return map;
	}

}
