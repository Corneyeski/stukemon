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
@WebServlet(name = "RankingEntrenadores", urlPatterns = {"/RankingEntrenadores"})
public class RankingEntrenadores extends HttpServlet {

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
            out.println("<title>Servlet RankingEntrenadores</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RankingEntrenadores at " + request.getContextPath() + "</h1>");
            
            List<Trainer> trainers = ejb.trainersRanking();
            
            out.println("<table style=\"border: 1px solid\">");
            out.println("<tr>");
            out.println("<th> Nombre <td>");
            out.println("<th> Pokeballs <td>");
            out.println("<th> Pociones<td>");
            out.println("<th> Puntos <td>");
            out.println("</tr>");
            
            for (Trainer t : trainers){
                out.println("<tr>");
                out.println("<td>" + t.getName() + "<td>");
                out.println("<td>" + t.getPokeballs() + "<td>");
                out.println("<td>" +  t.getPotions() + "<td>");
                out.println("<td>" +  t.getPoints() + "<td>");
                out.println("</tr>");
            }
            out.println("</table>");
            out.println("<form action=\"index.html\" method=\"POST\">\n" +
                                "<input type=\"submit\" value=\"menu principal\">\n" +
                                "</form>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
