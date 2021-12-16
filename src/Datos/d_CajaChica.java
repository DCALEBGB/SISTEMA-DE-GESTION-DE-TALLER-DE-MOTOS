
package Datos;

/**
 *
 * @author PC
 */
public class d_CajaChica {
    
    private int id;
    private String fecha_sistema;
    private Double saldo_inicial;
    private Double total_ingreso;
    private Double total_egreso;
    private Double total_saldo;
    private int id_local;
    private int id_usuario;
    private int id_empresa;
    private String estado;
    private String fecha_cierre;
    private String fecha_apertura;

    public d_CajaChica() {
    }

    public d_CajaChica(int id, int id_usuario) {
        this.id = id;
        this.id_usuario = id_usuario;
    }

    public d_CajaChica(int id, Double saldo_inicial, Double total_ingreso, Double total_egreso, Double total_saldo, int id_usuario) {
        this.id = id;
        this.saldo_inicial = saldo_inicial;
        this.total_ingreso = total_ingreso;
        this.total_egreso = total_egreso;
        this.total_saldo = total_saldo;
        this.id_usuario = id_usuario;
    }
    
    
    
    

    public d_CajaChica(int id, String fecha_sistema, Double saldo_inicial, Double total_ingreso, Double total_egreso, Double total_saldo, int id_local, int id_usuario, int id_empresa, String estado, String fecha_cierre, String fecha_apertura) {
        this.id = id;
        this.fecha_sistema = fecha_sistema;
        this.saldo_inicial = saldo_inicial;
        this.total_ingreso = total_ingreso;
        this.total_egreso = total_egreso;
        this.total_saldo = total_saldo;
        this.id_local = id_local;
        this.id_usuario = id_usuario;
        this.id_empresa = id_empresa;
        this.estado = estado;
        this.fecha_cierre = fecha_cierre;
        this.fecha_apertura = fecha_apertura;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha_sistema() {
        return fecha_sistema;
    }

    public void setFecha_sistema(String fecha_sistema) {
        this.fecha_sistema = fecha_sistema;
    }

    public Double getSaldo_inicial() {
        return saldo_inicial;
    }

    public void setSaldo_inicial(Double saldo_inicial) {
        this.saldo_inicial = saldo_inicial;
    }

    public Double getTotal_ingreso() {
        return total_ingreso;
    }

    public void setTotal_ingreso(Double total_ingreso) {
        this.total_ingreso = total_ingreso;
    }

    public Double getTotal_egreso() {
        return total_egreso;
    }

    public void setTotal_egreso(Double total_egreso) {
        this.total_egreso = total_egreso;
    }

    public Double getTotal_saldo() {
        return total_saldo;
    }

    public void setTotal_saldo(Double total_saldo) {
        this.total_saldo = total_saldo;
    }

    public int getId_local() {
        return id_local;
    }

    public void setId_local(int id_local) {
        this.id_local = id_local;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(int id_empresa) {
        this.id_empresa = id_empresa;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFecha_cierre() {
        return fecha_cierre;
    }

    public void setFecha_cierre(String fecha_cierre) {
        this.fecha_cierre = fecha_cierre;
    }

    public String getFecha_apertura() {
        return fecha_apertura;
    }

    public void setFecha_apertura(String fecha_apertura) {
        this.fecha_apertura = fecha_apertura;
    }

    
    

    
}
