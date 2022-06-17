package com.example.demo.controller;


import com.example.demo.model.Post;
import com.example.demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/posts")
public class PostController {
    @Autowired
    PostService postService;

    @GetMapping
    public ModelAndView show() {
        ModelAndView modelAndView = new ModelAndView("/post/list");
        modelAndView.addObject("posts", postService.findAll());
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/post/create");
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView save(Post post) {
        postService.save(post);
        ModelAndView modelAndView = new ModelAndView("redirect:/posts");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/post/edit");
        Post post = postService.findById(id).get();
        modelAndView.addObject("pro", post);
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView upDate(@PathVariable Long id, Post post) {
        ModelAndView modelAndView = new ModelAndView("redirect:/posts");
        postService.save(post);
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/post/delete");
        Post post = postService.findById(id).get();
        modelAndView.addObject("pro", post);
        return modelAndView;
    }

    @PostMapping("/delete/{id}")
    public ModelAndView delete (@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/posts");
        postService.remove(id);
        return modelAndView;
    }

    @GetMapping("/find")
    public ModelAndView find(@RequestParam String search) {
        ModelAndView modelAndView = new ModelAndView("/post/list");
        Iterable<Post> post = postService.findAllByTitleContaining(search);
        modelAndView.addObject("posts", post);
        return modelAndView;
    }

    @GetMapping("/findtop4")
    public ModelAndView findTop4 () {
        ModelAndView modelAndView = new ModelAndView("/post/list");
        Iterable<Post> post = postService.findTop4New();
        modelAndView.addObject("posts", post);
        return modelAndView;
    }

    @GetMapping("/findlike")
    public ModelAndView findLike () {
        ModelAndView modelAndView = new ModelAndView("/post/list");
        Iterable<Post> post = postService.findAllByLikesAsc();
        modelAndView.addObject("posts", post);
        return modelAndView;
    }
}
