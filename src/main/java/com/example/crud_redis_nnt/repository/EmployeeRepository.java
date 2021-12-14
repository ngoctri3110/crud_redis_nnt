package com.example.crud_redis_nnt.repository;

import com.example.crud_redis_nnt.model.Employee;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public class EmployeeRepository {

    private static final String KEY = "SET_EMPLOYEE";

    private SetOperations setOperations;//crud hash
    private RedisTemplate redisTemplate;

    public EmployeeRepository(RedisTemplate redisTemplate) {
        this.setOperations = redisTemplate.opsForSet();
        this.redisTemplate = redisTemplate;

    }

    public void saveEmployee(Employee employee){
        setOperations.add(KEY, employee);
    }
    public Set<Employee> findAll(){
        return setOperations.members(KEY);
    }
    public Employee findById(Integer id){
        Set<Employee> employees = findAll();
        for (Employee employee : employees){
            if(employee.getId() == id){
                return employee;
            }
        }
        return null;
    }

    public void update(Employee employee){
        saveEmployee(employee);
    }
    public void delete(Integer id){
        setOperations.remove(KEY, findById(id));
    }
}
