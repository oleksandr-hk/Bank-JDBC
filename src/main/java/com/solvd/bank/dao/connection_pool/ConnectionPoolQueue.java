package com.solvd.bank.dao.connection_pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;

public class ConnectionPoolQueue {

    private String  driver;
    private String  url;
    private String  username;
    private String  password;

    private volatile int busyConnectionAmount;
    private int  connectionsSize;
    private ArrayBlockingQueue<Connection> connections;
    private static ConnectionPoolQueue Instance;


    //stupid lazy impl of singleton
    public  static ConnectionPoolQueue getInstance(){

        if (Instance == null){
            try {
                Instance = new ConnectionPoolQueue("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/bank(hometaskqaautomation)","root","1111",10);
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

}
