package gob.pe.essalud.apireporseg.util;





import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;


@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix = "us")
public class UsuarioOspeProperties {
    String usuario;
    String ip;
    String urlws;
    String ownerBD;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUrlws() {
        return urlws;
    }

    public void setUrlws(String urlws) {
        this.urlws = urlws;
    }

    public String getOwnerBD() {
        return ownerBD;
    }

    public void setOwnerBD(String ownerBD) {
        this.ownerBD = ownerBD;
    }

    public String getPackageBD() {
        return packageBD;
    }

    public void setPackageBD(String packageBD) {
        this.packageBD = packageBD;
    }

    public String getDatasourceBD() {
        return datasourceBD;
    }

    public void setDatasourceBD(String datasourceBD) {
        this.datasourceBD = datasourceBD;
    }

    String packageBD;
    String datasourceBD;
}
