package com.rest.dao;

import com.rest.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public class EmployeeDAOEmplementation implements EmployeeDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Employee> findAll() {
        Session session = sessionFactory.getCurrentSession();
        List<Employee> list = session.createQuery("from Employee ", Employee.class)
                .getResultList();
        return list;
    }

    @Override
    public void save(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(employee);
    }

    @Override
    public Employee getEmp(int id) {
        Session session = sessionFactory.getCurrentSession();
        return  session.get(Employee.class, id);
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.createQuery("DELETE from Employee WHERE id = :idE")
                .setParameter("idE", id)
                .executeUpdate();
    }
}
