package com.example.demo.models;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.*;
import java.util.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentDAO {
    private Connection conn;

    public StudentDAO(Connection conn){
        this.conn = conn;
    }

    public StudentDTO getSingleStudentById(int id){

        StudentDTO objectToReturn = new StudentDTO();
        try {
            PreparedStatement getSingleStatement = conn.prepareStatement("select * from student where student_id=?");
            getSingleStatement.setInt(1,id);
            ResultSet singleStudent = getSingleStatement.executeQuery();

            while(singleStudent.next()){
                objectToReturn.setId(singleStudent.getInt("student_id"));
                objectToReturn.setFirstName(singleStudent.getString("first_name"));
                objectToReturn.setLastName(singleStudent.getString("lastName"));
                objectToReturn.setEnrollmentDate(singleStudent.getDate("enroll_date"));
                objectToReturn.setCpr(singleStudent.getString("cpr"));
            }

        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return objectToReturn;
    }

    /*
    public ArrayList<DepartmentDTO> getAllDepartments(){
        ArrayList<DepartmentDTO>allDepartmentsList = new ArrayList<DepartmentDTO>();

        try {
            Statement getAllDepartments = conn.createStatement();
            ResultSet allDepartments = getAllDepartments.executeQuery("select * from dept");
            while(allDepartments.next()){
                DepartmentDTO tmp = new DepartmentDTO();
                tmp.setDeptno(allDepartments.getInt(1));
                tmp.setDname(allDepartments.getString(2));
                tmp.setLoc(allDepartments.getString(3));
                allDepartmentsList.add(tmp);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return allDepartmentsList;
    }
    */
}