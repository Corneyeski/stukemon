/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import beans.Bean;
import entities.Trainer;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alan
 */
@WebServlet(name = "AltaPokemon", urlPatterns = {"/AltaPokemon"})
public class AltaPokemon extends HttpServlet {

    @EJB
    Bean ejb;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Alta Pokemon</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AltaPokemon at " + request.getContextPath() + "</h1>");
            out.println("<form method=\"POST\">");
            out.println("nombre <input type=\"text\" name=\"name\" required>");
            out.println("<br>");
            out.println("tipo <input type=\"text\" name=\"type\" required>");
            out.println("<br>");
            out.println("habilidad <input type=\"text\" name=\"ability\" required>");
            out.println("<br>");
            out.println("ataque <input type=\"number\" name=\"atack\" required>");
            out.println("<br>");
            out.println("defensa <input type=\"number\" name=\"defense\" required> ");
            out.println("<br>");
            out.println("velocidad <input type=\"number\" name=\"speed\" required>");
            out.println("<br>");
            out.println("vida <input type=\"number\" name=\"life\" required>");
            out.println("<br>");
            out.println("¿A qué entrenado quieres asignar el pokemon?");
            out.println("<select name=\"entrenador\">");
            
            List<Trainer> trainers = ejb.getTrainersForAddPokemons();
             for (Trainer t : trainers) {
                out.println("<option value=\"" + t + "\">" + t.getName() + "</option>");
             }
             
            out.println("</select>");
            out.println("<input type=\"submit\" name=\"alta\" value=\"guardar\">");
            out.println("</form>");

            if ("guardar".equals(request.getParameter("alta"))) {
                
                String name = request.getParameter("name");
                String type = request.getParameter("type");
                String hability = request.getParameter("ability");
                int atack = Integer.parseInt(request.getParameter("atack"));
                int defense = Integer.parseInt(request.getParameter("defense"));
                int speed = Integer.parseInt(request.getParameter("speed"));
                int life = Integer.parseInt(request.getParameter("life"));
                
                out.println("</body>");
                out.println("</html>");
            }
        }catch(NumberFormatException e){
            System.out.println(e);
        }
    }
        @Override
        protected void doGet
        (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            processRequest(request, response);
        }
        @Override
        protected void doPost
        (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            processRequest(request, response);
        }
        @Override
        public String getServletInfo
        
            () {
        return "Short description";
        }// </editor-fold>

    }
