
package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author CINTHYA PAOLA GUILLERMO CASTRO
 */
public class Conexion {
    
    public String conect = "bd_venta_mantenimiento";
    public String url = "jdbc:mysql://127.0.0.1/" + conect;
    public String user = "root";
    public String pass="";
    
    public Conexion(){
        
    }
    
    public Connection conectar(){
        Connection link = null;
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            link=DriverManager.getConnection(this.url, this.user, this.pass);
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos" + e);
        }
        return link;
    }
}
