package com.akulyk.DAO.implementation;

import com.akulyk.DAO.DepartmentDAO;
import com.akulyk.model.DepartmentEntity;
import com.akulyk.persistant.ConnectionManager;
import com.akulyk.transformer.Transformer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoImpl implements DepartmentDAO {
    private static final String FIND_ALL = "SELECT * FROM department";
    private static final String DELETE = "DELETE FROM department WHERE dept_no=?";
    private static final String CREATE = "INSERT department (dept_no, dept_name, location) VALUES (?, ?, ?)";
    private static final String UPDATE = "UPDATE department SET dept_name=?, location=? WHERE dept_no=?";
    private static final String FIND_BY_ID = "SELECT * FROM department WHERE dept_no=?";



    @Override
    public List<DepartmentEntity> findAll() throws SQLException {
        List<DepartmentEntity> departments = new ArrayList<>();
        Connection connection = ConnectionManager.getConnection();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(FIND_ALL)) {
                while (resultSet.next()) {
                    departments.add(new DepartmentEntity(resultSet.getString("dept_no"), resultSet.getString("dept_name"), resultSet.getString("location")));
                }
            }
        }
        return departments;
    }

    @Override
    public DepartmentEntity findById(String id) throws SQLException {
        DepartmentEntity entity=null;
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(FIND_BY_ID)) {
            ps.setString(1,id);
            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    entity=new DepartmentEntity(resultSet.getString("dept_no"), resultSet.getString("dept_name"), resultSet.getString("location"));
                    break;
                }
            }
        }
        return entity;
    }

    @Override
    public int create(DepartmentEntity entity) throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        try (PreparedStatement ps = conn.prepareStatement(CREATE)) {
            ps.setString(1,entity.getDeptNo());
            ps.setString(2,entity.getDeptName());
            ps.setString(3,entity.getLocation());
            return ps.executeUpdate();
        }
    }

    @Override
    public int update(DepartmentEntity entity) throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        try (PreparedStatement ps = conn.prepareStatement(UPDATE)) {
            ps.setString(1,entity.getDeptName());
            ps.setString(2,entity.getLocation());
            ps.setString(3,entity.getDeptNo());
            return ps.executeUpdate();
        }
    }

    @Override
    public int delete(String id) throws SQLException {
        Connection conn = ConnectionManager.getConnection();
        try (PreparedStatement ps = conn.prepareStatement(DELETE)) {
            ps.setString(1,id);
            return ps.executeUpdate();
        }
    }

}
