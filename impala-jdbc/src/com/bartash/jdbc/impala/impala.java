
package com.bartash.jdbc.impala;

import jdk.nashorn.api.scripting.ScriptObjectMirror;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class impala {

  public static void main(String[] args) {
    System.out.println("hello world");


    Driver d = new com.cloudera.impala.jdbc.Driver();

    String driverName = "com.cloudera.impala.jdbc.Driver";
    try {
      // Register driver and create driver instance
      Class.forName(driverName);
    } catch (ClassNotFoundException ex) {
      ex.printStackTrace();
    }
    System.out.println("before trying to connect");
    // jdbc:impala://coordinator-asherman-run-run-run.env-sxmzbh.dwx-dev-public.cloudera.com:443/default;AuthMech=3;transportMode=http;httpPath=cliservice;ssl=1
    try (Connection con = DriverManager
        .getConnection(
            "jdbc:impala://localhost:28000/default;transportMode=http;httpPath=cliservice;AuthMech=0")) {
      System.out.println("connected");
      // create statement
      Statement stmt = con.createStatement();
      // execute statement
      ResultSet resultSet = stmt.executeQuery("show databases");
      while (resultSet.next()) {
        String name = resultSet.getString(1);
        System.out.println("db = " + name);
      }
    } catch (Exception e) {
      System.out.println("caught " + e);
      e.printStackTrace();
    }
  }
}