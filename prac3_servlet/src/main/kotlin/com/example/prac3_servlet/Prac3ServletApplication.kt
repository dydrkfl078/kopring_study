package com.example.prac3_servlet

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.ServletComponentScan

@ServletComponentScan
@SpringBootApplication
class Prac3ServletApplication

fun main(args: Array<String>) {
	runApplication<Prac3ServletApplication>(*args)
}
