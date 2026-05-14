package com.productcard.builder.component

import com.productcard.builder.model.DiscoverySnippetConfigContext

class PriceComponentConfig : ProductSnippetComponentVisualConfig {
    override val isRequired = true
    override fun createComponent(context: DiscoverySnippetConfigContext): SnippetComponent? {
        val p = context.payload.price
        if (p.currentPrice == 0L) return null
        return PriceComponent(p.currentPrice, p.originalPrice, p.currency)
    }
}

class PriceComponent(
    private val current: Long,
    private val original: Long,
    private val currency: String
) : SnippetComponent {
    override fun render(): Map<String, Any> {
        val sym = if (currency == "RUB") "₽" else currency
        val items = mutableListOf<Map<String, Any>>(
            mapOf(
                "type"        to "div_text",
                "text"        to "${formatPrice(current)} $sym",
                "font_size"   to 15,
                "font_weight" to "bold",
                "text_color"  to "#1A1A1A"
            )
        )
        if (original > current && original > 0) {
            items += mapOf(
                "type"       to "div_text",
                "text"       to "${formatPrice(original)} $sym",
                "font_size"  to 12,
                "text_color" to "#999999",
                "strike"     to "line_through",
                "paddings"   to mapOf("left" to 4)
            )
        }
        return mapOf(
            "type"        to "div_container",
            "orientation" to "horizontal",
            "paddings"    to mapOf("left" to 8, "right" to 8, "top" to 4),
            "items"       to items
        )
    }
    private fun formatPrice(v: Long) = String.format("%,d", v).replace(',', ' ')
}
