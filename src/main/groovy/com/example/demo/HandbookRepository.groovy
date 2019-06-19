package com.example.demo

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query

interface HandbookRepository extends MongoRepository<Handbook, String>{

    Handbook findByName(String name)
    Handbook removeByName(String name)


}