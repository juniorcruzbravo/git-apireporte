package gob.pe.essalud.apireporseg.planilla.model;

public class Resultado {

    private Integer flag;//correcto o incorrecto
    private Integer tipoResultado;
    private String descripcion;
    private Object object;

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getTipoResultado() {
        return tipoResultado;
    }

    public void setTipoResultado(Integer tipoResultado) {
        this.tipoResultado = tipoResultado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
