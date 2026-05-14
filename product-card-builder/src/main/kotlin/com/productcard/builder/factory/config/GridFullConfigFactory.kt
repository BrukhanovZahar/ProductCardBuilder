package com.productcard.builder.factory.config

import com.productcard.builder.component.*
import com.productcard.builder.factory.CardRenderConfigFactory
import com.productcard.builder.model.CardRenderConfig
import com.productcard.builder.model.CardThemeType
import org.springframework.stereotype.Component

@Component
class GridFullConfigFactory : CardRenderConfigFactory {
    override val themeType = CardThemeType.GRID_FULL
    override fun create() = CardRenderConfig(
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
