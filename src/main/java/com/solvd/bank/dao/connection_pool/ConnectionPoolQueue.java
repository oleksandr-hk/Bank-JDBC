package com.solvd.bank.dao.connection_pool;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;

public class ConnectionPoolQueue {

    private static String  driver;
    private static String  url;
    private static String  username;
    private static String  password;

    private volatile int busyConnectionAmount;
    private static int  connectionsSize;
    private ArrayBlockingQueue<Connection> connections;
    private static ConnectionPoolQueue Instance;


    //stupid lazy impl of singleton
    public  static ConnectionPoolQueue getInstance(){

        if (Instance == null) {

            try {

                List<String> properties = ConnectionPoolQueue.getProperties();

                if (!(properties.size() == 0)) {

                     driver = properties.get(0);
                     url    = properties.get(1);
                     username  = properties.get(2);
                     password  = properties.get(3);
                     connectionsSize = Integer.parseInt(properties.get(4));
                }

                Instance = new ConnectionPoolQueue(driver,url,username,password,connectionsSize);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return Instance;
    }

    private  ConnectionPoolQueue(String driver,
                               String url,
                               String username,
                               String password,
                               int connectionsSize
                               ) throws SQLException {
        this.driver = driver;
        this.url = url;
        this.username = username;
        this.password = password;

        this.connectionsSize = connectionsSize;
        this.connections = new ArrayBlockingQueue<>(connectionsSize);
        this.busyConnectionAmount = 0;


        for (int i = 0; i < 3; i++){
            Connection conn = DriverManager.getConnection(url,username,password);
            connections.add(conn);
        }

    }

    public Connection getConnection() {

       try {
           if (busyConnectionAmount <= connectionsSize) {
               if (connections.size() == 0) {
                   busyConnectionAmount++;
                   connections.add(DriverManager.getConnection(url, username, password));
                   return connections.take();

               } else if (connections.size() > 0) {
                   busyConnectionAmount++;
                   return connections.take();
               }
           } else {

               return connections.take();

           }
       }catch (InterruptedException ex){

       }
       catch (SQLException ex){

       }
        return null;
    }

    public boolean releaseConnection(Connection con)
    {

            if (connections.size() < connectionsSize) {
                busyConnectionAmount--;
                return connections.add(con);
            } else {
                return false;
            }

    }

    public boolean closeAllConnections() throws SQLException {

        if (connections.size() > 0){
          for (Connection connection:connections){
                connection.close();
            }
            return true;
        } else  return  false;
    }

    public static List<String> getProperties(){

        //read from file .properties
        FileInputStream fis;
        Properties property = new Properties();

        try {

            fis = new FileInputStream("src/main/resources/db.properties");
            property.load(fis);

            List<String> values = new ArrayList<>();

            values.add( property.getProperty("db.driver"));
            values.add(property.getProperty("db.url"));
            values.add(property.getProperty("db.login"));
            values.add(property.getProperty("db.password"));
            values.add(property.getProperty("db.connectionAmount"));



            return  values;


        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

}
