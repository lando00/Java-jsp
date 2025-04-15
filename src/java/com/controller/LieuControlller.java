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

@WebServlet(name = "LieuControlller", urlPatterns = {"/LieuControlller"})
public class LieuControlller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LieuServices lieuService = new LieuServices();
        try {
            List<Lieu> listLieu = lieuService.recuper_all();
            request.getSession().setAttribute("listLieu", listLieu);
            if (listLieu == null || listLieu.isEmpty()) {
                request.getSession().setAttribute("message", "Aucun lieu disponible.");
                request.getSession().setAttribute("messageType", "info");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("message", "Erreur lors de la récupération des lieu.");
            request.getSession().setAttribute("messageType", "danger");
        }
        request.getSession().setAttribute("showLocationTable", true); // Correction du nom de l'attribut
        request.getSession().setAttribute("showEmployeeTable", false);
        request.getSession().setAttribute("showAssignmentTable", false);
        request.getRequestDispatcher("/vues/Dashboard.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String codeLieu = request.getParameter("codeLieu");
        String designation = request.getParameter("designation");
        String province = request.getParameter("province");

        LieuServices lieuService = new LieuServices();

        if (codeLieu != null && !codeLieu.trim().isEmpty()
                && designation != null && !designation.trim().isEmpty()
                && province!= null && !province.trim().isEmpty()) {
            try {
                Lieu lieu = new Lieu(codeLieu, designation, province);
                boolean success = lieuService.create(lieu);

                // Mettre à jour la liste des lieux dans la session (correction du nom de la variable)
                List<Lieu> listLieu = lieuService.recuper_all();
                request.getSession().setAttribute("listLieu", listLieu);

                if (success) {
                    request.getSession().setAttribute("message", "Lieu ajoutée avec succès !");
                    request.getSession().setAttribute("messageType", "success");
                } else {
                    request.getSession().setAttribute("message", "Erreur lors de l'ajout de la Lieu.");
                    request.getSession().setAttribute("messageType", "danger");
                }
            } catch (Exception e) {
                e.printStackTrace();
                request.getSession().setAttribute("message", "Erreur lors de l'ajout de la Lieu : " + e.getMessage());
                request.getSession().setAttribute("messageType", "danger");
            }
        } else {
            request.getSession().setAttribute("message", "Veuillez remplir tous les champs.");
            request.getSession().setAttribute("messageType", "warning");
        }

        request.getSession().setAttribute("showLocationTable", true); // Correction du nom de l'attribut
        request.getSession().setAttribute("showEmployeeTable", false);
        request.getSession().setAttribute("showAssignmentTable", false);
        request.getRequestDispatcher("/vues/Dashboard.jsp").forward(request, response);
    }
}