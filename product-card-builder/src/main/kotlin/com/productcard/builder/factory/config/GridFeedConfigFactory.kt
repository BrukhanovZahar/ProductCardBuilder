package com.productcard.builder.factory.config

import com.productcard.builder.component.*
import com.productcard.builder.factory.DiscoverySnippetConfigFactory
import com.productcard.builder.model.DiscoverySnippetConfig
import com.productcard.builder.model.ServiceSnippetThemeType
import org.springframework.stereotype.Component

@Component
class GridFeedConfigFactory : DiscoverySnippetConfigFactory {
    override val themeType = ServiceSnippetThemeType.GRID_FEED
    override fun create() = DiscoverySnippetConfig(
        themeType        = themeType,
        componentConfigs = listOf(
            GalleryComponentConfig(160, 160),
            TitleComponentConfig(2),
            PriceComponentConfig(),
            RatingComponentConfig(),
            SignalComponentConfig(),
            CartButtonComponentConfig()
        ),
        widthDp = 160
    )
}
