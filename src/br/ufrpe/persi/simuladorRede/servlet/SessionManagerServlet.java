package br.ufrpe.persi.simuladorRede.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufrpe.persi.simuladorRede.controle.SessionManager;

/**
 * Servlet implementation class SessionManagerServlet
 */
@WebServlet("/SessionManagerServlet")
public class SessionManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SessionManagerServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-store, no-cache");
		String operacao = (String)request.getParameter("operacao");
		if (operacao.equals("criarNovaRede")) {
			String newId = SessionManager.getInstance().criarNovaRede();
			response.getWriter().write("{\"result\" : \"OK\", \"id\" : \""  + newId + "\"}");
		} else if (operacao.equals("criarDispositivo")) {
			String retorno = SessionManager.getInstance().criarDispositivo((String)request.getParameter("idRede"), 
					(String)request.getParameter("nomeDispositivo"), 
					(String)request.getParameter("tipoDispositivo"), 
					Integer.valueOf(request.getParameter("numInterfaces")));
			response.getWriter().write("{\"result\" : \"OK\", \"retorno\" : \""  + retorno + "\"}");
		} else if (operacao.equals("alterarPropriedadeDispositivo")) {
			String retorno = SessionManager.getInstance().criarDispositivo((String)request.getParameter("idRede"), 
					(String)request.getParameter("nomeDispositivo"), 
					(String)request.getParameter("nome"), 
					Integer.valueOf(request.getParameter("valor")));
			response.getWriter().write("{\"result\" : \"OK\", \"retorno\" : \""  + retorno + "\"}");
		} else if (operacao.equals("processarPacote")) {
			String retorno = SessionManager.getInstance().criarDispositivo((String)request.getParameter("idRede"), 
					(String)request.getParameter("nomeOrigem"), 
					(String)request.getParameter("ipDestino"), 
					Integer.valueOf(request.getParameter("conteudo")));
			response.getWriter().write("{\"result\" : \"OK\", \"retorno\" : \""  + retorno + "\"}");
		}
	}

}
