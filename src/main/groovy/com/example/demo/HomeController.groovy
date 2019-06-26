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
    def home(Model model) {
        'ok'
    }

    @GetMapping('api/handbook')
    def handbook() {
        SqlHandbookRepository.findAll()
    }

    @DeleteMapping('api/handbook/{name}')
    def delete(@PathVariable String name) {
        try {
            log.debug("Start delete handbook by name $name")
            handbookRepository.removeByName(name)
            return 'ok'
        } catch (Exception e) {
            log.error(e.message, e)
        }
        log.info("Deleted handbook with name $name")
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

    @PutMapping("api/handbook/{name}")
    def update(@PathVariable String name, @RequestBody Handbook handbook) {
        log.debug("Start update handbook by name $name")
        try {
            def doc = handbookRepository.findByName(name)
            handbook.id = doc.id
            handbookRepository.save(handbook)
            return 'ok'
        } catch (e) {
            log.error(e.message, e)
            return 'ErrorPage'
        }
    }
}
