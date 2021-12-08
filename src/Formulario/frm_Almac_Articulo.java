
package Formulario;

import Helper.Helper;
import Datos.d_Almac_Articulo;
import Logica.l_Almac_Articulo;
import Datos.d_Almac_Almacen;
import Logica.l_Almac_Almacen;
import Datos.d_Almac_Marca;
import Logica.l_Almac_Marca;
import Datos.d_Almac_Presentacion;
import Logica.l_Almac_Presentacion;
import Datos.d_Almac_Tipo_Articulo;
import Logica.l_Almac_Tipo_Articulo;
import Datos.d_Stati_Unidad_Medida;
import Logica.l_Static;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author CINTHYA PAOLA GUILLERMO CASTRO
 */
public class frm_Almac_Articulo extends javax.swing.JFrame {

    /**
     * Creates new form frm_Almac_Articulo
     */
    private String accion = "";
    private String ruta = "";
    SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd");
    Helper help = new Helper();
    
    int id_usuario;
    
    public frm_Almac_Articulo() {
        initComponents();
        
        this.setLocation(15, 40);
        
        cargar_table("");  
        
        select_almacen(0);
        select_unidad_medida(0);
        select_tipo_articulo(0);
        select_presentacion(0);
        select_marca(0);
 
        
        txtid.setVisible(false);
        
        accion = "nuevo";
        btnregistrar.setEnabled(true);
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);
        
        frm_Menu f_menu = new frm_Menu();
        id_usuario = Integer.parseInt(f_menu.txtid_usuario.getText());
        
    
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Procedimientos">
    
    
    
    private void cargar_table(String buscar) {
        
        
        DefaultTableModel modelo;
        
        String [] titulos = {"Id","Nombre","Descripcion","Cantidad","Costo","Precio","Vencimiento"};
        
        modelo = new DefaultTableModel (null, titulos);
        
        table_registro.setDefaultRenderer(Object.class, new RenderImagen());

        ArrayList lista;
        d_Almac_Articulo dts;
        
        l_Almac_Articulo func = new l_Almac_Articulo();
        lista = func.CargarTable(buscar);
        
        Object Datos[] = new Object[7];
        
        if (lista != null) {
            for (int i = 0; i < lista.size(); i++) {

                dts = (d_Almac_Articulo) lista.get(i);
                
                Datos[0] = String.valueOf(dts.getId());
                Datos[1] = dts.getNombre();
                Datos[2] = dts.getDescripcion();
                Datos[3] = dts.getCantidad();
                Datos[4] = dts.getCosto();
                Datos[5] = dts.getPrecio();
                Datos[6] = dts.getFecha_vencimiento();
                
                /*try {

                    byte[] imagen = dts.getFoto();
                    BufferedImage bufferedImage = null;
                    InputStream inputStream = new ByteArrayInputStream(imagen);
                    bufferedImage = ImageIO.read(inputStream);
                    ImageIcon mIcono = new ImageIcon(bufferedImage.getScaledInstance(60, 60, 0));

                    Datos[7] = new JLabel(mIcono);
                } catch (Exception e) {
                    Datos[7] = new JLabel("No imagen");
                }*/

                modelo.addRow(Datos);
            }

            table_registro.setModel(modelo);
            
            /**table_registro.setRowHeight(60);
            table_registro.getColumnModel().getColumn(0).setPreferredWidth(60);
            table_registro.getColumnModel().getColumn(1).setPreferredWidth(60);
            table_registro.getColumnModel().getColumn(2).setPreferredWidth(60);**/
            
            ajustes_jtable();

        }

    }
    
    void select_almacen(int id){
        
        l_Almac_Almacen obj = new l_Almac_Almacen();
        ArrayList<d_Almac_Almacen> lista = obj.select_almacen();
        
        cboalmacen.removeAllItems();
        
        for(int x = 0; x < lista.size(); x++){
            
            cboalmacen.addItem(new d_Almac_Almacen(lista.get(x).getId(), lista.get(x).getNombre(), lista.get(x).getFl_estado()));     
            
        }
 
        if (id != 0) {
            
            cboalmacen.setSelectedItem(new d_Almac_Almacen(id));
            
        }      
        
    }
    
    void select_marca(int id){
        
        l_Almac_Marca obj = new l_Almac_Marca();
        ArrayList<d_Almac_Marca> lista = obj.select_marca();
        
        cbomarca.removeAllItems();
        
        for(int x = 0; x < lista.size(); x++){
            
            cbomarca.addItem(new d_Almac_Marca(lista.get(x).getId(), lista.get(x).getNombre(), lista.get(x).getFl_estado()));     
            
        }
 
        if (id != 0) {
            
            cbomarca.setSelectedItem(new d_Almac_Marca(id));
            
        }      
        
    }
    
    void select_presentacion(int id){
        
        l_Almac_Presentacion obj = new l_Almac_Presentacion();
        ArrayList<d_Almac_Presentacion> lista = obj.select_presentacion();
        
        cbopresentacion.removeAllItems();
        
        for(int x = 0; x < lista.size(); x++){
            
            cbopresentacion.addItem(new d_Almac_Presentacion(lista.get(x).getId(), lista.get(x).getNombre(), lista.get(x).getFl_estado()));     
            
        }
 
        if (id != 0) {
            
            cbopresentacion.setSelectedItem(new d_Almac_Presentacion(id));
            
        }      
        
    }
    
    
    void select_tipo_articulo(int id){
        
        l_Almac_Tipo_Articulo obj = new l_Almac_Tipo_Articulo();
        ArrayList<d_Almac_Tipo_Articulo> lista = obj.select_tipo_articulo();
        
        cbotipo_articulo.removeAllItems();
        
        for(int x = 0; x < lista.size(); x++){
            
            cbotipo_articulo.addItem(new d_Almac_Tipo_Articulo(lista.get(x).getId(), lista.get(x).getNombre(), lista.get(x).getFl_estado()));     
            
        }
 
        if (id != 0) {
            
            cbotipo_articulo.setSelectedItem(new d_Almac_Tipo_Articulo(id));
            
        }      
        
    }
    
    
    
    void select_unidad_medida(int id){
        
        l_Static obj = new l_Static();
        ArrayList<d_Stati_Unidad_Medida> lista = obj.select_unidad_medida();
        
        cbounidad_medida.removeAllItems();
        
        for(int x = 0; x < lista.size(); x++){
            
            cbounidad_medida.addItem(new d_Stati_Unidad_Medida(lista.get(x).getId(), lista.get(x).getNombre(), lista.get(x).getFl_estado()));     
            
        }
 
        if (id != 0) {
            
            cbounidad_medida.setSelectedItem(new d_Stati_Unidad_Medida(id));

        }
        
    }
    
    
    
    void limpiar(){
        
        this.txtnombre.setText("");
        this.txtdescripcion.setText("");
        this.txtcantidad.setText("");
        this.txtcantidad_minima.setText("");
        this.txtcosto.setText("");
        this.txtprecio.setText("");
        this.txtcodigo_barra.setText("");
        this.txtcodigo.setText("");
        this.lblimagen.setIcon(null);
        this.jdfecha_vencimiento.setDate(new Date());
        this.jcbfl_vence.setSelected(false);
        txtid.setText("");
        this.jdfecha_vencimiento.setEnabled(true);
        
        select_almacen(0);
        select_unidad_medida(0);
        select_tipo_articulo(0);
        select_presentacion(0);
        select_marca(0);
        
        
        accion = "nuevo";
        btnregistrar.setEnabled(true);
        btneditar.setEnabled(false);
        btneliminar.setEnabled(false);
        
    }
    
    
    String validar(){
        
        String r = "";
        
        if(txtnombre.getText().trim().isEmpty()){ r += "\n-Nombre";}
        if(txtdescripcion.getText().trim().isEmpty()){ r += "\n-Descripción";}
        if(txtcantidad.getText().trim().isEmpty()){ r += "\n-Cantidad";}
        if(txtcantidad_minima.getText().trim().isEmpty()){ r += "\n-Cantidad mínima";}
        if(txtcosto.getText().trim().isEmpty()){ r += "\n-Costo";}
        if(txtprecio.getText().trim().isEmpty()){ r += "\n-Precio";}
        if(txtcodigo_barra.getText().trim().isEmpty()){ r += "\n-Código de barra";}
        
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
    
   
    
    void nuevo(){
        
        /* VALIDAR CAMPOS VACÍOS*/
        
        String r = validar();
        
        if (!r.isEmpty()) {
            
            JOptionPane.showMessageDialog(null, "Faltan completar los siguientes campos: " + r, "error", JOptionPane.WARNING_MESSAGE);  
            return;
        }
        
        /**/
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

        d_Almac_Articulo dts = new d_Almac_Articulo();
        l_Almac_Articulo func = new l_Almac_Articulo();

        dts.setId_almacen(cboalmacen.getItemAt(cboalmacen.getSelectedIndex()).getId());
        dts.setId_unidad_medida(cbounidad_medida.getItemAt(cbounidad_medida.getSelectedIndex()).getId());
        dts.setId_usuario(id_usuario);
        dts.setId_tipo_articulo(cbotipo_articulo.getItemAt(cbotipo_articulo.getSelectedIndex()).getId());
        dts.setId_presentacion(cbopresentacion.getItemAt(cbopresentacion.getSelectedIndex()).getId());
        dts.setId_marca(cbomarca.getItemAt(cbomarca.getSelectedIndex()).getId());
        dts.setCodigo(txtcodigo.getText().trim());
        dts.setNombre(txtnombre.getText().trim().toUpperCase());
        dts.setDescripcion(txtdescripcion.getText().trim().toUpperCase());
        dts.setCantidad(Double.parseDouble(txtcantidad.getText().trim()));
        dts.setCantidad_minima(Double.parseDouble(txtcantidad_minima.getText().trim()));
        dts.setCosto(Double.parseDouble(txtcosto.getText().trim()));
        dts.setPrecio(Double.parseDouble(txtprecio.getText().trim()));
        dts.setFecha_sistema(dtf.format(LocalDateTime.now()));
        
        if (this.jcbfl_vence.isSelected()) {
            
            dts.setFecha_vencimiento(dformat.format(jdfecha_vencimiento.getDate()));
            dts.setFl_vence(1);
            
        }else{
            
            dts.setFecha_vencimiento(null);
            dts.setFl_vence(0);

        }
        
        dts.setCodigo_barra(txtcodigo_barra.getText().trim().toUpperCase());
        dts.setImagen(help.get_imagen(ruta));

        
        if (func.insertar(dts)) {
            
            JOptionPane.showMessageDialog(rootPane, "Registrado Correctamente");
            limpiar();
            cargar_table("");
            
        }else{
            
            JOptionPane.showMessageDialog(rootPane, "No se registró");
            
        }
        
    }
    
    void editar(){
        
        /* VALIDAR CAMPOS VACÍOS*/
        
        String r = validar();
        
        if (!r.isEmpty()) {
            
            JOptionPane.showMessageDialog(null, "Faltan completar los siguientes campos: " + r, "error", JOptionPane.WARNING_MESSAGE);  
            return;
        }
        
        /**/
        
        d_Almac_Articulo dts = new d_Almac_Articulo();
        l_Almac_Articulo func = new l_Almac_Articulo();

        dts.setId_almacen(cboalmacen.getItemAt(cboalmacen.getSelectedIndex()).getId());
        dts.setId_unidad_medida(cbounidad_medida.getItemAt(cbounidad_medida.getSelectedIndex()).getId());
        dts.setId_usuario(id_usuario);
        dts.setId_tipo_articulo(cbotipo_articulo.getItemAt(cbotipo_articulo.getSelectedIndex()).getId());
        dts.setId_presentacion(cbopresentacion.getItemAt(cbopresentacion.getSelectedIndex()).getId());
        dts.setId_marca(cbomarca.getItemAt(cbomarca.getSelectedIndex()).getId());
        dts.setCodigo(txtcodigo.getText().trim());
        dts.setNombre(txtnombre.getText().trim().toUpperCase());
        dts.setDescripcion(txtdescripcion.getText().trim().toUpperCase());
        dts.setCantidad(Double.parseDouble(txtcantidad.getText().trim()));
        dts.setCantidad_minima(Double.parseDouble(txtcantidad_minima.getText().trim()));
        dts.setCosto(Double.parseDouble(txtcosto.getText().trim()));
        dts.setPrecio(Double.parseDouble(txtprecio.getText().trim()));
        
        if (this.jcbfl_vence.isSelected()) {
            
            dts.setFecha_vencimiento(dformat.format(jdfecha_vencimiento.getDate()));
            dts.setFl_vence(1);
            
        }else{
            
            dts.setFecha_vencimiento(null);
            dts.setFl_vence(0);

        }
        
        dts.setCodigo_barra(txtcodigo_barra.getText().trim().toUpperCase());
        dts.setImagen(help.get_imagen(ruta));
        
        dts.setId(Integer.parseInt(txtid.getText().trim()));
        
        if (func.editar(dts)) {
            
            JOptionPane.showMessageDialog(rootPane, "Editado Correctamente");
            limpiar();
            cargar_table("");
            
        }else{
            
            JOptionPane.showMessageDialog(rootPane, "No se edito");
            
        }
        
    }
    
    void eliminar(){
        
        if (!txtid.getText().equals("")) {
            
            int confirmacion = JOptionPane.showConfirmDialog(this, "Seguro de eliminar?","Confirmar", 2);
            
            if (confirmacion == 0) {
                
                d_Almac_Articulo dts = new d_Almac_Articulo();
                l_Almac_Articulo func = new l_Almac_Articulo();
                
                dts.setId(Integer.parseInt(txtid.getText()));
                
                if (func.eliminar(dts)) {
                    
                    limpiar();
                    cargar_table("");
                    
                }else{
                    
                    JOptionPane.showMessageDialog(rootPane, "No se eliminó");
                    
                }
                

            }
        }
        
    }
    
   
    
    
    // </editor-fold>      
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        jtpdatos = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtnombre = new javax.swing.JTextField();
        cboalmacen = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        txtdescripcion = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbounidad_medida = new javax.swing.JComboBox<>();
        cbotipo_articulo = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cbopresentacion = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        cbomarca = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txtcantidad = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtcantidad_minima = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtcosto = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtprecio = new javax.swing.JTextField();
        txtcodigo_barra = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        txtcodigo = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        lblimagen = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        btnseleccionar_imagen = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jcbfl_vence = new javax.swing.JCheckBox();
        jdfecha_vencimiento = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(229, 244, 253));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("ARTÍCULO");

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(lbltotal_registro, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
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
                .addGap(7, 7, 7)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnbuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnexportar_excel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbltotal_registro, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jtpdatos.setBackground(new java.awt.Color(229, 244, 253));
        jtpdatos.setForeground(new java.awt.Color(51, 51, 51));
        jtpdatos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setForeground(new java.awt.Color(51, 51, 51));
        jPanel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Nombre");

        txtnombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnombreKeyTyped(evt);
            }
        });

        cboalmacen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboalmacenActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Almacen");

        txtdescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtdescripcionKeyTyped(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Descripción");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Unidad medida");

        cbounidad_medida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbounidad_medidaActionPerformed(evt);
            }
        });

        cbotipo_articulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbotipo_articuloActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Tipo artículo");

        cbopresentacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbopresentacionActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Presentación");

        cbomarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbomarcaActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Marca");

        txtcantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcantidadKeyTyped(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Cantidad");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Cantidad mínima");

        txtcantidad_minima.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcantidad_minimaKeyTyped(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Costo");

        txtcosto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcostoKeyTyped(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("Precio");

        txtprecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtprecioKeyTyped(evt);
            }
        });

        txtcodigo_barra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcodigo_barraKeyTyped(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setText("Código Barra");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(txtcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtcantidad_minima, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cbounidad_medida, 0, 314, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtnombre))
                        .addComponent(jLabel3)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtdescripcion))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6)
                            .addComponent(cbomarca, javax.swing.GroupLayout.Alignment.TRAILING, 0, 256, Short.MAX_VALUE)
                            .addComponent(cboalmacen, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2))
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(txtcosto, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(txtprecio, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addComponent(cbotipo_articulo, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel17)
                                    .addComponent(txtcodigo_barra))
                                .addGap(13, 13, 13))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(cbopresentacion, 0, 326, Short.MAX_VALUE)
                                .addGap(295, 295, 295))))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboalmacen, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtdescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbounidad_medida, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbotipo_articulo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbopresentacion, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbomarca, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtcantidad_minima, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtcosto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtprecio, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtcodigo_barra, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jtpdatos.addTab("Datos 1", jPanel3);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtcodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcodigoKeyTyped(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setText("Código");

        lblimagen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setText("Imagen");

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

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setText("Fecha vencimiento");

        jcbfl_vence.setBackground(new java.awt.Color(255, 255, 255));
        jcbfl_vence.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jcbfl_vence.setText("Vence");
        jcbfl_vence.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jcbfl_venceMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel20)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jdfecha_vencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jcbfl_vence)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblimagen, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(jLabel18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnseleccionar_imagen)
                        .addGap(27, 27, 27)))
                .addGap(762, 762, 762))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(lblimagen, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnseleccionar_imagen))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(txtcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel20)
                        .addGap(6, 6, 6)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jcbfl_vence, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                            .addComponent(jdfecha_vencimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jtpdatos.addTab("Datos 2", jPanel7);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jtpdatos))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtpdatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(561, 561, 561))
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
        
        String id_articulo = table_registro.getValueAt(fila, 0).toString();
        
        d_Almac_Articulo  r;
        
        l_Almac_Articulo func = new l_Almac_Articulo();
        
        r = func.show_selection(Integer.parseInt(id_articulo));
         
        if (r != null) {
            
            txtid.setText(String.valueOf(r.getId()));
            txtnombre.setText(String.valueOf(r.getNombre()));
            txtdescripcion.setText(r.getDescripcion());
            cboalmacen.setSelectedItem(new d_Almac_Almacen(r.getId_almacen()));
            cbounidad_medida.setSelectedItem(new d_Stati_Unidad_Medida(r.getId_unidad_medida()));
            cbotipo_articulo.setSelectedItem(new d_Almac_Tipo_Articulo(r.getId_tipo_articulo()));
            cbopresentacion.setSelectedItem(new d_Almac_Presentacion(r.getId_presentacion()));
            cbomarca.setSelectedItem(new d_Almac_Marca(r.getId_marca()));
             
            txtcantidad.setText(String.valueOf(r.getCantidad()));
            txtcantidad_minima.setText(String.valueOf(r.getCantidad_minima()));
            txtcosto.setText(String.valueOf(r.getCosto()));
            txtprecio.setText(String.valueOf(r.getPrecio()));
            txtcodigo_barra.setText(r.getCodigo_barra());
            txtcodigo.setText(r.getCodigo());
            
            
            if (r.getFl_vence() == 1) {
                
                Date fecha_vencimiento;
            
                try {
                    fecha_vencimiento = dformat.parse(r.getFecha_vencimiento());
                    jdfecha_vencimiento.setDate(fecha_vencimiento);
                } catch (ParseException ex) {
                    Logger.getLogger(frm_Almac_Articulo.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                jcbfl_vence.setSelected(true);
                jdfecha_vencimiento.setEnabled(false);
                
            }else{
                
                jcbfl_vence.setSelected(false); 
                jdfecha_vencimiento.setDate(new Date());
                jdfecha_vencimiento.setEnabled(true);
                
            }
            
            try {
                
                lblimagen.setIcon(null);
                        
                byte[] imagen = r.getImagen();
                BufferedImage bufferedImage = null;
                InputStream inputStream = new ByteArrayInputStream(imagen);
                bufferedImage = ImageIO.read(inputStream);
                ImageIcon mIcono = new ImageIcon(bufferedImage.getScaledInstance(lblimagen.getWidth(), lblimagen.getHeight(), 0));
                
                lblimagen.setIcon(mIcono);
                        
          
            } catch (Exception e) {
                lblimagen.setIcon(null);
            }
            

            
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

    private void lblcloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblcloseMouseClicked

        this.dispose();
    }//GEN-LAST:event_lblcloseMouseClicked

    private void txtbuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbuscarKeyTyped

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarActionPerformed
  
        cargar_table(txtbuscar.getText().trim());
    }//GEN-LAST:event_btnbuscarActionPerformed

    private void btnexportar_excelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexportar_excelActionPerformed
 
        Helper func = new Helper();
        
        try {
            
            func.tabletoexcel(table_registro, "REPORTE_ARTÍCULO");
            
            JOptionPane.showMessageDialog(rootPane, "Reporte generado con éxito");
            
            
        } catch (IOException ex) {
            Logger.getLogger(frm_Almac_Articulo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnexportar_excelActionPerformed

    private void txtprecioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtprecioKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtprecioKeyTyped

    private void txtcostoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcostoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcostoKeyTyped

    private void txtcantidad_minimaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcantidad_minimaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcantidad_minimaKeyTyped

    private void txtcantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcantidadKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcantidadKeyTyped

    private void cbomarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbomarcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbomarcaActionPerformed

    private void cbopresentacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbopresentacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbopresentacionActionPerformed

    private void cbotipo_articuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbotipo_articuloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbotipo_articuloActionPerformed

    private void cbounidad_medidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbounidad_medidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbounidad_medidaActionPerformed

    private void txtdescripcionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdescripcionKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdescripcionKeyTyped

    private void cboalmacenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboalmacenActionPerformed
       
    }//GEN-LAST:event_cboalmacenActionPerformed

    private void txtnombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombreKeyTyped

    }//GEN-LAST:event_txtnombreKeyTyped

    private void txtcodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcodigoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcodigoKeyTyped

    private void txtcodigo_barraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcodigo_barraKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcodigo_barraKeyTyped

    private void btnseleccionar_imagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnseleccionar_imagenActionPerformed

        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("JPG, PNG & GIF", "jpg", "png", "gif");
        fileChooser.setFileFilter(extensionFilter);

        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            ruta = fileChooser.getSelectedFile().getAbsolutePath();
            Image mImagen = new ImageIcon(ruta).getImage();
            ImageIcon mIcono = new ImageIcon(mImagen.getScaledInstance(lblimagen.getWidth(), lblimagen.getHeight(), 0));
            lblimagen.setIcon(mIcono);
        }

    }//GEN-LAST:event_btnseleccionar_imagenActionPerformed

    private void jcbfl_venceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jcbfl_venceMouseClicked
        // TODO add your handling code here:
        if (this.jcbfl_vence.isSelected()) {
            this.jdfecha_vencimiento.setEnabled(false);

            
        }else{
            this.jdfecha_vencimiento.setEnabled(true);
          
        }
    }//GEN-LAST:event_jcbfl_venceMouseClicked

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
            java.util.logging.Logger.getLogger(frm_Almac_Articulo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_Almac_Articulo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_Almac_Articulo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_Almac_Articulo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_Almac_Articulo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnbuscar;
    private javax.swing.JButton btneditar;
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton btnexportar_excel;
    private javax.swing.JButton btnlimpiar;
    private javax.swing.JButton btnregistrar;
    private javax.swing.JButton btnseleccionar_imagen;
    private javax.swing.JComboBox<Datos.d_Almac_Almacen> cboalmacen;
    private javax.swing.JComboBox<Datos.d_Almac_Marca> cbomarca;
    private javax.swing.JComboBox<Datos.d_Almac_Presentacion> cbopresentacion;
    private javax.swing.JComboBox<Datos.d_Almac_Tipo_Articulo> cbotipo_articulo;
    private javax.swing.JComboBox<Datos.d_Stati_Unidad_Medida> cbounidad_medida;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JCheckBox jcbfl_vence;
    private com.toedter.calendar.JDateChooser jdfecha_vencimiento;
    private javax.swing.JTabbedPane jtpdatos;
    private javax.swing.JLabel lblclose;
    private javax.swing.JLabel lblimagen;
    private javax.swing.JLabel lbltotal_registro;
    private javax.swing.JTable table_registro;
    private javax.swing.JTextField txtbuscar;
    private javax.swing.JTextField txtcantidad;
    private javax.swing.JTextField txtcantidad_minima;
    private javax.swing.JTextField txtcodigo;
    private javax.swing.JTextField txtcodigo_barra;
    private javax.swing.JTextField txtcosto;
    private javax.swing.JTextField txtdescripcion;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtnombre;
    private javax.swing.JTextField txtprecio;
    // End of variables declaration//GEN-END:variables
}
