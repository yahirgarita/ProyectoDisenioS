package gui;

/**
 *
 * @author Carlos Rojas Molina
 * @author Jimmy Tsang Feng
 * @author Yahir Garita Arias
 * 
 * @version 1.0
 */
public class Menu extends javax.swing.JFrame {

    /**
     * Creates new form Menu
     */
    public Menu() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton5 = new javax.swing.JButton();
        botonListarClientes = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnVentaDolar = new javax.swing.JButton();
        botonListarCuentas = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        butonCrearCuenta = new javax.swing.JButton();
        botonRegistrarClienteMenu1 = new javax.swing.JButton();
        btnConsultarSaldo = new javax.swing.JButton();
        btnConsultarSaldoDolares = new javax.swing.JButton();
        btnDepositarColones = new javax.swing.JButton();
        btnDepositarDolares1 = new javax.swing.JButton();
        btnRetiroEnColones = new javax.swing.JButton();
        btnCompraDolar = new javax.swing.JButton();
        botonCambiarPin = new javax.swing.JButton();
        btnRetiroEnDolares1 = new javax.swing.JButton();
        btnrealizarTransferencia = new javax.swing.JButton();
        btnGananciasCuenta = new javax.swing.JButton();
        btnGananciaBanco = new javax.swing.JButton();
        btnVerEstatus = new javax.swing.JButton();

        jButton5.setText("Volver");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        botonListarClientes.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        botonListarClientes.setText("Listar clientes");
        botonListarClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonListarClientesActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel1.setText("Menú de inicio");

        btnVentaDolar.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btnVentaDolar.setText("Tipo de Cambio Venta");
        btnVentaDolar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVentaDolarActionPerformed(evt);
            }
        });

        botonListarCuentas.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        botonListarCuentas.setText("Listar cuentas");
        botonListarCuentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonListarCuentasActionPerformed(evt);
            }
        });

        btnCerrar.setText("Cerrar");

        butonCrearCuenta.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        butonCrearCuenta.setText("Crear cuenta bancaria");
        butonCrearCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butonCrearCuentaActionPerformed(evt);
            }
        });

        botonRegistrarClienteMenu1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        botonRegistrarClienteMenu1.setText("Registrar cliente");
        botonRegistrarClienteMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRegistrarClienteMenu1ActionPerformed(evt);
            }
        });

        btnConsultarSaldo.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btnConsultarSaldo.setText("Consultar saldo actual");

        btnConsultarSaldoDolares.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btnConsultarSaldoDolares.setText("Saldo actual en Dólares");

        btnDepositarColones.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btnDepositarColones.setText("Depositar en Colones");
        btnDepositarColones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDepositarColonesActionPerformed(evt);
            }
        });

        btnDepositarDolares1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btnDepositarDolares1.setText("Depositar en Dolares");
        btnDepositarDolares1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDepositarDolares1ActionPerformed(evt);
            }
        });

        btnRetiroEnColones.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btnRetiroEnColones.setText("Retiro en Colones");
        btnRetiroEnColones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRetiroEnColonesActionPerformed(evt);
            }
        });

        btnCompraDolar.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btnCompraDolar.setText("Tipo de Cambio Compra");
        btnCompraDolar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompraDolarActionPerformed(evt);
            }
        });

        botonCambiarPin.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        botonCambiarPin.setText("Cambiar PIN");
        botonCambiarPin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCambiarPinActionPerformed(evt);
            }
        });

        btnRetiroEnDolares1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btnRetiroEnDolares1.setText("Retiro en Dolares");
        btnRetiroEnDolares1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRetiroEnDolares1ActionPerformed(evt);
            }
        });

        btnrealizarTransferencia.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btnrealizarTransferencia.setText("Realizar transferencia");
        btnrealizarTransferencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrealizarTransferenciaActionPerformed(evt);
            }
        });

        btnGananciasCuenta.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnGananciasCuenta.setText(" Ganancias comisiones por cuenta");
        btnGananciasCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGananciasCuentaActionPerformed(evt);
            }
        });

        btnGananciaBanco.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnGananciaBanco.setText("Ganancia comisiones banco");
        btnGananciaBanco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGananciaBancoActionPerformed(evt);
            }
        });

        btnVerEstatus.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btnVerEstatus.setText("Ver estatus de cuenta");
        btnVerEstatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerEstatusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnCerrar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(444, 444, 444)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(botonCambiarPin, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnRetiroEnColones, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(botonListarCuentas, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(77, 77, 77)
                                    .addComponent(btnDepositarDolares1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(botonRegistrarClienteMenu1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(butonCrearCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(botonListarClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(77, 77, 77)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(btnConsultarSaldo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnConsultarSaldoDolares, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnDepositarColones, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(btnVerEstatus, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(53, 53, 53)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnGananciaBanco, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnVentaDolar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnCompraDolar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnRetiroEnDolares1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnrealizarTransferencia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnGananciasCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(104, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(botonRegistrarClienteMenu1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnConsultarSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnRetiroEnDolares1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(butonCrearCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConsultarSaldoDolares, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVentaDolar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonListarClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDepositarColones, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCompraDolar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonListarCuentas, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDepositarDolares1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnrealizarTransferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRetiroEnColones, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonCambiarPin, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGananciasCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGananciaBanco, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVerEstatus, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                .addComponent(btnCerrar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonListarClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonListarClientesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonListarClientesActionPerformed

    private void btnVentaDolarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVentaDolarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVentaDolarActionPerformed

    private void botonListarCuentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonListarCuentasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonListarCuentasActionPerformed

    private void butonCrearCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butonCrearCuentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_butonCrearCuentaActionPerformed

    private void botonRegistrarClienteMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegistrarClienteMenu1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonRegistrarClienteMenu1ActionPerformed

    private void btnDepositarColonesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDepositarColonesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDepositarColonesActionPerformed

    private void btnDepositarDolares1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDepositarDolares1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDepositarDolares1ActionPerformed

    private void btnRetiroEnColonesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRetiroEnColonesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRetiroEnColonesActionPerformed

    private void btnCompraDolarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompraDolarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCompraDolarActionPerformed

    private void botonCambiarPinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCambiarPinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonCambiarPinActionPerformed

    private void btnRetiroEnDolares1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRetiroEnDolares1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRetiroEnDolares1ActionPerformed

    private void btnrealizarTransferenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrealizarTransferenciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnrealizarTransferenciaActionPerformed

    private void btnGananciasCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGananciasCuentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGananciasCuentaActionPerformed

    private void btnGananciaBancoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGananciaBancoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGananciaBancoActionPerformed

    private void btnVerEstatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerEstatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVerEstatusActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton botonCambiarPin;
    public javax.swing.JButton botonListarClientes;
    public javax.swing.JButton botonListarCuentas;
    public javax.swing.JButton botonRegistrarClienteMenu1;
    public javax.swing.JButton btnCerrar;
    public javax.swing.JButton btnCompraDolar;
    public javax.swing.JButton btnConsultarSaldo;
    public javax.swing.JButton btnConsultarSaldoDolares;
    public javax.swing.JButton btnDepositarColones;
    public javax.swing.JButton btnDepositarDolares1;
    public javax.swing.JButton btnGananciaBanco;
    public javax.swing.JButton btnGananciasCuenta;
    public javax.swing.JButton btnRetiroEnColones;
    public javax.swing.JButton btnRetiroEnDolares1;
    public javax.swing.JButton btnVentaDolar;
    public javax.swing.JButton btnVerEstatus;
    public javax.swing.JButton btnrealizarTransferencia;
    public javax.swing.JButton butonCrearCuenta;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
