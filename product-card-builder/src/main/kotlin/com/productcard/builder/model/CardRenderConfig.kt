package com.productcard.builder.model

import com.productcard.builder.component.CardComponentConfig

data class CardRenderConfig(
    val themeType: CardThemeType,
    val componentConfigs: List<CardComponentConfig>,
    val widthDp: Int = 160,
    val isHorizontal: Boolean = false
)
