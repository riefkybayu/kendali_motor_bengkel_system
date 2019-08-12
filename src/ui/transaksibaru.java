/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import kontrol.koneksi;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;


/**
 *
 * @author Bayu
 */
public class transaksibaru extends javax.swing.JFrame {
    private final Connection conn = new koneksi().connect();
    String kdbrg1, kdbrg2, kdbrg3, kdbrg4, kdbrg5;
    int harga1, harga2, harga3, harga4, harga5;
    int total1, total2, total3, total4, total5;
    int jumlah1, jumlah2, jumlah3, jumlah4, jumlah5;
    int modal1, modal2, modal3, modal4, modal5;
    int notrans, jumlahtemp;
    int status_transaksi = 0;
    int global_total = 0;
        /**
     * Creates new form transaksibaru
     */
    public transaksibaru() {
        initComponents();
        isicombobox();
    }
    
    private void isicombobox(){
        try{
            String sql = "select kode_barang from barang";
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            cbbarang1.addItem("");
            cbbarang2.addItem("");
            cbbarang3.addItem("");
            cbbarang4.addItem("");
            cbbarang5.addItem("");
            while(hasil.next()){
                String skdbrg = hasil.getString("kode_barang");
                cbbarang1.addItem(skdbrg);
                cbbarang2.addItem(skdbrg);
                cbbarang3.addItem(skdbrg);
                cbbarang4.addItem(skdbrg);
                cbbarang5.addItem(skdbrg);
            }
            
            String sqljasa = "select no_jasa from jasa";
            ResultSet hasiljasa = stat.executeQuery(sqljasa);
            cb_jasa.addItem("");
            while(hasiljasa.next()){
                int no_jasa = hasiljasa.getInt("no_jasa");
                String string_no_jasa = Integer.toString(no_jasa);
                cb_jasa.addItem(string_no_jasa);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void isinama(String item, int nomer){
        try{
            String sql = "select nama_barang from barang where kode_barang='"+item+"'";
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String snmbrg = hasil.getString("nama_barang");
                switch(nomer){
                    case 1 : 
                        tfbarang1.setText(snmbrg); break;
                    case 2 :
                        tfbarang2.setText(snmbrg); break;
                    case 3 :
                        tfbarang3.setText(snmbrg); break;
                    case 4 :
                        tfbarang4.setText(snmbrg); break;
                    case 5 :
                        tfbarang5.setText(snmbrg); break;
                }    
            }
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    
    }

    private void isiharga(String item, int nomer){
        try{
            String sql = "select harga from barang where kode_barang='"+item+"'";
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                int harga = hasil.getInt("harga");
                String sharga = Integer.toString(harga);
                switch(nomer){
                    case 1 : 
                        tfharga1.setText(sharga); 
                        harga1 = harga; 
                        tfjumlah1.requestFocus();
                        break;
                    case 2 :
                        tfharga2.setText(sharga); 
                        harga2 = harga; 
                        tfjumlah2.requestFocus();
                        break;
                    case 3 :
                        tfharga3.setText(sharga);
                        harga3 = harga;
                        tfjumlah3.requestFocus();
                        break;
                    case 4 :
                        tfharga4.setText(sharga);
                        harga4 = harga; 
                        tfjumlah4.requestFocus();
                        break;
                    case 5 :
                        tfharga5.setText(sharga); 
                        harga5 = harga;
                        tfjumlah5.requestFocus();
                        break;
                }   
            }
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void kosong(){
        cbbarang1.setSelectedIndex(0);
        cbbarang2.setSelectedIndex(0);
        cbbarang3.setSelectedIndex(0);
        cbbarang4.setSelectedIndex(0);
        cbbarang5.setSelectedIndex(0);
        tfbarang1.setText("");
        tfbarang2.setText("");
        tfbarang3.setText("");
        tfbarang4.setText("");
        tfbarang5.setText("");
        tfjumlah1.setText("");
        tfjumlah2.setText("");
        tfjumlah3.setText("");
        tfjumlah4.setText("");
        tfjumlah5.setText("");
        tfharga1.setText("");
        tfharga2.setText("");
        tfharga3.setText("");
        tfharga4.setText("");
        tfharga5.setText("");
        tftotal1.setText("");
        tftotal2.setText("");
        tftotal3.setText("");
        tftotal4.setText("");
        tftotal5.setText("");
        cbbarang1.setEnabled(false);
        cbbarang2.setEnabled(false);
        cbbarang3.setEnabled(false);
        cbbarang4.setEnabled(false);
        cbbarang5.setEnabled(false);
        
        cb_jasa.setSelectedIndex(0);
        cb_jasa.setEnabled(false);
        tbjasa.setText("");
        tbhargajasa.setText("");
        bsave.setEnabled(false);
    }
    
    private void setsatu(){
        String sjumlah = tfjumlah1.getText();
        jumlah1 = Integer.valueOf(sjumlah);
        total1 = jumlah1*harga1;
        tftotal1.setText(Integer.toString(total1));
        cbbarang2.setEnabled(true);
    }
    
    private void setdua(){
        String sjumlah = tfjumlah2.getText();
        jumlah2 = Integer.valueOf(sjumlah);
        total2 = jumlah2*harga2;
        tftotal2.setText(Integer.toString(total2));
        cbbarang3.setEnabled(true);
    }
    
    private void settiga(){
        String sjumlah = tfjumlah3.getText();
        jumlah3 = Integer.valueOf(sjumlah);
        total3 = jumlah3*harga3;
        tftotal3.setText(Integer.toString(total3));
        cbbarang4.setEnabled(true);
    }
    
    private void setempat(){
        String sjumlah = tfjumlah4.getText();
        jumlah4 = Integer.valueOf(sjumlah);
        total4 = jumlah4*harga4;
        tftotal4.setText(Integer.toString(total4));
        cbbarang5.setEnabled(true);
    }
    
    private void setlima(){
        String sjumlah = tfjumlah5.getText();
        jumlah5 = Integer.valueOf(sjumlah);
        total5 = jumlah5*harga5;
        tftotal5.setText(Integer.toString(total5));
    }
    
    private void cetak_nota(){
        String param_kdbrg_1= null, param_kdbrg_2 = null, param_kdbrg_3 = null, param_kdbrg_4 = null, param_kdbrg_5 = null;
        String param_nmbrg_1 = null, param_nmbrg_2 = null, param_nmbrg_3 = null, param_nmbrg_4 = null, param_nmbrg_5 = null;
        String param_jumlah_1 = null, param_jumlah_2 = null, param_jumlah_3 = null, param_jumlah_4 = null, param_jumlah_5 = null;
        String param_harga_1 = null, param_harga_2 = null, param_harga_3 = null, param_harga_4 = null, param_harga_5 = null;
        String param_total_1 = null, param_total_2 = null, param_total_3 = null, param_total_4 = null, param_total_5 = null;
        String param_nojasa = null, param_nmjasa = null, param_hargajasa = null; String param_global_total = null;
        
        param_kdbrg_1 = (String)cbbarang1.getSelectedItem(); param_kdbrg_2 = (String)cbbarang2.getSelectedItem(); param_kdbrg_3 = (String)cbbarang3.getSelectedItem(); 
        param_kdbrg_4 = (String)cbbarang4.getSelectedItem(); param_kdbrg_5 = (String)cbbarang5.getSelectedItem();
        param_nmbrg_1 = tfbarang1.getText(); param_nmbrg_2 = tfbarang2.getText(); param_nmbrg_3 = tfbarang3.getText(); 
        param_nmbrg_4 = tfbarang4.getText(); param_nmbrg_5 = tfbarang5.getText();
        param_jumlah_1 = tfjumlah1.getText(); param_jumlah_2 = tfjumlah2.getText(); param_jumlah_3 = tfjumlah3.getText();
        param_jumlah_4 = tfjumlah4.getText(); param_jumlah_5 = tfjumlah5.getText();
        param_harga_1 = tfharga1.getText(); param_harga_2 = tfharga2.getText(); param_harga_3 = tfharga3.getText();
        param_harga_4 = tfharga4.getText(); param_harga_5 = tfharga5.getText();
        param_total_1 = tftotal1.getText(); param_total_2 = tftotal2.getText(); param_total_3 = tftotal3.getText();
        param_total_4 = tftotal4.getText(); param_total_5 = tftotal5.getText();
        param_nojasa = (String)cb_jasa.getSelectedItem(); param_nmjasa = tbjasa.getText(); param_hargajasa = tbhargajasa.getText();
        if(status_transaksi == 2){
            param_global_total = null;
        }
        else{
            param_global_total = Integer.toString(global_total);
        }
        
        try {               
            JasperReport jasperReport = null;
            InputStream path=this.getClass().getResourceAsStream("report_nota_transaksi.jrxml");
            InputStream logo = this.getClass().getResourceAsStream("pistonseditan3small60.jpg");
            JasperPrint jasperPrint = null;
            jasperReport = JasperCompileManager.compileReport(path);
            HashMap param = new HashMap();
            param.put("LOGO", logo);
            param.put("kdbrg_1", param_kdbrg_1); param.put("kdbrg_2", param_kdbrg_2); param.put("kdbrg_3", param_kdbrg_3);
            param.put("kdbrg_4", param_kdbrg_4); param.put("kdbrg_5", param_kdbrg_5);
            param.put("nmbrg_1", param_nmbrg_1); param.put("nmbrg_2", param_nmbrg_2); param.put("nmbrg_3", param_nmbrg_3);
            param.put("nmbrg_4", param_nmbrg_4); param.put("nmbrg_5", param_nmbrg_5);
            param.put("jumlah_1", param_jumlah_1); param.put("jumlah_2", param_jumlah_2); param.put("jumlah_3", param_jumlah_3);
            param.put("jumlah_4", param_jumlah_4); param.put("jumlah_5", param_jumlah_5);
            param.put("harga_1", param_harga_1); param.put("harga_2", param_harga_2); param.put("harga_3", param_harga_3);
            param.put("harga_4", param_harga_4); param.put("harga_5", param_harga_5);
            param.put("total_1", param_total_1); param.put("total_2", param_total_2); param.put("total_3", param_total_3);
            param.put("total_4", param_total_4); param.put("total_5", param_total_5);
            param.put("global_total", param_global_total);
            param.put("nojasa", param_nojasa); param.put("nmjasa", param_nmjasa); param.put("hargajasa", param_hargajasa);
            jasperPrint = JasperFillManager.fillReport(jasperReport, param, conn);
            JasperViewer.viewReport(jasperPrint,false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jInternalFrame1 = new javax.swing.JInternalFrame();
        jLabel6 = new javax.swing.JLabel();
        tfcarikode = new javax.swing.JTextField();
        bfind = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        takode = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        bsave = new javax.swing.JButton();
        bsave1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        tftotal3 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tfbarang3 = new javax.swing.JTextField();
        cbbarang5 = new javax.swing.JComboBox<>();
        tfharga4 = new javax.swing.JTextField();
        tfbarang2 = new javax.swing.JTextField();
        tfharga1 = new javax.swing.JTextField();
        tfharga5 = new javax.swing.JTextField();
        tfharga2 = new javax.swing.JTextField();
        tftotal1 = new javax.swing.JTextField();
        tfjumlah2 = new javax.swing.JTextField();
        cbbarang1 = new javax.swing.JComboBox<>();
        tfharga3 = new javax.swing.JTextField();
        tfjumlah4 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cbbarang4 = new javax.swing.JComboBox<>();
        tftotal4 = new javax.swing.JTextField();
        cbbarang2 = new javax.swing.JComboBox<>();
        tfjumlah5 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tfjumlah3 = new javax.swing.JTextField();
        tftotal5 = new javax.swing.JTextField();
        tfjumlah1 = new javax.swing.JTextField();
        tfbarang1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbbarang3 = new javax.swing.JComboBox<>();
        tfbarang5 = new javax.swing.JTextField();
        tftotal2 = new javax.swing.JTextField();
        tfbarang4 = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        bt_jasa_barang = new javax.swing.JButton();
        bt_barang = new javax.swing.JButton();
        bt_jasa = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jInternalFrame2 = new javax.swing.JInternalFrame();
        jLabel10 = new javax.swing.JLabel();
        tfcarijasa = new javax.swing.JTextField();
        bfindjasa = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tanomerjasa = new javax.swing.JTextArea();
        jLabel12 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        cb_jasa = new javax.swing.JComboBox<>();
        tbjasa = new javax.swing.JTextField();
        tbhargajasa = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        menubar = new javax.swing.JMenuBar();
        gudang = new javax.swing.JMenu();
        gbaru = new javax.swing.JMenuItem();
        gbarang = new javax.swing.JMenuItem();
        jasa = new javax.swing.JMenu();
        transaksi = new javax.swing.JMenu();
        tbaru = new javax.swing.JMenuItem();
        tdaftar = new javax.swing.JMenuItem();
        laporan = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Form Nota Transaksi");
        setLocation(new java.awt.Point(250, 140));
        setMinimumSize(new java.awt.Dimension(1000, 500));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jInternalFrame1.setVisible(true);
        jInternalFrame1.getContentPane().setLayout(null);

        jLabel6.setText("Find Kode By Nama : ");
        jInternalFrame1.getContentPane().add(jLabel6);
        jLabel6.setBounds(12, 13, 123, 16);

        tfcarikode.setText("Isi Nama disini");
        tfcarikode.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tfcarikodeMouseClicked(evt);
            }
        });
        tfcarikode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfcarikodeActionPerformed(evt);
            }
        });
        jInternalFrame1.getContentPane().add(tfcarikode);
        tfcarikode.setBounds(12, 36, 166, 22);

        bfind.setText("FIND");
        bfind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bfindActionPerformed(evt);
            }
        });
        jInternalFrame1.getContentPane().add(bfind);
        bfind.setBounds(120, 60, 59, 25);

        jLabel7.setText("KODE : ");
        jInternalFrame1.getContentPane().add(jLabel7);
        jLabel7.setBounds(12, 110, 44, 16);

        takode.setColumns(20);
        takode.setRows(5);
        jScrollPane1.setViewportView(takode);

        jInternalFrame1.getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(12, 139, 166, 160);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/GreyBackground.png"))); // NOI18N
        jInternalFrame1.getContentPane().add(jLabel9);
        jLabel9.setBounds(0, 0, 190, 310);

        getContentPane().add(jInternalFrame1, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 70, 210, 350));

        bsave.setText("SAVE");
        bsave.setEnabled(false);
        bsave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bsaveActionPerformed(evt);
            }
        });
        getContentPane().add(bsave, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 630, 116, 50));

        bsave1.setText("CLEAR");
        bsave1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bsave1ActionPerformed(evt);
            }
        });
        getContentPane().add(bsave1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 630, 113, 50));

        tftotal3.setEditable(false);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("TOTAL");

        tfbarang3.setEditable(false);

        cbbarang5.setEnabled(false);
        cbbarang5.setPreferredSize(new java.awt.Dimension(40, 30));
        cbbarang5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbarang5ActionPerformed(evt);
            }
        });

        tfharga4.setEditable(false);

        tfbarang2.setEditable(false);

        tfharga1.setEditable(false);

        tfharga5.setEditable(false);

        tfharga2.setEditable(false);

        tftotal1.setEditable(false);

        tfjumlah2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfjumlah2FocusLost(evt);
            }
        });
        tfjumlah2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfjumlah2ActionPerformed(evt);
            }
        });

        cbbarang1.setEnabled(false);
        cbbarang1.setPreferredSize(new java.awt.Dimension(40, 30));
        cbbarang1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbarang1ActionPerformed(evt);
            }
        });

        tfharga3.setEditable(false);

        tfjumlah4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfjumlah4FocusLost(evt);
            }
        });
        tfjumlah4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfjumlah4ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("HARGA");

        cbbarang4.setEnabled(false);
        cbbarang4.setPreferredSize(new java.awt.Dimension(40, 30));
        cbbarang4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbarang4ActionPerformed(evt);
            }
        });

        tftotal4.setEditable(false);

        cbbarang2.setEnabled(false);
        cbbarang2.setPreferredSize(new java.awt.Dimension(40, 30));
        cbbarang2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbarang2ActionPerformed(evt);
            }
        });

        tfjumlah5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfjumlah5FocusLost(evt);
            }
        });
        tfjumlah5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfjumlah5ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("NAMA BARANG");

        tfjumlah3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfjumlah3FocusLost(evt);
            }
        });
        tfjumlah3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfjumlah3ActionPerformed(evt);
            }
        });

        tftotal5.setEditable(false);

        tfjumlah1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfjumlah1FocusLost(evt);
            }
        });
        tfjumlah1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfjumlah1ActionPerformed(evt);
            }
        });

        tfbarang1.setEditable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("KODE");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("JUMLAH");

        cbbarang3.setEnabled(false);
        cbbarang3.setPreferredSize(new java.awt.Dimension(40, 30));
        cbbarang3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbarang3ActionPerformed(evt);
            }
        });

        tfbarang5.setEditable(false);

        tftotal2.setEditable(false);

        tfbarang4.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(cbbarang5, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbbarang4, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbbarang3, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbbarang2, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbbarang1, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(tfbarang5, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfbarang4, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfbarang3, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfbarang2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfbarang1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(tfjumlah2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfharga2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(tfjumlah1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfharga1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(tfjumlah3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfharga3, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(tfjumlah4, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfharga4, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(tfjumlah5, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfharga5, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(tftotal4, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tftotal3, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tftotal2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tftotal1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addComponent(tftotal5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbarang1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfbarang1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfjumlah1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfharga1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tftotal1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbarang2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfbarang2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfjumlah2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfharga2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tftotal2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbarang3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfbarang3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfjumlah3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfharga3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tftotal3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbarang4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfbarang4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfjumlah4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfharga4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tftotal4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbarang5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfbarang5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfjumlah5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfharga5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tftotal5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, 340));
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 64, 980, -1));

        bt_jasa_barang.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        bt_jasa_barang.setText("Transaksi Jasa & Barang");
        bt_jasa_barang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_jasa_barangActionPerformed(evt);
            }
        });
        getContentPane().add(bt_jasa_barang, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 20, -1, -1));

        bt_barang.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        bt_barang.setText("Transaksi Barang");
        bt_barang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_barangActionPerformed(evt);
            }
        });
        getContentPane().add(bt_barang, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, -1, -1));

        bt_jasa.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        bt_jasa.setText("Transaksi Jasa");
        bt_jasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_jasaActionPerformed(evt);
            }
        });
        getContentPane().add(bt_jasa, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 20, -1, -1));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 430, 980, 10));

        jInternalFrame2.setVisible(true);
        jInternalFrame2.getContentPane().setLayout(null);

        jLabel10.setText("Find Nomer By Nama : ");
        jInternalFrame2.getContentPane().add(jLabel10);
        jLabel10.setBounds(12, 13, 150, 16);

        tfcarijasa.setText("Isi Nama disini");
        tfcarijasa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tfcarijasaMouseClicked(evt);
            }
        });
        tfcarijasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfcarijasaActionPerformed(evt);
            }
        });
        jInternalFrame2.getContentPane().add(tfcarijasa);
        tfcarijasa.setBounds(12, 36, 170, 22);

        bfindjasa.setText("FIND");
        bfindjasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bfindjasaActionPerformed(evt);
            }
        });
        jInternalFrame2.getContentPane().add(bfindjasa);
        bfindjasa.setBounds(120, 60, 59, 25);

        jLabel11.setText("KODE : ");
        jInternalFrame2.getContentPane().add(jLabel11);
        jLabel11.setBounds(220, 10, 44, 16);

        tanomerjasa.setColumns(20);
        tanomerjasa.setRows(5);
        jScrollPane2.setViewportView(tanomerjasa);

        jInternalFrame2.getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(216, 30, 180, 100);

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/GreyBackground.png"))); // NOI18N
        jInternalFrame2.getContentPane().add(jLabel12);
        jLabel12.setBounds(0, 0, 410, 150);

        getContentPane().add(jInternalFrame2, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 440, 430, 170));

        cb_jasa.setEnabled(false);
        cb_jasa.setPreferredSize(new java.awt.Dimension(40, 30));
        cb_jasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_jasaActionPerformed(evt);
            }
        });

        tbjasa.setEditable(false);

        tbhargajasa.setEditable(false);

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel14.setText("No. Jasa      :");
        jLabel14.setPreferredSize(new java.awt.Dimension(57, 30));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel13.setText("Nama Jasa   :");
        jLabel13.setPreferredSize(new java.awt.Dimension(48, 30));

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel15.setText("Harga          :  ");
        jLabel15.setPreferredSize(new java.awt.Dimension(60, 30));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tbjasa)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tbhargajasa, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cb_jasa, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 111, Short.MAX_VALUE)))
                .addGap(25, 25, 25))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cb_jasa, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tbjasa, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tbhargajasa, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, 520, 170));
        getContentPane().add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 622, 980, 10));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/GreyBackground.png"))); // NOI18N
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 690));

        gudang.setText("Gudang");

        gbaru.setText("Barang Baru");
        gbaru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gbaruActionPerformed(evt);
            }
        });
        gudang.add(gbaru);

        gbarang.setText("Daftar Barang");
        gbarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gbarangActionPerformed(evt);
            }
        });
        gudang.add(gbarang);

        menubar.add(gudang);

        jasa.setText("Jasa");
        jasa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jasaMouseClicked(evt);
            }
        });
        menubar.add(jasa);

        transaksi.setText("Transaksi");

        tbaru.setText("Transaksi Baru");
        tbaru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbaruActionPerformed(evt);
            }
        });
        transaksi.add(tbaru);

        tdaftar.setText("Daftar Transaksi");
        tdaftar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tdaftarActionPerformed(evt);
            }
        });
        transaksi.add(tdaftar);

        menubar.add(transaksi);

        laporan.setText("Laporan");
        laporan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                laporanMouseClicked(evt);
            }
        });
        menubar.add(laporan);

        setJMenuBar(menubar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbbarang1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbarang1ActionPerformed
        String item = (String)cbbarang1.getSelectedItem();
        kdbrg1 = item;
        isinama(item, 1);
        isiharga(item, 1);    
    }//GEN-LAST:event_cbbarang1ActionPerformed

    private void cbbarang2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbarang2ActionPerformed
        String item = (String)cbbarang2.getSelectedItem();
        kdbrg2 = item;
        isinama(item, 2);
        isiharga(item, 2);
    }//GEN-LAST:event_cbbarang2ActionPerformed

    private void cbbarang3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbarang3ActionPerformed
        String item = (String)cbbarang3.getSelectedItem();
        kdbrg3 = item;
        isinama(item, 3);
        isiharga(item, 3);
    }//GEN-LAST:event_cbbarang3ActionPerformed

    private void cbbarang4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbarang4ActionPerformed
        String item = (String)cbbarang4.getSelectedItem();
        kdbrg4 = item;
        isinama(item, 4);
        isiharga(item, 4);
    }//GEN-LAST:event_cbbarang4ActionPerformed

    private void cbbarang5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbarang5ActionPerformed
        String item = (String)cbbarang5.getSelectedItem();
        kdbrg5 = item;
        isinama(item, 5);
        isiharga(item, 5);
    }//GEN-LAST:event_cbbarang5ActionPerformed

    private void tfjumlah1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfjumlah1ActionPerformed
        setsatu();
    }//GEN-LAST:event_tfjumlah1ActionPerformed

    private void tfjumlah2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfjumlah2ActionPerformed
        setdua();     
    }//GEN-LAST:event_tfjumlah2ActionPerformed

    private void tfjumlah3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfjumlah3ActionPerformed
        settiga();
    }//GEN-LAST:event_tfjumlah3ActionPerformed

    private void tfjumlah4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfjumlah4ActionPerformed
        setempat();
    }//GEN-LAST:event_tfjumlah4ActionPerformed

    private void tfjumlah5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfjumlah5ActionPerformed
        setlima();
    }//GEN-LAST:event_tfjumlah5ActionPerformed

    private void bsave1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bsave1ActionPerformed
        kosong();
    }//GEN-LAST:event_bsave1ActionPerformed

    private void bsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bsaveActionPerformed
        System.out.println(status_transaksi);
        int kondisi_input1 = 1, kondisi_input2 = 1, kondisi_input3 = 1, kondisi_input4 = 1, kondisi_input5 = 1;
        int error = 0;
        try{
            String sql = "SELECT no_trans_total FROM transaksi_total";
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                notrans = hasil.getInt("no_trans_total");
            }
            notrans = notrans + 1;
        }catch (SQLException e){
                JOptionPane.showMessageDialog(null, e);
        }
        
        if((status_transaksi == 1) || (status_transaksi == 3)){
            try{
                String sql = "insert into cart values (?,?,?,?,?,?,?)";
                PreparedStatement stat1 = conn.prepareStatement(sql);
                PreparedStatement stat2 = conn.prepareStatement(sql);
                PreparedStatement stat3 = conn.prepareStatement(sql);
                PreparedStatement stat4 = conn.prepareStatement(sql);
                PreparedStatement stat5 = conn.prepareStatement(sql);
                PreparedStatement statubah1 = conn.prepareStatement(sql);
                PreparedStatement statubah2 = conn.prepareStatement(sql);
                PreparedStatement statubah3 = conn.prepareStatement(sql);
                PreparedStatement statubah4 = conn.prepareStatement(sql);
                PreparedStatement statubah5 = conn.prepareStatement(sql);
                if((tftotal1.getText().equals(""))!=true){
                    stat1.setInt(1, notrans);
                    //stat1.setInt(1, );
                    stat1.setInt(2, 1);
                    stat1.setString(3, kdbrg1);
                    stat1.setInt(4, jumlah1);
                    String tarikmodal1 = "select modal from barang where kode_barang='"+kdbrg1+"';";
                    java.sql.Statement statmodal1 = conn.createStatement();
                    ResultSet rsmodal1 = statmodal1.executeQuery(tarikmodal1);
                    while(rsmodal1.next()){
                        modal1 = rsmodal1.getInt("barang.modal");
                    }
                    stat1.setInt(5, (modal1*jumlah1));
                    stat1.setInt(6, total1);
                    stat1.setInt(7, 0);

                    String sqlambil1 = "select jumlah from barang where kode_barang='"+kdbrg1+"'";
                    java.sql.Statement statambil1 = conn.createStatement();
                    ResultSet ambil1 = statambil1.executeQuery(sqlambil1);
                    while(ambil1.next()){
                        jumlahtemp = ambil1.getInt("jumlah");
                    }
                    if(jumlahtemp > jumlah1){
                        String sqlubah1 = "update barang set jumlah='"+(jumlahtemp-jumlah1)+"' where kode_barang='"+kdbrg1+"' and jumlah > 0";
                        statubah1 = conn.prepareStatement(sqlubah1);
                        kondisi_input1 = 1;
                    }
                    else {
                         kondisi_input1 = 0;
                        JOptionPane.showMessageDialog(null, "Jumlah barang No.1 digudang tidak sebanyak jumlah barang dibeli");
                    }

                } 
                if((tftotal2.getText().equals(""))!=true){
                    stat2.setInt(1, notrans);
                    stat2.setInt(2, 2);
                    stat2.setString(3, kdbrg2);
                    stat2.setInt(4, jumlah2);
                    String tarikmodal2 = "select modal from barang where kode_barang='"+kdbrg2+"';";
                    java.sql.Statement statmodal2 = conn.createStatement();
                    ResultSet rsmodal2 = statmodal2.executeQuery(tarikmodal2);
                    while(rsmodal2.next()){
                        modal2 = rsmodal2.getInt("barang.modal");
                    }
                    stat2.setInt(5, (modal2*jumlah2));
                    stat2.setInt(6, total2);
                    stat2.setInt(7, 0);
                        

                    String sqlambil2 = "select jumlah from barang where kode_barang='"+kdbrg2+"'";
                    java.sql.Statement statambil2 = conn.createStatement();
                    ResultSet ambil2 = statambil2.executeQuery(sqlambil2);
                    while(ambil2.next()){
                        jumlahtemp = ambil2.getInt("jumlah");
                    }
                    if(jumlahtemp > jumlah2){
                        String sqlubah2 = "update barang set jumlah='"+(jumlahtemp-jumlah2)+"' where kode_barang='"+kdbrg2+"'";
                        statubah2 = conn.prepareStatement(sqlubah2);
                        kondisi_input2 = 1;
                    }
                    else {
                         kondisi_input2 = 0;
                        JOptionPane.showMessageDialog(null, "Jumlah barang digudang No.2 tidak sebanyak jumlah barang dibeli");
                    }
                }
                if((tftotal3.getText().equals(""))!=true){
                    stat3.setInt(1, notrans);
                    stat3.setInt(2, 3);
                    stat3.setString(3, kdbrg3);
                    stat3.setInt(4, jumlah3);
                    String tarikmodal3 = "select modal from barang where kode_barang='"+kdbrg3+"';";
                    java.sql.Statement statmodal3 = conn.createStatement();
                    ResultSet rsmodal3 = statmodal3.executeQuery(tarikmodal3);
                    while(rsmodal3.next()){
                        modal3 = rsmodal3.getInt("barang.modal");
                    }
                    stat3.setInt(5, (modal3*jumlah3));
                    stat3.setInt(6, total3);
                    stat3.setInt(7, 0);
                         

                    String sqlambil3 = "select jumlah from barang where kode_barang='"+kdbrg3+"'";
                    java.sql.Statement statambil3 = conn.createStatement();
                    ResultSet ambil3 = statambil3.executeQuery(sqlambil3);
                    while(ambil3.next()){
                        jumlahtemp = ambil3.getInt("jumlah");
                    }
                    if(jumlahtemp > jumlah3){
                        String sqlubah3 = "update barang set jumlah='"+(jumlahtemp-jumlah3)+"' where kode_barang='"+kdbrg3+"'";
                        statubah3 = conn.prepareStatement(sqlubah3);
                        kondisi_input3 = 1;
                    }
                    else {
                        kondisi_input3 = 0;
                        JOptionPane.showMessageDialog(null, "Jumlah barang No.3 digudang tidak sebanyak jumlah barang dibeli");
                    }
                }
                if((tftotal4.getText().equals(""))!=true){
                    stat4.setInt(1, notrans);
                    stat4.setInt(2, 4);
                    stat4.setString(3, kdbrg4);
                    stat4.setInt(4, jumlah4);
                    String tarikmodal4 = "select modal from barang where kode_barang='"+kdbrg4+"';";
                    java.sql.Statement statmodal4 = conn.createStatement();
                    ResultSet rsmodal4 = statmodal4.executeQuery(tarikmodal4);
                    while(rsmodal4.next()){
                        modal4 = rsmodal4.getInt("barang.modal");
                    }
                    stat4.setInt(5, (modal4*jumlah4));
                    stat4.setInt(6, total4);
                    stat4.setInt(7, 0);
                    

                    String sqlambil4 = "select jumlah from barang where kode_barang='"+kdbrg4+"'";
                    java.sql.Statement statambil4 = conn.createStatement();
                    ResultSet ambil4 = statambil4.executeQuery(sqlambil4);
                    while(ambil4.next()){
                        jumlahtemp = ambil4.getInt("jumlah");
                    }
                    if(jumlahtemp > jumlah4 ){
                        String sqlubah4 = "update barang set jumlah='"+(jumlahtemp-jumlah4)+"' where kode_barang='"+kdbrg4+"'";
                        statubah4 = conn.prepareStatement(sqlubah4);
                        kondisi_input4 = 1;
                    }
                    else {
                        kondisi_input4 = 0;
                        JOptionPane.showMessageDialog(null, "Jumlah barang No.4 digudang tidak sebanyak jumlah barang dibeli");
                    }
                }
                if((tftotal5.getText().equals(""))!=true){
                    stat5.setInt(1, notrans);
                    stat5.setInt(2, 5);
                    stat5.setString(3, kdbrg5);
                    stat5.setInt(4, jumlah5);
                    String tarikmodal5 = "select modal from barang where kode_barang='"+kdbrg5+"';";
                    java.sql.Statement statmodal5 = conn.createStatement();
                    ResultSet rsmodal5 = statmodal5.executeQuery(tarikmodal5);
                    while(rsmodal5.next()){
                        modal5 = rsmodal5.getInt("barang.modal");
                    }
                    stat5.setInt(5, (modal5*jumlah5));
                    stat5.setInt(6, total5);
                    stat5.setInt(7, 0);
                       

                    String sqlambil5 = "select jumlah from barang where kode_barang='"+kdbrg5+"'";
                    java.sql.Statement statambil5 = conn.createStatement();
                    ResultSet ambil5 = statambil5.executeQuery(sqlambil5);
                    while(ambil5.next()){
                        jumlahtemp = ambil5.getInt("jumlah");
                    }
                    if(jumlahtemp > jumlah5){
                        String sqlubah5 = "update barang set jumlah='"+(jumlahtemp-jumlah5)+"' where kode_barang='"+kdbrg5+"'";
                        statubah5 = conn.prepareStatement(sqlubah5);
                        kondisi_input5 = 1;
                    }
                    else {
                        kondisi_input5 = 0;
                        JOptionPane.showMessageDialog(null, "Jumlah barang No.5 digudang tidak sebanyak jumlah barang dibeli");
                    }
                }
                if((kondisi_input1==1) && (kondisi_input2==1) && (kondisi_input3==1) && (kondisi_input4==1) && (kondisi_input5==1)){
                    if((tftotal1.getText().equals(""))!=true){
                        statubah1.executeUpdate();
                        stat1.executeUpdate();
                    }
                    if((tftotal2.getText().equals(""))!=true){
                        statubah2.executeUpdate();
                        stat2.executeUpdate();
                    }
                    if((tftotal3.getText().equals(""))!=true){
                        statubah3.executeUpdate();
                        stat3.executeUpdate();
                    }
                    if((tftotal4.getText().equals(""))!=true){
                        statubah4.executeUpdate();
                        stat4.executeUpdate();
                    }
                    if((tftotal5.getText().equals(""))!=true){
                        statubah5.executeUpdate();
                        stat5.executeUpdate();
                    }
                    String sqltot = "insert into transaksi_total(no_trans_total, no_trans) values (?,?)";
                    PreparedStatement stattot = conn.prepareStatement(sqltot);
                    stattot.setInt(1, notrans);
                    stattot.setInt(2, notrans);
                    stattot.executeUpdate();
                    String sqlbarang = "insert into transaksi values (?,?,?)";
                    PreparedStatement statbarang = conn.prepareStatement(sqlbarang);
                    statbarang.setInt(1, notrans);
                    Timestamp tanggal = new Timestamp(System.currentTimeMillis());
                    statbarang.setTimestamp(2, tanggal);
                    //stattot.setDate(2, tanggal);
                    global_total = total1+total2+total3+total4+total5;
                    statbarang.setInt(3, global_total);
                    statbarang.executeUpdate();
                    JOptionPane.showMessageDialog(null,
                            "Data Berasil disimpan");
                    }
                else {
                    error = 1;
                    JOptionPane.showMessageDialog(null,
                        "Data Gagal disimpan");
                }
                
            } catch (SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
            
            try{
                if(status_transaksi == 3){
                    String sql_jasa_barang = "insert into transaksi_jasa values (?,?,?)";
                    PreparedStatement statjasa = conn.prepareStatement(sql_jasa_barang);
                    statjasa.setInt(1, notrans);
                    String sitem = (String)cb_jasa.getSelectedItem();
                    int item = Integer.parseInt(sitem);
                    statjasa.setInt(2, item);
                    Timestamp tanggal = new Timestamp(System.currentTimeMillis());
                    statjasa.setTimestamp(3, tanggal);
                    statjasa.executeUpdate();
                    
                    //update
                    String sqltot = "update transaksi_total set no_trans_jasa='"+notrans+"' where no_trans_total='"+notrans+"'";
                    PreparedStatement statupdate = conn.prepareStatement(sqltot);
                    statupdate.executeUpdate();
                }
            } catch(SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        }
        else if(status_transaksi == 2){
            try{
                String sqltot = "insert into transaksi_total(no_trans_total, no_trans_jasa) values (?,?)";
                PreparedStatement stattot = conn.prepareStatement(sqltot);
                stattot.setInt(1, notrans);
                stattot.setInt(2, notrans);
                stattot.executeUpdate();
                
                String sql_jasa_barang = "insert into transaksi_jasa values (?,?,?)";
                PreparedStatement statjasa = conn.prepareStatement(sql_jasa_barang);
                statjasa.setInt(1, notrans);
                String sitem = (String)cb_jasa.getSelectedItem();
                int item = Integer.parseInt(sitem);
                statjasa.setInt(2, item);
                Timestamp tanggal = new Timestamp(System.currentTimeMillis());
                statjasa.setTimestamp(3, tanggal);
                statjasa.executeUpdate();
                
                
                JOptionPane.showMessageDialog(null,
                    "Data Berasil disimpan");

            } catch(SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        }
        if(error != 1){
            cetak_nota();
        }
        kosong();
    }//GEN-LAST:event_bsaveActionPerformed

    private void gbarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gbarangActionPerformed
        try {
            new daftarbarang().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        }
        dispose();
    }//GEN-LAST:event_gbarangActionPerformed

    private void gbaruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gbaruActionPerformed
        new inputbarang().setVisible(true);
        dispose();
    }//GEN-LAST:event_gbaruActionPerformed

    private void tdaftarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tdaftarActionPerformed
        try {
            new daftartransaksi().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        }
        dispose();
    }//GEN-LAST:event_tdaftarActionPerformed

    private void tbaruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbaruActionPerformed
        new transaksibaru().setVisible(true);
        dispose();
    }//GEN-LAST:event_tbaruActionPerformed

    private void tfjumlah1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfjumlah1FocusLost
        setsatu();
    }//GEN-LAST:event_tfjumlah1FocusLost

    private void tfjumlah2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfjumlah2FocusLost
        setdua();
    }//GEN-LAST:event_tfjumlah2FocusLost

    private void tfjumlah3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfjumlah3FocusLost
        settiga();
    }//GEN-LAST:event_tfjumlah3FocusLost

    private void tfjumlah4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfjumlah4FocusLost
        setempat();
    }//GEN-LAST:event_tfjumlah4FocusLost

    private void tfjumlah5FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfjumlah5FocusLost
        setlima();
    }//GEN-LAST:event_tfjumlah5FocusLost

    private void bfindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bfindActionPerformed
        takode.setText("");
        String carinama = tfcarikode.getText();
        String hasilkode = "";
        String sql = "select kode_barang,nama_barang from barang where nama_barang LIKE '%"+carinama+"%'";
        try{
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String skdbrg = hasil.getString("kode_barang");
                String nmbrg = hasil.getString("nama_barang");
                hasilkode = hasilkode+skdbrg+" - "+nmbrg+"\n";
            }
            takode.setText(hasilkode);
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null,
                "Gk bisa nampilin data bos"+e);
        }
    }//GEN-LAST:event_bfindActionPerformed

    private void tfcarikodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfcarikodeActionPerformed
        bfindActionPerformed(evt);
    }//GEN-LAST:event_tfcarikodeActionPerformed

    private void tfcarikodeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfcarikodeMouseClicked
       tfcarikode.setText("");
    }//GEN-LAST:event_tfcarikodeMouseClicked

    private void laporanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_laporanMouseClicked
        new home().setVisible(true);
        dispose();
    }//GEN-LAST:event_laporanMouseClicked

    private void tfcarijasaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfcarijasaMouseClicked
        tfcarijasa.setText("");
    }//GEN-LAST:event_tfcarijasaMouseClicked

    private void tfcarijasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfcarijasaActionPerformed
        bfindjasaActionPerformed(evt);
    }//GEN-LAST:event_tfcarijasaActionPerformed

    private void bfindjasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bfindjasaActionPerformed
        tanomerjasa.setText("");
        String carinama = tfcarijasa.getText();
        String hasilkode = "";
        String sql = "select no_jasa,nama_jasa from jasa where nama_jasa LIKE '%"+carinama+"%'";
        try{
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                int no_jasa = hasil.getInt("no_jasa");
                String st_no_jasa = Integer.toString(no_jasa);
                String nm_jasa = hasil.getString("nama_jasa");
                hasilkode = hasilkode+st_no_jasa+" - "+nm_jasa+"\n";
            }
            tanomerjasa.setText(hasilkode);
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null,
                ""+e);
        }
    }//GEN-LAST:event_bfindjasaActionPerformed

    private void cb_jasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_jasaActionPerformed
        String item = (String)cb_jasa.getSelectedItem();
        try{
            String sql = "select nama_jasa from jasa where no_jasa='"+item+"'";
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String snm_jasa = hasil.getString("nama_jasa");  
                tbjasa.setText(snm_jasa);
            }            
            String sql_harga = "select harga_jasa from jasa where no_jasa='"+item+"'";
            ResultSet hasil_harga = stat.executeQuery(sql_harga);
            while(hasil_harga.next()){
                int harga_jasa = hasil_harga.getInt("harga_jasa");
                tbhargajasa.setText(Integer.toString(harga_jasa));
            }
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_cb_jasaActionPerformed

    private void bt_jasa_barangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_jasa_barangActionPerformed
       kosong();
       cbbarang1.setEnabled(true);
       cb_jasa.setEnabled(true);
       bsave.setEnabled(true);
       status_transaksi = 3;
    }//GEN-LAST:event_bt_jasa_barangActionPerformed

    private void bt_barangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_barangActionPerformed
        kosong();
        cbbarang1.setEnabled(true);
        cb_jasa.setEnabled(false);
        bsave.setEnabled(true);
        status_transaksi = 1;
    }//GEN-LAST:event_bt_barangActionPerformed

    private void bt_jasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_jasaActionPerformed
        kosong();
        cb_jasa.setEnabled(true);
        cbbarang1.setEnabled(false);
        bsave.setEnabled(true);
        status_transaksi = 2;
    }//GEN-LAST:event_bt_jasaActionPerformed

    private void jasaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jasaMouseClicked
        try {
            new daftarjasa().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        }
        dispose();
    }//GEN-LAST:event_jasaMouseClicked

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
            java.util.logging.Logger.getLogger(transaksibaru.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(transaksibaru.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(transaksibaru.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(transaksibaru.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new transaksibaru().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bfind;
    private javax.swing.JButton bfindjasa;
    private javax.swing.JButton bsave;
    private javax.swing.JButton bsave1;
    private javax.swing.JButton bt_barang;
    private javax.swing.JButton bt_jasa;
    private javax.swing.JButton bt_jasa_barang;
    private javax.swing.JComboBox<String> cb_jasa;
    private javax.swing.JComboBox<String> cbbarang1;
    private javax.swing.JComboBox<String> cbbarang2;
    private javax.swing.JComboBox<String> cbbarang3;
    private javax.swing.JComboBox<String> cbbarang4;
    private javax.swing.JComboBox<String> cbbarang5;
    private javax.swing.JMenuItem gbarang;
    private javax.swing.JMenuItem gbaru;
    private javax.swing.JMenu gudang;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JInternalFrame jInternalFrame2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JMenu jasa;
    private javax.swing.JMenu laporan;
    private javax.swing.JMenuBar menubar;
    private javax.swing.JTextArea takode;
    private javax.swing.JTextArea tanomerjasa;
    private javax.swing.JMenuItem tbaru;
    private javax.swing.JTextField tbhargajasa;
    private javax.swing.JTextField tbjasa;
    private javax.swing.JMenuItem tdaftar;
    private javax.swing.JTextField tfbarang1;
    private javax.swing.JTextField tfbarang2;
    private javax.swing.JTextField tfbarang3;
    private javax.swing.JTextField tfbarang4;
    private javax.swing.JTextField tfbarang5;
    private javax.swing.JTextField tfcarijasa;
    private javax.swing.JTextField tfcarikode;
    private javax.swing.JTextField tfharga1;
    private javax.swing.JTextField tfharga2;
    private javax.swing.JTextField tfharga3;
    private javax.swing.JTextField tfharga4;
    private javax.swing.JTextField tfharga5;
    private javax.swing.JTextField tfjumlah1;
    private javax.swing.JTextField tfjumlah2;
    private javax.swing.JTextField tfjumlah3;
    private javax.swing.JTextField tfjumlah4;
    private javax.swing.JTextField tfjumlah5;
    private javax.swing.JTextField tftotal1;
    private javax.swing.JTextField tftotal2;
    private javax.swing.JTextField tftotal3;
    private javax.swing.JTextField tftotal4;
    private javax.swing.JTextField tftotal5;
    private javax.swing.JMenu transaksi;
    // End of variables declaration//GEN-END:variables
}
