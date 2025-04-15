package com.controller;

import Bean.Affecter;
import Services.AffecterService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DeleteAffecter", urlPatterns = {"/DeleteAffecter"})
public class DeleteAffecter extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/vues/Dashboard.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("id");

        AffecterService occService = new AffecterService();

        if (idParam != null && !idParam.isEmpty()) {
            try {
                long id = Long.parseLong(idParam);
                boolean success = occService.delete(id);
                List<Affecter> listaffecter = occService.recuper_all();
                request.getSession().setAttribute("listaffecter", listaffecter);

                if (success) {
                    request.getSession().setAttribute("message", "Affectation supprimée avec succès !");
                    request.getSession().setAttribute("messageType", "success");
                } else {
                    request.getSession().setAttribute("message", "Erreur lors de la suppression de l'Affecter.");
                    request.getSession().setAttribute("messageType", "danger");
                }
            } catch (Exception e) {
                e.printStackTrace();
                request.getSession().setAttribute("message", "Erreur lors de la suppression de l'Affecter : " + e.getMessage());
                request.getSession().setAttribute("messageType", "danger");
            }
        } else {
            request.getSession().setAttribute("message", "ID invalide.");
            request.getSession().setAttribute("messageType", "warning");
        }

        request.getSession().setAttribute("showLocationTable", false); // Correction du nom de l'attribut
        request.getSession().setAttribute("showEmployeeTable", false);
        request.getSession().setAttribute("showAssignmentTable", true);
        request.getRequestDispatcher("/vues/Dashboard.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet pour supprimer une Affecter";
    }
}