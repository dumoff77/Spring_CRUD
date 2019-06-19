package com.example.demo

import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository  extends MongoRepository<User, String>{

    User findByLoginAndPassword(String login, String password)
}