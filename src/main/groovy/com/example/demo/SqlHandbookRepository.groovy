package com.example.demo

import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param;





interface SqlHandbookRepository extends CrudRepository<SqlHandbook, Integer> {

    SqlHandbook findByName(String name)

    int deleteByName(String name)

    @Modifying
    @Query("update SqlHandbook u set u.name = ?1, u.number = ?2, u.description = ?3 where u.name = ?4")
    int setHandbook(String name, String number, String description, String oldnake)

}