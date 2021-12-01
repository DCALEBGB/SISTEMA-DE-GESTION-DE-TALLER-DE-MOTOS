
package Logica;

import Conexion.Conexion;
import Datos.d_Almac_Articulo;
import Datos.d_Confi_Cliente;
import Datos.d_Venta_Venta;
import Datos.d_Venta_VentaDetalle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PC
 */
public class l_Venta_Venta {
    
    
    private Conexion mysql = new Conexion();
    private Connection conect = mysql.conectar();
    private String sSql = "";
    public Integer total_registro;
    
    
    public DefaultTableModel mostrar_venta (){
        
        DefaultTableModel modelo;
        
        String [] titulos = {"Id","Fecha","Venta","Comprobante","Cliente","Importe total"};
        String [] registro = new String [6];
        
        total_registro = 0;
        
        modelo = new DefaultTableModel (null, titulos);
        
         sSql = "SELECT * FROM venta_venta_v";

        try {
            Statement st = conect.createStatement();
            ResultSet rs = st.executeQuery(sSql);
            
            while(rs.next()){
                
                registro [0] = rs.getString("id");
                registro [1] = rs.getString("fecha");
                registro [2] = (rs.getString("serie")+ " - " + rs.getString("numero"));
                registro [3] = rs.getString("comprobante").toUpperCase();
                registro [4] = rs.getString("cliente").toUpperCase();
                registro [5] = rs.getString("total_importe");
                
                total_registro += 1;
                
                modelo.addRow(registro);
                
                
            }
            return modelo;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    public DefaultTableModel mostrar_ventadetalle (int id_venta){
        
        DefaultTableModel modelo;
        
        String [] titulos = {"Id","Artículo","Cantidad","Precio unitario", "Importe"};
        String [] registro = new String [5];
        
        total_registro = 0;
        
        modelo = new DefaultTableModel (null, titulos);
        
         sSql = "SELECT * FROM venta_ventadetalle_v WHERE id_venta="+id_venta;

        try {
            Statement st = conect.createStatement();
            ResultSet rs = st.executeQuery(sSql);
            
            while(rs.next()){
                
                registro [0] = rs.getString("id");
                registro [1] = rs.getString("articulo");
                registro [2] = rs.getString("cantidad");
                registro [3] = rs.getString("precio_unitario");
                registro [4] = rs.getString("importe");
                total_registro += 1;
                
                modelo.addRow(registro);
                
                
            }
            return modelo;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    public d_Almac_Articulo get_articulo(String tipo_filtro, String valor){
        
        d_Almac_Articulo r = null;
        
        int id;
        int id_almacen;
        int id_unidad_medida;
        int id_usuario;
        int id_tipo_articulo;
        int id_presentacion;
        int id_marca;
        String codigo;
        String nombre;
        String descripcion;
        double cantidad;
        double cantidad_minima;
        double costo;
        double precio;
        String fecha_sistema;
        String fecha_vencimiento;
        int fl_estado;
        int fl_vence;
        String codigo_barra;
        byte [] imagen;
        
        if (tipo_filtro == "codigo_barra") {
            
            sSql = "SELECT * FROM almacen_articulo WHERE codigo_barra LIKE '%"+ valor + "%'";
            
        }else{
            
            sSql = "SELECT * FROM almacen_articulo WHERE nombre LIKE '%"+ valor + "%'";
            
        }
        
        
         
        try {
            Statement st = conect.createStatement();
            ResultSet rs = st.executeQuery(sSql);
            
            if(rs.next()){
        
                id = rs.getInt("id");
                id_almacen = rs.getInt("id_almacen");
                id_unidad_medida = rs.getInt("id_unidad_medida");
                id_usuario = rs.getInt("id_usuario");
                id_tipo_articulo = rs.getInt("id_tipo_articulo");
                id_presentacion = rs.getInt("id_presentacion");
                id_marca = rs.getInt("id_marca");
                codigo = rs.getString("codigo");
                nombre = rs.getString("nombre");
                descripcion = rs.getString("descripcion");
                cantidad = rs.getDouble("cantidad");
                cantidad_minima = rs.getDouble("cantidad_minima");
                costo = rs.getDouble("costo");
                precio = rs.getDouble("precio");
                fecha_sistema = rs.getString("fecha_sistema");
                fecha_vencimiento = rs.getString("fecha_vencimiento");
                fl_estado = rs.getInt("fl_estado");
                fl_vence = rs.getInt("fl_vence");
                codigo_barra = rs.getString("codigo_barra");
                imagen = rs.getBytes("imagen");

                r = new d_Almac_Articulo(id,id_almacen,id_unidad_medida,id_usuario,id_tipo_articulo,id_presentacion,
                                         id_marca,codigo,nombre,descripcion,cantidad,cantidad_minima, costo, precio,
                                         fecha_sistema, fecha_vencimiento,fl_estado,fl_vence,codigo_barra,imagen);
            }
            
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
        
        return r;
    
    }
    
    public d_Venta_Venta correlativo(){
        
        d_Venta_Venta r = null;
        
        String numero,serie;
        
            
        sSql = "SELECT YEAR(CURDATE()) as serie, LPAD(MAX(id) + 1, 8, '0') AS numero FROM venta_venta";
        
         
        try {
            Statement st = conect.createStatement();
            ResultSet rs = st.executeQuery(sSql);
            
            if(rs.next()){
                
                serie = rs.getString("serie");
                numero = rs.getString("numero");
                 
                r = new d_Venta_Venta(serie, numero);
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
        
        return r;

    }
    
    
    
    public boolean insertar_venta (d_Venta_Venta dts){
        
        sSql = "call save_venta(?,?,?,?,?,?,?)";
        try {
            
            PreparedStatement pst = conect.prepareStatement(sSql);
            
            pst.setString(1, dts.getSerie());
            pst.setString(2, dts.getNumero());
            pst.setDouble(3, dts.getTotal_importe());
            pst.setString(4, dts.getObservacion());
            pst.setInt(5, dts.getId_usuario());
            pst.setInt(6, dts.getId_cliente());
            pst.setInt(7, dts.getId_comprobante());
            
            int n = pst.executeUpdate();
            return n != 0;
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al ingresar datos "+e);
            return false;
        }
    }
    
    
    public boolean insertar_venta_detalle (d_Venta_VentaDetalle dts){
        
        sSql = "call save_ventadetalle(?,?,?,?)";
        try {
            
            PreparedStatement pst = conect.prepareStatement(sSql);
            
            pst.setDouble(1, dts.getCantidad());
            pst.setDouble(2, dts.getPrecio_unitario());
            pst.setDouble(3, dts.getImporte());
            pst.setInt(4, dts.getId_articulo());
           
            
            int n = pst.executeUpdate();
            return n != 0;
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al ingresar datos "+e);
            return false;
        }
    }
    
}
