package com.oliveira.rest.restfulapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oliveira.rest.restfulapi.beans.Post;

public interface PostRepository extends JpaRepository<Post, Integer>{

}
