package com.spring.mvc.etc;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    @Test
    void ttt(){
        Employee e = Employee.builder()
                .position("대리")
                .empName("뚜뚜루")
                .hireDate(LocalDate.of(2022, 4, 2))
                .empId(553)
                .build();

        System.out.println("e = " + e);

        Actor a = Actor.builder()
                .actorName("또또롯")
                .actorAge(22)
                .hasPhone(true)
                .build();
        System.out.println("a = " + a);
    }
}