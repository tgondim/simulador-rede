package br.ufrpe.persi.simuladorRede;

import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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

		mpCityState.put("Indianapolis", "Indiana");
		mpCityState.put("Atlanta", "Georgia");
		mpCityState.put("Chicago", "Illinois");
		mpCityState.put("Madison", "Wisconsin");
		mpCityState.put("Detroit", "Michigan");

	}

	private String handleRequest(String param) {
		return null;
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		res.setHeader("Cache-Control", "no-store, no-cache");
		String city = "";

		// String method = req.getParameter("method");
		// String parameter = req.getParameter("parameter"); //Erro ao ler o
		// parametro <Parameter>
		// Gson gs = new Gson();
		// gs.fromJson(req.getParameter("parameter"), typeOfT)
		JsonElement jsElem = new JsonParser().parse((String) req
				.getParameter("parameter"));
		JsonObject jsObj = jsElem.getAsJsonObject();

		for (final Entry<String, JsonElement> entry : jsObj.entrySet()) {
			final String key = entry.getKey();
			final JsonElement value = entry.getValue();

			if (key.toLowerCase().equals("pc")) {
				try {
					hanblerListPCObject(value.getAsJsonObject());
				} catch (EnderecoIPMalFormadoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if(key.toLowerCase().equals("router")) {
				try {
					hanblerListRouterObject(value.getAsJsonObject());
				} catch (EnderecoIPMalFormadoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		// JSONObject json =
		// JSONObject.fromObject(req.getParameter("parameter"));
		// System.out.println(json);

		// if(req.getParameter("city") != null) {
		// city = req.getParameter("city").trim();
		// }
		// String state = (String)mpCityState.get(city);
		// if(state != null) {
		// res.getWriter().write("{\"result\" : \"OK\", \"state\" : \"" + state
		// + "\"}");
		// }
		// else {
		res.getWriter().write(
				"{\"result\" : \"OK\", \"state\" : \"Oieeeeeeeeee\"}");
		// }
	}

	private ArrayList<Host> hanblerListPCObject(JsonObject jsObj)
			throws EnderecoIPMalFormadoException {

		ArrayList<Host> list = new ArrayList<Host>();

		for (final Entry<String, JsonElement> entry : jsObj.entrySet()) {
			final String key = entry.getKey();
			final JsonElement value = entry.getValue();
			JsonObject lanJsObj = value.getAsJsonObject();
			JsonObject pcJsObj = (JsonObject) lanJsObj.get("Lan1");
			String ip = pcJsObj.get("ip").getAsString();
			String mask = pcJsObj.get("mask").getAsString();
			Host host1 = new Host(1);
			ConfiguracaoRede configuracaoHost1 = new ConfiguracaoRede();
			configuracaoHost1.setIp(new EnderecoIP(ip));
			configuracaoHost1.setMascara(new EnderecoIP(mask));
			host1.setConfiguracao(configuracaoHost1);
			list.add(host1);
		}

		return list;
	}

	private ArrayList<Router> hanblerListRouterObject(JsonObject jsObj)
			throws EnderecoIPMalFormadoException {

		ArrayList<Router> list = new ArrayList<Router>();

		for (final Entry<String, JsonElement> entry : jsObj.entrySet()) {
			final String key = entry.getKey();
			final JsonElement value = entry.getValue();
			JsonObject lanJsObj = value.getAsJsonObject();
			
			for (final Entry<String, JsonElement> entry1 : lanJsObj.entrySet()) {
				final String key1 = entry.getKey();
				final JsonElement value1 = entry.getValue();
				
				JsonObject pcJsObj = value1.getAsJsonObject();
				String ip = pcJsObj.get("ip").getAsString();
				String mask = pcJsObj.get("mask").getAsString();
				
				Router router1 = new Router(32);
				ConfiguracaoRede configuracaoRouter = new ConfiguracaoRede();
				configuracaoRouter.setIp(new EnderecoIP(ip));
				configuracaoRouter.setMascara(new EnderecoIP(mask));
				router1.setConfiguracao(configuracaoRouter);
				list.add(router1);
			}
		}

		return list;
	}

	// protected void json_con

}