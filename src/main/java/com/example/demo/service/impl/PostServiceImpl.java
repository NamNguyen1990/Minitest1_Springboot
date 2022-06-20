package com.example.demo.service.impl;

import com.example.demo.model.Post;
import com.example.demo.repository.PostRepository;
import com.example.demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Override
    public Iterable<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public Post save(Post post) {
       return postRepository.save(post);
    }

//    Thông thường là override save như thế này. Muốn test PostMan thì phải chỉnh lại
//    @Override
//    public void save(Post post) {
//        postRepository.save(post);
//
//    }

    @Override
    public void remove(Long id) {
        postRepository.deleteById(id);

    }


    @Override
    public Iterable<Post> findAllByTitleContaining(String title) {
        return postRepository.findAllByTitleContaining(title);
    }

    @Override
    public Iterable<Post> findTop4New() {
        return postRepository.findTop4New();
    }

    @Override
    public Iterable<Post> findAllByLikesAsc() {
        return postRepository.findAllByLikesAsc();
    }
}
