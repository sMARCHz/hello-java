package com.example.hellorest.employee;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private Random random;

    @Mock
    private EmployeeRepository repository;

    @Test
    public void foundEmployeeID1() {
        when(random.nextInt(10)).thenReturn(10);
        Employee mock = new Employee(1, "Service name", "Service lname");
        when(repository.findById(1)).thenReturn(
                Optional.of(mock)
        );

        EmployeeService service = new EmployeeService();
        service.setRandom(random);
        service.setRepository(repository);
        EmployeeResponse result = service.process(1);
        assertEquals(1, result.getId());
        assertEquals("Service name10", result.getFname());
        assertEquals("Service lname", result.getLname());
    }

    @Test
    public void employee100NotFound() {
        when(random.nextInt(10)).thenReturn(5);
        when(repository.findById(100)).thenReturn(
                Optional.empty()
        );

        EmployeeService service = new EmployeeService();
        service.setRandom(random);
        service.setRepository(repository);

        EmployeeResponse result = service.process(100);
        assertEquals(0, result.getId());
        assertNull(result.getFname());
        assertNull(result.getLname());
    }
}