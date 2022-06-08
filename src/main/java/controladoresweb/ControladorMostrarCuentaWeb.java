/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladoresweb;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logicadeaccesoadatos.CuentaBD;
import logicadeaccesoadatos.PersonaBD;
import logicadenegocios.CuentaBancaria;
import logicadenegocios.Persona;
import util.Encriptar;

/**
 *
 * @author Jimmy
 */
@WebServlet(name = "ControladorMostrarCuentaWeb", urlPatterns = {"/MostrarCuentaWeb"})
public class ControladorMostrarCuentaWeb extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String numeroCuenta = request.getParameter("numeroCuenta");
        CuentaBancaria cuenta = CuentaBD.recuperarCuentaXNum(Encriptar.cifrar(numeroCuenta));
        request.setAttribute("cuenta", cuenta.getNumCuenta());
        request.setAttribute("estatus", cuenta.getEstatus());
        request.setAttribute("saldo", cuenta.getSaldo());
        request.setAttribute("fecha", cuenta.getFechaCreacion());
        
        
        request.getRequestDispatcher("VistasWeb/MostrarCuenta.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
