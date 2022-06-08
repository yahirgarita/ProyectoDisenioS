/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladoresweb;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logicadeaccesoadatos.CuentaBD;
import logicadeaccesoadatos.OperacionBD;
import logicadenegocios.CuentaBancaria;
import logicadenegocios.Operacion;
import logicadenegocios.Persona;
import util.Email;
import util.Encriptar;
import util.TipoCambio;
import validaciones.Validar;

/**
 *
 * @author Jimmy
 */
@WebServlet(name = "ControladorEstadoCuentaWeb", urlPatterns = {"/EstadoCuentaWeb"})
public class ControladorEstadoCuentaWeb extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    int intentos = 0;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        TipoCambio tipoCambio = new TipoCambio();
        String numeroCuenta = request.getParameter("numeroCuenta");
        if(Validar.validarEstatusInactivo(Encriptar.cifrar(numeroCuenta))){
            PrintWriter out = response.getWriter();  
            response.setContentType("text/html");  
            out.println("<script type=\"text/javascript\">");  
            out.println("alert('Cuenta esta inactiva');");
            out.println("location='VistasWeb/ConsultarEstadoCuenta.jsp';");
            out.println("</script>");
        }
        CuentaBancaria cuenta = CuentaBD.recuperarCuentaXNum(Encriptar.cifrar(numeroCuenta));
        
        String pinActual = request.getParameter("pin");
        if(intentos <= 1){
            if(pinActual.equals(cuenta.getPin())){
                double saldo = cuenta.getSaldo();
                /*Operacion oper = new Operacion("consultas", "No aplica", false, 0, LocalDate.now());
                OperacionBD.realizarOperacionEnBD(oper,Encriptar.cifrar(numeroCuenta));*/
                intentos = 0;
                if (request.getParameter("colones") != null) {
                    ArrayList<HashMap<String,String>> resultados = new ArrayList<>();
                    ArrayList<Operacion> operaciones = OperacionBD.obtenerOperacionesPorNumCuenta(Encriptar.cifrar(numeroCuenta));
                    for(int i = 0;i < operaciones.size(); i++){
                        HashMap<String,String> nuevo = new HashMap<>();
                        double monto = operaciones.get(i).getMonto();
                        if(operaciones.get(i).getMoneda().equals("Dólares")){
                            monto = operaciones.get(i).getMonto() * tipoCambio.getCompra();
                        }
                        nuevo.put("fecha", operaciones.get(i).getFechaOperacion().toString());
                        nuevo.put("tipo", operaciones.get(i).getTipo());
                        nuevo.put("monto", String.valueOf(Math.round(monto*100.0)/100.0));
                        nuevo.put("cargo", operaciones.get(i).getCargo());        
                        resultados.add(nuevo);
                    }
                    request.setAttribute("cuenta", numeroCuenta);
                    request.setAttribute("operaciones", resultados);
                    request.getRequestDispatcher("VistasWeb/EstadoDeCuenta.jsp").forward(request, response);
                }      
                else if (request.getParameter("dolares") != null) {
                    ArrayList<HashMap<String,String>> resultados = new ArrayList<>();
                    ArrayList<Operacion> operaciones = OperacionBD.obtenerOperacionesPorNumCuenta(Encriptar.cifrar(numeroCuenta));
                    for(int i = 0;i < operaciones.size(); i++){
                        HashMap<String,String> nuevo = new HashMap<>();
                        double monto = operaciones.get(i).getMonto();
                        if(operaciones.get(i).getMoneda().equals("Colones")){
                            monto = operaciones.get(i).getMonto() / tipoCambio.getVenta();
                        }
                        
                        nuevo.put("fecha", operaciones.get(i).getFechaOperacion().toString());
                        nuevo.put("tipo", operaciones.get(i).getTipo());
                        nuevo.put("monto", String.valueOf(Math.round(monto*100.0)/100.0));
                        nuevo.put("cargo", operaciones.get(i).getCargo());
                        resultados.add(nuevo);
                    }
                    request.setAttribute("cuenta", numeroCuenta);
                    request.setAttribute("operaciones", resultados);
                    request.getRequestDispatcher("VistasWeb/EstadoDeCuenta.jsp").forward(request, response);
                }
                return;
            }
            intentos++;
            PrintWriter out = response.getWriter();  
            response.setContentType("text/html");  
            out.println("<script type=\"text/javascript\">");  
            out.println("alert('Pin invalido "+intentos+"');");
            out.println("location='VistasWeb/ConsultarEstadoCuenta.jsp';");
            out.println("</script>");
            return;
        }
        CuentaBD.modificarEstado(Encriptar.cifrar(numeroCuenta), "Inactiva");
        Persona comparacionPersonaCuenta = CuentaBD.compararPersonaConCuenta(Encriptar.cifrar(numeroCuenta));
        try {
            Email.enviarEmail(comparacionPersonaCuenta.getCorreoPersona(), "Su cuenta a pasado a estar Inactiva por fallar el PIN");
        } catch (MessagingException ex) {
            Logger.getLogger(ControladorConsultarSaldoActualWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        intentos = 0;
        PrintWriter out = response.getWriter();  
        response.setContentType("text/html");  
        out.println("<script type=\"text/javascript\">");  
        out.println("alert('Su cuenta se ha inactivado');");
        out.println("location='VistasWeb/ConsultarEstadoCuenta.jsp';");
        out.println("</script>");
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
