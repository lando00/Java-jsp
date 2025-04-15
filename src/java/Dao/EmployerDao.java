/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Bean.Employeur;
import java.util.List;

public interface EmployerDao<T>{
     boolean create(T f);
     boolean update(T f);
     boolean  delete(long id);
     T recuper(long id);
     List<T> recuper_all();
     
}
///vues/Dashboard.jsp