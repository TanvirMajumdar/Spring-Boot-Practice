package com.example.cruddemo.dao;

import com.example.cruddemo.entity.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.*;
import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    private EntityManager entityManager;

    @Autowired
    public EmployeeDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {
        Session currentSession= entityManager.unwrap(Session.class);
        Query<Employee> query= currentSession.createQuery("from Employee", Employee.class);
        List<Employee> employees= query.getResultList();
        return employees;
    }

    @Override
    public Employee findById(int theId) {
        Session currentSession= entityManager.unwrap(Session.class);
        Employee employee=currentSession.get(Employee.class, theId);
        return employee;
    }

    @Override
    public void save(Employee employee) {
        Session currentSession= entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(employee);
    }

    @Override
    public void deleteById(int theId) {
        Session currentSession= entityManager.unwrap(Session.class);
        Query query=currentSession.createQuery("delete from Employee where id=:employeeId");
        query.setParameter("employeeId", theId);
        query.executeUpdate();
    }
}
