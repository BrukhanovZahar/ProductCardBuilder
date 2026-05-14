package com.productcard.builder.component

import com.productcard.builder.model.DiscoverySnippetConfigContext

class DeliveryComponentConfig : ProductSnippetComponentVisualConfig {
    override fun createComponent(context: DiscoverySnippetConfigContext): SnippetComponent? {
        val text = context.payload.delivery.text
        if (text.isBlank()) return null
        return DeliveryComponent(text)
    }
}

class DeliveryComponent(private val text: String) : SnippetComponent {
    override fun render(): Map<String, Any> = mapOf(
        "type"       to "div_text",
        "text"       to text,
        "font_size"  to 11,
        "text_color" to "#007A00",
        "paddings"   to mapOf("left" to 8, "right" to 8, "top" to 2, "bottom" to 4)
    )
}
