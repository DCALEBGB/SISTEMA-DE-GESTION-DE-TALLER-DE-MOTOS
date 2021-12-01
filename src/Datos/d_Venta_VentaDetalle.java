
package Datos;

/**
 *
 * @author PC
 */
public class d_Venta_VentaDetalle {
    
    private int id;
    private double cantidad;
    private double importe;
    private double precio_unitario;
    private double igv;
    private double valor_unitario;
    private double porcentaje_igv;
    private double subtotal;
    private String descripcion;
    private int id_articulo;
    private int id_venta;

    public d_Venta_VentaDetalle() {
    }

    public d_Venta_VentaDetalle(int id, double cantidad, double importe, double precio_unitario, double igv, double valor_unitario, double porcentaje_igv, double subtotal, String descripcion, int id_articulo, int id_venta) {
        this.id = id;
        this.cantidad = cantidad;
        this.importe = importe;
        this.precio_unitario = precio_unitario;
        this.igv = igv;
        this.valor_unitario = valor_unitario;
        this.porcentaje_igv = porcentaje_igv;
        this.subtotal = subtotal;
        this.descripcion = descripcion;
        this.id_articulo = id_articulo;
        this.id_venta = id_venta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public double getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public double getIgv() {
        return igv;
    }

    public void setIgv(double igv) {
        this.igv = igv;
    }

    public double getValor_unitario() {
        return valor_unitario;
    }

    public void setValor_unitario(double valor_unitario) {
        this.valor_unitario = valor_unitario;
    }

    public double getPorcentaje_igv() {
        return porcentaje_igv;
    }

    public void setPorcentaje_igv(double porcentaje_igv) {
        this.porcentaje_igv = porcentaje_igv;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId_articulo() {
        return id_articulo;
    }

    public void setId_articulo(int id_articulo) {
        this.id_articulo = id_articulo;
    }

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }
    
    
    
}
