package br.ufrpe.persi.simuladorRede;
import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonPrimitive;
import com.google.gson.stream.JsonReader;

/*
 * 
 * @author kbaig
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
@WebServlet("/RemoteApiServlet")
public class RemoteApiServlet extends HttpServlet implements Servlet {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -7508161358039807879L;
	private Map<String, String> mpCityState;

    public RemoteApiServlet() {
        super();
        mpCityState = new HashMap<String, String>();
        
        mpCityState.put("Indianapolis","Indiana");
        mpCityState.put("Atlanta","Georgia");
        mpCityState.put("Chicago","Illinois");
        mpCityState.put("Madison","Wisconsin");
        mpCityState.put("Detroit","Michigan");
        
    }

    private String handleRequest(String param){
        return null;
    }
    
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	doPost(req, res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setHeader("Cache-Control", "no-store, no-cache");
        String city = "";
        
        String method = req.getParameter("method");
        String parameter = req.getParameter("parameter"); 
        
        if(req.getParameter("city") != null) { 
        	city = req.getParameter("city").trim();    
        }
        String state = (String)mpCityState.get(city);
        if(state != null) {
        	res.getWriter().write("{\"result\" : \"OK\", \"state\" : \"" + state + "\"}");
        }
        else {
        	res.getWriter().write("{\"result\" : \"ERROR\", \"state\" : \"State not found for city\"}");    
        }
    }
    
    //protected void json_con
    
    

}