package Alina.BD.Controllers;

import Alina.BD.Models.Client;
import Alina.BD.Models.Commit;
import Alina.BD.Models.Deal;
import Alina.BD.Models.Employee;
import Alina.BD.Repositories.ClientRepository;
import Alina.BD.Repositories.CommitRepository;
import Alina.BD.Repositories.DealRepository;
import Alina.BD.Repositories.EmployeeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("commit")
public class CommitController {
    private final CommitRepository commitRepository;
    private final DealRepository dealRepository;
    private final EmployeeRepository employeeRepository;
    private final ClientRepository clientRepository;

    public CommitController(CommitRepository commitRepository, DealRepository dealRepository, EmployeeRepository employeeRepository, ClientRepository clientRepository) {
        this.commitRepository = commitRepository;
        this.dealRepository = dealRepository;
        this.employeeRepository = employeeRepository;
        this.clientRepository = clientRepository;
    }
    @GetMapping
    public String all(Model model) {
        Iterable<Commit> commits = commitRepository.findAll();
        Iterable<Deal> deals = dealRepository.findAll();
        Iterable<Employee> employees = employeeRepository.findAll();
        Iterable<Client> clients = clientRepository.findAll();

        model.addAttribute("commits", commits);
        model.addAttribute("deals", deals);
        model.addAttribute("employees", employees);
        model.addAttribute("clients", clients);

        return "commit";
    }
    @PostMapping("add")
    public String add(@RequestParam Integer bonus,
                      @RequestParam Deal deal,
                      @RequestParam Employee employee,
                      @RequestParam Client client) {
        Commit commit = new Commit(bonus, deal, employee, client);
        commitRepository.save(commit);
        return "redirect:/commit";
    }
    @PostMapping("delete/{id}")
    public String delete(@PathVariable(value = "id") Long id) {
        Commit commit = commitRepository.findById(id).orElseThrow();
        commitRepository.delete(commit);
        return "redirect:/commit";
    }
    @GetMapping("{id}")
    public String one(@PathVariable(value = "id") Long id,
                      Model model) {
        Commit commit = commitRepository.findById(id).orElseThrow();
        Iterable<Deal> deals = dealRepository.findAll();
        Iterable<Employee> employees = employeeRepository.findAll();
        Iterable<Client> clients = clientRepository.findAll();

        model.addAttribute("commit", commit);
        model.addAttribute("deals", deals);
        model.addAttribute("employees", employees);
        model.addAttribute("clients", clients);

        return "commit-edit";
    }
    @PostMapping("{id}")
    public String edit(@PathVariable(value = "id") Long id,
                       @RequestParam Integer bonus,
                       @RequestParam Deal deal,
                       @RequestParam Employee employee,
                       @RequestParam Client client) {
        Commit commit = commitRepository.findById(id).orElseThrow();
        commit.setBonus(bonus);
        commit.setDeal(deal);
        commit.setEmployee(employee);
        commit.setClient(client);
        commitRepository.save(commit);
        return "redirect:/commit";
    }
}