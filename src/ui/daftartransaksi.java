/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import kontrol.koneksi;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.HashMap;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Bayu
 */
public class daftartransaksi extends javax.swing.JFrame {
    private final Connection conn = new koneksi().connect();
    private DefaultTableModel tabmodel, tabmodel_jasa, tabmodel_detail;
    Locale idnlocale = new Locale("id");
    SimpleDateFormat jdate = new SimpleDateFormat("yyyy-MM-dd", idnlocale);
    SimpleDateFormat tableformat = new SimpleDateFormat("kk:mm:ss, dd-MMMM-yyyy", idnlocale);
    String tanggal1, tanggal2;
    int status_transaksi = 0;
    int total_barang_global = 0;
    
    private void tableinit() throws SQLException{
        Date date_tanggal = java.sql.Date.valueOf(java.time.LocalDate.now());
        tanggal2 = String.valueOf(date_tanggal);
        LocalDate tgl2 = LocalDate.parse(tanggal2);
        LocalDate tgl1 = tgl2.minusYears(1);
        tgl2 = tgl2.plusDays(1);
        tanggal1 = String.valueOf(tgl1);
        System.out.println(tanggal1);
        tanggal2 = String.valueOf(tgl2);
        System.out.println(tanggal2);
        dataTable(tanggal1, tanggal2);
    }
    
    protected void dataTable(String tgl1, String tgl2) throws SQLException{
        int notrans = 0, noitem=0, jumlah=0, totcart=0, tottrans=0;
        int harga = 0;
        String tanggal = "null";
        String nmbrg = "null";
        String kdbrg = "null";
        Object[] baris = {"Tanggal", "ID Trans", "No. Item", "Kode Barang", "Nama Barang", "Harga", "Jumlah", "Total"};
        tabmodel = new DefaultTableModel(null, baris);
        ttrans.setModel(tabmodel);
        //String sqlcart = "select * from cart";
        String coba = "select cart.no_trans, cart.no_item, cart.kode_barang, cart.jumlah, cart.total, "
                + "transaksi.waktu, barang.nama_barang, barang.harga from cart,transaksi,barang "
                + "where cart.no_trans=transaksi.no_trans and barang.kode_barang=cart.kode_barang and transaksi.waktu >= '"+tgl1+"' and transaksi.waktu <= '"+tgl2+"' ORDER BY cart.no_trans ASC, cart.no_item ASC;";
        java.sql.Statement stat = conn.createStatement();
        try{
            ResultSet hasilcart = stat.executeQuery(coba);
            while(hasilcart.next()){
                notrans = hasilcart.getInt("cart.no_trans");
                noitem = hasilcart.getInt("cart.no_item");
                kdbrg = hasilcart.getString("cart.kode_barang");
                jumlah = hasilcart.getInt("cart.jumlah");
                totcart = hasilcart.getInt("cart.total");
                Timestamp waktu = hasilcart.getTimestamp("transaksi.waktu");
                //Date waktu = hasilcart.getDate("transaksi.waktu");
                String stringwaktu = String.valueOf(tableformat.format(waktu));
                
                //tanggal = waktu.toString();
                nmbrg = hasilcart.getString("barang.nama_barang");
                harga = hasilcart.getInt("barang.harga");
                tabmodel.addRow(new Object[]{stringwaktu, notrans, noitem, kdbrg, nmbrg, harga, jumlah, totcart});
            }
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null,
                    "Data barang tidak dapat ditampilkan"+e);
        }

    }
    private void dataTableJasa(String tgl1, String tgl2){
        Object[] baris_jasa = {"Tanggal", "ID Trans", "No Jasa", "Nama Jasa", "Harga"};
        tabmodel_jasa = new DefaultTableModel(null, baris_jasa);
        ttrans.setModel(tabmodel_jasa);
        String sql_jasa = "select transaksi_jasa.no_trans_jasa, transaksi_jasa.no_trans_jasa, transaksi_jasa.no_jasa, transaksi_jasa.tanggal, jasa.nama_jasa, jasa.harga_jasa "
                + "from jasa, transaksi_jasa "
                + "where transaksi_jasa.no_jasa=jasa.no_jasa and transaksi_jasa.tanggal >= '"+tgl1+"' and transaksi_jasa.tanggal <= '"+tgl2+"' ORDER BY transaksi_jasa.no_trans_jasa ASC;";
        try{
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasilcart_jasa = stat.executeQuery(sql_jasa);
            while(hasilcart_jasa.next()){
                int no_trans_jasa = hasilcart_jasa.getInt("transaksi_jasa.no_trans_jasa");
                int no_jasa = hasilcart_jasa.getInt("transaksi_jasa.no_jasa");
                //Date waktu_jasa = hasilcart_jasa.getDate("transaksi_jasa.tanggal");
                Timestamp waktu = hasilcart_jasa.getTimestamp("transaksi_jasa.tanggal");
                String stringwaktu = String.valueOf(tableformat.format(waktu));
                //String string_waktu = waktu_jasa.toString();
                String nama_jasa = hasilcart_jasa.getString("jasa.nama_jasa");
                String harga_jasa = hasilcart_jasa.getString("jasa.harga_jasa");
                tabmodel_jasa.addRow(new Object[]{stringwaktu, no_trans_jasa, no_jasa, nama_jasa, harga_jasa});
            }
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null,
                    "Data jasa tidak dapat ditampilkan"+e);
        }
    }
    private void dataTableTrans(int nomor) throws ParseException{
        //Object[] baris_detail = {"No Trans", "Tanggal", "Total Barang", "No Trans", "No Trans Jasa", "Total"};
        Object[] baris_detail = {"Tanggal", "No. Trans", "Kode Barang", "Nama Barang", "Jumlah", "Harga", "Total Barang", "ID Jasa", "Nama Jasa", "Total Jasa"};
        tabmodel_detail = new DefaultTableModel(null, baris_detail);
        ttrans.setModel(tabmodel_detail);
        int no_trans_total=0, no_trans=0, no_trans_jasa=0;
        int total_barang=0, total_jasa=0;
        
        String sqlcheck = "select * from transaksi_total where no_trans_total='"+nomor+"'";
        
        String sqlsemua = "select transaksi_total.no_trans_total, transaksi.waktu, transaksi.no_trans, cart.kode_barang, barang.nama_barang, cart.jumlah, barang.harga, cart.total, "
                +"transaksi_jasa.no_jasa, jasa.nama_jasa, jasa.harga_jasa "
                +"from transaksi, cart, barang, transaksi_total, transaksi_jasa, jasa "
                +"where transaksi_total.no_trans_total='"+nomor+"' and transaksi_total.no_trans_total=transaksi.no_trans and transaksi.no_trans=cart.no_trans and "
                +"cart.kode_barang=barang.kode_barang and transaksi_total.no_trans_total=transaksi_jasa.no_trans_jasa and transaksi_jasa.no_jasa=jasa.no_jasa";
        
        String sqlbarang = "select transaksi.waktu, transaksi.no_trans, cart.kode_barang, barang.nama_barang, cart.jumlah, barang.harga, cart.total "
               + "from transaksi, cart, barang, transaksi_total "
               + "where transaksi_total.no_trans_total='"+nomor+"' and transaksi_total.no_trans_total=transaksi.no_trans AND "	
               + "transaksi.no_trans=cart.no_trans and cart.kode_barang=barang.kode_barang";
        
        String sqljasa = "select transaksi_jasa.tanggal, transaksi_total.no_trans_total, transaksi_jasa.no_jasa, jasa.nama_jasa, jasa.harga_jasa "
                +"from transaksi_total, transaksi_jasa, jasa "
                +"where transaksi_total.no_trans_total='"+nomor+"' and transaksi_total.no_trans_total=transaksi_jasa.no_trans_jasa and transaksi_jasa.no_jasa=jasa.no_jasa";
        
        try{
            java.sql.Statement stat = conn.createStatement();
            
            ResultSet checknull = stat.executeQuery(sqlcheck);
            while(checknull.next()){
                no_trans_total = checknull.getInt("no_trans_total");
                no_trans = checknull.getInt("no_trans");
                no_trans_jasa = checknull.getInt("no_trans_jasa");
            }
            if((no_trans != 0) && (no_trans_jasa != 0)) {
                ResultSet hasil = stat.executeQuery(sqlsemua);
                while(hasil.next()){
                    Timestamp timestamp_waktu = hasil.getTimestamp("transaksi.waktu");
                    String string_ts_waktu = String.valueOf(timestamp_waktu);
                    int hasil_no_trans = hasil.getInt("transaksi.no_trans");
                    String kode_barang = hasil.getString("cart.kode_barang");
                    String nama_barang = hasil.getString("barang.nama_barang");
                    int jumlah = hasil.getInt("cart.jumlah");
                    int harga = hasil.getInt("barang.harga");
                    int total = hasil.getInt("cart.total");
                    total_barang = total_barang+total;
                    int id_jasa = hasil.getInt("transaksi_jasa.no_jasa");
                    String nama_jasa = hasil.getString("jasa.nama_jasa");
                    int harga_jasa = hasil.getInt("jasa.harga_jasa");
                    total_jasa = total_jasa + harga_jasa;
                    tabmodel_detail.addRow(new Object[]{string_ts_waktu, hasil_no_trans, kode_barang, nama_barang, jumlah, harga, total, id_jasa, nama_jasa, harga_jasa});
                }
                tabmodel_detail.addRow(new Object[]{"", "", "", "", "", "", total_barang, "", "", total_jasa});
            } else if ((no_trans != 0) && (no_trans_jasa == 0)){
                ResultSet hasil_barang = stat.executeQuery(sqlbarang);
                while(hasil_barang.next()){
                    Timestamp timestamp_waktu = hasil_barang.getTimestamp("transaksi.waktu");
                    String string_ts_waktu = String.valueOf(timestamp_waktu);
                    int hasil_no_trans = hasil_barang.getInt("transaksi.no_trans");
                    String kode_barang = hasil_barang.getString("cart.kode_barang");
                    String nama_barang = hasil_barang.getString("barang.nama_barang");
                    int jumlah = hasil_barang.getInt("cart.jumlah");
                    int harga = hasil_barang.getInt("barang.harga");
                    int total = hasil_barang.getInt("cart.total");
                    total_barang = total_barang+total;
                    String id_jasa = "-";
                    String nama_jasa = ("-");
                    String harga_jasa = ("-");
                    tabmodel_detail.addRow(new Object[]{string_ts_waktu, hasil_no_trans, kode_barang, nama_barang, jumlah, harga, total, id_jasa, nama_jasa, harga_jasa});
                }
                tabmodel_detail.addRow(new Object[]{"", "", "", "", "", "", total_barang, "", "", 0});
            } else if ((no_trans == 0) && (no_trans_jasa != 0)){
                ResultSet hasil_jasa = stat.executeQuery(sqljasa);
                while(hasil_jasa.next()){
                    Timestamp timestamp_waktu = hasil_jasa.getTimestamp("transaksi_jasa.tanggal");
                    String string_ts_waktu = String.valueOf(timestamp_waktu);
                    int hasil_no_trans = hasil_jasa.getInt("transaksi_total.no_trans_total");
                    String kode_barang = ("-");
                    String nama_barang = ("-");
                    String jumlah = ("-");
                    String harga = ("-");
                    String total = ("-");
                    int id_jasa = hasil_jasa.getInt("transaksi_jasa.no_jasa");
                    String nama_jasa = hasil_jasa.getString("jasa.nama_jasa");
                    int harga_jasa = hasil_jasa.getInt("jasa.harga_jasa");
                    total_jasa = total_jasa+harga_jasa;
                    tabmodel_detail.addRow(new Object[]{string_ts_waktu, hasil_no_trans, kode_barang, nama_barang, jumlah, harga, total, id_jasa, nama_jasa, harga_jasa});
                }
                tabmodel_detail.addRow(new Object[]{"", "", "", "", "", "", 0, "", "", total_jasa});
            }
            total_barang_global = 0;
            total_barang_global = total_barang;
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null,
                    "Data jasa tidak dapat ditampilkan"+e);
        }
    }
    
    private void cetaknotaperiode(String tgl1, String tgl2){
        try{
            if(status_transaksi == 1){
                JasperReport jasperReport = null;
                InputStream path=this.getClass().getResourceAsStream("report_periode_barang.jrxml");
                InputStream logo = this.getClass().getResourceAsStream("pistonseditan3small60.jpg");
                JasperPrint jasperPrint = null;
                jasperReport = JasperCompileManager.compileReport(path);
                HashMap param = new HashMap();
                param.put("tanggal_1", tgl1);
                param.put("tanggal_2", tgl2);
                param.put("LOGO", logo);
                jasperPrint = JasperFillManager.fillReport(jasperReport, param, conn);
                JasperViewer.viewReport(jasperPrint,false);
            }
            else if(status_transaksi == 2){
                JasperReport jasperReport = null;
                InputStream path=this.getClass().getResourceAsStream("report_periode_jasa.jrxml");
                InputStream logo = this.getClass().getResourceAsStream("pistonseditan3small60.jpg");
                JasperPrint jasperPrint = null;
                jasperReport = JasperCompileManager.compileReport(path);
                HashMap param = new HashMap();
                param.put("tanggal_1", tgl1);
                param.put("tanggal_2", tgl2);
                param.put("LOGO", logo);
                jasperPrint = JasperFillManager.fillReport(jasperReport, param, conn);
                JasperViewer.viewReport(jasperPrint,false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void cetaknotadetail(int nomor, int status){
        String kosong = "-";
        try{
            JasperReport jasperReport = null;
            InputStream path=this.getClass().getResourceAsStream("report_detail_trans.jrxml");
            InputStream logo = this.getClass().getResourceAsStream("pistonseditan3small60.jpg");
            if(status == 1){ //trans barang dan jasa
                jasperReport = null;
                path=this.getClass().getResourceAsStream("report_detail_trans.jrxml");
            } else if(status == 2){ //Trans barang no jasa
                jasperReport = null;
                path=this.getClass().getResourceAsStream("report_detail_trans_barang.jrxml");
            } else if (status == 3){ //Trans jasa no barang
                jasperReport = null;
                path=this.getClass().getResourceAsStream("report_detail_trans_jasa.jrxml");
            }
            JasperPrint jasperPrint = null;
            jasperReport = JasperCompileManager.compileReport(path);
            HashMap param = new HashMap();
            param.put("nomor", nomor);
            param.put("kosong", kosong);
            param.put("LOGO", logo);
            param.put("total", total_barang_global);
            jasperPrint = JasperFillManager.fillReport(jasperReport, param, conn);
            JasperViewer.viewReport(jasperPrint,false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Creates new form daftartransaksi
     * @throws java.sql.SQLException
     */
    public daftartransaksi() throws SQLException {
        initComponents();
        tableinit();
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        ttrans = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jb_detail = new javax.swing.JButton();
        tf_trans = new javax.swing.JTextField();
        jb_caritrans = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jb_barang = new javax.swing.JButton();
        jb_jasa = new javax.swing.JButton();
        jds_satu = new com.toedter.calendar.JDateChooser();
        jds_dua = new com.toedter.calendar.JDateChooser();
        jb_caribarang = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jb_cetak = new javax.swing.JButton();
        background = new javax.swing.JLabel();
        menubar = new javax.swing.JMenuBar();
        gudang = new javax.swing.JMenu();
        gbaru = new javax.swing.JMenuItem();
        gbarang = new javax.swing.JMenuItem();
        jasa = new javax.swing.JMenu();
        transaksi = new javax.swing.JMenu();
        tbaru = new javax.swing.JMenuItem();
        tdaftar = new javax.swing.JMenuItem();
        laporan = new javax.swing.JMenu();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Daftar Transaksi");
        setLocation(new java.awt.Point(250, 140));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ttrans.setBackground(new java.awt.Color(204, 204, 204));
        ttrans.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "No Transaksi", "No Item", "Tanggal", "Kode Barang", "Nama Barang", "Harga", "Jumlah", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        ttrans.setGridColor(new java.awt.Color(0, 0, 0));
        jScrollPane1.setViewportView(ttrans);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 250, 850, 230));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("No. Trans : ");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, 30));

        jb_detail.setText("Detail Transaksi");
        jb_detail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_detailActionPerformed(evt);
            }
        });
        jPanel1.add(jb_detail, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, -1, -1));
        jPanel1.add(tf_trans, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, 100, 30));

        jb_caritrans.setText("CARI");
        jb_caritrans.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_caritransActionPerformed(evt);
            }
        });
        jPanel1.add(jb_caritrans, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, -1, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 130, 260, 100));

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jb_barang.setText("Transaksi Barang");
        jb_barang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_barangActionPerformed(evt);
            }
        });
        jPanel2.add(jb_barang, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, -1, -1));

        jb_jasa.setText("Transaksi Jasa");
        jb_jasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_jasaActionPerformed(evt);
            }
        });
        jPanel2.add(jb_jasa, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, -1, -1));
        jPanel2.add(jds_satu, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 150, -1));
        jPanel2.add(jds_dua, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 120, -1));

        jb_caribarang.setText("CARI");
        jb_caribarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_caribarangActionPerformed(evt);
            }
        });
        jPanel2.add(jb_caribarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 60, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 130, 420, 100));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel3.setText("DAFTAR TRANSAKSI");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 30, -1, -1));

        jb_cetak.setText("CETAK");
        jb_cetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_cetakActionPerformed(evt);
            }
        });
        getContentPane().add(jb_cetak, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 500, -1, -1));

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/GreyBackground.png"))); // NOI18N
        getContentPane().add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 2, 960, 540));

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

    private void laporanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_laporanMouseClicked
       new home().setVisible(true);
        dispose();
    }//GEN-LAST:event_laporanMouseClicked

    private void jasaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jasaMouseClicked
        try {
            new daftarjasa().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        }
        dispose();
    }//GEN-LAST:event_jasaMouseClicked

    private void jb_cetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_cetakActionPerformed
        if(status_transaksi == 1 || status_transaksi == 2){
            String sqldate1= String.valueOf(jdate.format(jds_satu.getDate()));
            String sqldate2 = String.valueOf(jdate.format(jds_dua.getDate()));
            
            cetaknotaperiode(sqldate1, sqldate2);
        }else if(status_transaksi ==3){
            String temp = tf_trans.getText();
            int nomor = Integer.valueOf(temp);
            int no_trans_total=0, no_trans=0, no_trans_jasa=0;
            String sqlcheck = "select * from transaksi_total where no_trans_total='"+nomor+"'";
            try{
                java.sql.Statement stat = conn.createStatement();
                ResultSet checknull = stat.executeQuery(sqlcheck);
                while(checknull.next()){
                    no_trans_total = checknull.getInt("no_trans_total");
                    no_trans = checknull.getInt("no_trans");
                    no_trans_jasa = checknull.getInt("no_trans_jasa");
                }
                if((no_trans != 0) && (no_trans_jasa != 0)) {
                cetaknotadetail(nomor, 1);
                }else if((no_trans != 0) && (no_trans_jasa == 0)){
                cetaknotadetail(nomor, 2);
                }else if ((no_trans == 0) && (no_trans_jasa != 0)){
                cetaknotadetail(nomor, 3);
                }
            }catch(SQLException e){
            JOptionPane.showMessageDialog(null,
                    "Data jasa tidak dapat ditampilkan"+e);
            }
        }
    }//GEN-LAST:event_jb_cetakActionPerformed

    private void jb_caribarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_caribarangActionPerformed
        String sqldate1= String.valueOf(jdate.format(jds_satu.getDate()));
        String sqldate2 = String.valueOf(jdate.format(jds_dua.getDate()));
        
        if(status_transaksi == 1){
            try {
                if((sqldate1 != null && !sqldate1.isEmpty()) && (sqldate2 != null && !sqldate2.isEmpty())){
                    java.util.Date Date1 = jdate.parse(sqldate1);
                    java.util.Date Date2 = jdate.parse(sqldate2);
                    if(Date1.before(Date2)){
                        System.out.println("Date 1 before Date 2");
                        dataTable(sqldate1, sqldate2);
                    }
                } else {
                    System.out.println("Data kosong");
                }
            } catch (ParseException | SQLException ex) {
                JOptionPane.showMessageDialog(null,
                    "Data barang tidak dapat ditampilkan"+ex);
            }
        }
        else if(status_transaksi == 2){
            try {
                if((sqldate1 != null && !sqldate1.isEmpty()) && (sqldate2 != null && !sqldate2.isEmpty())){
                    java.util.Date Date1 = jdate.parse(sqldate1);
                    java.util.Date Date2 = jdate.parse(sqldate2);
                    if(Date1.before(Date2)){
                        System.out.println("Date 1 before Date 2");
                        dataTableJasa(sqldate1, sqldate2);
                    }
                } else {
                    System.out.println("Data kosong");
                }
  
        }   catch (ParseException ex) {
                Logger.getLogger(daftartransaksi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jb_caribarangActionPerformed

    private void jb_barangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_barangActionPerformed
        status_transaksi = 1;
        try {
            dataTable(tanggal1, tanggal2);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "Data barang tidak dapat ditampilkan"+ex);
        }
    }//GEN-LAST:event_jb_barangActionPerformed

    private void jb_jasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_jasaActionPerformed
        status_transaksi = 2;
        dataTableJasa(tanggal1, tanggal2);
    }//GEN-LAST:event_jb_jasaActionPerformed

    private void jb_detailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_detailActionPerformed
        status_transaksi = 3;
        try {
            tf_trans.setText("1");
            dataTableTrans(1);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null,
                    "Data barang tidak dapat ditampilkan"+ex);
        }
    }//GEN-LAST:event_jb_detailActionPerformed

    private void jb_caritransActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_caritransActionPerformed
        String string_notrans = tf_trans.getText();
        if(!(string_notrans.equals(""))){
            int notrans = Integer.valueOf(string_notrans);
            try {
                dataTableTrans(notrans);
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(null,
                    "Data barang tidak dapat ditampilkan"+ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Tolong isi kotak No. Trans");
        }
    }//GEN-LAST:event_jb_caritransActionPerformed

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
            java.util.logging.Logger.getLogger(daftartransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(daftartransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(daftartransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(daftartransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new daftartransaksi().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(daftartransaksi.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JMenuItem gbarang;
    private javax.swing.JMenuItem gbaru;
    private javax.swing.JMenu gudang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JMenu jasa;
    private javax.swing.JButton jb_barang;
    private javax.swing.JButton jb_caribarang;
    private javax.swing.JButton jb_caritrans;
    private javax.swing.JButton jb_cetak;
    private javax.swing.JButton jb_detail;
    private javax.swing.JButton jb_jasa;
    private com.toedter.calendar.JDateChooser jds_dua;
    private com.toedter.calendar.JDateChooser jds_satu;
    private javax.swing.JMenu laporan;
    private javax.swing.JMenuBar menubar;
    private javax.swing.JMenuItem tbaru;
    private javax.swing.JMenuItem tdaftar;
    private javax.swing.JTextField tf_trans;
    private javax.swing.JMenu transaksi;
    private javax.swing.JTable ttrans;
    // End of variables declaration//GEN-END:variables
}
