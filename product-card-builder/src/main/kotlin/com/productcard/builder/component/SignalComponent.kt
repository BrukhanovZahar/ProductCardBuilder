package com.productcard.builder.component

import com.productcard.builder.model.CardRenderContext

class SignalComponentConfig : CardComponentConfig {
    override fun createComponent(context: CardRenderContext): CardComponent? {
        val s = context.payload.signal
        if (s.text.isBlank()) return null
        val color = if (s.signalType == "CUSTOMS") "#0055CC" else "#FF6600"
        return SignalComponent(s.text, color)
    }
}

class SignalComponent(private val text: String, private val color: String) : CardComponent {
    override fun render(): Map<String, Any> = mapOf(
        "type"       to "text",
        "text"       to text,
        "font_size"  to 11,
        "text_color" to color,
        "paddings"   to mapOf("left" to 8, "right" to 8, "top" to 2)
    )
}
