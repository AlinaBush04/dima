package Alina.BD.Controllers;

import Alina.BD.Models.Client;
import Alina.BD.Models.Employee;
import Alina.BD.Repositories.ClientRepository;
import Alina.BD.Repositories.EmployeeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("client")
public class ClientController {
    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;

    public ClientController(ClientRepository clientRepository, EmployeeRepository employeeRepository) {
        this.clientRepository = clientRepository;
        this.employeeRepository = employeeRepository;
    }
    @GetMapping
    public String all(Model model) {
        Iterable<Client> clients = clientRepository.findAll();
        Iterable<Employee> employees = employeeRepository.findAll();

        model.addAttribute("clients", clients);
        model.addAttribute("employees", employees);
        return "client";
    }
    @PostMapping("add")
    public String add(@RequestParam String full_name,
                      @RequestParam Employee employee) {
        Client client = new Client(full_name, employee);
        clientRepository.save(client);
        return "redirect:/client";
    }
    @PostMapping("delete/{id}")
    public String delete(@PathVariable(value = "id") Long id) {
        Client client = clientRepository.findById(id).orElseThrow();
        clientRepository.delete(client);
        return "redirect:/client";
    }
    @GetMapping("{id}")
    public String one(@PathVariable(value = "id") Long id,
                      Model model) {
        Client client = clientRepository.findById(id).orElseThrow();
        Iterable<Employee> employees = employeeRepository.findAll();

        model.addAttribute("client", client);
        model.addAttribute("employees", employees);
        return "client-edit";
    }
    @PostMapping("{id}")
    public String edit(@PathVariable(value = "id") Long id,
                       @RequestParam String full_name,
                       @RequestParam Employee employee) {
        Client client = clientRepository.findById(id).orElseThrow();
        client.setFull_name(full_name);
        client.setEmployee(employee);
        clientRepository.save(client);
        return "redirect:/client";
    }
}