package com.productcard.builder.factory

import com.productcard.builder.model.CardRenderConfig
import com.productcard.builder.model.CardThemeType

interface CardRenderConfigFactory {
    val themeType: CardThemeType
    fun create(): CardRenderConfig
}
