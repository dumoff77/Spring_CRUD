package com.example.demo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
class HomeController {

    @Autowired
    UserRepository userRepository

    @Autowired
    HandbookRepository handbookRepository

    @GetMapping('/handbook')
    def handbook(Model model) {
        handbookRepository.findAll().toString()
    }

    @GetMapping('/home')
    def home(Model model) {
        userRepository.findAll()
    }

    @GetMapping('/home776')
    def home776(Model model) {
        model.addAttribute('date', userRepository.findByLogin('776').toString())
        'ok'
    }

    @GetMapping('/add')
    def add() {
        userRepository.save(new User(
                login: new Random().nextInt(2999),
                password: new Random().nextInt(34545)
        ))
    }

    @GetMapping('/insert')
    def insert() {
        handbookRepository.save(new Handbook(
                name: 'Mary',
                description: 'sss',
                number: '0980446514'
        ))
    }
}
