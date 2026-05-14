package com.productcard.builder.component

import com.productcard.builder.model.DiscoverySnippetConfigContext

class RatingComponentConfig : ProductSnippetComponentVisualConfig {
    override fun createComponent(context: DiscoverySnippetConfigContext): SnippetComponent? {
        val r = context.payload.rating
        if (r.value <= 0f) return null
        return RatingComponent(r.value, r.count)
    }
}

class RatingComponent(private val value: Float, private val count: Int) : SnippetComponent {
    override fun render(): Map<String, Any> {
        val full  = value.toInt().coerceIn(0, 5)
        val empty = (5 - full).coerceIn(0, 5)
        val stars = "★".repeat(full) + "☆".repeat(empty)
        val countPart = if (count > 0) " $count" else ""
        return mapOf(
            "type"       to "div_text",
            "text"       to "$stars ${"%.1f".format(value)}$countPart",
            "font_size"  to 11,
            "text_color" to "#FF6600",
            "paddings"   to mapOf("left" to 8, "right" to 8, "top" to 2)
        )
    }
}
