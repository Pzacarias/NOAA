package ar.com.api.noaa.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table (name = "boya")
public class Boya {
    
    @Id
    @Column(name = "boya_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer boyaId;

    @Column (name = "color_luz")
    private Integer colorLuz;

    @Column (name = "longitud_instalacion")
    private Double longitudInstalacion;

    @Column (name = "latitud_instalacion")
    private Double latitudInstalacion;

    @OneToMany(mappedBy = "boya", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Muestra> muestras = new ArrayList<>();

    public Integer getBoyaId() {
        return boyaId;
    }

    public void setBoyaId(Integer boyaID) {
        this.boyaId = boyaID;
    }

    public ColorLuzEnum getColorLuz() {
        return ColorLuzEnum.parse(colorLuz);
    }

    public void setColorLuz(ColorLuzEnum colorLuz) {
        this.colorLuz = colorLuz.getValue();
    }

    public Double getLongitudInstalacion() {
        return longitudInstalacion;
    }

    public void setLongitudInstalacion(Double longitudInstalacion) {
        this.longitudInstalacion = longitudInstalacion;
    }

    public Double getLatitudInstalacion() {
        return latitudInstalacion;
    }

    public void setLatitudInstalacion(Double latitudInstalacion) {
        this.latitudInstalacion = latitudInstalacion;
    }

    
    public List<Muestra> getMuestras() {
        return muestras;
    }

    public void setMuestras(List<Muestra> muestras) {
        this.muestras = muestras;
    }

    public void agregarMuestra (Muestra muestra){
        this.muestras.add(muestra);
    }
   
    //ROJO: Marea peligrosa
    //AMARILLO: Advertencia de marea peligrosa
    //VERDE: todo Ok
    //AZUL: indefinido
    
    public enum ColorLuzEnum {
        ROJO(1), AMARILLO(2), VERDE(3), AZUL(4);

    
        private final Integer value;

        private ColorLuzEnum(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }

        public static ColorLuzEnum parse(Integer id) {
            ColorLuzEnum status = null; 
            for (ColorLuzEnum item : ColorLuzEnum.values()) {
                if (item.getValue().equals(id)) {
                    status = item;
                    break;
                }
            }
            return status;
        }
        
    }
}
