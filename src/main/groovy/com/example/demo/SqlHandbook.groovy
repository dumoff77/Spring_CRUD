package com.example.demo

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
class SqlHandbook {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    Integer id

    String name

    String number

    String description
}
