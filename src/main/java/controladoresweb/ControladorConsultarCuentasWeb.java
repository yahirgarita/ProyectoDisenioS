/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladoresweb;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logicadeaccesoadatos.CuentaBD;
import logicadenegocios.CuentaBancaria;
import logicadenegocios.Persona;
import util.Encriptar;

/**
 *
 * @author Jimmy
 */
@WebServlet(name = "ControladorConsultarCuentasWeb", urlPatterns = {"/ConsultarCuentasWeb"})
public class ControladorConsultarCuentasWeb extends HttpServlet {
    
    private ArrayList<CuentaBancaria> cuentas;
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
        this.cuentas = CuentaBD.recuperarCuentas();
        organizarCuentas();
        ArrayList<HashMap<String,String>> resultados = new ArrayList<>();
        for(int i = 0;i<this.cuentas.size();i++){
            Persona duenio = CuentaBD.compararPersonaConCuenta(Encriptar.cifrar(String.valueOf(cuentas.get(i).getNumCuenta())));
            HashMap<String,String> nuevo = new HashMap<>();
            nuevo.put("numeroCuenta",String.valueOf(cuentas.get(i).getNumCuenta()));
            nuevo.put("estatus",cuentas.get(i).getEstatus());
            nuevo.put("saldo",String.valueOf(cuentas.get(i).getSaldo()));
            nuevo.put("nombre",String.valueOf(duenio.getNombreCompleto()));
            nuevo.put("identificacion",String.valueOf(duenio.getIdPersona()));
            resultados.add(nuevo);
        } 
        request.setAttribute("cuentas", resultados);
        request.getRequestDispatcher("VistasWeb/ListarCuentas.jsp").forward(request, response);
        
    }
    
    private void organizarCuentas(){
        cuentas.sort(Comparator.comparing(CuentaBancaria::getSaldo).reversed());
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
