
package Logica;

import Conexion.Conexion;
import Datos.d_Almac_Articulo;
import java.awt.Font;
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
 * @author PC
 */
public class l_Almac_Articulo {
    
    
    private Conexion mysql = new Conexion();
    private Connection conect = mysql.conectar();
    private String sSql = "";
    public Integer total_registro;
    
    
    public ArrayList CargarTable(String buscar){
        
        ArrayList lista = new ArrayList();
        
        
        total_registro = 0;
        
        if (buscar.equals("")) {
            sSql = "SELECT * FROM almacen_articulo";
        } else {
            sSql = "SELECT * FROM almacen_articulo WHERE nombre LIKE '%"+buscar+"%'";
        }
        
        try {
            
            Statement st = conect.createStatement();
            ResultSet rs = st.executeQuery(sSql);
            
            while (rs.next()) {
                
                d_Almac_Articulo dts = new d_Almac_Articulo();
                
                dts.setId(rs.getInt("id"));
                dts.setId_almacen(rs.getInt("id_almacen"));
                dts.setId_unidad_medida(rs.getInt("id_unidad_medida"));
                dts.setId_usuario(rs.getInt("id_usuario"));
                dts.setId_tipo_articulo(rs.getInt("id_tipo_articulo"));
                dts.setId_presentacion(rs.getInt("id_presentacion"));
                dts.setId_marca(rs.getInt("id_marca"));
                dts.setCodigo(rs.getString("codigo"));
                dts.setNombre(rs.getString("nombre"));
                dts.setDescripcion(rs.getString("descripcion"));
                dts.setCantidad(rs.getDouble("cantidad"));
                dts.setCantidad_minima(rs.getDouble("cantidad_minima"));
                dts.setCosto(rs.getDouble("costo"));
                dts.setPrecio(rs.getDouble("precio"));
                dts.setFecha_vencimiento(rs.getString("fecha_vencimiento"));
                dts.setCodigo_barra(rs.getString("codigo_barra"));
                dts.setImagen(rs.getBytes("imagen"));
                dts.setFl_estado(rs.getInt("fl_estado"));
                dts.setFl_vence(rs.getInt("fl_vence"));
                
                lista.add(dts);
                
                total_registro += 1;
            }
            
        }catch (Exception e){
            return null;
        }
        return lista;
    }
    

    
    public ArrayList<d_Almac_Articulo> select_articulo(){
        
        ArrayList<d_Almac_Articulo> lista= new ArrayList<d_Almac_Articulo>();
        
        sSql = "SELECT * FROM almacen_articulo";
         
        try {
            
            Statement st = conect.createStatement();
            ResultSet rs = st.executeQuery(sSql);
            
            while(rs.next()){
                
                d_Almac_Articulo dts = new d_Almac_Articulo();
                
                dts.setId(rs.getInt("id"));
                dts.setNombre(rs.getString("nombre"));
                 
                lista.add(dts);
                
            }
            
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
        
        return lista;    
    
    }
    
    public d_Almac_Articulo show_selection(int id_articulo){
        
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
        
        sSql = "SELECT * FROM almacen_articulo WHERE id="+ id_articulo;
         
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
    

    public boolean insertar (d_Almac_Articulo dts){
        
        sSql = "INSERT INTO almacen_articulo(id_almacen, id_unidad_medida, id_usuario, id_tipo_articulo, id_presentacion, id_marca,"
                + "codigo, nombre, descripcion, cantidad, cantidad_minima, costo, precio, fecha_sistema, fecha_vencimiento, codigo_barra,"
                + "imagen,fl_estado, fl_vence)"
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            
            PreparedStatement pst = conect.prepareStatement(sSql);
            
            pst.setInt(1, dts.getId_almacen());
            pst.setInt(2, dts.getId_unidad_medida());
            pst.setInt(3, dts.getId_usuario());
            pst.setInt(4, dts.getId_tipo_articulo());
            pst.setInt(5, dts.getId_presentacion());
            pst.setInt(6, dts.getId_marca());
            pst.setString(7, dts.getCodigo());
            pst.setString(8, dts.getNombre());
            pst.setString(9, dts.getDescripcion());
            pst.setDouble(10, dts.getCantidad());
            pst.setDouble(11, dts.getCantidad_minima());
            pst.setDouble(12, dts.getCosto());
            pst.setDouble(13, dts.getPrecio());
            pst.setString(14, dts.getFecha_sistema()); 
            pst.setString(15, dts.getFecha_vencimiento());
            pst.setString(16, dts.getCodigo_barra());
            pst.setBytes(17, dts.getImagen());
            pst.setInt(18, 1);
            pst.setInt(19, dts.getFl_vence());
            
            int n = pst.executeUpdate();
            return n != 0;
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al ingresar datos "+e);
            return false;
        }
    }
    
    public boolean editar (d_Almac_Articulo dts){
        
        sSql = "UPDATE almacen_articulo SET id_almacen = ?, id_unidad_medida = ?, id_usuario = ?,id_tipo_articulo = ?,"
                + "id_presentacion = ?, id_marca = ?, codigo = ?, nombre = ?, descripcion = ?, cantidad = ?, cantidad_minima = ?,"
                + "costo = ?, precio = ?, fecha_vencimiento = ?, codigo_barra = ?, imagen = ?, fl_vence = ? WHERE id = ?";
        try {
            
            PreparedStatement pst = conect.prepareStatement(sSql);
            
            pst.setInt(1, dts.getId_almacen());
            pst.setInt(2, dts.getId_unidad_medida());
            pst.setInt(3, dts.getId_usuario());
            pst.setInt(4, dts.getId_tipo_articulo());
            pst.setInt(5, dts.getId_presentacion());
            pst.setInt(6, dts.getId_marca());
            pst.setString(7, dts.getCodigo());
            pst.setString(8, dts.getNombre());
            pst.setString(9, dts.getDescripcion());
            pst.setDouble(10, dts.getCantidad());
            pst.setDouble(11, dts.getCantidad_minima());
            pst.setDouble(12, dts.getCosto());
            pst.setDouble(13, dts.getPrecio());
            pst.setString(14, dts.getFecha_vencimiento());
            pst.setString(15, dts.getCodigo_barra());
            pst.setBytes(16, dts.getImagen());
            pst.setInt(17, dts.getFl_vence());
            pst.setInt(18, dts.getId());
            
            int n = pst.executeUpdate();
            return n!=0;
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al editar datos "+e);
            return false;
        }
    }
    
    
    public boolean eliminar (d_Almac_Articulo dts){
        
        sSql = "DELETE FROM almacen_articulo WHERE id = ?";
        
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
