package com.example.demo.service;

import com.example.demo.model.Post;

public interface PostService extends IGeneralService<Post>{
    Iterable<Post>findAllByTitleContaining(String title);

    Iterable<Post> findTop4New();

    Iterable<Post> findAllByLikesAsc();


}
