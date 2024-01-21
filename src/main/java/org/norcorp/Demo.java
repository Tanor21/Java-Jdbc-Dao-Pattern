package org.norcorp;

import java.sql.DriverManager;
import java.sql.SQLException;

class Pqr {
    static {
        System.out.println("In Static Block");
    }

    // instance
    {
        System.out.println("In Instance");
    }
}

// Class.forName()
public class Demo {
    public static void main(String[] args) throws Exception {
        // The static block will be call when you load the className.
        Class.forName("com.mysql.cj.jdbc.Driver");


        // The static block and the instantiation class will be called when you instantiated the class.
        //Class.forName("org.norcorp.Pqr").newInstance();

        //DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
    }
}
