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
import static java.lang.Thread.sleep;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "ConseguirPociones", urlPatterns = {"/ConseguirPociones"})
public class ConseguirPociones extends HttpServlet {
    
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
            out.println("<title>Servlet ConseguirPociones</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ConseguirPociones at " + request.getContextPath() + "</h1>");
            
            List<Trainer> trainers = ejb.getAllTrainers();
            
            out.println("<form method=\"POST\">");
            out.println("<select name=\"trainer\">");
            for (Trainer t : trainers) {
                out.println("<option value=\"" + t.getName() + "\">" + t.getName() + " Puntos: " + t.getPoints() + "</option>");
            }
            out.println("</select>");
            out.println("<input type=\"submit\" name=\"pociones\" value=\"pociones\">");
            out.println("</form>");
            
            if ("pociones".equals(request.getParameter("pociones"))) {
                
                Trainer t = ejb.findTrainerByName(request.getParameter("trainer"));
                
                if (t.getPoints()< 10) {
                    out.print("<h2>Te faltan " + (10 - t.getPoints()) + " para poder comprar una pocion</h2>");
                    
                    out.print("<form action=\"index.html\" method=\"POST\">\n"
                            + "<input type=\"submit\" value=\"inicio\">\n"
                            + "</form>");
                } else {
                    t.setPoints(t.getPoints() - 10);
                    t.setPotions(t.getPotions() + 1);

//                    ejb.updateTrainer(t);
//                    sleep(500);
//                    out.println("<h2>Tienes " + t.getPotions() + " Pociones</h2>");
//                    sleep(5000);
                    //response.sendRedirect("index.html");
                    out.print("<form action=\"index.html\" method=\"POST\">\n"
                            + "<input type=\"submit\" value=\"inicio\">\n"
                            + "</form>");
                }
            }
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
