
package Logica;

import Conexion.Conexion;
import Datos.d_Usuario;
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
public class l_Usuario {
    
    private Conexion mysql = new Conexion();
    private Connection conect = mysql.conectar();
    private String sSql = "";
    
    public d_Usuario login(String usuario, String pass)
    {
       
        d_Usuario r = null;
       
        sSql = "SELECT * FROM configuracion_usuario WHERE usuario='"+usuario+"' && password='"+pass+"'";
       
        int id;
        String nombre;
        String apellido;
         
        try {
            
            Statement st = conect.createStatement();
            ResultSet rs = st.executeQuery(sSql);
            
            if(rs.next()){
                
                id = Integer.parseInt(rs.getString("id"));
                nombre = rs.getString("nombre");
                apellido = rs.getString("apellido");
                 
                r = new d_Usuario (id, nombre, apellido);
            }
            
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
        
        return r;
       
    }
    
}
