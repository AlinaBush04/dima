package Alina.BD.Controllers;

import Alina.BD.Models.Employee;
import Alina.BD.Models.Post;
import Alina.BD.Repositories.EmployeeRepository;
import Alina.BD.Repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("employee")
public class EmployeeController {
    private final EmployeeRepository employeeRepository;
    private final PostRepository postRepository;

    public EmployeeController(EmployeeRepository employeeRepository, PostRepository postRepository) {
        this.employeeRepository = employeeRepository;
        this.postRepository = postRepository;
    }
    @GetMapping
    public String all(Model model) {
        Iterable<Employee> employees = employeeRepository.findAll();
        Iterable<Post> posts = postRepository.findAll();

        model.addAttribute("employees", employees);
        model.addAttribute("posts", posts);
        return "employee";
    }
    @PostMapping("add")
    public String add(@RequestParam String full_name,
                      @RequestParam Post post) {
        Employee employee = new Employee(full_name, post);
        employeeRepository.save(employee);
        return "redirect:/employee";
    }
    @PostMapping("delete/{id}")
    public String delete(@PathVariable(value = "id") Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow();
        employeeRepository.delete(employee);
        return "redirect:/employee";
    }
    @GetMapping("{id}")
    public String one(@PathVariable(value = "id") Long id,
                      Model model) {
        Employee employee = employeeRepository.findById(id).orElseThrow();
        Iterable<Post> posts = postRepository.findAll();

        model.addAttribute("employee", employee);
        model.addAttribute("posts", posts);
        return "employee-edit";
    }
    @PostMapping("{id}")
    public String edit(@PathVariable(value = "id") Long id,
                       @RequestParam String full_name,
                       @RequestParam Post post) {
        Employee employee = employeeRepository.findById(id).orElseThrow();
        employee.setFull_name(full_name);
        employee.setPost(post);
        employeeRepository.save(employee);
        return "redirect:/employee";
    }
}
