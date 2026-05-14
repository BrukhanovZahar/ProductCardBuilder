package com.productcard.builder.component

import com.productcard.builder.model.CardRenderContext

class DeliveryComponentConfig : CardComponentConfig {
    override fun createComponent(context: CardRenderContext): CardComponent? {
        val text = context.payload.delivery.text
        if (text.isBlank()) return null
        return DeliveryComponent(text)
    }
}

class DeliveryComponent(private val text: String) : CardComponent {
    override fun render(): Map<String, Any> = mapOf(
        "type"       to "text",
        "text"       to text,
        "font_size"  to 11,
        "text_color" to "#007A00",
        "paddings"   to mapOf("left" to 8, "right" to 8, "top" to 2, "bottom" to 4)
    )
}
