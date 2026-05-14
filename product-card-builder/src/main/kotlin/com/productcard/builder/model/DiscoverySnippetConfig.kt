package com.productcard.builder.model

import com.productcard.builder.component.ProductSnippetComponentVisualConfig

data class DiscoverySnippetConfig(
    val themeType: ServiceSnippetThemeType,
    val componentConfigs: List<ProductSnippetComponentVisualConfig>,
    val widthDp: Int = 160,
    val isHorizontal: Boolean = false
)
