
package Logica;

import Conexion.Conexion;
import Datos.d_Confi_Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author PC
 */
public class l_Confi_Usuario {
    
    
    private Conexion mysql = new Conexion();
    private Connection conect = mysql.conectar();
    private String sSql = "";
    public Integer total_registro;
    
    
    public ArrayList CargarTable(String buscar){
        
        ArrayList lista = new ArrayList();
        
        
        total_registro = 0;
        
        if (buscar.equals("")) {
            sSql = "SELECT * FROM configuracion_usuario";
        } else {
            sSql = "SELECT * FROM configuracion_usuario WHERE nombre LIKE '%"+buscar+"%'";
        }
        
        try {
            
            Statement st = conect.createStatement();
            ResultSet rs = st.executeQuery(sSql);
            
            while (rs.next()) {
                
                d_Confi_Usuario dts = new d_Confi_Usuario();
                
                dts.setId(rs.getInt("id"));
                dts.setNombre(rs.getString("nombre"));
                dts.setApellido(rs.getString("apellido"));
                dts.setUsuario(rs.getString("usuario"));
                dts.setPassword(rs.getString("password"));
                dts.setEmail(rs.getString("email"));
                dts.setTipo(rs.getString("tipo"));
                dts.setImagen(rs.getBytes("imagen"));
                dts.setFl_estado(rs.getInt("fl_estado"));
                
                lista.add(dts);
                
                total_registro += 1;
            }
            
        }catch (Exception e){
            return null;
        }
        return lista;
    }
    
    
    public d_Confi_Usuario show_selection(int id_usuario){
        
        d_Confi_Usuario r = null;
        
        int id;
        byte [] imagen;
        String nombre;
        String apellido;
        String usuario;
        String password;
        String email;
        String tipo;
        String fecha_sistema;
        String login_date;
        String token;
        int fl_estado;
        
        sSql = "SELECT * FROM configuracion_usuario WHERE id="+ id_usuario;
         
        try {
            Statement st = conect.createStatement();
            ResultSet rs = st.executeQuery(sSql);
            
            if(rs.next()){
        
                id = rs.getInt("id");
                imagen = rs.getBytes("imagen");
                nombre = rs.getString("nombre");
                apellido = rs.getString("apellido");
                usuario = rs.getString("usuario");
                password = rs.getString("password");
                email = rs.getString("email");
                tipo = rs.getString("tipo");
                fecha_sistema = rs.getString("fecha_sistema");
                login_date = rs.getString("login_date");
                token = rs.getString("token");
                fl_estado = rs.getInt("fl_estado");

                r = new d_Confi_Usuario(id,imagen,nombre,apellido,usuario,password,
                                         email,tipo,fecha_sistema,login_date,token,
                                         fl_estado);
            }
            
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
        
        return r;
    
    }
    

    public boolean insertar (d_Confi_Usuario dts){
        
        sSql = "INSERT INTO configuracion_usuario(imagen, nombre, apellido, usuario, password, email,"
                + "tipo,fecha_sistema,fl_estado)"
                + "VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            
            PreparedStatement pst = conect.prepareStatement(sSql);
            
            pst.setBytes(1, dts.getImagen());
            pst.setString(2, dts.getNombre());
            pst.setString(3, dts.getApellido());
            pst.setString(4, dts.getUsuario());
            pst.setString(5, dts.getPassword());
            pst.setString(6, dts.getEmail());
            pst.setString(7, dts.getTipo());
            pst.setString(8, dts.getFecha_sistema());
            pst.setInt(9, 1);


            
            int n = pst.executeUpdate();
            return n != 0;
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al ingresar datos "+e);
            return false;
        }
    }
    
    public boolean editar (d_Confi_Usuario dts){
        
        sSql = "UPDATE configuracion_usuario SET imagen= ?, nombre = ?, apellido = ?,usuario = ?,"
                + "password = ?, email = ?, tipo = ? WHERE id = ?";
        try {
            
            PreparedStatement pst = conect.prepareStatement(sSql);
            
            pst.setBytes(1, dts.getImagen());
            pst.setString(2, dts.getNombre());
            pst.setString(3, dts.getApellido());
            pst.setString(4, dts.getUsuario());
            pst.setString(5, dts.getPassword());
            pst.setString(6, dts.getEmail());
            pst.setString(7, dts.getTipo());
            pst.setInt(8, dts.getId());
            
            int n = pst.executeUpdate();
            return n!=0;
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al editar datos "+e);
            return false;
        }
    }
    
    
    public boolean eliminar (d_Confi_Usuario dts){
        
        sSql = "DELETE FROM configuracion_usuario WHERE id = ?";
        
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
