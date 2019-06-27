package com.example.demo

import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

import javax.transaction.Transactional

//import groovy.json.JsonSlurper

@Slf4j
@RestController
class HomeController {

    @Autowired
    SqlHandbookRepository SqlHandbookRepository

    @Autowired
    UserRepository userRepository

    @Autowired
    HandbookRepository handbookRepository

    @GetMapping('/home')
    def home() {
        'ok'
    }

    @GetMapping('api/handbook')
    def handbook() {
        SqlHandbookRepository.findAll()
    }

    @GetMapping('api/handbook/{name}')
    def find_handbook(@PathVariable String name) {
        SqlHandbookRepository.findByName(name)
    }

    @Transactional
    @DeleteMapping('api/handbook/{name}')
    def delete(@PathVariable String name) {
        try {
            log.debug("Start delete handbook by name $name")
            SqlHandbookRepository.deleteByName(name)
            log.info("Deleted handbook with name $name")
            return 'ok'
        } catch (Exception e) {
            log.error(e.message, e)
        }
    }

    @PostMapping("api/handbook")
    def insert(@RequestBody SqlHandbook handbook) {
        try {
            SqlHandbookRepository.save(handbook)
            return 'ok'
        } catch (e) {
            log.error(e.message, e)
            return 'ErrorPage'
        }
        return 'ok'
    }

    @Transactional
    @PutMapping("api/handbook/{name}")
    def update(@PathVariable String name, @RequestBody Handbook handbook) {
        log.debug("Start update handbook by name $name")
        try {
            SqlHandbookRepository.setHandbook(handbook.name, handbook.number, handbook.description, name)
            return 'ok'
        } catch (e) {
            log.error(e.message, e)
            return 'ErrorPage'
        }
    }
}
