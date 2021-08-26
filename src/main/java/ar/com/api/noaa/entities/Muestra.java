package ar.com.api.noaa.entities;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table (name = "muestra")
public class Muestra {
    
    @Id
    @Column(name = "muestra_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer muestraId;
  
    @ManyToOne
    @JoinColumn (name = "boya_id", referencedColumnName = "boya_id")
    private Boya boya; 

    @Column (name = "horario_muestra")
    private Date horarioMuestra;

    @Column (name = "matricula_embarcacion")
    private String matriculaEmbarcacion;

    private Double longitud;

    private Double latitud;

    @Column (name = "altura_al_nivel_del_mar")
    private Double alturaAlNivelDelMar;

    public Integer getMuestraId() {
        return muestraId;
    }

    public void setMuestraId(Integer muestraId) {
        this.muestraId = muestraId;
    }

    public Date getHorarioMuestra() {
        return horarioMuestra;
    }

    public void setHorarioMuestra(Date horarioMuestra) {
        this.horarioMuestra = horarioMuestra;
    }

    public String getMatriculaEmbarcacion() {
        return matriculaEmbarcacion;
    }

    public void setMatriculaEmbarcacion(String matriculaEmbarcacion) {
        this.matriculaEmbarcacion = matriculaEmbarcacion;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getAlturaAlNivelDelMar() {
        return alturaAlNivelDelMar;
    }

    public void setAlturaAlNivelDelMar(Double alturaAlNivelDelMar) {
        this.alturaAlNivelDelMar = alturaAlNivelDelMar;
    }

    public Boya getBoya() {
        return boya;
    }

    public void setBoya(Boya boya) {
        this.boya = boya;
        this.boya.agregarMuestra(this);
    }


}
