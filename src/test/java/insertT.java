/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.projectoop.primefaces.connectdb.DBConfig;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Santi
 */
public class insertT {

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DBConfig.getUrl(),
                DBConfig.getUsername(),
                DBConfig.getPassword()
        );
    }

    @Test
    public void insertT() throws ClassNotFoundException, SQLException {
        Class.forName(DBConfig.getDriver());

        Connection connection = null;
        Statement statement = null;

        try {

            connection = getConnection();
            statement = connection.createStatement();

            String sql = "INSERT INTO Employee"
                    + "(employee_id,first_name,last_name,email,phone_number,hire_date,job_id,salary,commission_pct,manager_id,department_id)"
                    + "VALUES('207','Santi','Homcheun','aizawa_vs@hotmail.com','097.234.1680','SYSDATE','IT_PROG','7000','0','101','80')";

            statement.execute(sql);
            System.out.println("Record Inserted Successfully");
            
            connection.commit();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }

            if (connection != null) {
                connection.close();
            }
        }

    }
    
    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
