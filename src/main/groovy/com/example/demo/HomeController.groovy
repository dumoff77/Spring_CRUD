package com.example.demo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

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
    //@GetMapping('/handbook')
    //def handbook(Model model) {
    //    handbookRepository.findAll().toString()
    //}

    //@GetMapping('/add')
    //def add() {
    //    userRepository.save(new User(
    //            login: new Random().nextInt(2999),
    //            password: new Random().nextInt(34545)
    //    ))
    //}


    @DeleteMapping('/delete/{name}')
    def delete(@PathVariable String name) {
        try {
            println name
            handbookRepository.removeByName(name)
            return 'ok'
        } catch (Exception e) {
            return "ErrorPage"
        }
    }

    @PostMapping("/insert")
    def insert(@RequestParam String number, String name, String description) {
        println 'POST'
        try {
            handbookRepository.save(new Handbook(
                    name: name,
                    description: description,
                    number: number))
        } catch (Exception e) {
            return "ErrorPage"
        }
    }
}
