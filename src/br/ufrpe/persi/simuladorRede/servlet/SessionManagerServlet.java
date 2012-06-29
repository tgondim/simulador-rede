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
		String retorno = "";
		response.setHeader("Cache-Control", "no-store, no-cache");
		String operacao = (String)request.getParameter("operacao");
		
		
		if (operacao.toLowerCase().equals("criarnovarede")) {
			String newId = SessionManager.getInstance().criarNovaRede();
			response.getWriter().write("{\"result\" : \"OK\", \"id\" : \""  + newId + "\"}");
		} else if (operacao.equals("criarDispositivo")) {
			try {
				retorno = SessionManager.getInstance().criarDispositivo((String)request.getParameter("idRede"), 
						(String)request.getParameter("nomeDispositivo"), 
						(String)request.getParameter("tipoDispositivo"), 
						Integer.valueOf(request.getParameter("numInterfaces")));
				response.getWriter().write("{\"result\" : \"OK\", \"retorno\" : \""  + retorno + "\"}");
			} catch (Exception e) {
				response.getWriter().write("{\"result\" : \"ERRO\", \"retorno\" : \""  + e.getMessage() + "\"}");
			}			
		} else if (operacao.toLowerCase().equals("alterarpropriedadedispositivo")) {
			try {
				retorno = SessionManager.getInstance().alterarPropriedadeDispositivo((String)request.getParameter("idRede"), 
						(String)request.getParameter("nomeDispositivo"), 
						(String)request.getParameter("nome"), 
						//Integer.valueOf(request.getParameter("valor")));
						(String)request.getParameter("valor"));
				response.getWriter().write("{\"result\" : \"OK\", \"retorno\" : \""  + retorno + "\"}");
			} catch (Exception e) {				
				response.getWriter().write("{\"result\" : \"ERRO\", \"retorno\" : \""  + e.getMessage() + "\"}");
			}			
		} else if (operacao.toLowerCase().equals("processarpacote")) {
			try {
				 retorno = SessionManager.getInstance().processarPacote((String)request.getParameter("idRede"), (String)request.getParameter("nomeOrigem"),
						(String)request.getParameter("ipDestino"),
						(String)request.getParameter("conteudo"));
				response.getWriter().write("{\"result\" : \"OK\", \"retorno\" : \""  + retorno + "\"}");
			} catch (Exception e) {
				response.getWriter().write("{\"result\" : \"ERRO\", \"retorno\" : \""  + e.getMessage() + "\"}");
			}			
		} else if (operacao.toLowerCase().equals("conectardispositivos")) {
			try {
				retorno = SessionManager.getInstance().conectarDispositivos((String)request.getParameter("idRede"), 
						(String)request.getParameter("nomeDispositivo1"), 
						(String)request.getParameter("nomeDispositivo2"));
				response.getWriter().write("{\"result\" : \"OK\", \"retorno\" : \""  + retorno + "\"}");
			} catch (Exception e) {
				response.getWriter().write("{\"result\" : \"ERRO\", \"retorno\" : \""  + e.getMessage() + "\"}");
			}		
		} else if (operacao.toLowerCase().equals("limparconsole")) {
			SessionManager.getInstance().limparConsole((String)request.getParameter("idRede"));
			response.getWriter().write("{\"result\" : \"OK\"}");
		} else if (operacao.toLowerCase().equals("getconsole")) {
			String console = SessionManager.getInstance().getConsole((String)request.getParameter("idRede")).toString();
			response.getWriter().write("{\"result\" : \"OK\", \"id\" : \""  + console + "\"}");
		} else if (operacao.toLowerCase().equals("adicionarrota")) {
			try {
				retorno = SessionManager.getInstance().adicionarRota((String)request.getParameter("idRede"), 
						(String)request.getParameter("nomeDispositivo"), 
						(String)request.getParameter("nomeRede"),
						(String)request.getParameter("idDispositivo"));
				response.getWriter().write("{\"result\" : \"OK\", \"retorno\" : \""  + retorno + "\"}");
			} catch (Exception e) {
				response.getWriter().write("{\"result\" : \"ERRO\", \"retorno\" : \""  + e.getMessage() + "\"}");
			}
		}
	}

}
