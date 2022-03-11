package com.indra.actions;

import com.indra.models.DataExcelModels;
import oracle.jdbc.datasource.impl.OracleDataSource;

import java.sql.*;

public class DatabasePortInActions extends DataExcelModels {

    public void cleanLinesMsisdn(String msisdn) throws SQLException {
        cleanLineMsisdn(getUrlDBE(), getPort(), getServiceE(), getUserE(), getPasswordE()
                , msisdn);
    }

    public void cleanLinesMsi(String msi) throws SQLException {
        cleanLineMsi(getUrlDBS(), getPort(), getServiceS(), getUserS(), getPasswordS()
                , msi);
    }

    public void executePortabilityTransactionStatus(String msisdn) throws SQLException {
        //msisdn= getMSISDN();-- se tomaria desde el excel datamodel se debe crear
        portabilityTransactionStatus(getUrlDBA(),getPort(),getServiceA(),getUserA(),getPasswordA()
                ,msisdn);
    }

    public void executePortabilityNip(String msisdn) throws SQLException {
        portabilityNip(getUrlDBA(),getPort(),getServiceA(),getUserA(),getPasswordA()
                ,msisdn);
    }

    public void executeSelectNip(String nip) throws SQLException {
        selectNip(getUrlDBA(),getPort(),getServiceA(),getUserA(),getPasswordA()
                ,nip);
    }

    public void executePortabilityRecept(String msisdn) throws SQLException {
        portabilityRecept(getUrlDBA(),getPort(),getServiceA(),getUserA(),getPasswordA()
                ,msisdn);
    }

    public void executeUpdatePortId(String portId, String msisdn) throws SQLException {
        updatePortId(getUrlDBA(),getPort(),getServiceA(),getUserA(),getPasswordA()
                ,portId,msisdn);
    }

    public void executePortId(String portId) throws SQLException {
        portId(getUrlDBA(),getPort(),getServiceA(),getUserA(),getPasswordA()
                ,portId);
    }

    public void executePortabilityTransaction(String portId) throws SQLException {
        portabilityTransaction(getUrlDBA(),getPort(),getServiceA(),getUserA(),getPasswordA()
                ,portId);
    }

    public void executeWindowPortability(String msisdn) throws SQLException {
        windowPortability(getUrlDBA(),getPort(),getServiceA(),getUserA(),getPasswordA()
                ,msisdn);
    }

//---------------------------------PortIn clean lines-------------------------------------

    public void cleanLineMsisdn(String url, String port, String service, String user, String password,
                                             String msisdn) throws SQLException {
        OracleDataSource ods = null;
        Connection conn= null;
        ResultSet resultSet = null;
        // Create DataSource and connect to the local database
        ods = new OracleDataSource();
        ods.setURL("jdbc:oracle:thin:@//"+url+":"+port+"/"+service);
        ods.setUser(user);
        ods.setPassword(password);
        conn = ods.getConnection();
        Statement stmt=conn.createStatement();
        try {
            String query = "delete from siebel.CX_DEVICE_MSISDN where device='"+msisdn+"'; COMMIT;";
            resultSet = stmt.executeQuery(query);// realiza la ejecución de query
            while (resultSet.next()) {
                System.out.println(resultSet.getString(3));
            }
        }
        //Close the result set, statement, and the connection
        finally{
            if(conn!=null) conn.close();
        }
    }

    public void cleanLineMsi(String url, String port, String service, String user, String password,
                              String msi) throws SQLException {
        OracleDataSource ods = null;
        Connection conn= null;
        ResultSet resultSet = null;
        // Create DataSource and connect to the local database
        ods = new OracleDataSource();
        ods.setURL("jdbc:oracle:thin:@//"+url+":"+port+"/"+service);
        ods.setUser(user);
        ods.setPassword(password);
        conn = ods.getConnection();
        Statement stmt=conn.createStatement();
        try {
            String query = "delete from SIEBEL.cx_device_chip where IMSI='"+msi+"'; COMMIT;";
            resultSet = stmt.executeQuery(query);// realiza la ejecución de query
            while (resultSet.next()) {
                System.out.println(resultSet.getString(3));
            }
        }
        //Close the result set, statement, and the connection
        finally{
            if(conn!=null) conn.close();
        }
    }
//---------------------------------PortIn-------------------------------------

    public void portabilityTransactionStatus(String url, String port, String service, String user, String password,
                                             String msisdn) throws SQLException {
        OracleDataSource ods = null;
        Connection conn= null;
        ResultSet resultSet = null;
        // Create DataSource and connect to the local database
        ods = new OracleDataSource();
        ods.setURL("jdbc:oracle:thin:@//"+url+":"+port+"/"+service);
        ods.setUser(user);
        ods.setPassword(password);
        conn = ods.getConnection();
        Statement stmt=conn.createStatement();
        try {
            String query = "SELECT ps.*, TO_CHAR(ps.create_date, 'DD-MM-YYYY HH24:MI:SS' )\n" +
                    "FROM portability_transactions pt,\n" +
                    "     portability_status_audit ps\n" +
                    "WHERE pt.id = ps.id_portability\n" +
                    "AND pt.msisdn= '"+msisdn+"'\n" +
                    "ORDER BY ps.create_date ASC";
            resultSet = stmt.executeQuery(query);// realiza la ejecución de query
            while (resultSet.next()) {
           /* System.out.println(resultSet.getString(1)+"----"+resultSet.getString(2)
                    +"----"+resultSet.getString(3)+"----"+resultSet.getString(4)
                    +"----"+resultSet.getString(5)+"----"+resultSet.getString(6)
                    +"----"+resultSet.getString(7)+"----"+resultSet.getString(8)
                    +"----"+resultSet.getString(9)+"----"+resultSet.getString(10)
                    +"----"+resultSet.getString(11)+"----"+resultSet.getString(12));*/
                System.out.println(resultSet.getString(3));
            }
        }
        //Close the result set, statement, and the connection
        finally{
            if(conn!=null) conn.close();
        }
    }

    public void portabilityNip(String url, String port, String service, String user, String password,
                                             String msisdn) throws SQLException {
        OracleDataSource ods = null;
        Connection conn= null;
        ResultSet resultSet = null;
        // Create DataSource and connect to the local database
        ods = new OracleDataSource();
        ods.setURL("jdbc:oracle:thin:@//"+url+":"+port+"/"+service);
        ods.setUser(user);
        ods.setPassword(password);
        conn = ods.getConnection();
        Statement stmt=conn.createStatement();
        try {
            String query = "update activator.portability_transactions set STATUS = 'PIN_REQUEST_ACEPTADO', NIP=null where msisdn in ('"+msisdn+"','','');COMMIT;";
            resultSet = stmt.executeQuery(query);// realiza la ejecución de query
            while (resultSet.next()) {
                System.out.println(resultSet.getString(3));
            }
        }
        //Close the result set, statement, and the connection
        finally{
            if(conn!=null) conn.close();
        }
    }

    public void selectNip(String url, String port, String service, String user, String password,
                               String nip) throws SQLException {
        OracleDataSource ods = null;
        Connection conn= null;
        ResultSet resultSet = null;
        // Create DataSource and connect to the local database
        ods = new OracleDataSource();
        ods.setURL("jdbc:oracle:thin:@//"+url+":"+port+"/"+service);
        ods.setUser(user);
        ods.setPassword(password);
        conn = ods.getConnection();
        Statement stmt=conn.createStatement();
        try {
            String query = "select * from activator.portability_transactions where NIP IN ('"+nip+"')";
            resultSet = stmt.executeQuery(query);// realiza la ejecución de query
            while (resultSet.next()) {
                System.out.println(resultSet.getString(3));
            }
        }
        //Close the result set, statement, and the connection
        finally{
            if(conn!=null) conn.close();
        }
    }

    public void portabilityRecept(String url, String port, String service, String user, String password,
                               String msisdn) throws SQLException {
        OracleDataSource ods = null;
        Connection conn= null;
        ResultSet resultSet = null;
        // Create DataSource and connect to the local database
        ods = new OracleDataSource();
        ods.setURL("jdbc:oracle:thin:@//"+url+":"+port+"/"+service);
        ods.setUser(user);
        ods.setPassword(password);
        conn = ods.getConnection();
        Statement stmt=conn.createStatement();
        try {
            String query = "update activator.portability_transactions set STATUS = 'PORT_IN_ACK_RECIBIDO' where msisdn in ('"+msisdn+"','','');COMMIT;";
            resultSet = stmt.executeQuery(query);// realiza la ejecución de query
            while (resultSet.next()) {
                System.out.println(resultSet.getString(3));
            }
        }
        //Close the result set, statement, and the connection
        finally{
            if(conn!=null) conn.close();
        }
    }

    public void portId(String url, String port, String service, String user, String password,
                                  String portId) throws SQLException {
        OracleDataSource ods = null;
        Connection conn= null;
        ResultSet resultSet = null;
        // Create DataSource and connect to the local database
        ods = new OracleDataSource();
        ods.setURL("jdbc:oracle:thin:@//"+url+":"+port+"/"+service);
        ods.setUser(user);
        ods.setPassword(password);
        conn = ods.getConnection();
        Statement stmt=conn.createStatement();
        try {
            String query = "select * from  activator.portability_transactions where PORT_ID ='"+portId+"';";
            resultSet = stmt.executeQuery(query);// realiza la ejecución de query
            while (resultSet.next()) {
                System.out.println(resultSet.getString(3));
            }
        }
        //Close the result set, statement, and the connection
        finally{
            if(conn!=null) conn.close();
        }
    }

    public void updatePortId(String url, String port, String service, String user, String password,
                       String portId, String msisdn) throws SQLException {
        OracleDataSource ods = null;
        Connection conn= null;
        ResultSet resultSet = null;
        // Create DataSource and connect to the local database
        ods = new OracleDataSource();
        ods.setURL("jdbc:oracle:thin:@//"+url+":"+port+"/"+service);
        ods.setUser(user);
        ods.setPassword(password);
        conn = ods.getConnection();
        Statement stmt=conn.createStatement();
        try {
            String query = "update activator.portability_transactions set PORT_ID ='"+portId+"' where msisdn ='"+msisdn+"'; COMMIT;";
            resultSet = stmt.executeQuery(query);// realiza la ejecución de query
            while (resultSet.next()) {
                System.out.println(resultSet.getString(3));
            }
        }
        //Close the result set, statement, and the connection
        finally{
            if(conn!=null) conn.close();
        }
    }

    public void windowPortability(String url, String port, String service, String user, String password,
                             String msisdn) throws SQLException {
        OracleDataSource ods = null;
        Connection conn= null;
        ResultSet resultSet = null;
        // Create DataSource and connect to the local database
        ods = new OracleDataSource();
        ods.setURL("jdbc:oracle:thin:@//"+url+":"+port+"/"+service);
        ods.setUser(user);
        ods.setPassword(password);
        conn = ods.getConnection();
        Statement stmt=conn.createStatement();
        try {
            String query = "select * from ACTIVATOR.PORTABILITY_WINDOW_PROCESS where MSISDN in ('"+msisdn+"');";
            resultSet = stmt.executeQuery(query);// realiza la ejecución de query
            while (resultSet.next()) {
                System.out.println(resultSet.getString(3));
            }
        }
        //Close the result set, statement, and the connection
        finally{
            if(conn!=null) conn.close();
        }
    }

    public void portabilityTransaction(String url, String port, String service, String user, String password,
                                  String msisdn) throws SQLException {
        OracleDataSource ods = null;
        Connection conn= null;
        ResultSet resultSet = null;
        // Create DataSource and connect to the local database
        ods = new OracleDataSource();
        ods.setURL("jdbc:oracle:thin:@//"+url+":"+port+"/"+service);
        ods.setUser(user);
        ods.setPassword(password);
        conn = ods.getConnection();
        Statement stmt=conn.createStatement();
        try {
            String query = "select * from  activator.portability_transactions where msisdn ='"+msisdn+"';";
            resultSet = stmt.executeQuery(query);// realiza la ejecución de query
            while (resultSet.next()) {
                System.out.println(resultSet.getString(3));
            }
        }
        //Close the result set, statement, and the connection
        finally{
            if(conn!=null) conn.close();
        }
    }
}