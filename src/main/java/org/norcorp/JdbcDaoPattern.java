package org.norcorp;

import java.sql.*;

// Class
class Student {
    int rollno;
    String sname;

}

// DAO
class StudentDAO {
    Connection con = null;

    // Method For Connection
    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/aliens", "root", "root");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Method To Get An Unique Student
    public Student getStudent(int rollno) {
        try {
            String query = "select sname from student where rollno="+rollno;
            Student s = new Student();
            s.rollno = rollno;

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            String name = rs.getString(1);
            s.sname = name;
            return s;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    // Method to Add Student
    public Student addStudent(Student s) {
        String query = "insert into Student values (?, ?)";
        PreparedStatement pst;
        try {
            pst = con.prepareStatement(query);
            pst.setInt(1, s.rollno);
            pst.setString(2, s.sname);
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    // Method to Remove Student
    public Student removeStudent(int rollno) {
        try {
            String query = "SELECT sname FROM student WHERE rollno=" + rollno;
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            if (rs.next()) {
                String name = rs.getString("sname");

                // Delete the student from the database
                String deleteQuery = "DELETE FROM student WHERE rollno=" + rollno;
                int rowsAffected = st.executeUpdate(deleteQuery);

                if (rowsAffected > 0) {
                    // If the student was successfully deleted, return the student object
                    Student removedStudent = new Student();
                    removedStudent.rollno = rollno;
                    removedStudent.sname = name;
                    return removedStudent;
                } else {
                    System.out.println("Failed to remove student with roll number " + rollno);
                }
            } else {
                System.out.println("Student with roll number " + rollno + " not found");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

}

// App
public class JdbcDaoPattern {
    public static void main(String[] args) {
        // Instantiate DAO
        StudentDAO dao = new StudentDAO();

        // Get An Unique Student Name
//        Student s1 = dao.getStudent(2);
//        System.out.println(s1.sname);

        // Add A Student
//        Student s2 = new Student();
//        s2.rollno = 5;
//        s2.sname = "Ababacar";
//        dao.connect();
//        dao.addStudent(s2);

        // Remove A Student
        dao.connect();
        dao.removeStudent(5);

    }
}
