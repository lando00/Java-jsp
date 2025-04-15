package com.controller;

import Services.EmployerServices;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "SuppressionEmp", urlPatterns = {"/SuppressionEmp"})
public class SuppressionEmp extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(SuppressionEmp.class);
    private final EmployerServices profServices = new EmployerServices();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");

        logger.info("SuppressionEmp: Received ID parameter: {}", idParam);

        if (idParam != null && !idParam.isEmpty()) {
            try {
                Long id = Long.parseLong(idParam); // Changement en Long si nécessaire
                boolean deleted = profServices.delete(id);

                if (deleted) {
                    logger.info("SuppressionEmp: Professor deleted successfully with ID: {}", id);
                    request.getSession().setAttribute("message", "Employé supprimé avec succès !");
                    request.getSession().setAttribute("messageType", "success");
                    request.getSession().setAttribute("showEmployeeTable", true);
                } else {
                    logger.warn("SuppressionEmp: Professor not found with ID: {}", id);
                    request.getSession().setAttribute("message", "SuppressionEmp introuvable.");
                    request.getSession().setAttribute("messageType", "danger");
                    request.getSession().setAttribute("showEmployeeTable", true);
                }
            } catch (NumberFormatException e) {
                logger.error("SuppressionEmp: Invalid SuppressionEmp ID format: {}", idParam, e);
                request.getSession().setAttribute("message", "ID invalide.");
                request.getSession().setAttribute("messageType", "danger");
                request.getSession().setAttribute("showEmployeeTable", true);
            } catch (Exception e) {
                logger.error("SuppressionEmp: Error deleting SuppressionEmp with ID: {}", idParam, e);
                request.getSession().setAttribute("message", "Erreur lors de la suppression.");
                request.getSession().setAttribute("messageType", "danger");
                request.getSession().setAttribute("showEmployeeTable", true);
            }
        } else {
            logger.warn("SuppressionEmp: Missing SuppressionEmp ID.");
            request.getSession().setAttribute("message", "ID manquant.");
            request.getSession().setAttribute("messageType", "warning");
            request.getSession().setAttribute("showEmployeeTable", true);
        }
            request.getSession().setAttribute("showLocationTable", true); // Correction du nom de l'attribut
    request.getSession().setAttribute("showEmployeeTable", false);
    request.getSession().setAttribute("showAssignmentTable", false);
        response.sendRedirect(request.getContextPath() + "/EmployerController");
    }
}