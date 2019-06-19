package com.example.demo

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query

interface HandbookRepository extends MongoRepository<Handbook, String>{

    @Query('')
    User findByNumberAndNameAndDescription(String number, String name, String description)
}