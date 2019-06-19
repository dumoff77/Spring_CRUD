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
@Controller
class HomeController {

    @Autowired
    UserRepository userRepository

    @Autowired
    HandbookRepository handbookRepository

    @GetMapping('/home')
    def home(Model model) {
        'ok'
    }

    //@GetMapping('/home776')
    //def home776(Model model) {
    //    model.addAttribute('date', userRepository.findByLogin('776').toString())
    //    'ok'
    //}
    //
    //@GetMapping('/add')
    //def add() {
    //    userRepository.save(new User(
    //            login: new Random().nextInt(2999),
    //            password: new Random().nextInt(34545)
    //    ))
    //}

    @GetMapping('api/handbook')
    def handbook(Model model) {
        handbookRepository.findAll().toString()
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

    //@PostMapping("api/handbook")
    //def insert(@RequestParam String number, String name, String description) {
    //    try {
    //        handbookRepository.save(new Handbook(
    //                name: name,
    //                description: description,
    //                number: number))
    //        return 'ok'
    //    } catch (Exception e) {
    //        return "ErrorPage"
    //    }
    //}

    @PostMapping("api/handbook")
    def insert(@RequestBody Handbook handbook) {
        try {
            handbookRepository.save(handbook)
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
