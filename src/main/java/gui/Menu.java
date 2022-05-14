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
        botonCambiarPin = new javax.swing.JButton();
        botonListarCuentas = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        butonCrearCuenta = new javax.swing.JButton();
        botonRegistrarClienteMenu1 = new javax.swing.JButton();
        btnConsultarSaldo = new javax.swing.JButton();

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

        botonCambiarPin.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        botonCambiarPin.setText("Cambiar PIN");
        botonCambiarPin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCambiarPinActionPerformed(evt);
            }
        });

        botonListarCuentas.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        botonListarCuentas.setText("Listar cuentas");
        botonListarCuentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonListarCuentasActionPerformed(evt);
            }
        });

        jButton6.setText("Cerrar");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(botonListarCuentas, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botonListarClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botonCambiarPin, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(butonCrearCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(botonRegistrarClienteMenu1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(77, 77, 77)
                                .addComponent(btnConsultarSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton6)))
                .addContainerGap(66, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(265, 265, 265))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonRegistrarClienteMenu1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConsultarSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addComponent(butonCrearCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botonListarClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botonListarCuentas, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botonCambiarPin, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jButton6)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonListarClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonListarClientesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonListarClientesActionPerformed

    private void botonCambiarPinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCambiarPinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonCambiarPinActionPerformed

    private void botonListarCuentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonListarCuentasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonListarCuentasActionPerformed

    private void butonCrearCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butonCrearCuentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_butonCrearCuentaActionPerformed

    private void botonRegistrarClienteMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegistrarClienteMenu1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonRegistrarClienteMenu1ActionPerformed

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
    public javax.swing.JButton btnConsultarSaldo;
    public javax.swing.JButton butonCrearCuenta;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
