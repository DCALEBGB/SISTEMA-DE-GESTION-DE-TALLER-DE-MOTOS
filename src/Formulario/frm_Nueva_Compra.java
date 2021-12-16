
package Formulario;

import Datos.d_Almac_Articulo;
import Datos.d_Confi_Proveedor;
import Datos.d_Stati_Comprobante;
import Datos.d_Almac_Compra;
import Datos.d_Almac_CompraDetalle;
import Datos.d_Caja;
import Datos.d_CajaChica;
import Logica.l_Confi_Proveedor;
import Logica.l_Static;
import Logica.l_Almac_Compra;
import Logica.l_Caja;
import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PC
 */
public class frm_Nueva_Compra extends javax.swing.JDialog {

    DefaultTableModel model;
    int fila = 0;
    
    int id_usuario;
    
    
    String serie_caja, numero_caja;

    public frm_Nueva_Compra(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        this.setLocation(15, 40);
        txtid.setVisible(false);
        this.jrbcodigo_barra.setSelected(true);
        this.ajustes_jtable();
        select_comprobante(0);
        select_proveedor(0);
        correlativo();
        
        txtid_articulo.setVisible(false);
        
         
        frm_Menu f_menu = new frm_Menu();
        id_usuario = Integer.parseInt(f_menu.txtid_usuario.getText());
    }
    
    void limpiar(){
        
        this.txtserie.setText("");
        this.txtnumero.setText("");       
        this.jdcfecha.setDate(new Date());     
        this.txtbusqueda.setText("");
        this.txtenviar_correo.setText("");
        this.txtaobservacion.setText("");

        
        this.jrbcodigo_barra.setSelected(true);
        
        this.select_comprobante(0);
        this.select_proveedor(0);
        this.correlativo();
        this.LimpiarTable();
        
        this.txtnombre_articulo.setText("");
        this.txtstock.setText("");
        this.txtcantidad.setText("");
        this.txtprecio_venta.setText("");
        this.txtid_articulo.setText("");
        
        this.txtsubtotal.setText("");
        this.txttotal_igv.setText("");
        this.txtimporte.setText("");
        
    }
    
    void ajustes_jtable(){
        
        // TAMAÑO A COLUMNA

        table_detalle.getColumnModel().getColumn(1).setPreferredWidth(500);
        
        // COLOR A LA COLUMNA
        
        table_detalle.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 12));
        table_detalle.getTableHeader().setBackground(new Color(229,244,253));
        
        //ALINEAR A LA DERECHA
        
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
        
        table_detalle.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
        table_detalle.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
        
        //ALINEAR AL CENTRO 
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        
        table_detalle.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        
        //OCULTAR ID ARTICULO
        
        table_detalle.getColumnModel().getColumn(0).setMaxWidth(0);
        table_detalle.getColumnModel().getColumn(0).setMinWidth(0);
        table_detalle.getColumnModel().getColumn(0).setPreferredWidth(0);
        
    }
    
    void select_comprobante(int id){
        
        l_Static func = new l_Static();
        ArrayList<d_Stati_Comprobante> array= func.select_comprobante();
        
        cbocomprobante.removeAllItems();
        
        for(int x = 0; x < array.size(); x++){
            
            cbocomprobante.addItem(new d_Stati_Comprobante(array.get(x).getId(), array.get(x).getNombre()));     
            
        }
 
        if (id != 0) {
            
            cbocomprobante.setSelectedItem(new d_Stati_Comprobante(id));
            
        }   
 
        
    }
    
    void select_proveedor(int id){
        
        l_Confi_Proveedor func = new l_Confi_Proveedor();
        ArrayList<d_Confi_Proveedor> array= func.select_proveedor();
        
        cboproveedor.removeAllItems();
        
        for(int x = 0; x < array.size(); x++){
            
            cboproveedor.addItem(new d_Confi_Proveedor(array.get(x).getId(), array.get(x).getNumero_documento(), array.get(x).getNombre(), array.get(x).getApellido_paterno(), array.get(x).getApellido_materno()));     
            
        }
 
        if (id != 0) {
            
            cboproveedor.setSelectedItem(new d_Confi_Proveedor(id));
            
        }      
        
    }
    
    
    
    
    void get_articulo(String tipo_filtro, String valor){
        
        d_Almac_Articulo  r;
        
        l_Almac_Compra func = new l_Almac_Compra();
        
        r = func.get_articulo(tipo_filtro , valor);
         
        if (r != null) {
            
            this.txtnombre_articulo.setText(r.getNombre());
            this.txtstock.setText(String.valueOf(r.getCantidad()));
            this.txtprecio_venta.setText(String.valueOf(r.getPrecio()));
            this.txtid_articulo.setText(String.valueOf(r.getId()));

        }else{
            
            this.txtnombre_articulo.setText("");
            this.txtstock.setText("");
            this.txtcantidad.setText("");
            this.txtprecio_venta.setText("");
            this.txtid_articulo.setText("");
            
        }
        
    }
    
    
    void correlativo(){
        
        d_Almac_Compra  dts;
        
        l_Almac_Compra func = new l_Almac_Compra();
  
        dts = func.correlativo();

        if (dts != null) {
            
            this.txtserie.setText(dts.getSerie());
            this.txtnumero.setText(dts.getNumero());
        
        }

        
    }
    
    
    void correlativo_caja(){
        
        d_Caja  dts;
        
        l_Caja func = new l_Caja();
  
        dts = func.correlativo();

        if (dts != null) {
            
            serie_caja = dts.getSerie();
            numero_caja = dts.getNumero();
        
        }

        
    }
    
    
    void calcular_total(){
        
        double importe = 0, total_importe = 0;
        
        if (this.table_detalle.getRowCount() > 0) {
            
            for (int i = 0; i < this.table_detalle.getRowCount(); i++) {
                
                importe = Double.parseDouble(this.table_detalle.getValueAt(i, 4).toString());
                total_importe += importe;
                
                
            }
            
            total_importe = Math.round((total_importe) * 100.0) / 100.0;
            
            this.txtsubtotal.setText(String.valueOf(total_importe));
            this.txttotal_igv.setText("0.00");
            this.txtimporte.setText(String.valueOf(total_importe));
            
        }else{
            
            this.txtsubtotal.setText("");
            this.txttotal_igv.setText("");
            this.txtimporte.setText("");
            
        }
        
    }
    
    
    void insertar_venta(){
        
        
        
        d_Almac_Compra dts = new d_Almac_Compra();
        l_Almac_Compra func = new l_Almac_Compra();
        
   
        dts.setSerie(txtserie.getText().trim().toUpperCase());
        dts.setNumero(txtnumero.getText().trim());
        dts.setTotal_importe(Double.parseDouble(txtimporte.getText().trim()));
        dts.setObservacion(txtaobservacion.getText().trim());
        dts.setId_usuario(id_usuario);
        dts.setId_proveedor(cboproveedor.getItemAt(cboproveedor.getSelectedIndex()).getId());
        dts.setId_comprobante(cbocomprobante.getItemAt(cbocomprobante.getSelectedIndex()).getId());

        
        if (func.insertar_compra(dts)) {
            
            
            for (int i = 0; i < this.table_detalle.getRowCount(); i++) {
                
                d_Almac_CompraDetalle dtvd = new d_Almac_CompraDetalle();
                l_Almac_Compra func_d = new l_Almac_Compra(); 
                
                dtvd.setCantidad(Double.parseDouble(this.table_detalle.getValueAt(i, 2).toString()));
                dtvd.setPrecio_unitario(Double.parseDouble(this.table_detalle.getValueAt(i, 3).toString()));
                dtvd.setImporte(Double.parseDouble(this.table_detalle.getValueAt(i, 4).toString()));
                dtvd.setId_articulo(Integer.parseInt(this.table_detalle.getValueAt(i, 0).toString()));
                
                func_d.insertar_compra_detalle(dtvd);

                
            }
            
            SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd");
            
            frm_Menu f_menu = new frm_Menu();
            int id_caja_chica = Integer.parseInt(f_menu.txtidcaja_chica.getText());
            
            d_Caja dts_c = new d_Caja();
            l_Caja func_c = new l_Caja();
            
            correlativo_caja();
            
            dts_c.setFecha(dformat.format(jdcfecha.getDate()));
            dts_c.setSerie(serie_caja);
            dts_c.setNumero(numero_caja);
            dts_c.setMotivo("COMPRA");
            dts_c.setImporte(Double.parseDouble(txtimporte.getText().trim()));
            dts_c.setFl_estado(1);
            dts_c.setId_usuario(id_usuario);
            dts_c.setId_caja_chica(id_caja_chica);
            dts_c.setTipo_movimiento("EGRESO");
            
            func_c.save_caja_compra(dts_c);
            
            
            d_CajaChica dts_cc = new d_CajaChica();
            l_Caja func_cc = new l_Caja();
            
            dts_cc.setTotal_egreso(Double.parseDouble(txtimporte.getText().trim()));
            dts_cc.setTotal_saldo(Double.parseDouble(txtimporte.getText().trim()));
            dts_cc.setId(id_caja_chica);
            func_cc.update_cajachica_compra(dts_cc);
            
 
            JOptionPane.showMessageDialog(rootPane, "Registrado Correctamente");
            limpiar();
            
        }else{
            
            JOptionPane.showMessageDialog(rootPane, "No se registró");
            
        }
        
    }
    
    

    
    public void agregar(){
        
        if (this.txtcantidad.getText().trim().length() <= 0 || this.txtprecio_venta.getText().trim().length() <= 0 || this.txtid_articulo.getText().trim().length() <= 0) {
            
            JOptionPane.showMessageDialog(rootPane, "Seleccione un artículo y digite la cantidad");
            
            return;
            
        }

        
        model = (DefaultTableModel)this.table_detalle.getModel();
        model.addRow(new Object[fila]);
        
        double importe;
        double cantidad = Double.parseDouble(this.txtcantidad.getText().trim());
        double precio = Double.parseDouble(this.txtprecio_venta.getText().trim());
        
        importe = Math.round((cantidad * precio) * 100.0) / 100.0;
      
        
        for (int i = 0; i < this.table_detalle.getColumnCount() - 1; i++) {
            
            model.setValueAt(this.txtid_articulo.getText(), fila , 0);
            model.setValueAt(this.txtnombre_articulo.getText(), fila , 1);
            model.setValueAt(this.txtcantidad.getText(), fila , 2);
            model.setValueAt(this.txtprecio_venta.getText(), fila , 3);
            model.setValueAt(importe, fila , 4);
            
        }
        
        fila ++;
        
        this.calcular_total();
        
    }
    
    
    
    public void quitar(){
        
        model = (DefaultTableModel)this.table_detalle.getModel();
        model.removeRow(this.table_detalle.getSelectedRow());
        
        fila --;
        
        this.calcular_total();
        
    }
    
    void LimpiarTable() {
        for (int i = 0; i < model.getRowCount(); i++) {
            model.removeRow(i);
            i = i - 1;
        }
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bground_filtro_articulo = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lbltitulo_venta = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();
        lblclose = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txtserie = new javax.swing.JTextField();
        txtnumero = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jdcfecha = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        cbocomprobante = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        cboproveedor = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        txtbusqueda = new javax.swing.JTextField();
        jrbcodigo_barra = new javax.swing.JRadioButton();
        jrbnombre = new javax.swing.JRadioButton();
        txtstock = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtcantidad = new javax.swing.JTextField();
        txtprecio_venta = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        btnagregar = new javax.swing.JButton();
        txtnombre_articulo = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        btnquitar = new javax.swing.JButton();
        txtid_articulo = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_detalle = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtsubtotal = new javax.swing.JTextField();
        txttotal_igv = new javax.swing.JTextField();
        txtimporte = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtenviar_correo = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtaobservacion = new javax.swing.JTextArea();
        btnregistrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(229, 244, 253));
        jPanel2.setPreferredSize(new java.awt.Dimension(989, 40));

        lbltitulo_venta.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbltitulo_venta.setForeground(new java.awt.Color(51, 51, 51));
        lbltitulo_venta.setText("NUEVA COMPRA");

        lblclose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/cerrar-b1.png"))); // NOI18N
        lblclose.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblclose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblcloseMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbltitulo_venta)
                .addGap(532, 532, 532)
                .addComponent(lblclose)
                .addGap(43, 43, 43))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblclose, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbltitulo_venta)
                        .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Compra", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Consolas", 1, 14), new java.awt.Color(102, 102, 102))); // NOI18N

        txtserie.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N

        txtnumero.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Consolas", 1, 13)); // NOI18N
        jLabel2.setText("Número");

        jLabel3.setFont(new java.awt.Font("Consolas", 1, 13)); // NOI18N
        jLabel3.setText("Serie");

        jLabel4.setFont(new java.awt.Font("Consolas", 1, 13)); // NOI18N
        jLabel4.setText("Fecha");

        cbocomprobante.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        cbocomprobante.setPreferredSize(new java.awt.Dimension(28, 21));
        cbocomprobante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbocomprobanteActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Consolas", 1, 13)); // NOI18N
        jLabel19.setText("Comprobante");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbocomprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtserie, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtnumero, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jdcfecha, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtserie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtnumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbocomprobante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdcfecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Proveedor", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Consolas", 1, 14), new java.awt.Color(102, 102, 102))); // NOI18N

        cboproveedor.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        cboproveedor.setPreferredSize(new java.awt.Dimension(28, 21));
        cboproveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboproveedorActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Consolas", 1, 13)); // NOI18N
        jLabel1.setText("Nombre proveedor");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 383, Short.MAX_VALUE))
                    .addComponent(cboproveedor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboproveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Artículo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Consolas", 1, 14), new java.awt.Color(102, 102, 102))); // NOI18N

        txtbusqueda.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        txtbusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtbusquedaKeyTyped(evt);
            }
        });

        bground_filtro_articulo.add(jrbcodigo_barra);
        jrbcodigo_barra.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        jrbcodigo_barra.setText("Código barra");

        bground_filtro_articulo.add(jrbnombre);
        jrbnombre.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        jrbnombre.setText("Nombre");

        txtstock.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        txtstock.setEnabled(false);

        jLabel5.setFont(new java.awt.Font("Consolas", 1, 13)); // NOI18N
        jLabel5.setText("Stock");

        jLabel8.setFont(new java.awt.Font("Consolas", 1, 13)); // NOI18N
        jLabel8.setText("Cantidad");

        txtcantidad.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N

        txtprecio_venta.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Consolas", 1, 13)); // NOI18N
        jLabel9.setText("Precio compra");

        btnagregar.setBackground(new java.awt.Color(51, 181, 231));
        btnagregar.setForeground(new java.awt.Color(255, 255, 255));
        btnagregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/plus-1.png"))); // NOI18N
        btnagregar.setText("AGREGAR");
        btnagregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnagregarActionPerformed(evt);
            }
        });

        txtnombre_articulo.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        txtnombre_articulo.setEnabled(false);

        jLabel18.setFont(new java.awt.Font("Consolas", 1, 13)); // NOI18N
        jLabel18.setText("Nombre artículo");

        btnquitar.setBackground(new java.awt.Color(255, 53, 72));
        btnquitar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnquitar.setForeground(new java.awt.Color(255, 255, 255));
        btnquitar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/minus.png"))); // NOI18N
        btnquitar.setText("QUITAR");
        btnquitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnquitarActionPerformed(evt);
            }
        });

        txtid_articulo.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        txtid_articulo.setEnabled(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(txtnombre_articulo, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtid_articulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(txtstock, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(txtcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(txtprecio_venta, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(51, 51, 51)
                                .addComponent(btnagregar)))
                        .addGap(136, 136, 136)
                        .addComponent(btnquitar))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jrbcodigo_barra)
                        .addGap(18, 18, 18)
                        .addComponent(jrbnombre))
                    .addComponent(txtbusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 1262, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jrbcodigo_barra)
                            .addComponent(jrbnombre))
                        .addGap(1, 1, 1)
                        .addComponent(txtbusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtnombre_articulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnquitar))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtprecio_venta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnagregar)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtstock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtid_articulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalle", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Consolas", 1, 14), new java.awt.Color(102, 102, 102))); // NOI18N

        table_detalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id_articulo", "Artículo", "Cantidad", "Precio compra", "Importe"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_detalle.setGridColor(new java.awt.Color(229, 244, 253));
        table_detalle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                table_detalleMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(table_detalle);

        jLabel13.setFont(new java.awt.Font("Consolas", 1, 13)); // NOI18N
        jLabel13.setText("Subtotal");

        jLabel14.setFont(new java.awt.Font("Consolas", 1, 13)); // NOI18N
        jLabel14.setText("Total igv");

        jLabel15.setFont(new java.awt.Font("Consolas", 1, 13)); // NOI18N
        jLabel15.setText("Total a importe");

        txtsubtotal.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        txtsubtotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        txttotal_igv.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        txttotal_igv.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        txtimporte.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        txtimporte.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel16.setFont(new java.awt.Font("Consolas", 1, 13)); // NOI18N
        jLabel16.setText("Observación:");

        jLabel17.setFont(new java.awt.Font("Consolas", 1, 13)); // NOI18N
        jLabel17.setText("Enviar a correo:");

        txtenviar_correo.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N

        txtaobservacion.setColumns(20);
        txtaobservacion.setRows(5);
        jScrollPane2.setViewportView(txtaobservacion);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtenviar_correo)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txttotal_igv, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtimporte, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtsubtotal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtsubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel17)
                        .addComponent(txtenviar_correo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txttotal_igv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtimporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnregistrar.setBackground(new java.awt.Color(42, 132, 242));
        btnregistrar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnregistrar.setForeground(new java.awt.Color(255, 255, 255));
        btnregistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/verificar.png"))); // NOI18N
        btnregistrar.setText("Guardar compra !");
        btnregistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnregistrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1329, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnregistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(507, 507, 507))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(10, 10, 10)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(25, 25, 25))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnregistrar)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1319, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblcloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblcloseMouseClicked

        this.dispose();

    }//GEN-LAST:event_lblcloseMouseClicked

    private void cboproveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboproveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboproveedorActionPerformed

    private void btnagregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnagregarActionPerformed

        this.agregar();
       
    }//GEN-LAST:event_btnagregarActionPerformed

    private void table_detalleMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_detalleMousePressed

       
    }//GEN-LAST:event_table_detalleMousePressed

    private void btnregistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnregistrarActionPerformed
        
        this.insertar_venta();
        
    }//GEN-LAST:event_btnregistrarActionPerformed

    private void cbocomprobanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbocomprobanteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbocomprobanteActionPerformed

    private void btnquitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnquitarActionPerformed
        // TODO add your handling code here:
        this.quitar();
    }//GEN-LAST:event_btnquitarActionPerformed

    private void txtbusquedaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbusquedaKeyTyped
        
        if (this.jrbcodigo_barra.isSelected()) {
            
            this.get_articulo("codigo_barra", this.txtbusqueda.getText());
            
        }else{
            
            this.get_articulo("nombre", this.txtbusqueda.getText());

        }
        
        if (this.txtbusqueda.getText().length() <= 0) {
            
            this.txtnombre_articulo.setText("");
            this.txtstock.setText("");
            this.txtcantidad.setText("");
            this.txtprecio_venta.setText("");
            this.txtid_articulo.setText("");
            
        }
        
    }//GEN-LAST:event_txtbusquedaKeyTyped

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
            java.util.logging.Logger.getLogger(frm_Nueva_Compra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_Nueva_Compra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_Nueva_Compra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_Nueva_Compra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                frm_Nueva_Compra dialog = new frm_Nueva_Compra(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bground_filtro_articulo;
    private javax.swing.JButton btnagregar;
    private javax.swing.JButton btnquitar;
    private javax.swing.JButton btnregistrar;
    private javax.swing.JComboBox<Datos.d_Stati_Comprobante> cbocomprobante;
    private javax.swing.JComboBox<Datos.d_Confi_Proveedor> cboproveedor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.toedter.calendar.JDateChooser jdcfecha;
    private javax.swing.JRadioButton jrbcodigo_barra;
    private javax.swing.JRadioButton jrbnombre;
    private javax.swing.JLabel lblclose;
    private javax.swing.JLabel lbltitulo_venta;
    private javax.swing.JTable table_detalle;
    private javax.swing.JTextArea txtaobservacion;
    private javax.swing.JTextField txtbusqueda;
    private javax.swing.JTextField txtcantidad;
    private javax.swing.JTextField txtenviar_correo;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtid_articulo;
    private javax.swing.JTextField txtimporte;
    private javax.swing.JTextField txtnombre_articulo;
    private javax.swing.JTextField txtnumero;
    private javax.swing.JTextField txtprecio_venta;
    private javax.swing.JTextField txtserie;
    private javax.swing.JTextField txtstock;
    private javax.swing.JTextField txtsubtotal;
    private javax.swing.JTextField txttotal_igv;
    // End of variables declaration//GEN-END:variables
}
