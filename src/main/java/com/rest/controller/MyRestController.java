package com.rest.controller;

import com.rest.entity.Employee;
import com.rest.exception.IncorrectData;
import com.rest.exception.NoSuchEmployeeException;
import com.rest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRestController {
        @Autowired
        EmployeeService employee;

        @GetMapping("/employees")
        public List<Employee> getAll() {
              return   employee.findAll();
        }

        @GetMapping("/employees/{id}")
        public Employee getEmp(@PathVariable int id) {

              Employee emp = employee.getEmp(id);
              if (emp == null) {
                  throw new NoSuchEmployeeException("employee with id" + id + " no exist");
              }
              return emp;
        }

        @PostMapping("/employees")
    public Employee postEmp(@RequestBody Employee empl) {
       employee.save(empl);
       return empl;
        }

    @PutMapping("/employees")
    public Employee putEmp(@RequestBody Employee empl) {
        employee.save(empl);
        return empl;
    }

    @DeleteMapping("/employees/{id}")
    public String delete(@PathVariable int id) {
            if (employee.getEmp(id) == null) {
                throw new NoSuchEmployeeException("There no employee with id = " + id);
            }
            employee.delete(id);
            return "Employee with id = " + id + " was deleted";
        }
}
