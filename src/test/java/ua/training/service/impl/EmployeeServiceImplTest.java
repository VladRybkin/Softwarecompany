package ua.training.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.training.model.entity.Employee;
import ua.training.service.EmployeeService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {


    @Mock
    private EmployeeService employeeServiceMock;

    private EmployeeService employeeServiceTest;

    @BeforeEach
    void setup() {
        employeeServiceTest = new EmployeeServiceImpl();
    }


    @Test
    void shouldReturnEmployeeList() {
        List<Employee> employeesList = new ArrayList<>();
        lenient().when(employeeServiceMock.getAllEmployees()).thenReturn(employeesList);
    }

    @Test
    void createEmployee() throws Exception {
        employeeServiceTest.createEmployee("vlad");
        assertEquals(employeeServiceTest.getAllEmployees().size(), 1);


    }

    @Test
    void ShouldThrowEmployeeDoesntExistException() throws Exception {
        Employee employee = new Employee();
        employee.setId(1);
        assertThrows(Exception.class, () -> {
            employeeServiceTest.updateEmployee(employee);
        });
    }

    @Test
    void ShouldNotThrowEmployeeAlreadyExistException() throws Exception {
        Employee employee = new Employee();
        employee.setId(1);
        assertDoesNotThrow(() -> {
            employeeServiceTest.createEmployee("Vlad");
        });
    }

    @Test
    void ShouldNotThrowEmployeeAlreadyDoesNotExistException() throws Exception {
        employeeServiceTest.createEmployee("vlad");
        Employee employee = new Employee();
        employee.setId(1);
        assertDoesNotThrow(() -> {
            employeeServiceTest.updateEmployee(employee);
        });
    }

}