/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import kontrol.koneksi;

/**
 *
 * @author Bayu
 */
public class main_menu extends javax.swing.JFrame {
    private final Connection conn = new koneksi().connect();
    private DefaultTableModel tabmodel;
    protected void dataTable() throws SQLException{
        Object[] baris = {"ID", "Username"};
        tabmodel = new DefaultTableModel(null, baris);
        table_user.setModel(tabmodel);
        String sql = "select userid, username from login ORDER BY userid ASC";
        try{
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                int id = hasil.getInt("userid");
                String username = hasil.getString("username");
                tabmodel.addRow(new Object[]{id, username});
            }
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null,
                    "Gk bisa nampilin data bos"+e);
        }
    
    }
     
    /**
     * Creates new form main_menu
     */
    int cekusername(String username){
        String sql = "select * from login where username='"+username+"'";
        int count = 0;
        try{
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                count = count+1;   
            }
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
        return count;
    }
    int cekpassword(String username, String password){
        String sql = "select * from login where username='"+username+"' and password='"+password+"'";
        int count = 0;
        try{
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                count = count+1;   
            }
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
        return count;
    }
    void login(int user, int pass) throws SQLException{
        if((user == 1) && (pass == 1)){
            statustext.setForeground(Color.blue);
            statustext.setText("Masuk(Normal User)");
            bukapanel();
            if(tf_username.getText().equals("superadmin")){
                statustext.setText("Masuk(Superadmin)");
                dataTable();
                tf_userbaru.setEnabled(true);
                pf_passwordbaru.setEnabled(true);
                bt_regist.setEnabled(true);
            }
            kosong();
        }
        else{
            JOptionPane.showMessageDialog(null, "Username dan/atau Password Salah");
        }
    }
    void bukapanel(){
        jb_barang_baru.setEnabled(true);
        jb_daftar_barang.setEnabled(true);
        jb_daftar_jasa.setEnabled(true);
        jb_trans_baru.setEnabled(true);
        jb_daftar_trans.setEnabled(true);
        jb_laporan.setEnabled(true);
    }
    void kosong(){
        tf_username.setText("");
        pf_password.setText("");
        tf_userbaru.setText("");
        pf_passwordbaru.setText("");
    }
    
    public main_menu() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bengkel = new javax.swing.JLabel();
        alamat = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jb_barang_baru = new javax.swing.JButton();
        jb_daftar_barang = new javax.swing.JButton();
        jb_daftar_jasa = new javax.swing.JButton();
        jb_trans_baru = new javax.swing.JButton();
        jb_daftar_trans = new javax.swing.JButton();
        jb_laporan = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        pf_password = new javax.swing.JPasswordField();
        tf_username = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        statustext = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tf_userbaru = new javax.swing.JTextField();
        pf_passwordbaru = new javax.swing.JPasswordField();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_user = new javax.swing.JTable();
        bt_regist = new javax.swing.JButton();
        bt_delete = new javax.swing.JButton();
        bt_clear = new javax.swing.JButton();
        logo = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Main Menu");
        setLocation(new java.awt.Point(250, 140));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bengkel.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        bengkel.setText("BENGKEL KENDALI MOTOR");
        getContentPane().add(bengkel, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, -1, -1));

        alamat.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        alamat.setText("Jl.Karangrayung No.24 RT 1/RW 1, Purwodadi, Grobongan, Jawa Tengah.");
        getContentPane().add(alamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, -1, -1));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(192, 0, 10, 170));

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(352, 0, 10, 170));

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(532, 0, 10, 170));

        jb_barang_baru.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/small48-012-paper-1.png"))); // NOI18N
        jb_barang_baru.setText("Barang Baru");
        jb_barang_baru.setContentAreaFilled(false);
        jb_barang_baru.setEnabled(false);
        jb_barang_baru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_barang_baruActionPerformed(evt);
            }
        });
        jPanel1.add(jb_barang_baru, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jb_daftar_barang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/small48-014-stack.png"))); // NOI18N
        jb_daftar_barang.setText("Daftar Barang");
        jb_daftar_barang.setContentAreaFilled(false);
        jb_daftar_barang.setEnabled(false);
        jb_daftar_barang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_daftar_barangActionPerformed(evt);
            }
        });
        jPanel1.add(jb_daftar_barang, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        jb_daftar_jasa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/small48-014-stack.png"))); // NOI18N
        jb_daftar_jasa.setText("Daftar Jasa");
        jb_daftar_jasa.setContentAreaFilled(false);
        jb_daftar_jasa.setEnabled(false);
        jb_daftar_jasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_daftar_jasaActionPerformed(evt);
            }
        });
        jPanel1.add(jb_daftar_jasa, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, -1, -1));

        jb_trans_baru.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/small48-012-paper-1.png"))); // NOI18N
        jb_trans_baru.setText("Transaksi Baru");
        jb_trans_baru.setContentAreaFilled(false);
        jb_trans_baru.setEnabled(false);
        jb_trans_baru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_trans_baruActionPerformed(evt);
            }
        });
        jPanel1.add(jb_trans_baru, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, -1, -1));

        jb_daftar_trans.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/small48-014-stack.png"))); // NOI18N
        jb_daftar_trans.setText("Daftar Transaksi");
        jb_daftar_trans.setContentAreaFilled(false);
        jb_daftar_trans.setEnabled(false);
        jb_daftar_trans.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_daftar_transActionPerformed(evt);
            }
        });
        jPanel1.add(jb_daftar_trans, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 100, -1, -1));

        jb_laporan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/small48-013-print.png"))); // NOI18N
        jb_laporan.setText("Laporan Bulanan");
        jb_laporan.setContentAreaFilled(false);
        jb_laporan.setEnabled(false);
        jb_laporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_laporanActionPerformed(evt);
            }
        });
        jPanel1.add(jb_laporan, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 20, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 320, 710, 170));

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Username : ");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));

        jLabel2.setText("Password  : ");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));
        jPanel2.add(pf_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, 90, -1));
        jPanel2.add(tf_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, 90, -1));

        jButton1.setText("Login");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, -1, -1));

        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel2.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 0, 10, 170));

        jLabel3.setText("Status : ");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, -1, -1));

        statustext.setForeground(new java.awt.Color(255, 51, 51));
        statustext.setText("BELUM MASUK");
        jPanel2.add(statustext, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, -1, -1));
        jPanel2.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 28, 180, 10));

        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel2.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(392, 0, 20, 30));

        jLabel4.setText("Password  : ");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 40, -1, -1));

        jLabel5.setText("Username : ");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 40, -1, -1));

        tf_userbaru.setEnabled(false);
        jPanel2.add(tf_userbaru, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 40, 90, -1));

        pf_passwordbaru.setEnabled(false);
        jPanel2.add(pf_passwordbaru, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 40, 90, -1));

        table_user.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "Username"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_user.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_userMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_user);
        if (table_user.getColumnModel().getColumnCount() > 0) {
            table_user.getColumnModel().getColumn(0).setMinWidth(30);
            table_user.getColumnModel().getColumn(0).setMaxWidth(30);
        }

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 70, 180, 80));

        bt_regist.setText("Register");
        bt_regist.setEnabled(false);
        bt_regist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_registActionPerformed(evt);
            }
        });
        jPanel2.add(bt_regist, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 80, -1, -1));

        bt_delete.setText("Delete");
        bt_delete.setEnabled(false);
        bt_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_deleteActionPerformed(evt);
            }
        });
        jPanel2.add(bt_delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 120, 70, -1));

        bt_clear.setText("Clear");
        bt_clear.setEnabled(false);
        bt_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_clearActionPerformed(evt);
            }
        });
        jPanel2.add(bt_clear, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 120, 70, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 140, 590, 170));

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/pistonseditan3small80.png"))); // NOI18N
        logo.setMaximumSize(new java.awt.Dimension(80, 80));
        logo.setMinimumSize(new java.awt.Dimension(80, 80));
        getContentPane().add(logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 80, 80));

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/GreyBackground.png"))); // NOI18N
        getContentPane().add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(-9, -8, 810, 530));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jb_barang_baruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_barang_baruActionPerformed
        new inputbarang().setVisible(true);
        dispose();
    }//GEN-LAST:event_jb_barang_baruActionPerformed

    private void jb_daftar_barangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_daftar_barangActionPerformed
        try {
            new daftarbarang().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        }
        dispose();
    }//GEN-LAST:event_jb_daftar_barangActionPerformed

    private void jb_daftar_jasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_daftar_jasaActionPerformed
        try {
            new daftarjasa().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        }
        dispose();
    }//GEN-LAST:event_jb_daftar_jasaActionPerformed

    private void jb_trans_baruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_trans_baruActionPerformed
        new transaksibaru().setVisible(true);
        dispose();
    }//GEN-LAST:event_jb_trans_baruActionPerformed

    private void jb_daftar_transActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_daftar_transActionPerformed
        try {
            new daftartransaksi().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        }
        dispose();
    }//GEN-LAST:event_jb_daftar_transActionPerformed

    private void jb_laporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_laporanActionPerformed
        new home().setVisible(true);
        dispose();
    }//GEN-LAST:event_jb_laporanActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String user = tf_username.getText();
        char[] ch_pass = pf_password.getPassword();
        int count_user =0, count_pass = 0;
        String pass = "";
        for(int i=0; i<ch_pass.length; i++){
            pass = pass + ch_pass[i];
        }
        count_user = cekusername(user);
        count_pass = cekpassword(user, pass);
        try {
            login(count_user, count_pass);
        } catch (SQLException ex) {
            Logger.getLogger(main_menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void table_userMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_userMouseClicked
        int row = table_user.getSelectedRow();
        if (row != -1) {
            tf_userbaru.setText(table_user.getValueAt(row, 1).toString());
            bt_regist.setEnabled(false);
            bt_delete.setEnabled(true);
            bt_clear.setEnabled(true);
        }
    }//GEN-LAST:event_table_userMouseClicked

    private void bt_registActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_registActionPerformed
        String sql_save = "insert into login(username, password) values (?,?)";
        try{
            PreparedStatement stat = conn.prepareStatement(sql_save);
            char[] ch_pass = pf_passwordbaru.getPassword();
            String pass = "";
            for(int i=0; i<ch_pass.length; i++){
                pass = pass + ch_pass[i];
            }
            stat.setString(1, tf_userbaru.getText());
            stat.setString(2, pass);           
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null,
                    "Data Berasil disimpan");
            dataTable();
            kosong();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Data gagal disimpan"+ex);
        }
    }//GEN-LAST:event_bt_registActionPerformed

    private void bt_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_deleteActionPerformed
        int ok = JOptionPane.showConfirmDialog(null,
                "Anda yakin ingin menghapus data?",
                "Konfirmasi",
                JOptionPane.YES_NO_OPTION);
        if (ok==0){
            if(!(tf_userbaru.getText().equals("superadmin"))){
                String sql = "delete from login where username='"+tf_userbaru.getText()+"'";
                System.out.println(sql);
                try{
                    PreparedStatement stat = conn.prepareStatement(sql);
                    stat.executeUpdate();
                    JOptionPane.showMessageDialog(null,
                           "Data berhasil dihapus");
                    dataTable();
                    kosong();
                    bt_regist.setEnabled(true);
                    
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Data gagal dihapus"+e);
                }
            }
            else{
                JOptionPane.showMessageDialog(null,"superadmin tidak bisa dihapus");
            }
        }
    }//GEN-LAST:event_bt_deleteActionPerformed

    private void bt_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_clearActionPerformed
        kosong();
        bt_regist.setEnabled(true);
    }//GEN-LAST:event_bt_clearActionPerformed

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
            java.util.logging.Logger.getLogger(main_menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(main_menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(main_menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(main_menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new main_menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel alamat;
    private javax.swing.JLabel background;
    private javax.swing.JLabel bengkel;
    private javax.swing.JButton bt_clear;
    private javax.swing.JButton bt_delete;
    private javax.swing.JButton bt_regist;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JButton jb_barang_baru;
    private javax.swing.JButton jb_daftar_barang;
    private javax.swing.JButton jb_daftar_jasa;
    private javax.swing.JButton jb_daftar_trans;
    private javax.swing.JButton jb_laporan;
    private javax.swing.JButton jb_trans_baru;
    private javax.swing.JLabel logo;
    private javax.swing.JPasswordField pf_password;
    private javax.swing.JPasswordField pf_passwordbaru;
    private javax.swing.JLabel statustext;
    private javax.swing.JTable table_user;
    private javax.swing.JTextField tf_userbaru;
    private javax.swing.JTextField tf_username;
    // End of variables declaration//GEN-END:variables
}
