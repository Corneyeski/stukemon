/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import beans.Bean;
import entities.Pokemon;
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
 * @author alanv
 */
@WebServlet(name = "Batalla", urlPatterns = {"/Batalla"})
public class Batalla extends HttpServlet {

    @EJB
    Bean ejb;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Batalla</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Batalla at " + request.getContextPath() + "</h1>");

            out.println("<form method=\"POST\">");
            out.println("<select name=\"trainers\">");
            List<Trainer> trainers = ejb.GetTrainersBattle();
            for (Trainer t : trainers) {
                out.println("<option value=\"" + t.getName() + "\">" + t.getName() + "</option>");
            }
            out.println("</select>");

            out.println("<form method=\"POST\">");
            out.println("<select name=\"trainersDos\">");
            for (Trainer t : trainers) {
                out.println("<option value=\"" + t.getName() + "\">" + t.getName() + "</option>");
            }
            out.println("</select>");
            out.println("<input type=\"submit\" name=\"trainer\" value=\"trainer\">");
            out.println("</form>");

            if ("trainer".equals(request.getParameter("trainer"))) {
                out.println("<form method=\"POST\">");
                Trainer trainer = ejb.findTrainerByName(request.getParameter("trainers"));
                out.println("<select name=\"pokemon\">");
                for (Pokemon p : trainer.getPokemonCollection()) {
                    out.println("<option value=\"" + p.getName() + "\">" + p.getName() + "</option>");
                }
                out.println("</select>");

                out.println("<form method=\"POST\">");
                Trainer trainerDos = ejb.findTrainerByName(request.getParameter("trainersDos"));
                out.println("<select name=\"pokemonDos\">");
                for (Pokemon p : trainerDos.getPokemonCollection()) {
                    out.println("<option value=\"" + p.getName() + "\">" + p.getName() + "</option>");
                }
                out.println("</select>");
                out.println("<input type=\"submit\" name=\"batalla\" value=\"batalla\">");
                out.println("</form>");
            }

            if ("batalla".equals(request.getParameter("batalla"))) {

                Pokemon pokemon = ejb.getPokemonByName(request.getParameter("pokemon"));
                Pokemon pokemon2 = ejb.getPokemonByName(request.getParameter("pokemonDos"));

                if (pokemon.getSpeed() > pokemon2.getSpeed()) {

                    boolean muerto = false;
                    boolean turno = false;
                    do {
                        if (!turno) {
                            pokemon2.setLife(pelea(turno, pokemon, pokemon2));
                            turno = true;
                        } else {
                            pelea(turno, pokemon, pokemon2);
                            turno = false;
                        }
                        String check = updateData(muerto, pokemon, pokemon2);
                        
                       if(!check.equals("")){
                           muerto = true;
                           out.print(check);
                       }
                    } while (!muerto);

                } else if (pokemon.getSpeed() == pokemon2.getSpeed()) {
                    int ran = (int) (Math.random() * 0) + 3;

                    if (ran == 1) {
                        boolean muerto = false;
                        boolean turno = false;
                        do {
                            if (!turno) {
                                pokemon2.setLife(pelea(turno, pokemon, pokemon2));
                                turno = true;
                            } else {
                                pelea(turno, pokemon, pokemon2);
                                turno = false;
                            }
                            String check = updateData(muerto, pokemon, pokemon2);
                        
                       if(!check.equals("")){
                           muerto = true;
                           out.print(check);
                       }
                    } while (!muerto);
                    } else {
                        boolean muerto = false;
                        boolean turno = false;
                        do {
                            if (!turno) {
                                pokemon2.setLife(pelea(turno, pokemon, pokemon2));
                                turno = true;
                            } else {
                                pelea(turno, pokemon, pokemon2);
                                turno = false;
                            }
                            String check = updateData(muerto, pokemon, pokemon2);
                        
                       if(!check.equals("")){
                           muerto = true;
                           out.print(check);
                       }
                    } while (!muerto);
                    }
                } else {
                    boolean muerto = false;
                    boolean turno = true;
                    do {
                        if (!turno) {
                            pokemon2.setLife(pelea(turno, pokemon, pokemon2));
                            turno = true;
                        } else {
                            pelea(turno, pokemon, pokemon2);
                            turno = false;
                        }
                        
                        String check = updateData(muerto, pokemon, pokemon2);
                        
                       if(!check.equals("")){
                           muerto = true;
                           out.print(check);
                       }
                    } while (!muerto);

                }
                out.println("<h1>Fin de batalla</h1>");
                out.print("<form action=\"index.html\" method=\"POST\">\n"
                        + "<input type=\"submit\" value=\"inicio\">\n"
                        + "</form>");
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

    public int pelea(boolean turno, Pokemon p, Pokemon p2) {

        int vida = 0;

        if (!turno) {
            vida = p2.getLife() - (p.getAttack() + (p.getLevel() * 2) - (p2.getDefense() / 3));
        } else {
            vida = p.getLife() - (p2.getAttack() + (p2.getLevel() * 2) - (p.getDefense() / 3));
        }

        if (vida < 0) {
            vida = 0;
        }
        return vida;
    }

    public String updateData(boolean muerto, Pokemon pokemon, Pokemon pokemon2) {

        String check = "";
        if (pokemon2.getLife() == 0) {
            muerto = true;
            check = "<h1>Gana el pokemon " + pokemon.getName() + "</h1>";
            pokemon.setLevel(pokemon.getLevel() + 1);

            pokemon.getTrainer().setPoints(pokemon.getTrainer().getPoints() + 10);
            ejb.updateTrainer(pokemon.getTrainer());

            pokemon2.getTrainer().setPoints(pokemon2.getTrainer().getPoints() + 1);
            ejb.updateTrainer(pokemon2.getTrainer());
            
            ejb.updatePokemon(pokemon);
            ejb.updatePokemon(pokemon2);
        }
        if (pokemon.getLife() == 0) {
            muerto = true;
            check = "<h1>Gana el pokemon " + pokemon2.getName() + "</h1>";
            pokemon.setLevel(pokemon2.getLevel() + 1);

            pokemon.getTrainer().setPoints(pokemon2.getTrainer().getPoints() + 10);
            ejb.updateTrainer(pokemon2.getTrainer());

            pokemon.getTrainer().setPoints(pokemon.getTrainer().getPoints() + 1);
            ejb.updateTrainer(pokemon.getTrainer());
            
            ejb.updatePokemon(pokemon);
            ejb.updatePokemon(pokemon2);
        }
        return check;
    }
}
