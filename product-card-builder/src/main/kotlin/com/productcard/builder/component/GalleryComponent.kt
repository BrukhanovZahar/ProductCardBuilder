package com.productcard.builder.component

import com.productcard.builder.model.CardRenderContext

class GalleryComponentConfig(
    private val widthDp: Int  = 160,
    private val heightDp: Int = 160
) : CardComponentConfig {
    override val isRequired = true
    override fun createComponent(context: CardRenderContext): CardComponent? {
        if (context.payload.gallery.imageUrlsCount == 0) return null
        return GalleryComponent(context.payload.gallery.imageUrlsList.first(), widthDp, heightDp)
    }
}

class GalleryComponent(
    private val imageUrl: String,
    private val widthDp: Int,
    private val heightDp: Int
) : CardComponent {
    override fun render(): Map<String, Any> = mapOf(
        "type"      to "image",
        "image_url" to imageUrl,
        "width"     to mapOf("type" to "match_parent"),
        "height"    to mapOf("type" to "fixed", "value" to heightDp),
        "scale"     to "fill"
    )
}
