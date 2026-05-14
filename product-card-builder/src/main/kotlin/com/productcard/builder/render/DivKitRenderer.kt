package com.productcard.builder.render

import com.productcard.builder.component.CardComponent
import com.productcard.builder.model.CardRenderConfig
import com.productcard.builder.model.CardRenderContext

class DivKitRenderer {

    fun render(
        config: CardRenderConfig,
        context: CardRenderContext
    ): Map<String, Any> {
        val components = config.componentConfigs.mapNotNull { it.createComponent(context) }
        val card = if (config.isHorizontal) buildHorizontalCard(components, config.widthDp)
                   else buildVerticalCard(components, config.widthDp)
        return mapOf(
            "log_id"    to "card_${context.offerId}",
            "card"      to card,
            "templates" to emptyMap<String, Any>()
        )
    }

    private fun buildVerticalCard(
        components: List<CardComponent>,
        widthDp: Int
    ): Map<String, Any> {
        val div = mapOf(
            "type"        to "div_container",
            "orientation" to "vertical",
            "width"       to mapOf("type" to "fixed", "value" to widthDp),
            "background"  to listOf(mapOf("type" to "solid", "color" to "#FFFFFF")),
            "border"      to mapOf(
                "corner_radius" to 12,
                "stroke"        to mapOf("color" to "#E0E0E0", "width" to 1)
            ),
            "items" to components.map { it.render() }
        )
        return mapOf("states" to listOf(mapOf("state_id" to 0, "div" to div)))
    }

    private fun buildHorizontalCard(
        components: List<CardComponent>,
        widthDp: Int
    ): Map<String, Any> {
        val imageItem    = components.firstOrNull()?.render()
        val contentItems = components.drop(1).map { it.render() }

        val allItems = buildList {
            if (imageItem != null) add(imageItem)
            add(mapOf(
                "type"        to "div_container",
                "orientation" to "vertical",
                "width"       to mapOf("type" to "match_parent"),
                "paddings"    to mapOf("left" to 8),
                "items"       to contentItems
            ))
        }

        val div = mapOf(
            "type"        to "div_container",
            "orientation" to "horizontal",
            "width"       to mapOf("type" to "fixed", "value" to widthDp),
            "background"  to listOf(mapOf("type" to "solid", "color" to "#FFFFFF")),
            "border"      to mapOf(
                "corner_radius" to 8,
                "stroke"        to mapOf("color" to "#E0E0E0", "width" to 1)
            ),
            "paddings" to mapOf("all" to 8),
            "items"    to allItems
        )
        return mapOf("states" to listOf(mapOf("state_id" to 0, "div" to div)))
    }
}
