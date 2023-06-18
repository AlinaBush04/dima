package Alina.BD.Controllers;

import Alina.BD.Models.Post;
import Alina.BD.Repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("post")
public class PostController {
    private final PostRepository postRepository;
    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    @GetMapping
    public String all(Model model) {
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "post";
    }
    @PostMapping("add")
    public String add(@RequestParam String name,
                      @RequestParam Integer bet) {
        Post post = new Post(name, bet);
        postRepository.save(post);
        return "redirect:/post";
    }
    @PostMapping("delete/{id}")
    public String delete(@PathVariable(value = "id") Long id) {
        Post post  = postRepository.findById(id).orElseThrow();
        postRepository.delete(post);
        return "redirect:/post";
    }
    @GetMapping("{id}")
    public String one(@PathVariable(value = "id") Long id,
                      Model model) {
        Post post = postRepository.findById(id).orElseThrow();
        model.addAttribute("post", post);
        return "post-edit";
    }
    @PostMapping("{id}")
    public String edit(@PathVariable(value = "id") Long id,
                       @RequestParam String name,
                       @RequestParam Integer bet) {
        Post post = postRepository.findById(id).orElseThrow();
        post.setName(name);
        post.setBet(bet);
        postRepository.save(post);
        return "redirect:/post";
    }
}
