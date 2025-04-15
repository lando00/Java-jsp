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

@WebServlet(name = "UpdateAffecter", urlPatterns = {"/UpdateAffecter"})
public class UpdateAffecter extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/vues/Dashboard.jsp");
    }

 @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String idParam = request.getParameter("id");
    String code_emp = request.getParameter("code_emp");
    String codeLieu = request.getParameter("codeLieu");
    String dateAffectationStr = request.getParameter("dateAffectation");

    AffecterService occAffecter = new AffecterService();

    if (idParam != null && !idParam.isEmpty() &&
            codeLieu != null && !codeLieu.trim().isEmpty() &&
            code_emp != null && !code_emp.trim().isEmpty() &&
            dateAffectationStr != null && !dateAffectationStr.trim().isEmpty()) {
        try {
            long id = Long.parseLong(idParam);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dateAffectation = sdf.parse(dateAffectationStr);

            // Vérifier si la salle est occupée à cette date
            if (occAffecter.isAffecter(code_emp,codeLieu, dateAffectation)) {
                // Récupérer l'occupation actuelle pour vérifier si c'est la même
                Affecter existingOcc = occAffecter.recuper(id);
                if (existingOcc == null || !existingOcc.getCodeLieu().equals(codeLieu) 
                || !existingOcc.getDateAffectation().equals(dateAffectation) || !existingOcc.getCode_emp().equals(code_emp)) {
                    request.getSession().setAttribute("message", "Erreur de l'affectation.");
                    request.getSession().setAttribute("messageType", "danger");
                } else {
                    // Si c'est la même occupation, procéder à la mise à jour
                    Affecter occ = new Affecter(codeLieu, code_emp, dateAffectation);
                    occ.setId(id);
                    boolean success = occAffecter.update(occ);
                    List<Affecter> listaffecter = occAffecter.recuper_all();
                    request.getSession().setAttribute("listaffecter", listaffecter);

                    if (success) {
                        request.getSession().setAttribute("message", "Affectation modifiée avec succès !");
                        request.getSession().setAttribute("messageType", "success");
                    } else {
                        request.getSession().setAttribute("message", "Erreur lors de la modification de l' affectation.");
                        request.getSession().setAttribute("messageType", "danger");
                    }
                }
            } else {
                // Si la salle n'est pas occupée, procéder à la mise à jour
                Affecter occ = new Affecter(codeLieu, code_emp, dateAffectation);
                occ.setId(id);
                boolean success = occAffecter.update(occ);
                List<Affecter> listaffecter = occAffecter.recuper_all();
                request.getSession().setAttribute("listaffecter", listaffecter);

                if (success) {
                    request.getSession().setAttribute("message", "Affectation modifiée avec succès !");
                    request.getSession().setAttribute("messageType", "success");
                } else {
                    request.getSession().setAttribute("message", "Erreur lors de la modification de l'affectation.");
                    request.getSession().setAttribute("messageType", "danger");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("message", "Erreur lors de la modification de l'affectation : " + e.getMessage());
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
        return "Servlet pour modifier une affecter";
    }
}