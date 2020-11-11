package com.example.demo.repositories;

import com.example.demo.models.StudentDTO;
import com.example.demo.util.DatabaseConnectionManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepositoryImpl implements IStudentRepository {
    private Connection conn;
    private static final String CREATE_USER_SQL = "INSERT INTO student" + "(student_id, first_name, last_name, enroll_date, cpr) VALUES" + "(?,?,?,?,?);";
    private static final String DELETE_USER_SQL = "DELETE FROM student WHERE student_id =?";
    private static final String UPDATE_USER_SQL = "UPDATE student SET first_name =?, last_name =? WHERE student_id=?;";



    public StudentRepositoryImpl() {
        this.conn = DatabaseConnectionManager.getDatabaseConnection();

    }

    @Override
    public void create(StudentDTO student) {
        try {


            PreparedStatement prep = conn.prepareStatement(CREATE_USER_SQL);
            {
                prep.setInt(1, student.getId());
                prep.setString(2, student.getFirstName());
                prep.setString(3, student.getLastName());
                prep.setDate(4, new java.sql.Date(student.getEnrollmentDate().getTime()));
                prep.setString(5, student.getCpr());

                prep.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public StudentDTO read(int id) {
        StudentDTO studentToReturn = new StudentDTO();
        try {
            PreparedStatement getSingleStudent = conn.prepareStatement("SELECT * FROM student WHERE student_id=?");
            getSingleStudent.setInt(1, id);
            ResultSet rs = getSingleStudent.executeQuery();
            while (rs.next()) {
                studentToReturn = new StudentDTO();

                studentToReturn.setCpr(String.valueOf(rs.getInt(1)));
                studentToReturn.setFirstName(rs.getString(2));
                studentToReturn.setLastName(rs.getString(3));
                studentToReturn.setEnrollmentDate(rs.getDate(4));
                studentToReturn.setCpr(rs.getString(5));
                studentToReturn.setId(rs.getInt("student_id"));
            }
        } catch (SQLException s) {
            s.printStackTrace();
        }
        return studentToReturn;
    }

    @Override
    public List<StudentDTO> readAll() {
        List<StudentDTO> allStudents = new ArrayList<StudentDTO>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM student");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                StudentDTO tempStudent = new StudentDTO();
                tempStudent.setCpr(String.valueOf(rs.getInt(1)));
                tempStudent.setFirstName(rs.getString(2));
                tempStudent.setLastName(rs.getString(3));
                tempStudent.setEnrollmentDate(rs.getDate(4));
                tempStudent.setCpr(rs.getString(5));
                tempStudent.setId(rs.getInt("student_id"));
                allStudents.add(tempStudent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allStudents;
    }

    @Override
    public void update(StudentDTO student) {
        try {
            ;
            PreparedStatement prep = conn.prepareStatement(UPDATE_USER_SQL);

            prep.setString(1, student.getFirstName());
            prep.setString(2, student.getLastName());
            prep.setInt(3, student.getId());

            prep.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Test");

            e.printStackTrace();
        }
    }


    @Override
    public void delete(int id) {

        try {
            PreparedStatement prep = conn.prepareStatement(DELETE_USER_SQL);

            prep.setInt(1, id);
            prep.executeUpdate();




            {


            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }
}

