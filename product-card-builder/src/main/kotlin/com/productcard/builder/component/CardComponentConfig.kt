package com.productcard.builder.component

import com.productcard.builder.model.CardRenderContext

interface CardComponentConfig {
    val isRequired: Boolean get() = false
    fun createComponent(context: CardRenderContext): CardComponent?
}
