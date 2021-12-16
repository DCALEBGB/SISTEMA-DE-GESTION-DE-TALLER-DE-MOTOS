
package Datos;

/**
 *
 * @author PC
 */
public class d_Caja {
    
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

    public d_Caja() {
    }

    public d_Caja(String serie, String numero) {
        this.serie = serie;
        this.numero = numero;
    }
    

    public d_Caja(int id, String fecha, String serie, String numero, int id_venta, int id_compra, int id_empresa, String motivo, Double importe, String observacion, int fl_estado, int id_usuario, int id_caja_chica, String tipo_movimiento) {
        this.id = id;
        this.fecha = fecha;
        this.serie = serie;
        this.numero = numero;
        this.id_venta = id_venta;
        this.id_compra = id_compra;
        this.id_empresa = id_empresa;
        this.motivo = motivo;
        this.importe = importe;
        this.observacion = observacion;
        this.fl_estado = fl_estado;
        this.id_usuario = id_usuario;
        this.id_caja_chica = id_caja_chica;
        this.tipo_movimiento = tipo_movimiento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public int getId_compra() {
        return id_compra;
    }

    public void setId_compra(int id_compra) {
        this.id_compra = id_compra;
    }

    public int getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(int id_empresa) {
        this.id_empresa = id_empresa;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public int getFl_estado() {
        return fl_estado;
    }

    public void setFl_estado(int fl_estado) {
        this.fl_estado = fl_estado;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_caja_chica() {
        return id_caja_chica;
    }

    public void setId_caja_chica(int id_caja_chica) {
        this.id_caja_chica = id_caja_chica;
    }

    public String getTipo_movimiento() {
        return tipo_movimiento;
    }

    public void setTipo_movimiento(String tipo_movimiento) {
        this.tipo_movimiento = tipo_movimiento;
    }
    
    

    
}
