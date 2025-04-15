package com.controller;

import Bean.Lieu;
import Services.LieuServices;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "InitServlet", urlPatterns = {"/init"}, loadOnStartup = 1)
public class InitServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Charger les données des salles
        LieuServices salleServices = new LieuServices();
        List<Lieu> listLieu = salleServices.recuper_all();
        req.getSession().setAttribute("listLieu", listLieu);
        req.getSession().setAttribute("showSalleTable", true); // Afficher les salles par défaut
        req.getSession().setAttribute("showProfTable", false); // Masquer les profs

        // Charger les données des professeurs (si nécessaire)
        // ProfesseurServices profServices = new ProfesseurServices();
        // List<Professeur> listProfs = profServices.recuper_all();
        // req.getSession().setAttribute("list_prof", listProfs);

        // Rediriger vers Dashboard.jsp
        req.getRequestDispatcher("/vues/Dashboard.jsp").forward(req, resp);
    }
}