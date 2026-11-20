package com.fptaptech.company_webservice.employee;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployeePageController {

    @GetMapping("/")
    public String employeesPage() {
        return "employees";
    }
}
