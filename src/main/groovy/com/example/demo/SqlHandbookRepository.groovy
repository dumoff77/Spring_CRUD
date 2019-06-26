package com.example.demo

import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

interface SqlHandbookRepository extends CrudRepository<SqlHandbook, String> {
    String findByName(String name)
}