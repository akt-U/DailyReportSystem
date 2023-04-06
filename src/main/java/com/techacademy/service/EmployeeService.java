package com.techacademy.service;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techacademy.entity.Authentication;
import com.techacademy.entity.Employee;
import com.techacademy.repository.EmployeeRepository;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;


        public EmployeeService(EmployeeRepository repository) {
            this.employeeRepository = repository;
        }

        /** 全件を検索して返す */
        public List<Employee> getEmployeeList() {
            // リポジトリのfindAllメソッドを呼び出す
            return employeeRepository.findAll();
        }

        /** 従業員を1件検索して返す */
        public Employee getEmployee(Integer id) {
            return employeeRepository.findById(id).get();
        }

        /** 従業員の登録を行う */
        @Transactional
        public Employee saveEmployee(Employee employee) {

            Authentication authentication = employee.getAuthentication();
            employee.setDeleteFlag(0);
            employee.setCreatedAt(new Date(new java.util.Date().getTime()));
            employee.setUpdatedAt(new Date(new java.util.Date().getTime()));
            authentication.setEmployee(employee);

            return employeeRepository.save(employee);
        }

        /** 従業員の更新を行う */
        @Transactional
        public Employee updateEmployee(Employee employee) {
            //employeeRepository.deleteById(employee.getId());
            Employee upEmployee = getEmployee(employee.getId());
            Authentication authentication = employee.getAuthentication();

            employee.setCreatedAt(upEmployee.getCreatedAt());
            employee.setUpdatedAt(new Date(new java.util.Date().getTime()));

            authentication.setEmployee(employee);


            return employeeRepository.save(employee);
        }



        // ----- 追加:ここから -----
        /** 従業員の削除を行う */
        @Transactional
        public Employee deleteEmployee(Employee employee) {
            employee.setDeleteFlag(1);
            employee.setUpdatedAt(new Date(new java.util.Date().getTime()));

            return employeeRepository.save(employee);
        }
        }


/*//実験↓
 * //upAuthentication.setEmployee(authentication.getEmployee());
 * //upEmployee.setId(employee.getId());
            //upEmployee.setName(employee.getName());
 upAuthentication.setRole(authentication.getRole());
            //upAuthentication.setCode(authentication.getCode());
 *
 *
 * Authentication upAuthentication = upEmployee.getAuthentication();
            Authentication authentication = employee.getAuthentication();
Employee newEmployee = new Employee();
newEmployee.setId(upEmployee.getId());
newEmployee.setName(employee.getName());
newEmployee.setCreatedAt(new Date(new java.util.Date().getTime()));
newEmployee.setUpdatedAt(new Date(new java.util.Date().getTime()));

Authentication newAuthentication = new Authentication();
newAuthentication.setCode(authentication.getCode());
newAuthentication.setPassword(upAuthentication.getPassword());
newAuthentication.setRole(authentication.getRole());
newAuthentication.setEmployee(employee);

newEmployee.setAuthentication(newAuthentication);
newAuthentication.setEmployee(newEmployee);

employeeRepository.save(newEmployee);
employeeRepository.deleteById(upEmployee.getId());*/
//実験ここまで