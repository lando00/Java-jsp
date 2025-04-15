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

@WebServlet(name = "DeleteLieu", urlPatterns = {"/DeleteLieu"})
public class DeleteLieu extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/vues/Dashboard.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("id");

        LieuServices lieuService = new LieuServices();

        if (idParam != null && !idParam.isEmpty()) {
            try {
                long id = Long.parseLong(idParam);
                boolean success = lieuService.delete(id);

                // Mettre à jour la liste des lieux dans la session (correction du nom de l'attribut et de la variable)
                List<Lieu> listLieu = lieuService.recuper_all();
                request.getSession().setAttribute("listLieu", listLieu); // Utilisation de "listLieu"

                if (success) {
                    request.getSession().setAttribute("message", "Lieu supprimée avec succès !");
                    request.getSession().setAttribute("messageType", "success");
                } else {
                    request.getSession().setAttribute("message", "Erreur lors de la suppression de la lieu."); // Correction du message
                    request.getSession().setAttribute("messageType", "danger");
                }
            } catch (Exception e) {
                e.printStackTrace();
                request.getSession().setAttribute("message", "Erreur lors de la suppression de la lieu : " + e.getMessage()); // Correction du message
                request.getSession().setAttribute("messageType", "danger");
            }
        } else {
            request.getSession().setAttribute("message", "ID invalide.");
            request.getSession().setAttribute("messageType", "warning");
        }

        request.getSession().setAttribute("showLocationTable", true); // Correction du nom de l'attribut
        request.getSession().setAttribute("showEmployeeTable", false);
        request.getSession().setAttribute("showAssignmentTable", false);
         request.getRequestDispatcher("/vues/Dashboard.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet pour supprimer une lieu";
    }
}