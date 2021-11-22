
package Logica;

import Conexion.Conexion;
import Datos.d_Almac_Presentacion;
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
public class l_Almac_Presentacion {
    
    
    private Conexion mysql = new Conexion();
    private Connection conect = mysql.conectar();
    private String sSql = "";
    public Integer total_registro;
    
    
    
    public DefaultTableModel mostrar (String buscar){
        
        DefaultTableModel modelo;
        
        String [] titulos = {"Id","Nombre","Estado"};
        String [] registro = new String [3];
        
        total_registro = 0;
        
        modelo = new DefaultTableModel (null, titulos);
        
        if (buscar.equals("")) {
            sSql = "SELECT * FROM almacen_presentacion";
        } else {
            sSql = "SELECT * FROM almacen_presentacion WHERE nombre LIKE '%"+buscar+"%'";
        }
        try {
            Statement st = conect.createStatement();
            ResultSet rs = st.executeQuery(sSql);
            
            while(rs.next()){
                
                registro [0] = rs.getString("id");
                registro [1] = rs.getString("nombre");
                if (Integer.parseInt(rs.getString("fl_estado")) == 1) {
                    registro [2] = "ACTIVO";
                }else{
                    registro [2] = "ANULADO";
                }
                
               
                
                total_registro += 1;
                
                modelo.addRow(registro);
                
                
            }
            return modelo;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    
    /* SELECT PRESENTACIÓN*/
    public ArrayList<d_Almac_Presentacion> select_presentacion(){
        
        ArrayList<d_Almac_Presentacion> lista = new ArrayList<d_Almac_Presentacion>();
        
        sSql = "SELECT * FROM almacen_presentacion";
         
        try {
            
            Statement st = conect.createStatement();
            ResultSet rs = st.executeQuery(sSql);
            
            while(rs.next()){
                
                d_Almac_Presentacion d = new d_Almac_Presentacion();
                
                d.setId(rs.getInt("id"));
                d.setNombre(rs.getString("nombre"));
                 
                lista.add(d);
                
            }
            
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
        
        return lista;    
    
    }
    
    public d_Almac_Presentacion llenar_campos(String id_presentacion){
        
        d_Almac_Presentacion r = null;
        
        int id;
        String nombre;
        int fl_estado;
        
        sSql = "SELECT * FROM almacen_presentacion WHERE id="+id_presentacion;
         
        try {
            Statement st = conect.createStatement();
            ResultSet rs = st.executeQuery(sSql);
            
            if(rs.next()){
                
                id = Integer.parseInt(rs.getString("id"));
                nombre = rs.getString("nombre");
                fl_estado = Integer.parseInt(rs.getString("fl_estado"));
                 
                r = new d_Almac_Presentacion (id,nombre,fl_estado);
            }
            
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
        
        return r;
        
        
    
    }
    
    
    public d_Almac_Presentacion validar_nombre_exitente(String nombre, String accion, Integer id_presentacion){
        
        d_Almac_Presentacion r = null;
        
        int id;
        
        if (accion.equals("nuevo")) {
            
            sSql = "SELECT * FROM almacen_presentacion WHERE nombre = '"+nombre+"'";
        
        }else{
            
            sSql = "SELECT * FROM almacen_presentacion WHERE nombre = '"+nombre+"' and id !=" + id_presentacion;
            
        }
        
        
         
        try {
            
            Statement st = conect.createStatement();
            ResultSet rs = st.executeQuery(sSql);
            
            if(rs.next()){
                
                id = Integer.parseInt(rs.getString(1));
                 
                r = new d_Almac_Presentacion (id);
            }
            
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
        
        return r;
        
        
    
    }
    
    
    
    public boolean insertar (d_Almac_Presentacion dts){
        
        sSql = "INSERT INTO almacen_presentacion (nombre,fl_estado)"
                + "VALUES(?,1)";
        try {
            PreparedStatement pst = conect.prepareStatement(sSql);
            
            pst.setString(1, dts.getNombre());
            
            
            int n = pst.executeUpdate();
            return n != 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al ingresar datos "+e);
            return false;
        }
    }
    
    public boolean editar (d_Almac_Presentacion dts){
        
        sSql = "UPDATE almacen_presentacion SET nombre = ? WHERE id=?";
        try {
            PreparedStatement pst = conect.prepareStatement(sSql);
            
            pst.setString(1, dts.getNombre());
            pst.setInt(2, dts.getId());
            

            
            int n = pst.executeUpdate();
            return n!=0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al editar datos "+e);
            return false;
        }
    }
    
    
    public boolean eliminar (d_Almac_Presentacion dts){
        
        sSql = "DELETE FROM almacen_presentacion WHERE id=?";
        
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
