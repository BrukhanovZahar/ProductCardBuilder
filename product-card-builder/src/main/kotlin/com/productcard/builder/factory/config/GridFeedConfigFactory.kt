package com.productcard.builder.factory.config

import com.productcard.builder.component.*
import com.productcard.builder.factory.CardRenderConfigFactory
import com.productcard.builder.model.CardRenderConfig
import com.productcard.builder.model.CardThemeType
import org.springframework.stereotype.Component

@Component
class GridFeedConfigFactory : CardRenderConfigFactory {
    override val themeType = CardThemeType.GRID_FEED
    override fun create() = CardRenderConfig(
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
