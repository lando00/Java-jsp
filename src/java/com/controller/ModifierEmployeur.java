package com.controller;

import Bean.Employeur;
import Services.EmployerServices;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ModifierEmployeur", urlPatterns = {"/ModifierEmployeur"})
public class ModifierEmployeur extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Récupérer l'ID du professeur depuis les paramètres
        String idParam = req.getParameter("id");
        if (idParam != null && !idParam.isEmpty()) {
            try {
                long id = Long.parseLong(idParam);
                EmployerServices ps = new EmployerServices();
                Employeur emp = ps.recuper(id); // Récupérer le professeur depuis la base

                if (emp != null) {
                    req.setAttribute("Employeur", emp); // Passer le professeur au JSP
                } else {
                    req.setAttribute("message", "Employeur non trouvé.");
                    req.setAttribute("messageType", "danger");
                }
            } catch (NumberFormatException e) {
                req.setAttribute("message", "ID invalide.");
                req.setAttribute("messageType", "danger");
            }
        }
        
    req.getSession().setAttribute("showLocationTable", false); // Correction du nom de l'attribut
    req.getSession().setAttribute("showEmployeeTable", true);
    req.getSession().setAttribute("showAssignmentTable", false);

        // Rediriger vers Dashboard.jsp ou un JSP spécifique pour la modification
        req.getRequestDispatcher("/vues/Dashboard.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Récupérer les données du formulaire
        String idParam = req.getParameter("id");
        String code_emp = req.getParameter("code_emp");
        String nom = req.getParameter("nom");
        String prenom = req.getParameter("prenom");
        String poste = req.getParameter("poste");

        if (idParam != null && !idParam.isEmpty() &&
            code_emp != null && !code_emp.trim().isEmpty() &&
            nom != null && !nom.trim().isEmpty() &&
            prenom != null && !prenom.trim().isEmpty() &&
            poste != null && !poste.trim().isEmpty()) {
            
            try {
                long id = Long.parseLong(idParam);
                Employeur p = new Employeur(code_emp, nom, prenom, poste);
                p.setId(id); // Définir l'ID pour la mise à jour

                EmployerServices ps = new EmployerServices();
                boolean b = ps.update(p); // Mettre à jour le professeur

                if (b) {
                    req.getSession().setAttribute("message", "Employé modifié avec succès !");
                    req.getSession().setAttribute("messageType", "success");
                    req.getSession().setAttribute("showEmployeeTable", true);
                } else {
                    req.getSession().setAttribute("message", "Erreur lors de la modification du Employeur.");
                    req.getSession().setAttribute("messageType", "danger");
                    req.getSession().setAttribute("showEmployeeTable", true);
                }
            } catch (NumberFormatException e) {
                req.getSession().setAttribute("message", "ID invalide.");
                req.getSession().setAttribute("messageType", "danger");
                req.getSession().setAttribute("showEmployeeTable", true);
            }
        } else {
            req.getSession().setAttribute("message", "Veuillez remplir tous les champs.");
            req.getSession().setAttribute("messageType", "warning");
            req.getSession().setAttribute("showEmployeeTable", true);
        }
        
            req.getSession().setAttribute("showLocationTable", true); // Correction du nom de l'attribut
    req.getSession().setAttribute("showEmployeeTable", false);
    req.getSession().setAttribute("showAssignmentTable", false);

        // Redirection POST-Redirect-GET vers ProfesseurDash pour rafraîchir la liste
        resp.sendRedirect(req.getContextPath() + "/EmployerController");
    }

    @Override
    public String getServletInfo() {
        return "Servlet pour modifier un Employeur";
    }
}