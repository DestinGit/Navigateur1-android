/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pb.controls;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

/**
 *
 * @author formation
 */
@WebServlet(name = "CommuneToCSV", urlPatterns = {"/CommuneToCSV"})
public class CommuneToCSV extends HttpServlet {
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
             throws ServletException, IOException {
        
        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String lsMessage;
        StringBuilder sb = new StringBuilder();
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cours", "root", "");
                        
            String lsSQL = "SELECT * FROM communes";
            PreparedStatement ps = con.prepareStatement(lsSQL);
            ResultSet rs = ps.executeQuery();
                                  
            while (rs.next()) {
            sb.append(rs.getString(1));
            sb.append(" - ");
            sb.append(rs.getString(2));
            sb.append(" - ");
            sb.append(rs.getString(3));
            sb.append(" - ");
            sb.append(rs.getString(4));
            sb.append(" - ");
            sb.append(rs.getString(5));
            sb.append("\n");
            }
            
            lsMessage = sb.toString();
            
            //fermeture des connexions
            rs.close();
            ps.close();
            con.close();
            
        } catch (ClassNotFoundException e) {
            lsMessage = e.getMessage();
        } catch (SQLException e) {
            lsMessage = e.getMessage();
        }
        out.print(lsMessage);
    }///doGet
}
