package com.productcard.builder.api

import com.productcard.builder.service.ProductCardBuilderService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

data class ProductCardRequest(
    val offerIds:      List<String>,
    val surface:       String = "SEARCH",
    val themeOverride: String = ""
)

@RestController
@RequestMapping("/api/cards")
class ProductCardController(private val service: ProductCardBuilderService) {

    @PostMapping
    fun getSnippets(@RequestBody req: ProductCardRequest): ResponseEntity<Any> =
        ResponseEntity.ok(mapOf("snippets" to service.buildSnippets(req.offerIds, req.surface, req.themeOverride)))

    @PostMapping("/preview")
    fun preview(@RequestBody req: ProductCardRequest): ResponseEntity<Any> {
        val id = req.offerIds.firstOrNull()
            ?: return ResponseEntity.badRequest().body(mapOf("error" to "offerIds is empty"))
        val results = service.buildSnippets(listOf(id), req.surface, req.themeOverride)
        if (results.isEmpty()) return ResponseEntity.notFound().build()
        return ResponseEntity.ok(results.first())
    }
}
