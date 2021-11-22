
package Logica;

import Conexion.Conexion;
import Datos.d_Ubigeo;
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
public class l_Ubigeo {
    
    private Conexion mysql = new Conexion();
    private Connection conect = mysql.conectar();
    private String sSql = "";
    
    public ArrayList<d_Ubigeo> getUbigeo(){
        
        ArrayList<d_Ubigeo> lista_ubigeo = new ArrayList<d_Ubigeo>();
        
        sSql = "SELECT * FROM static_ubigeo";
         
        try {
            
            Statement st = conect.createStatement();
            ResultSet rs = st.executeQuery(sSql);
            
            while(rs.next()){
                
                d_Ubigeo ubigeo = new d_Ubigeo();
                
                ubigeo.setId(rs.getInt("id"));
                ubigeo.setDepartamento(rs.getString("departamento"));
                ubigeo.setProvincia(rs.getString("provincia"));
                ubigeo.setDistrito(rs.getString("distrito"));
                 
                lista_ubigeo.add(ubigeo);
                
                
            }
            
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
        
        return lista_ubigeo;    
    
    }
    
}
