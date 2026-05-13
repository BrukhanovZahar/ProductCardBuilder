package com.productcard.builder

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ProductCardBuilderApplication

fun main(args: Array<String>) {
    runApplication<ProductCardBuilderApplication>(*args)
}
