/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import gui.ConsultarCompraDolar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import util.TipoCambio;

/**
 *
 * @author Jimmy
 */
public class ControladorCompraDolar implements ActionListener {

    public ConsultarCompraDolar compraDolar;
    private TipoCambio tipoCambio;
    
    public ControladorCompraDolar(ConsultarCompraDolar compraDolar){
        this.compraDolar = compraDolar;
        this.tipoCambio = new TipoCambio();
        this.compraDolar.btnVolver.addActionListener(this);
        this.compraDolar.txtTipoCambio.setText(String.valueOf(this.tipoCambio.getCompra()));
    }
    @Override
    public void actionPerformed(ActionEvent evento) {
        switch(evento.getActionCommand()){
            case "Volver":
                controladores.ControladoresGlobales.volver();
                this.compraDolar.setVisible(false);
            default:
                break;
        }
    }
    
}
