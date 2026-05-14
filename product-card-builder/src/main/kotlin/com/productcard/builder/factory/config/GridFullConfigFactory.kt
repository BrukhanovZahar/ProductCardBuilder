package com.productcard.builder.factory.config

import com.productcard.builder.component.*
import com.productcard.builder.factory.DiscoverySnippetConfigFactory
import com.productcard.builder.model.DiscoverySnippetConfig
import com.productcard.builder.model.ServiceSnippetThemeType
import org.springframework.stereotype.Component

@Component
class GridFullConfigFactory : DiscoverySnippetConfigFactory {
    override val themeType = ServiceSnippetThemeType.GRID_FULL
    override fun create() = DiscoverySnippetConfig(
        themeType        = themeType,
        componentConfigs = listOf(
            GalleryComponentConfig(300, 240),
            TitleComponentConfig(3),
            PriceComponentConfig(),
            RatingComponentConfig(),
            SignalComponentConfig(),
            DeliveryComponentConfig(),
            CartButtonComponentConfig()
        ),
        widthDp = 300
    )
}
