package com.hegazy.springboot.cruddemo.rest;


import com.hegazy.springboot.cruddemo.entity.Employee;
import com.hegazy.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeRestController {
   @Autowired
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
    }

    // expose "/employees" and return a list of employees
    @GetMapping("/list")
    public String listEmployees(Model model) {
        List<Employee> theEmployees =employeeService.findAll();
        model.addAttribute("employees", theEmployees);
        return  "employees/list-employees";
    }


    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model){
         //create model attribute to bind the form data
        Employee employee=new Employee();
        model.addAttribute("employee",employee);
        return "employees/employee-form";

    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee){
        //save tthe employee
        employeeService.save(employee);
        //use a redirect to preven duplicate submision
        return "redirect:/employees/list";
    }

   @GetMapping("showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId")int id,Model theModel){
        //get the employee from the service
        Employee employee=employeeService.findById(id);
       //set employee in the model to population the form
       theModel.addAttribute("employee",employee);
       //send over our form
       return "employees/employee-form";
   }
@GetMapping("/delete")
    public String delete(@RequestParam("employeeId")int id){
        employeeService.deleteById(id);
        return "redirect:/employees/list";
}

}














