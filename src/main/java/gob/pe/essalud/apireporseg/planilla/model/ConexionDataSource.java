package gob.pe.essalud.apireporseg.planilla.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.util.TimeZone;

public class ConexionDataSource {
    @Autowired
    ApplicationContext context;

    static final Logger logger = LoggerFactory.getLogger(ConexionDataSource.class);
    private Connection connection = null;

    public ConexionDataSource(String datasource) {
        System.out.println("datasource "+datasource);
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup(datasource);
            TimeZone timeZone = TimeZone.getTimeZone("America/Lima");
            TimeZone.setDefault(timeZone);
            Connection con = ds.getConnection();
            this.connection = con;
            logger.info("Conecto BD");
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

    }

    public void close() {
        if (this.connection != null) {
            try {
                this.connection.close();
                logger.info("Desconecto BD");
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
    }

    public Connection getConnection() {
        return this.connection;
    }

}
