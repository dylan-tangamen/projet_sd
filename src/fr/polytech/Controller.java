package fr.polytech;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Jeux jeux2=new Jeux();
		request.setAttribute("listeJeux", jeux2.listerTousLesJeux());
		request.setAttribute("searched", jeux2.listerLike(request));	
		//Faille ? Mais je ne veux pas le mettre en post, sinon je ne pourrais pas avoir la liste de recherchés en même temps que d'éxecuter mes autres forms
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/recherche.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Jeux jeuxPost=new Jeux();
		// Solution peu élégante, mais qui fonctionne.
		if (request.getParameter("add")!=null) {
			jeuxPost.ajouterJeu(request);
		} else if (request.getParameter("remove")!=null) {
			jeuxPost.retirerJeu(request);
		} else if (request.getParameter("update")!=null) {
			jeuxPost.modifierJeu(request);
		}
		response.sendRedirect("/projet_sd/recherche");
		//doGet(request, response);
	} 


		
		
}



