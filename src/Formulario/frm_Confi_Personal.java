
package Formulario;

import Helper.Helper;
import Datos.d_Confi_Personal;
import Logica.l_Confi_Personal;
import Datos.d_Documento;
import Logica.l_Documento;
import Datos.d_Ubigeo;
import Logica.l_Ubigeo;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author CINTHYA PAOLA GUILLERMO CASTRO
 */
public class frm_Confi_Personal extends javax.swing.JFrame {

    /**
     * Creates new form frm_Cliente
     */
    private String accion = "";
    
    public frm_Confi_Personal() {
        initComponents();
        
        this.setLocation(15, 40);
        
        mostrar("");
        select_documento(0);
        select_ubigeo(0);
        ajustes_jtable();
        
        
        txtid.setVisible(false);
        
        accion = "nuevo";
        btnregistrar.setEnabled(true);
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);
        
        
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Procedimientos">
    
    void select_documento(int id){
        
        l_Documento documento = new l_Documento();
        ArrayList<d_Documento> array_documento = documento.getDocumento();
        
        cbotipo_documento.removeAllItems();
        
        for(int x = 0; x < array_documento.size(); x++){
            
            cbotipo_documento.addItem(new d_Documento(array_documento.get(x).getId(), array_documento.get(x).getNombre(), array_documento.get(x).getCodigo_sunat(), array_documento.get(x).getTipo()));     
            
        }
 
        if (id != 0) {
            
            cbotipo_documento.setSelectedItem(new d_Documento(id));
            
        }      
        
    }
    
    void select_ubigeo(int id){
        
        l_Ubigeo ubigeo = new l_Ubigeo();
        ArrayList<d_Ubigeo> array_ubigeo = ubigeo.getUbigeo();
        
        cboubigeo.removeAllItems();
        
        for(int x = 0; x < array_ubigeo.size(); x++){
            
            cboubigeo.addItem(new d_Ubigeo(array_ubigeo.get(x).getId(), array_ubigeo.get(x).getDepartamento(), array_ubigeo.get(x).getProvincia(), array_ubigeo.get(x).getDistrito()));     
            
        }
 
        if (id != 0) {
            
            cboubigeo.setSelectedItem(new d_Ubigeo(id));
            
            
        }
        
    }
    
    void bloquear_numero_documento(){
        
            
            String documento = String.valueOf(cbotipo_documento.getSelectedItem());

            if(documento.equals("DNI")){

                this.txtrazon_social.setEnabled(false);

            }else{

                this.txtrazon_social.setEnabled(true);
            }
        
    }
    
    void limpiar(){
        
        txtnumero_documento.setText("");
        txtapellido_paterno.setText("");
        txtapellido_materno.setText("");
        txtnombre.setText("");
        txtrazon_social.setText("");
        txtdireccion.setText("");
        txttelefono.setText("");
        txtcorreo.setText("");
        txtid.setText("");
        select_documento(0);
        select_ubigeo(0);
        accion = "nuevo";
        btnregistrar.setEnabled(true);
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);
        
    }
    
    
    String validar(){
        
        String r = "";
        
        if(txtnumero_documento.getText().trim().isEmpty()){ r += "\n-N??mero documento";}
        if(txtapellido_paterno.getText().trim().isEmpty()){ r += "\n-Apellido paterno";}
        if(txtapellido_materno.getText().trim().isEmpty()){ r += "\n-Apellido materno";}
        if(txtnombre.getText().trim().isEmpty()){ r += "\n-Nombre";}
        if(txtdireccion.getText().trim().isEmpty()){ r += "\n-Direcci??n";}
        if(txttelefono.getText().trim().isEmpty()){ r += "\n-Tel??fono";}
        if(txtcorreo.getText().trim().isEmpty()){ r += "\n-Correo";}
        
        return r;
    }
    
    String validar_tamano(){
        
        String r = "";
        String documento = cbotipo_documento.getItemAt(cbotipo_documento.getSelectedIndex()).getNombre();
        
        if(documento.equals("DNI") && txtnumero_documento.getText().trim().length() < 8){
            
            r += "\n-Debe ser de 8 d??gitos";
        
        }
        
        if(documento.equals("RUC") && txtnumero_documento.getText().trim().length() < 11){
            
            r += "\n-Debe ser de 11 d??gitos";
        
        }
 
        return r;
    }
    
    void ajustes_jtable(){
        
        // OCULTAR COLUMNAS
        table_registro.getColumnModel().getColumn(0).setMaxWidth(0);
        table_registro.getColumnModel().getColumn(0).setMinWidth(0);
        table_registro.getColumnModel().getColumn(0).setPreferredWidth(0);

        table_registro.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 12));
        table_registro.getTableHeader().setBackground(new Color(229,244,253));
        
    }
    
    void mostrar(String buscar){
        
        try {
            
            DefaultTableModel modelo;
            l_Confi_Personal func = new l_Confi_Personal();
            modelo = func.mostrar(buscar);
            
            table_registro.setModel(modelo);
            
            lbltotal_registro.setText(Integer.toString(func.total_registro));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    
    void nuevo(){
        
        /* VALIDAR CAMPOS VAC??OS*/
        
        String r = validar();
        
        if (!r.isEmpty()) {
            
            JOptionPane.showMessageDialog(null, "Faltan completar los siguientes campos: " + r, "error", JOptionPane.WARNING_MESSAGE);  
            return;
        }
        
        /**/
        
        /* VALIDAR TAMA??O*/
        
        String rt = validar_tamano();
        
        if (!rt.isEmpty()) {
            
            JOptionPane.showMessageDialog(null, "El tama??o de los campos: " + rt, "error", JOptionPane.WARNING_MESSAGE);  
            return;
        }
        
        /**/
        
        /* VALIDAR N??MERO DE DOCUMENTO EXISTENTE*/
        
        String rv = validar_numero_documento_existente(txtnumero_documento.getText(), 0);
        
        if (!rv.isEmpty()) {
            
            JOptionPane.showMessageDialog(null, rv, "error", JOptionPane.WARNING_MESSAGE);  
            return;
        }
        
        /**/
        
        d_Confi_Personal dts = new d_Confi_Personal();
        l_Confi_Personal func = new l_Confi_Personal();

        dts.setId_tipo_documento(cbotipo_documento.getItemAt(cbotipo_documento.getSelectedIndex()).getId());
        dts.setNumero_documento(txtnumero_documento.getText().trim());
        dts.setId_ubigeo(cboubigeo.getItemAt(cboubigeo.getSelectedIndex()).getId());
        dts.setApellido_paterno(txtapellido_paterno.getText().trim().toUpperCase());
        dts.setApellido_materno(txtapellido_materno.getText().trim().toUpperCase());
        dts.setNombre(txtnombre.getText().trim().toUpperCase());
        dts.setRazon_social(txtrazon_social.getText().trim().toUpperCase());
        dts.setDireccion(txtdireccion.getText().trim().toUpperCase());
        dts.setTelefono(txttelefono.getText().trim());
        dts.setCorreo(txtcorreo.getText().trim().toUpperCase());
        
        if (func.insertar(dts)) {
            
            JOptionPane.showMessageDialog(rootPane, "Registrado Correctamente");
            limpiar();
            mostrar("");
            
        }else{
            
            JOptionPane.showMessageDialog(rootPane, "No se registr??");
            
        }
        
    }
    
    void editar(){
        
        /* VALIDAR CAMPOS VAC??OS*/
        
        String r = validar();
        
        if (!r.isEmpty()) {
            
            JOptionPane.showMessageDialog(null, "Faltan completar los siguientes campos: " + r, "error", JOptionPane.WARNING_MESSAGE);  
            return;
        }
        
        /**/
        
        /* VALIDAR TAMA??O*/
        
        String rt = validar_tamano();
        
        if (!rt.isEmpty()) {
            
            JOptionPane.showMessageDialog(null, "El tama??o de los campos: " + rt, "error", JOptionPane.WARNING_MESSAGE);  
            return;
        }
        
        /**/
        
        /* VALIDAR N??MERO DE DOCUMENTO EXISTENTE*/
        
        String rv = validar_numero_documento_existente(txtnumero_documento.getText(),Integer.parseInt(txtid.getText().trim()));
        
        if (!rv.isEmpty()) {
            
            JOptionPane.showMessageDialog(null, rv, "error", JOptionPane.WARNING_MESSAGE);  
            return;
        }
        
        /**/
        
        d_Confi_Personal dts = new d_Confi_Personal();
        l_Confi_Personal func = new l_Confi_Personal();

        dts.setId_tipo_documento(cbotipo_documento.getItemAt(cbotipo_documento.getSelectedIndex()).getId());
        dts.setNumero_documento(txtnumero_documento.getText().trim());
        dts.setId_ubigeo(cboubigeo.getItemAt(cboubigeo.getSelectedIndex()).getId());
        dts.setApellido_paterno(txtapellido_paterno.getText().trim().toUpperCase());
        dts.setApellido_materno(txtapellido_materno.getText().trim().toUpperCase());
        dts.setNombre(txtnombre.getText().trim().toUpperCase());
        dts.setRazon_social(txtrazon_social.getText().trim().toUpperCase());
        dts.setDireccion(txtdireccion.getText().trim().toUpperCase());
        dts.setTelefono(txttelefono.getText().trim());
        dts.setCorreo(txtcorreo.getText().trim().toUpperCase());
        dts.setId(Integer.parseInt(txtid.getText().trim()));
        
        if (func.editar(dts)) {
            
            JOptionPane.showMessageDialog(rootPane, "Editado Correctamente");
            limpiar();
            mostrar("");
            
        }else{
            
            JOptionPane.showMessageDialog(rootPane, "No se edito");
            
        }
        
    }
    
    void eliminar(){
        
        if (!txtid.getText().equals("")) {
            
            int confirmacion = JOptionPane.showConfirmDialog(this, "Seguro de eliminar?","Confirmar", 2);
            
            if (confirmacion == 0) {
                
                d_Confi_Personal dts = new d_Confi_Personal();
                l_Confi_Personal func = new l_Confi_Personal();
                
                dts.setId(Integer.parseInt(txtid.getText()));
                
                if (func.eliminar(dts)) {
                    
                    limpiar();
                    mostrar("");
                    
                }else{
                    
                    JOptionPane.showMessageDialog(rootPane, "No se elimin??");
                    
                }
                
                
                
                
                
            }
        }
        
    }
    
    String validar_numero_documento_existente(String numero_documento, Integer id_Personal){
        
        String r = "";
        
        d_Confi_Personal dc;
        
        l_Confi_Personal func = new l_Confi_Personal();
        
        if (accion.equals("nuevo")) {
            
            dc = func.validar_numero_documento_exitente(numero_documento, accion, 0);
            
        }else{
            
            dc = func.validar_numero_documento_exitente(numero_documento, accion, id_Personal);
            
        }
     
        
        if (dc != null) {
            
            r = "Este n??mero de documento ya fue registrado";   
        
        }
        
        return r;
        
        
    }
    // </editor-fold>      
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        genero = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();
        lblclose = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btnregistrar = new javax.swing.JButton();
        btneditar = new javax.swing.JButton();
        btneliminar = new javax.swing.JButton();
        btnlimpiar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_registro = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        lbltotal_registro = new javax.swing.JLabel();
        btnexportar_excel = new javax.swing.JButton();
        txtbuscar = new javax.swing.JTextField();
        btnbuscar = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cbotipo_documento = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txtnumero_documento = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cboubigeo = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtapellido_paterno = new javax.swing.JTextField();
        txtapellido_materno = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtnombre = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtrazon_social = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtdireccion = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txttelefono = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtcorreo = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        cbtipopersonal1 = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        txtsueldo = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        bhombre = new javax.swing.JRadioButton();
        bmujer = new javax.swing.JRadioButton();
        jLabel20 = new javax.swing.JLabel();
        lblimagen1 = new javax.swing.JLabel();
        btnseleccionar_imagen = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        lblimagen = new javax.swing.JLabel();
        btnseleccionar_firma = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jCalendar1 = new com.toedter.calendar.JCalendar();
        jCalendar2 = new com.toedter.calendar.JCalendar();
        jCalendar3 = new com.toedter.calendar.JCalendar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(229, 244, 253));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("PERSONAL");

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
                .addGap(588, 588, 588)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblclose)
                .addGap(21, 21, 21))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblclose, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Acciones", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(51, 51, 51))); // NOI18N

        btnregistrar.setBackground(new java.awt.Color(42, 132, 242));
        btnregistrar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnregistrar.setForeground(new java.awt.Color(255, 255, 255));
        btnregistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/verificar.png"))); // NOI18N
        btnregistrar.setText("Registrar");
        btnregistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnregistrarActionPerformed(evt);
            }
        });

        btneditar.setBackground(new java.awt.Color(246, 145, 16));
        btneditar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btneditar.setForeground(new java.awt.Color(255, 255, 255));
        btneditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/editar-texto.png"))); // NOI18N
        btneditar.setText("Editar");
        btneditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditarActionPerformed(evt);
            }
        });

        btneliminar.setBackground(new java.awt.Color(255, 53, 72));
        btneliminar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btneliminar.setForeground(new java.awt.Color(255, 255, 255));
        btneliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/bin.png"))); // NOI18N
        btneliminar.setText("Eliminar");
        btneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminarActionPerformed(evt);
            }
        });

        btnlimpiar.setBackground(new java.awt.Color(170, 102, 205));
        btnlimpiar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnlimpiar.setForeground(new java.awt.Color(255, 255, 255));
        btnlimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/limpiar.png"))); // NOI18N
        btnlimpiar.setText("Limpiar");
        btnlimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(376, 376, 376)
                .addComponent(btnlimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnregistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btneditar, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btneliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnregistrar)
                    .addComponent(btneditar)
                    .addComponent(btneliminar)
                    .addComponent(btnlimpiar))
                .addGap(0, 6, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registros", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(51, 51, 51))); // NOI18N

        table_registro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        table_registro.setGridColor(new java.awt.Color(229, 244, 253));
        table_registro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                table_registroMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(table_registro);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("Total registros:");

        lbltotal_registro.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltotal_registro.setForeground(new java.awt.Color(0, 204, 0));
        lbltotal_registro.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        btnexportar_excel.setBackground(new java.awt.Color(0, 153, 13));
        btnexportar_excel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnexportar_excel.setForeground(new java.awt.Color(255, 255, 255));
        btnexportar_excel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/excel.png"))); // NOI18N
        btnexportar_excel.setText("Exportar");
        btnexportar_excel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexportar_excelActionPerformed(evt);
            }
        });

        txtbuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtbuscarKeyTyped(evt);
            }
        });

        btnbuscar.setBackground(new java.awt.Color(51, 181, 231));
        btnbuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnbuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/search.png"))); // NOI18N
        btnbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(lbltotal_registro, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(btnexportar_excel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtbuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(btnexportar_excel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnbuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbltotal_registro, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTabbedPane1.setForeground(new java.awt.Color(51, 51, 51));
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Tipo documento");

        cbotipo_documento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbotipo_documentoActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("N??mero documento");

        txtnumero_documento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnumero_documentoKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Ubigeo");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Apellido materno");

        txtapellido_paterno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtapellido_paternoKeyTyped(evt);
            }
        });

        txtapellido_materno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtapellido_maternoKeyTyped(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Apellido paterno");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Nombres");

        txtnombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnombreKeyTyped(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Raz??n social");

        txtrazon_social.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtrazon_socialKeyTyped(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Direcci??n");

        txtdireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtdireccionKeyTyped(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Tel??fono");

        txttelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txttelefonoKeyTyped(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Correo");

        txtcorreo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcorreoKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtapellido_paterno, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(153, 153, 153))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtapellido_materno)
                                .addGap(24, 24, 24))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbotipo_documento, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(19, 19, 19)
                                        .addComponent(jLabel3))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(txtnumero_documento, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(txtcorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(24, 24, 24)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtnombre, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cboubigeo, javax.swing.GroupLayout.Alignment.LEADING, 0, 350, Short.MAX_VALUE))
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtrazon_social)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel10))
                                .addGap(0, 344, Short.MAX_VALUE))
                            .addComponent(txttelefono)))
                    .addComponent(txtdireccion))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(7, 7, 7)
                                    .addComponent(cbotipo_documento, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addGap(7, 7, 7)
                                    .addComponent(txtnumero_documento, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cboubigeo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtrazon_social, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(6, 6, 6)
                                .addComponent(txtapellido_materno, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(6, 6, 6)
                                .addComponent(txtapellido_paterno, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(1, 1, 1)
                                .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(txttelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtcorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtdireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("DATOS 1", jPanel3);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Tipo personal");

        cbtipopersonal1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "GERENTE", "VENTAS Y FINANZAS", "POST VENTA Y MARQUETIN", "PROGRAMACI??N Y SOPORTE", "MANTENIMIENTO Y REPARACI??N DE MOTO" }));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setText("Sueldo");

        txtsueldo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsueldoActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("Genero");

        genero.add(bhombre);
        bhombre.setText("HOMBRE");
        bhombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bhombreActionPerformed(evt);
            }
        });

        genero.add(bmujer);
        bmujer.setText("MUJER");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setText("Imagen");

        lblimagen1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        btnseleccionar_imagen.setBackground(new java.awt.Color(0, 153, 13));
        btnseleccionar_imagen.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnseleccionar_imagen.setForeground(new java.awt.Color(255, 255, 255));
        btnseleccionar_imagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/insertar-icono-de-imagen.png"))); // NOI18N
        btnseleccionar_imagen.setText("Seleccionar");
        btnseleccionar_imagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnseleccionar_imagenActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel21.setText("Firma");

        lblimagen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        btnseleccionar_firma.setBackground(new java.awt.Color(0, 153, 13));
        btnseleccionar_firma.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnseleccionar_firma.setForeground(new java.awt.Color(255, 255, 255));
        btnseleccionar_firma.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/insertar-icono-de-imagen.png"))); // NOI18N
        btnseleccionar_firma.setText("Seleccionar");
        btnseleccionar_firma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnseleccionar_firmaActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("Fecha de Nacimiento");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setText("Fecha de Ingreso ");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setText("Fecha de Salida ");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(cbtipopersonal1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jCalendar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCalendar3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(jLabel17)
                    .addComponent(txtsueldo, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(bhombre, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(bmujer, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCalendar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel18))))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(lblimagen1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addComponent(btnseleccionar_imagen))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel20)
                        .addGap(58, 58, 58)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblimagen, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addComponent(jLabel21)))
                        .addContainerGap(88, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(btnseleccionar_firma)
                        .addGap(100, 100, 100))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbtipopersonal1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bhombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(bmujer)
                                    .addComponent(txtsueldo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(23, 23, 23)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel17)
                                        .addComponent(jLabel14))
                                    .addComponent(jLabel18))
                                .addGap(4, 4, 4)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCalendar3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jCalendar2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jCalendar1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(140, 140, 140))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblimagen1, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                                    .addComponent(lblimagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnseleccionar_firma)
                                    .addComponent(btnseleccionar_imagen))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jTabbedPane1.addTab("DATOS 2", jPanel6);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jTabbedPane1)
                        .addContainerGap())
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 665, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void table_registroMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_registroMousePressed

        /*btnGuardar.setText("Editar");
        habilitar();
        btnEliminar.setEnabled(true);
        accion = "editar";*/
        
        int fila = table_registro.rowAtPoint(evt.getPoint());
        
        String id_Personal = table_registro.getValueAt(fila, 0).toString();
        
        d_Confi_Personal  r;
        
        l_Confi_Personal func = new l_Confi_Personal();
        
        r = func.llenar_campos(id_Personal);
         
        if (r != null) {
            
            txtid.setText(String.valueOf(r.getId()));
            txtnumero_documento.setText(r.getNumero_documento());
            txtapellido_paterno.setText(r.getApellido_paterno());
            txtapellido_materno.setText(r.getApellido_materno());
            txtnombre.setText(r.getNombre());
            txtrazon_social.setText(r.getRazon_social());
            txtdireccion.setText(r.getDireccion());
            txttelefono.setText(r.getTelefono());
            txtcorreo.setText(r.getCorreo());
            cboubigeo.setSelectedItem(new d_Ubigeo(r.getId_ubigeo()));
            cbotipo_documento.setSelectedItem(new d_Documento(r.getId_tipo_documento()));
            
        }
        
        accion = "editar";
        btnregistrar.setEnabled(false);
        btneditar.setEnabled(true);
        btneliminar.setEnabled(true);

    }//GEN-LAST:event_table_registroMousePressed

    private void btnregistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnregistrarActionPerformed
        
        nuevo();
    }//GEN-LAST:event_btnregistrarActionPerformed

    private void btneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditarActionPerformed

        editar();
    }//GEN-LAST:event_btneditarActionPerformed

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed

        eliminar();
    }//GEN-LAST:event_btneliminarActionPerformed

    private void btnlimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlimpiarActionPerformed

        limpiar();
    }//GEN-LAST:event_btnlimpiarActionPerformed

    private void cbotipo_documentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbotipo_documentoActionPerformed
        bloquear_numero_documento();  
    }//GEN-LAST:event_cbotipo_documentoActionPerformed

    private void txtnumero_documentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnumero_documentoKeyTyped
    
        String documento = cbotipo_documento.getItemAt(cbotipo_documento.getSelectedIndex()).getNombre();
        
        if(documento.equals("DNI") && txtnumero_documento.getText().length() >= 8){
            
            evt.consume();
    
        }
        
        if(documento.equals("RUC") && txtnumero_documento.getText().length() >= 11){
            
            evt.consume();
            
        }
        
        char c = evt.getKeyChar();
        if(c < '0' || c > '9'){ evt.consume();}
        
        
    }//GEN-LAST:event_txtnumero_documentoKeyTyped

    private void txtapellido_paternoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtapellido_paternoKeyTyped
        // TODO add your handling code here:
        if(txtapellido_paterno.getText().length() >= 50){
            
            evt.consume();
    
        }
    }//GEN-LAST:event_txtapellido_paternoKeyTyped

    private void txtapellido_maternoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtapellido_maternoKeyTyped
        // TODO add your handling code here:
        if(txtapellido_materno.getText().length() >= 50){
            
            evt.consume();
    
        }
    }//GEN-LAST:event_txtapellido_maternoKeyTyped

    private void txtnombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombreKeyTyped
        // TODO add your handling code here:
        if(txtnombre.getText().length() >= 50){
            
            evt.consume();
    
        }
    }//GEN-LAST:event_txtnombreKeyTyped

    private void txtrazon_socialKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrazon_socialKeyTyped

        if(txtrazon_social.getText().length() >= 100){
            
            evt.consume();
    
        }
    }//GEN-LAST:event_txtrazon_socialKeyTyped

    private void txtdireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdireccionKeyTyped
    
        if(txtdireccion.getText().length() >= 100){
            
            evt.consume();
    
        }
    }//GEN-LAST:event_txtdireccionKeyTyped

    private void txttelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttelefonoKeyTyped
 
        if(txttelefono.getText().length() >= 9){
            
            evt.consume();
    
        }
    }//GEN-LAST:event_txttelefonoKeyTyped

    private void txtcorreoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcorreoKeyTyped
     
        if(txtcorreo.getText().length() >= 50){
            
            evt.consume();
    
        }
    }//GEN-LAST:event_txtcorreoKeyTyped

    private void lblcloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblcloseMouseClicked

        this.dispose();
    }//GEN-LAST:event_lblcloseMouseClicked

    private void txtbuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbuscarKeyTyped

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarActionPerformed
  
        mostrar(txtbuscar.getText().trim());
    }//GEN-LAST:event_btnbuscarActionPerformed

    private void btnexportar_excelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexportar_excelActionPerformed
 
        Helper func = new Helper();
        
        try {
            
            func.tabletoexcel(table_registro, "REPORTE_PERSONAL");
            
            JOptionPane.showMessageDialog(rootPane, "Reporte generado con ??xito");
            
            
        } catch (IOException ex) {
            Logger.getLogger(frm_Confi_Personal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnexportar_excelActionPerformed

    private void txtsueldoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsueldoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsueldoActionPerformed

    private void btnseleccionar_imagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnseleccionar_imagenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnseleccionar_imagenActionPerformed

    private void btnseleccionar_firmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnseleccionar_firmaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnseleccionar_firmaActionPerformed

    private void bhombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bhombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bhombreActionPerformed

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
            java.util.logging.Logger.getLogger(frm_Confi_Personal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_Confi_Personal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_Confi_Personal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_Confi_Personal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_Confi_Personal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton bhombre;
    private javax.swing.JRadioButton bmujer;
    private javax.swing.JButton btnbuscar;
    private javax.swing.JButton btneditar;
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton btnexportar_excel;
    private javax.swing.JButton btnlimpiar;
    private javax.swing.JButton btnregistrar;
    private javax.swing.JButton btnseleccionar_firma;
    private javax.swing.JButton btnseleccionar_imagen;
    private javax.swing.JComboBox<d_Documento> cbotipo_documento;
    private javax.swing.JComboBox<d_Ubigeo> cboubigeo;
    private javax.swing.JComboBox<String> cbtipopersonal1;
    private javax.swing.ButtonGroup genero;
    private com.toedter.calendar.JCalendar jCalendar1;
    private com.toedter.calendar.JCalendar jCalendar2;
    private com.toedter.calendar.JCalendar jCalendar3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblclose;
    private javax.swing.JLabel lblimagen;
    private javax.swing.JLabel lblimagen1;
    private javax.swing.JLabel lbltotal_registro;
    private javax.swing.JTable table_registro;
    private javax.swing.JTextField txtapellido_materno;
    private javax.swing.JTextField txtapellido_paterno;
    private javax.swing.JTextField txtbuscar;
    private javax.swing.JTextField txtcorreo;
    private javax.swing.JTextField txtdireccion;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtnombre;
    private javax.swing.JTextField txtnumero_documento;
    private javax.swing.JTextField txtrazon_social;
    private javax.swing.JTextField txtsueldo;
    private javax.swing.JTextField txttelefono;
    // End of variables declaration//GEN-END:variables
}
