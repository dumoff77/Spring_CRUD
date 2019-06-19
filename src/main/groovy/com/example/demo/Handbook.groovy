package com.example.demo

import groovy.transform.ToString
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document


@ToString(includeNames = true, includePackage = false)
@Document
class Handbook {
    @Id
    String id

    String number

    String name

    String description


}
