package gob.pe.essalud.apireporseg.planilla.model;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionJDBC {
    static final Logger logger = LoggerFactory.getLogger(ConexionJDBC.class);

    private Connection connection = null;

    public ConexionJDBC(String driver, String url, String user, String pass) {
        try {
            Class.forName(driver);
            this.connection = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void close() {
        if (this.connection != null) {
            try {
                this.connection.close();
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
    }

    public Connection getConnection() {
        return this.connection;
    }
}
