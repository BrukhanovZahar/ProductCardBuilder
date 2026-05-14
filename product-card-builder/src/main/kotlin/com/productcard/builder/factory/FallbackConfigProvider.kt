package com.productcard.builder.factory

import com.productcard.builder.component.GalleryComponentConfig
import com.productcard.builder.component.TitleComponentConfig
import com.productcard.builder.component.PriceComponentConfig
import com.productcard.builder.model.DiscoverySnippetConfig
import com.productcard.builder.model.ServiceSnippetThemeType
import org.springframework.stereotype.Component

@Component
class FallbackConfigProvider {
    fun createFallbackConfig() = DiscoverySnippetConfig(
        themeType        = ServiceSnippetThemeType.GRID_FEED,
        componentConfigs = listOf(
            GalleryComponentConfig(160, 160),
            TitleComponentConfig(2),
            PriceComponentConfig()
        ),
        widthDp = 160
    )
}
