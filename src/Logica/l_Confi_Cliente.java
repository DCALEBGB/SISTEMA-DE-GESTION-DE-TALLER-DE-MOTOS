
package Logica;

import Conexion.Conexion;
import Datos.d_Confi_Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CINTHYA PAOLA GUILLERMO CASTRO
 */
public class l_Confi_Cliente {
    
    
    private Conexion mysql = new Conexion();
    private Connection conect = mysql.conectar();
    private String sSql = "";
    public Integer total_registro;
    
    
    
    public DefaultTableModel mostrar (String buscar){
        
        DefaultTableModel modelo;
        
        String [] titulos = {"Id","Número documento","Nombres","Razón social","Dirección","Teléfono","Correo"};
        String [] registro = new String [7];
        
        total_registro = 0;
        
        modelo = new DefaultTableModel (null, titulos);
        
        if (buscar.equals("")) {
            sSql = "SELECT * FROM configuracion_cliente";
        } else {
            sSql = "SELECT * FROM configuracion_cliente WHERE numero_documento LIKE '%"+buscar+"%'";
        }
        try {
            Statement st = conect.createStatement();
            ResultSet rs = st.executeQuery(sSql);
            
            while(rs.next()){
                
                registro [0] = rs.getString(1);
                registro [1] = rs.getString(3);
                registro [2] = (rs.getString(7)+ " " + rs.getString(5)+ " " + rs.getString(6)).toUpperCase();
                registro [3] = rs.getString(8).toUpperCase();
                registro [4] = rs.getString(9).toUpperCase();
                registro [5] = rs.getString(10).toUpperCase();
                registro [6] = rs.getString(11).toUpperCase();
                
                total_registro += 1;
                
                modelo.addRow(registro);
                
                
            }
            return modelo;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    public d_Confi_Cliente llenar_campos(String id_cliente){
        
        d_Confi_Cliente r = null;
        
        int id;
        int id_tipo_documento;
        String numero_documento;
        int id_ubigeo;
        String apellido_paterno;
        String apellido_materno;
        String nombre;
        String razon_social;
        String direccion;
        String telefono;
        String correo;
        int fl_estado;
        
        sSql = "SELECT * FROM configuracion_cliente WHERE id="+id_cliente;
         
        try {
            Statement st = conect.createStatement();
            ResultSet rs = st.executeQuery(sSql);
            
            if(rs.next()){
                
                id = Integer.parseInt(rs.getString(1));
                id_tipo_documento = Integer.parseInt(rs.getString(2));
                numero_documento = rs.getString(3);
                id_ubigeo = Integer.parseInt(rs.getString(4));
                apellido_paterno = rs.getString(5);
                apellido_materno = rs.getString(6);
                nombre = rs.getString(7);
                razon_social = rs.getString(8);
                direccion = rs.getString(9);
                telefono = rs.getString(10);
                correo = rs.getString(11);
                fl_estado = Integer.parseInt(rs.getString(12));
                 
                r = new d_Confi_Cliente (id,id_tipo_documento,numero_documento,id_ubigeo,apellido_paterno,apellido_materno,nombre,razon_social,direccion,telefono,correo,fl_estado);
            }
            
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
        
        return r;
        
        
    
    }
    
    
    public d_Confi_Cliente validar_numero_documento_exitente(String numero_documento, String accion, Integer id_cliente){
        
        d_Confi_Cliente r = null;
        
        int id;
        
        if (accion.equals("nuevo")) {
            
            sSql = "SELECT * FROM configuracion_cliente WHERE numero_documento = '"+numero_documento+"'";
        
        }else{
            
            sSql = "SELECT * FROM configuracion_cliente WHERE numero_documento = '"+numero_documento+"' and id !=" + id_cliente;
            
        }
        
        
         
        try {
            
            Statement st = conect.createStatement();
            ResultSet rs = st.executeQuery(sSql);
            
            if(rs.next()){
                
                id = Integer.parseInt(rs.getString(1));
                 
                r = new d_Confi_Cliente (id);
            }
            
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
        
        return r;
        
        
    
    }
    
    
    
    public boolean insertar (d_Confi_Cliente dts){
        
        sSql = "INSERT INTO configuracion_cliente (id_tipo_documento,numero_documento,id_ubigeo,apellido_paterno,apellido_materno,"
                + "nombre,razon_social,direccion,telefono,correo,fl_estado)"
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = conect.prepareStatement(sSql);
            
            pst.setInt(1, dts.getId_tipo_documento());
            pst.setString(2, dts.getNumero_documento());
            pst.setInt(3, dts.getId_ubigeo());
            pst.setString(4, dts.getApellido_paterno());
            pst.setString(5, dts.getApellido_materno());
            pst.setString(6, dts.getNombre());
            pst.setString(7, dts.getRazon_social());
            pst.setString(8, dts.getDireccion());
            pst.setString(9, dts.getTelefono());
            pst.setString(10, dts.getCorreo());
            pst.setInt(11, 1);
            
            int n = pst.executeUpdate();
            return n != 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al ingresar datos "+e);
            return false;
        }
    }
    
    public boolean editar (d_Confi_Cliente dts){
        
        sSql = "UPDATE configuracion_cliente SET id_tipo_documento = ?,numero_documento = ?,id_ubigeo = ?,apellido_paterno = ?,apellido_materno = ?,"
                + "nombre = ?,razon_social = ?,direccion = ?,telefono = ?,correo = ? WHERE id=?";
        try {
            PreparedStatement pst = conect.prepareStatement(sSql);
            
            pst.setInt(1, dts.getId_tipo_documento());
            pst.setString(2, dts.getNumero_documento());
            pst.setInt(3, dts.getId_ubigeo());
            pst.setString(4, dts.getApellido_paterno());
            pst.setString(5, dts.getApellido_materno());
            pst.setString(6, dts.getNombre());
            pst.setString(7, dts.getRazon_social());
            pst.setString(8, dts.getDireccion());
            pst.setString(9, dts.getTelefono());
            pst.setString(10, dts.getCorreo());
            pst.setInt(11, dts.getId());
            

            
            int n = pst.executeUpdate();
            return n!=0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al editar datos "+e);
            return false;
        }
    }
    
    
    public boolean eliminar (d_Confi_Cliente dts){
        
        sSql = "DELETE FROM configuracion_cliente WHERE id=?";
        
        try {
            
            PreparedStatement pst = conect.prepareStatement(sSql);
            
            pst.setInt(1, dts.getId());
            
            int n = pst.executeUpdate();
            
            return n != 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar datos " +e);
            return false;
        }
    }
    
    
}
