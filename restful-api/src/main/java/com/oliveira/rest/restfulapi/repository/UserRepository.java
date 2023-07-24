package com.oliveira.rest.restfulapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oliveira.rest.restfulapi.beans.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
