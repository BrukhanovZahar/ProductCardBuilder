package com.productcard.builder.component

import com.productcard.builder.model.CardRenderContext

class CartButtonComponentConfig : CardComponentConfig {
    override fun createComponent(context: CardRenderContext): CardComponent? {
        val b = context.payload.cartButton
        if (b.offerId.isBlank()) return null
        return CartButtonComponent(b.inCart)
    }
}

class CartButtonComponent(private val inCart: Boolean) : CardComponent {
    override fun render(): Map<String, Any> = mapOf(
        "type"                         to "container",
        "orientation"                  to "horizontal",
        "content_alignment_horizontal" to "center",
        "content_alignment_vertical"   to "center",
        "height"     to mapOf("type" to "fixed", "value" to 36),
        "background" to listOf(mapOf("type" to "solid",
            "color" to if (inCart) "#E8F5E9" else "#FFD000")),
        "border"     to mapOf("corner_radius" to 8),
        "margins"    to mapOf("left" to 8, "right" to 8, "top" to 4, "bottom" to 4),
        "items"      to listOf(mapOf(
            "type"        to "text",
            "text"        to if (inCart) "В корзине" else "В корзину",
            "font_size"   to 13,
            "font_weight" to "bold",
            "text_color"  to if (inCart) "#2E7D32" else "#1A1A1A"
        ))
    )
}
