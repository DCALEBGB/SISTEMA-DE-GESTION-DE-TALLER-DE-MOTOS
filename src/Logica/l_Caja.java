
package Logica;

import Conexion.Conexion;
import Datos.d_Caja;
import Datos.d_CajaChica;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;


/**
 *
 * @author PC
 */
public class l_Caja {
    
    private Conexion mysql = new Conexion();
    private Connection conect = mysql.conectar();
    private String sSql = "";
    public Integer total_registro;
    
    private int id;
    private String fecha;
    private String serie;
    private String numero;
    private int id_venta;
    private int id_compra;
    private int id_empresa;
    private String motivo;
    private Double importe;
    private String observacion;
    private int fl_estado;
    private int id_usuario;
    private int id_caja_chica;
    private String tipo_movimiento;
    

    public boolean save_caja_venta(d_Caja dts){

        sSql = "INSERT INTO caja(fecha,serie,numero,motivo,importe,fl_estado,"
                + "id_usuario,id_caja_chica, tipo_movimiento)"
                + "VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = conect.prepareStatement(sSql);
            
            pst.setString(1, dts.getFecha());
            pst.setString(2, dts.getSerie());
            pst.setString(3, dts.getNumero());
            pst.setString(4, dts.getMotivo());
            pst.setDouble(5, dts.getImporte());
            pst.setInt(6, dts.getFl_estado());
            pst.setInt(7, dts.getId_usuario());
            pst.setInt(8, dts.getId_caja_chica());
            pst.setString(9, dts.getTipo_movimiento());
            
            int n = pst.executeUpdate();
            return n != 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al ingresar datos "+e);
            return false;
        }
    }
    
    public boolean save_caja_compra(d_Caja dts){

        sSql = "INSERT INTO caja(fecha,serie,numero,motivo,importe,fl_estado,"
                + "id_usuario,id_caja_chica, tipo_movimiento)"
                + "VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = conect.prepareStatement(sSql);
            
            pst.setString(1, dts.getFecha());
            pst.setString(2, dts.getSerie());
            pst.setString(3, dts.getNumero());
            pst.setString(4, dts.getMotivo());
            pst.setDouble(5, dts.getImporte());
            pst.setInt(6, dts.getFl_estado());
            pst.setInt(7, dts.getId_usuario());
            pst.setInt(8, dts.getId_caja_chica());
            pst.setString(9, dts.getTipo_movimiento());
            
            int n = pst.executeUpdate();
            return n != 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al ingresar datos "+e);
            return false;
        }
    }
    
    
    public boolean update_cajachica_venta(d_CajaChica dts){

        sSql = "UPDATE caja_chica SET total_ingreso = total_ingreso + ?, total_saldo = total_saldo + ? WHERE id = ?";
        try {
            PreparedStatement pst = conect.prepareStatement(sSql);
            

            pst.setDouble(1, dts.getTotal_ingreso());
            pst.setDouble(2, dts.getTotal_ingreso());
            pst.setInt(3, dts.getId());

            int n = pst.executeUpdate();
            return n != 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al ingresar datos "+e);
            return false;
        }
    }
    
    public boolean update_cajachica_compra(d_CajaChica dts){

        sSql = "UPDATE caja_chica SET total_egreso = total_egreso + ?, total_saldo = total_saldo - ? WHERE id = ?";
        try {
            PreparedStatement pst = conect.prepareStatement(sSql);
            

            pst.setDouble(1, dts.getTotal_egreso());
            pst.setDouble(2, dts.getTotal_egreso());
            pst.setInt(3, dts.getId());

            int n = pst.executeUpdate();
            return n != 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al ingresar datos "+e);
            return false;
        }
    }
    
    public d_Caja correlativo(){
        
        d_Caja r = null;
        
        String numero,serie;
        
            
        sSql = "SELECT YEAR(CURDATE()) as serie, LPAD(MAX(id) + 1, 8, '0') AS numero FROM caja";
        
         
        try {
            Statement st = conect.createStatement();
            ResultSet rs = st.executeQuery(sSql);
            
            if(rs.next()){
                
                serie = rs.getString("serie");
                numero = rs.getString("numero");
                 
                r = new d_Caja(serie, numero);
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
        
        return r;

    }
    
    
    
}
