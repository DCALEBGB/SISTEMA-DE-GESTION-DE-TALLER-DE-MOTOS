
package Datos;

/**
 *
 * @author PC
 */
public class d_Venta_Venta {
    
    private int id;
    private String serie;
    private String numero;
    private String fecha;
    private String fecha_sistema;
    private String condicion_pago;
    private double total_gravada;
    private double total_igv;
    private double total_importe;
    private double tipo_cambio;
    private String observacion;
    private String fecha_pago;
    private int fl_pagado;
    private int dias_pagar;
    private int fl_estado;
    private String tipo_operacion;
    private int id_comprobante;
    private int id_usuario;
    private int id_moneda;
    private int id_cliente;
    private int fl_tipo;
    private int fl_mantenimiento_vehiculo;

    public d_Venta_Venta() {
    }

    public d_Venta_Venta(String serie, String numero) {
        this.serie = serie;
        this.numero = numero;
    }
    
    

    public d_Venta_Venta(int id, String serie, String numero, String fecha, String fecha_sistema, String condicion_pago, double total_gravada, double total_igv, double total_importe, double tipo_cambio, String observacion, String fecha_pago, int fl_pagado, int dias_pagar, int fl_estado, String tipo_operacion, int id_comprobante, int id_usuario, int id_moneda, int id_cliente, int fl_tipo, int fl_mantenimiento_vehiculo) {
        this.id = id;
        this.serie = serie;
        this.numero = numero;
        this.fecha = fecha;
        this.fecha_sistema = fecha_sistema;
        this.condicion_pago = condicion_pago;
        this.total_gravada = total_gravada;
        this.total_igv = total_igv;
        this.total_importe = total_importe;
        this.tipo_cambio = tipo_cambio;
        this.observacion = observacion;
        this.fecha_pago = fecha_pago;
        this.fl_pagado = fl_pagado;
        this.dias_pagar = dias_pagar;
        this.fl_estado = fl_estado;
        this.tipo_operacion = tipo_operacion;
        this.id_comprobante = id_comprobante;
        this.id_usuario = id_usuario;
        this.id_moneda = id_moneda;
        this.id_cliente = id_cliente;
        this.fl_tipo = fl_tipo;
        this.fl_mantenimiento_vehiculo = fl_mantenimiento_vehiculo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFecha_sistema() {
        return fecha_sistema;
    }

    public void setFecha_sistema(String fecha_sistema) {
        this.fecha_sistema = fecha_sistema;
    }

    public String getCondicion_pago() {
        return condicion_pago;
    }

    public void setCondicion_pago(String condicion_pago) {
        this.condicion_pago = condicion_pago;
    }

    public double getTotal_gravada() {
        return total_gravada;
    }

    public void setTotal_gravada(double total_gravada) {
        this.total_gravada = total_gravada;
    }

    public double getTotal_igv() {
        return total_igv;
    }

    public void setTotal_igv(double total_igv) {
        this.total_igv = total_igv;
    }

    public double getTotal_importe() {
        return total_importe;
    }

    public void setTotal_importe(double total_importe) {
        this.total_importe = total_importe;
    }

    public double getTipo_cambio() {
        return tipo_cambio;
    }

    public void setTipo_cambio(double tipo_cambio) {
        this.tipo_cambio = tipo_cambio;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getFecha_pago() {
        return fecha_pago;
    }

    public void setFecha_pago(String fecha_pago) {
        this.fecha_pago = fecha_pago;
    }

    public int getFl_pagado() {
        return fl_pagado;
    }

    public void setFl_pagado(int fl_pagado) {
        this.fl_pagado = fl_pagado;
    }

    public int getDias_pagar() {
        return dias_pagar;
    }

    public void setDias_pagar(int dias_pagar) {
        this.dias_pagar = dias_pagar;
    }

    public int getFl_estado() {
        return fl_estado;
    }

    public void setFl_estado(int fl_estado) {
        this.fl_estado = fl_estado;
    }

    public String getTipo_operacion() {
        return tipo_operacion;
    }

    public void setTipo_operacion(String tipo_operacion) {
        this.tipo_operacion = tipo_operacion;
    }

    public int getId_comprobante() {
        return id_comprobante;
    }

    public void setId_comprobante(int id_comprobante) {
        this.id_comprobante = id_comprobante;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_moneda() {
        return id_moneda;
    }

    public void setId_moneda(int id_moneda) {
        this.id_moneda = id_moneda;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getFl_tipo() {
        return fl_tipo;
    }

    public void setFl_tipo(int fl_tipo) {
        this.fl_tipo = fl_tipo;
    }

    public int getFl_mantenimiento_vehiculo() {
        return fl_mantenimiento_vehiculo;
    }

    public void setFl_mantenimiento_vehiculo(int fl_mantenimiento_vehiculo) {
        this.fl_mantenimiento_vehiculo = fl_mantenimiento_vehiculo;
    }
    
    
    
    
    
    
}
