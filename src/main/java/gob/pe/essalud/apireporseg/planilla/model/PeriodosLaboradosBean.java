package gob.pe.essalud.apireporseg.planilla.model;

public class PeriodosLaboradosBean {

    private String periodo_laborado;
    private String per_aporta;
    private String dias_labor;

    @Override
    public String toString() {
        return "PeriodosLaboradosBean{" +
                "periodo_laborado='" + periodo_laborado + '\'' +
                ", per_aporta='" + per_aporta + '\'' +
                ", dias_labor='" + dias_labor + '\'' +
                '}';
    }

    public String getPeriodo_laborado() {
        return periodo_laborado;
    }

    public void setPeriodo_laborado(String periodo_laborado) {
        this.periodo_laborado = periodo_laborado;
    }

    public String getPer_aporta() {
        return per_aporta;
    }

    public void setPer_aporta(String per_aporta) {
        this.per_aporta = per_aporta;
    }

    public String getDias_labor() {
        return dias_labor;
    }

    public void setDias_labor(String dias_labor) {
        this.dias_labor = dias_labor;
    }
}
