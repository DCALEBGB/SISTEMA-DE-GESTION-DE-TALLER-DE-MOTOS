
package Datos;

/**
 *
 * @author PC
 */
public class d_Almac_Articulo {
    
    private int id;
    private int id_almacen;
    private int id_unidad_medida;
    private int id_usuario;
    private int id_tipo_articulo;
    private int id_presentacion;
    private int id_marca;
    private String codigo;
    private String nombre;
    private String descripcion;
    private double cantidad;
    private double cantidad_minima;
    private double costo;
    private double precio;
    private String fecha_sistema;
    private String fecha_vencimiento;
    private int fl_estado;
    private int fl_vence;
    private String codigo_barra;
    private byte [] imagen;

    public d_Almac_Articulo() {
    }

    public d_Almac_Articulo(int id) {
        this.id = id;
    }

    public d_Almac_Articulo(int id, int id_almacen, int id_unidad_medida, int id_usuario, int id_tipo_articulo, int id_presentacion, int id_marca, String codigo, String nombre, String descripcion, double cantidad, double cantidad_minima, double costo, double precio, String fecha_sistema, String fecha_vencimiento, int fl_estado, int fl_vence, String codigo_barra, byte[] imagen) {
        this.id = id;
        this.id_almacen = id_almacen;
        this.id_unidad_medida = id_unidad_medida;
        this.id_usuario = id_usuario;
        this.id_tipo_articulo = id_tipo_articulo;
        this.id_presentacion = id_presentacion;
        this.id_marca = id_marca;
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.cantidad_minima = cantidad_minima;
        this.costo = costo;
        this.precio = precio;
        this.fecha_sistema = fecha_sistema;
        this.fecha_vencimiento = fecha_vencimiento;
        this.fl_estado = fl_estado;
        this.fl_vence = fl_vence;
        this.codigo_barra = codigo_barra;
        this.imagen = imagen;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_almacen() {
        return id_almacen;
    }

    public void setId_almacen(int id_almacen) {
        this.id_almacen = id_almacen;
    }

    public int getId_unidad_medida() {
        return id_unidad_medida;
    }

    public void setId_unidad_medida(int id_unidad_medida) {
        this.id_unidad_medida = id_unidad_medida;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_tipo_articulo() {
        return id_tipo_articulo;
    }

    public void setId_tipo_articulo(int id_tipo_articulo) {
        this.id_tipo_articulo = id_tipo_articulo;
    }

    public int getId_presentacion() {
        return id_presentacion;
    }

    public void setId_presentacion(int id_presentacion) {
        this.id_presentacion = id_presentacion;
    }

    public int getId_marca() {
        return id_marca;
    }

    public void setId_marca(int id_marca) {
        this.id_marca = id_marca;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getCantidad_minima() {
        return cantidad_minima;
    }

    public void setCantidad_minima(double cantidad_minima) {
        this.cantidad_minima = cantidad_minima;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getFecha_sistema() {
        return fecha_sistema;
    }

    public void setFecha_sistema(String fecha_sistema) {
        this.fecha_sistema = fecha_sistema;
    }

    public String getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    public void setFecha_vencimiento(String fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }

    public int getFl_vence() {
        return fl_vence;
    }

    public void setFl_vence(int fl_vence) {
        this.fl_vence = fl_vence;
    }

    public String getCodigo_barra() {
        return codigo_barra;
    }

    public void setCodigo_barra(String codigo_barra) {
        this.codigo_barra = codigo_barra;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public int getFl_estado() {
        return fl_estado;
    }

    public void setFl_estado(int fl_estado) {
        this.fl_estado = fl_estado;
    }
    
    
    
    @Override
    public String toString(){
        
        return nombre;
    }
    
    @Override
    public boolean equals(Object obj){
        
        if (obj != null) {
            
            return this.id == ((d_Almac_Articulo) obj).id;
            
        }
        
        return false;  
        
    }
    
    
}
