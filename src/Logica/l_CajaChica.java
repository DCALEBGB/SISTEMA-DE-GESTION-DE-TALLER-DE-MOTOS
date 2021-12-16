
package Logica;

import Conexion.Conexion;
import Datos.d_CajaChica;
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
public class l_CajaChica {
    
    private Conexion mysql = new Conexion();
    private Connection conect = mysql.conectar();
    private String sSql = "";
    public Integer total_registro;
    
    public DefaultTableModel cargar_table (Integer id){
        
        DefaultTableModel modelo;
        
        String [] titulos = {"Id","Tipo movimiento","Número","Motivo","Importe"};
        String [] registro = new String [5];
        
        total_registro = 0;
        
        modelo = new DefaultTableModel (null, titulos);
        
        sSql = "SELECT * FROM caja where id_caja_chica = " + id;

        try {
            Statement st = conect.createStatement();
            ResultSet rs = st.executeQuery(sSql);
            
            while(rs.next()){
                
                registro [0] = rs.getString("id");
                registro [1] = rs.getString("tipo_movimiento").toUpperCase();
                registro [2] = rs.getString("serie") + "-" + rs.getString("numero");
                registro [3] = rs.getString("motivo").toUpperCase();
                registro [4] = rs.getString("importe");
               
                
                total_registro += 1;
                
                modelo.addRow(registro);
                
                
            }
            return modelo;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    public d_CajaChica Verificar_CajaChica_aperturado(){
        
        d_CajaChica r = null;
        
        int id;
        int id_usuario;
        
        Double saldo_inicial;
        Double total_ingreso;
        Double total_egreso;
        Double total_saldo;
        
        String estado;

            
        sSql = "SELECT * FROM caja_chica order by id desc limit 1";
        
        try {
            
            Statement st = conect.createStatement();
            ResultSet rs = st.executeQuery(sSql);
            
            if(rs.next()){
                
                estado = rs.getString("estado").trim().toString();
                
                if(estado.equals("ABIERTO")){
  
                    id = Integer.parseInt(rs.getString("id"));
                    saldo_inicial = Double.parseDouble(rs.getString("saldo_inicial"));
                    total_ingreso = 0.00;
                    
                    if (rs.getString("total_ingreso") != null) {
                        
                        total_ingreso = Double.parseDouble(rs.getString("total_ingreso"));
                        
                    }
                    
                    total_egreso = 0.00;
                    
                    if (rs.getString("total_ingreso") != null) {
                        
                        total_egreso = Double.parseDouble(rs.getString("total_egreso"));
                        
                    }

                    total_saldo = Double.parseDouble(rs.getString("total_saldo"));
                    id_usuario = Integer.parseInt(rs.getString("id_usuario"));

                    r = new d_CajaChica (id , saldo_inicial, total_ingreso, total_egreso, total_saldo, id_usuario);

                }
                
                

            }
            
            
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
        
        return r;
        
        
    
    }
    
    public boolean aperturar (d_CajaChica dts){

        sSql = "INSERT INTO caja_chica(fecha_sistema,saldo_inicial,"
                + "total_saldo,id_usuario,fecha_apertura,estado, total_ingreso, total_egreso)"
                + "VALUES(?,?,?,?,?,?,0,0)";
        try {
            
            PreparedStatement pst = conect.prepareStatement(sSql);
            
            pst.setString(1, dts.getFecha_sistema());
            pst.setDouble(2, dts.getSaldo_inicial());
            pst.setDouble(3, dts.getTotal_saldo());
            pst.setInt(4, dts.getId_usuario());
            pst.setString(5, dts.getFecha_apertura());
            pst.setString(6, dts.getEstado());
            
            int n = pst.executeUpdate();
            return n != 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al ingresar datos "+e);
            return false;
        }
    }
    
    public boolean cerrar (d_CajaChica dts){

        sSql = "UPDATE caja_chica SET estado = ? , fecha_cierre = ? where id = ?";
        
        try {
            PreparedStatement pst = conect.prepareStatement(sSql);
            
            pst.setString(1, dts.getEstado());
            pst.setString(2, dts.getFecha_cierre());
            pst.setInt(3, dts.getId());

            
            int n = pst.executeUpdate();
            return n != 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al ingresar datos "+e);
            return false;
        }
    }
    
    
    
    
    
}
