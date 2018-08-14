package com.example.demo;

import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

@Controller

public class HomeController{
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @RequestMapping("/")
    public String listEmployees(Model model){
        model.addAttribute("employees", employeeRepository.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String employeeForm(Model model){
        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("employee", new Employee());
        return "employeeform";
    }

    @PostMapping("/process")
    public String processForm(@Valid Employee employee, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("department", departmentRepository.findAll());
            return "employeeform";
        }
        employeeRepository.save(employee);
        return "redirect:/";
    }
    @GetMapping("/addDepartment")
    public String departmentForm(Model model){
        model.addAttribute("department", new Department());
        return "departmentform";
    }
    @PostMapping("/processDepartment")
    public String processDepartment(@Valid Department department, BindingResult result){
        if(result.hasErrors()){
            return "departmentform";
        }
        departmentRepository.save(department);
        return "redirect:/";
    }

    @RequestMapping("/detail/{id}")
    public String showEmployee(@PathVariable("id") long id, Model model){
        model.addAttribute("employee", employeeRepository.findById(id).get());
        return "show";
    }
    @RequestMapping("/update/{id}")
    public String updateEmployee(@PathVariable("id") long id, Model model) {
        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("employee", employeeRepository.findById(id));
        return "redirect:/";
    }
        @RequestMapping("/search")
        public String processForm(@RequestParam("search") String firstname,Model model) {

            model.addAttribute("employees", employeeRepository.findByFirstName(firstname));
            return "list";
        }

//            @PostMapping("/addLogo")
//            public String processEmployee(@ModelAttribute Employee employee,
//                    @RequestParam("file") MultipartFile file) {
//                if (file.isEmpty()){
//                    return "redirect:/addLogo";
//                }
//                try {
//                    Map uploadResult = cloudc.upload(file.getBytes(),
//                            ObjectUtils.asMap("resourcetype", "auto"));
//                    employee.setHeadshot(uploadResult.get("url").toString());
//                    employeeRepository.save(employee);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    return "redirect:/addLogo";
//                }
//                return "redirect:/";
//
//        }
}



