package InnerJoinConElCafe.modelo;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Premium")
public class ClientePremium extends Cliente {

//Constructor vacío para crea el objeto con Hibernate
    public ClientePremium() {
        super();
    }
private static final double CUOTA_ANUAL = 30.0;
private static final double DESCUENTO_ENVIO = 0.20;

    public ClientePremium(String nombre, String domicilio, String nif, String email){
        super(nombre,domicilio,nif,email);

    }


     //ToString
    @Override
    public String toString() {
        return super.toString() + 
        "Tipo Cliente: Premium " + 
        "[Cuota: " + String.format("%.2f", CUOTA_ANUAL) + 
        "euros, Descuento: " + DESCUENTO_ENVIO * 100 + 
        "%]";
    }
    
    @Override
    public double aplicarDescuento(double precioBase) {
        return precioBase - (precioBase * DESCUENTO_ENVIO);
    }

    public double getCuotaAnual() {
        return CUOTA_ANUAL;
    }

    @Override
    public double getDescuentoEnvio() {
        return DESCUENTO_ENVIO;
    }

}
