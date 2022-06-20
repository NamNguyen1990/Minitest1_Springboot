package com.example.demo.service;

import java.util.Optional;

public interface IGeneralService<T> { // interface sử dụng generic mô tả các phương chung mà tất cả các service cần có
    Iterable<T> findAll();

    Optional<T> findById(Long id);

    T save(T t);

//    void save(T t);   Bình thường là save để ntn.Muốn test PostMan thì phải chỉnh lại save như trên

    void remove(Long id);
}
