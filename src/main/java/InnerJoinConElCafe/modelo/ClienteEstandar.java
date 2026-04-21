package InnerJoinConElCafe.modelo;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Estandar")
public class ClienteEstandar extends Cliente {

    //Constructor vacío para que se pueda crear el objeto con Hibernate
    public ClienteEstandar() {
        super();
    }
    public ClienteEstandar(String nombre, String domicilio, String nif, String email){
        super(nombre, domicilio, nif, email);
    }


    @Override
    public String toString() {
        return super.toString() + 
        "Tipo Cliente: Estandar ";
    }

    @Override
    public double getCuotaAnual() {
        return 0.0;
    }

    @Override
    public double getDescuentoEnvio() {
        return 0.0;
    }
}
