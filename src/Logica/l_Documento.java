package Logica;

import Conexion.Conexion;
import Datos.d_Documento;
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
public class l_Documento {
    
    private Conexion mysql = new Conexion();
    private Connection conect = mysql.conectar();
    private String sSql = "";
    
    public ArrayList<d_Documento> getDocumento(){
        
        ArrayList<d_Documento> lista_documentos = new ArrayList<d_Documento>();
        
        sSql = "SELECT * FROM static_documento";
         
        try {
            
            Statement st = conect.createStatement();
            ResultSet rs = st.executeQuery(sSql);
            
            while(rs.next()){
                
                d_Documento documento = new d_Documento();
                
                documento.setId(rs.getInt("id"));
                documento.setNombre(rs.getString("nombre"));
                documento.setCodigo_sunat(rs.getString("codigo_sunat"));
                documento.setTipo(rs.getString("tipo"));
                 
                lista_documentos.add(documento);
                
            }
            
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
        
        return lista_documentos;    
    
    }
    
    
}
