/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import Bean.Employeur;
import Services.EmployerServices;
import java.io.IOException;
import java.io.PrintWriter;
import  java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "EmployerController", urlPatterns = {"/EmployerController"})
public class EmployerController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EmployerServices ps = new EmployerServices();
       List<Employeur> list_employees  = ps.recuper_all();
    req.getSession().setAttribute("list_employees", list_employees);
    req.getSession().setAttribute("showLocationTable", false); // Correction du nom de l'attribut
    req.getSession().setAttribute("showEmployeeTable", true);
    req.getSession().setAttribute("showAssignmentTable", false);
    req.getRequestDispatcher("/vues/Dashboard.jsp").forward(req, resp);
        
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException {
   String code_emp = req.getParameter("code_emp");
    String nom = req.getParameter("nom");
    String prenom = req.getParameter("prenom");
    String poste = req.getParameter("poste");

    // Debug log
    System.out.println("===> Nouveau employé : " + code_emp + ", " + nom + ", " + prenom + ", " + poste);

    if (code_emp != null && !code_emp.trim().isEmpty() &&
        nom != null && !nom.trim().isEmpty() &&
        prenom != null && !prenom.trim().isEmpty() &&
        poste != null && !poste.trim().isEmpty()) {
        
        Employeur p = new Employeur(code_emp, nom, prenom, poste);
        EmployerServices ps = new EmployerServices();
        boolean b = ps.create(p);

        if (b) {
            List<Employeur> list_employees = ps.recuper_all();
            req.getSession().setAttribute("list_employees", list_employees);
            req.getSession().setAttribute("message", "Employé enregistré avec succès !");
            req.getSession().setAttribute("messageType", "success");
        } else {
            req.getSession().setAttribute("message", "Erreur lors de l'enregistrement de l'employé.");
            req.getSession().setAttribute("messageType", "danger");
        }
    } else {
        req.getSession().setAttribute("message", "Tous les champs sont obligatoires !");
        req.getSession().setAttribute("messageType", "warning");
    }
    
    req.getSession().setAttribute("showLocationTable", false); // Correction du nom de l'attribut
    req.getSession().setAttribute("showEmployeeTable", true);
    req.getSession().setAttribute("showAssignmentTable", false);
    req.getRequestDispatcher("/vues/Dashboard.jsp").forward(req, resp);
    }

   
}
