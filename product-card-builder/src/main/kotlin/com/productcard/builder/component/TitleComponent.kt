package com.productcard.builder.component

import com.productcard.builder.model.CardRenderContext

class TitleComponentConfig(private val maxLines: Int = 2) : CardComponentConfig {
    override val isRequired = true
    override fun createComponent(context: CardRenderContext): CardComponent? {
        val text = context.payload.title.text
        if (text.isBlank()) return null
        return TitleComponent(text, maxLines)
    }
}

class TitleComponent(private val text: String, private val maxLines: Int) : CardComponent {
    override fun render(): Map<String, Any> = mapOf(
        "type"       to "div_text",
        "text"       to text,
        "font_size"  to 13,
        "max_lines"  to maxLines,
        "text_color" to "#1A1A1A",
        "paddings"   to mapOf("left" to 8, "right" to 8, "top" to 4)
    )
}
