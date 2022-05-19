/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladoresgui;

import gui.ConsultarVentaDolar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import util.TipoCambio;

/**
 *
 * @author Jimmy
 */
public class ControladorVentaDolar implements ActionListener {

    public ConsultarVentaDolar ventaDolar;
    private TipoCambio tipoCambio;
    
    public ControladorVentaDolar(ConsultarVentaDolar compraDolar){
        this.ventaDolar = compraDolar;
        this.tipoCambio = new TipoCambio();
        this.ventaDolar.btnVolver.addActionListener(this);
        this.ventaDolar.txtTipoCambio.setText(String.valueOf(this.tipoCambio.getVenta()));
    }
    @Override
    public void actionPerformed(ActionEvent evento) {
        switch(evento.getActionCommand()){
            case "Volver":
                controladoresgui.ControladoresGlobales.volver();
                this.ventaDolar.setVisible(false);
            default:
                break;
        }
    }
    
}
