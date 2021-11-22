
package Datos;

/**
 *
 * @author CINTHYA PAOLA GUILLERMO CASTRO
 */
public class d_Ubigeo {
    
    public int id;
    public String departamento;
    public String provincia;
    public String distrito;

    public d_Ubigeo() {
    }

    public d_Ubigeo(int id) {
        this.id = id;
    }

    public d_Ubigeo(int id, String departamento, String provincia, String distrito) {
        this.id = id;
        this.departamento = departamento;
        this.provincia = provincia;
        this.distrito = distrito;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public int getId() {
        return id;
    }

    public String getDepartamento() {
        return departamento;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getDistrito() {
        return distrito;
    }
    
    @Override
    public String toString(){
        
        return departamento + " - " + provincia + " - " + distrito;
    }
    
    @Override
    public boolean equals(Object obj){
        
         if (obj != null) {
        
            return this.id == ((d_Ubigeo) obj).id;
            
         }
         
         return false;
    }
    

    
    
}
