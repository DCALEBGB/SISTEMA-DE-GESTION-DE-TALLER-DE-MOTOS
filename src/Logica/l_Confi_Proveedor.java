
package Logica;

import Conexion.Conexion;
import Datos.d_Confi_Proveedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CINTHYA PAOLA GUILLERMO CASTRO
 */
public class l_Confi_Proveedor {
    
    
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
            sSql = "SELECT * FROM configuracion_proveedor";
        } else {
            sSql = "SELECT * FROM configuracion_proveedor WHERE numero_documento LIKE '%"+buscar+"%'";
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
    
    public d_Confi_Proveedor llenar_campos(String id_proveedor){
        
        d_Confi_Proveedor r = null;
        
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
        
        sSql = "SELECT * FROM configuracion_proveedor WHERE id="+id_proveedor;
         
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
                 
                r = new d_Confi_Proveedor (id,id_tipo_documento,numero_documento,id_ubigeo,apellido_paterno,apellido_materno,nombre,razon_social,direccion,telefono,correo,fl_estado);
            }
            
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
        
        return r;
        
        
    
    }
    
    
    /* SELECT PROVEEDOR*/
    public ArrayList<d_Confi_Proveedor> select_proveedor(){
        
        ArrayList<d_Confi_Proveedor> lista = new ArrayList<d_Confi_Proveedor>();
        
        sSql = "SELECT * FROM configuracion_proveedor";
         
        try {
            
            Statement st = conect.createStatement();
            ResultSet rs = st.executeQuery(sSql);
            
            while(rs.next()){
                
                d_Confi_Proveedor d = new d_Confi_Proveedor();
                
                d.setId(rs.getInt("id"));
                d.setNumero_documento(rs.getString("numero_documento"));
                d.setNombre(rs.getString("nombre"));
                d.setApellido_paterno(rs.getString("apellido_paterno"));
                d.setApellido_materno(rs.getString("apellido_materno"));

                lista.add(d);
                
            }
            
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
        
        return lista;    
    
    }
    
    
    public d_Confi_Proveedor validar_numero_documento_exitente(String numero_documento, String accion, Integer id_proveedor){
        
        d_Confi_Proveedor r = null;
        
        int id;
        
        if (accion.equals("nuevo")) {
            
            sSql = "SELECT * FROM configuracion_proveedor WHERE numero_documento = '"+numero_documento+"'";
        
        }else{
            
            sSql = "SELECT * FROM configuracion_proveedor WHERE numero_documento = '"+numero_documento+"' and id !=" + id_proveedor;
            
        }
        
        
         
        try {
            
            Statement st = conect.createStatement();
            ResultSet rs = st.executeQuery(sSql);
            
            if(rs.next()){
                
                id = Integer.parseInt(rs.getString(1));
                 
                r = new d_Confi_Proveedor (id);
            }
            
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
        
        return r;
        
        
    
    }
    
    
    
    public boolean insertar (d_Confi_Proveedor dts){
        
        sSql = "INSERT INTO configuracion_proveedor (id_tipo_documento,numero_documento,id_ubigeo,apellido_paterno,apellido_materno,"
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
    
    public boolean editar (d_Confi_Proveedor dts){
        
        sSql = "UPDATE configuracion_proveedor SET id_tipo_documento = ?,numero_documento = ?,id_ubigeo = ?,apellido_paterno = ?,apellido_materno = ?,"
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
    
    
    public boolean eliminar (d_Confi_Proveedor dts){
        
        sSql = "DELETE FROM configuracion_proveedor WHERE id=?";
        
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
