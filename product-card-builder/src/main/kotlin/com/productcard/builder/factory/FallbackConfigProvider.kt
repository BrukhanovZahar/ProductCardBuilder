package com.productcard.builder.factory

import com.productcard.builder.component.GalleryComponentConfig
import com.productcard.builder.component.TitleComponentConfig
import com.productcard.builder.component.PriceComponentConfig
import com.productcard.builder.model.CardRenderConfig
import com.productcard.builder.model.CardThemeType
import org.springframework.stereotype.Component

@Component
class FallbackConfigProvider {
    fun createFallbackConfig() = CardRenderConfig(
        themeType        = CardThemeType.GRID_FEED,
        componentConfigs = listOf(
            GalleryComponentConfig(160, 160),
            TitleComponentConfig(2),
            PriceComponentConfig()
        ),
        widthDp = 160
    )
}
