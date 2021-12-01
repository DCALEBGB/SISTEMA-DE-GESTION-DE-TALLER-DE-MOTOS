package Logica;

import Conexion.Conexion;
import Datos.d_Stati_Comprobante;
import Datos.d_Stati_Unidad_Medida;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author CINTHYA PAOLA GUILLERMO CASTRO
 */
public class l_Static {
    
    private Conexion mysql = new Conexion();
    private Connection conect = mysql.conectar();
    private String sSql = "";
    
    
    
    /* SELECT UNIDAD DE MEDIDA*/
    public ArrayList<d_Stati_Unidad_Medida> select_unidad_medida(){
        
        ArrayList<d_Stati_Unidad_Medida> lista = new ArrayList<d_Stati_Unidad_Medida>();
        
        sSql = "SELECT * FROM static_unidad_medida";
         
        try {
            
            Statement st = conect.createStatement();
            ResultSet rs = st.executeQuery(sSql);
            
            while(rs.next()){
                
                d_Stati_Unidad_Medida d = new d_Stati_Unidad_Medida();
                
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
    
    
    /* SELECT COMPROBANTE*/
    public ArrayList<d_Stati_Comprobante> select_comprobante(){
        
        ArrayList<d_Stati_Comprobante> lista = new ArrayList<d_Stati_Comprobante>();
        
        sSql = "SELECT * FROM static_comprobante";
         
        try {
            
            Statement st = conect.createStatement();
            ResultSet rs = st.executeQuery(sSql);
            
            while(rs.next()){
                
                d_Stati_Comprobante d = new d_Stati_Comprobante();
                
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
    
    
    
    
    
    
    
    
    
    
    
    
    
}
