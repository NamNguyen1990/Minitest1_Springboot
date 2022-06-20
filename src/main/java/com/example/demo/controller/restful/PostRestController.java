package com.example.demo.controller.restful;


import com.example.demo.model.Post;
import com.example.demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostRestController {


    @Autowired
    private PostService postService;

    @GetMapping
    public ResponseEntity<Iterable<Post>> findAllCustomer() {
        List<Post> posts = (List<Post>) postService.findAll();
        if (posts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(posts, HttpStatus.OK);
        }

    @GetMapping("/{id}")
    public ResponseEntity<Post> findCustomerById(@PathVariable Long id) {
        Optional<Post> postOptional = postService.findById(id);
        if (!postOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(postOptional.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Post> savePost (@RequestBody Post post) {
        return new ResponseEntity<>(postService.save(post), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost (@PathVariable Long id, @RequestBody Post post) {
        Optional<Post> postOptional = postService.findById(id);
        if (!postOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        post.setId(postOptional.get().getId());
        return new ResponseEntity<>(postService.save(post), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Post> deleteCustomer(@PathVariable Long id) {
        Optional<Post> customerOptional = postService.findById(id);
        if (!customerOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        postService.remove(id);
        return new ResponseEntity<>(customerOptional.get(), HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/searchtitle")
    public ResponseEntity<List<Post>> searchName (@RequestParam String title) {
        List<Post> posts = (List<Post>) postService.findAllByTitleContaining(title);
        if (posts == null) {
            return new ResponseEntity<List<Post>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
    }
}
