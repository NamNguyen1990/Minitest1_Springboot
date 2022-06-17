package com.example.demo.repository;

import com.example.demo.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Long> {
    Iterable<Post>findAllByTitleContaining(String title);
    @Query(value = "select * from Post order by likes desc limit 4 ", nativeQuery = true)
    Iterable<Post> findTop4New();

    @Query(value = "select * from Post order by likes asc ", nativeQuery = true)
    Iterable<Post> findAllByLikesAsc();
}
