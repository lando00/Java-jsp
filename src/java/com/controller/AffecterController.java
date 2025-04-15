package com.controller;

import Bean.Affecter;
import Services.AffecterService;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AffecterController", urlPatterns = {"/AffecterController"})
public class AffecterController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AffecterService affecterService = new AffecterService();
        try {
            List<Affecter> listaffecter = affecterService.recuper_all();
            request.getSession().setAttribute("listaffecter", listaffecter);
            if (listaffecter == null || listaffecter.isEmpty()) {
                request.getSession().setAttribute("message", "Aucune employer affecter disponible.");
                request.getSession().setAttribute("messageType", "info");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("message", "Erreur lors de la récupération des affecter.");
            request.getSession().setAttribute("messageType", "danger");
        }
        request.getSession().setAttribute("showLocationTable", false); // Correction du nom de l'attribut
        request.getSession().setAttribute("showEmployeeTable", false);
        request.getSession().setAttribute("showAssignmentTable", true);
        request.getRequestDispatcher("/vues/Dashboard.jsp").forward(request, response);
    }

 @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String codeLieu = request.getParameter("codeLieu");
    String code_emp = request.getParameter("code_emp");
    String dateAffectationStr = request.getParameter("dateAffectation");

    AffecterService affService = new AffecterService();

    if (codeLieu != null && !codeLieu.trim().isEmpty() &&
            code_emp != null && !code_emp.trim().isEmpty() &&
            dateAffectationStr != null && !dateAffectationStr.trim().isEmpty()) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dateAffectation = sdf.parse(dateAffectationStr);

            // Test de la méthode
            boolean testAffecter = affService.isAffecter(codeLieu,code_emp, dateAffectation);
            System.out.println("lieu affectr ? " + testAffecter); // Pour déboguer

            if (testAffecter) {
                request.getSession().setAttribute("message", "Cette employer est déjà  dans listaffecter à cette date.");
                request.getSession().setAttribute("messageType", "danger");
            } else {
                Affecter occ = new Affecter(codeLieu, code_emp, dateAffectation);
                boolean success = affService.create(occ);
                List<Affecter> listaffecter = affService.recuper_all();
                request.getSession().setAttribute("listaffecter", listaffecter);

                if (success) {
                    request.getSession().setAttribute("message", "Affectation enregistrée avec succès !");
                    request.getSession().setAttribute("messageType", "success");
                } else {
                    request.getSession().setAttribute("message", "Erreur lors de l'enregistrement de l'affecter.");
                    request.getSession().setAttribute("messageType", "danger");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("message", "Erreur lors de l'enregistrement de l'affecter : " + e.getMessage());
            request.getSession().setAttribute("messageType", "danger");
        }
    } else {
        request.getSession().setAttribute("message", "Veuillez remplir tous les champs.");
        request.getSession().setAttribute("messageType", "warning");
    }

        request.getSession().setAttribute("showLocationTable", false); // Correction du nom de l'attribut
        request.getSession().setAttribute("showEmployeeTable", false);
        request.getSession().setAttribute("showAssignmentTable", true);
    request.getRequestDispatcher("/vues/Dashboard.jsp").forward(request, response);
}

    @Override
    public String getServletInfo() {
        return "Servlet pour ajouter une affecter";
    }
}