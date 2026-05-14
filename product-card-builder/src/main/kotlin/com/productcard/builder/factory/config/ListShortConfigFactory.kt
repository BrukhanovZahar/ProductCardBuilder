package com.productcard.builder.factory.config

import com.productcard.builder.component.*
import com.productcard.builder.factory.CardRenderConfigFactory
import com.productcard.builder.model.CardRenderConfig
import com.productcard.builder.model.CardThemeType
import org.springframework.stereotype.Component

@Component
class ListShortConfigFactory : CardRenderConfigFactory {
    override val themeType = CardThemeType.LIST_SHORT
    override fun create() = CardRenderConfig(
        themeType        = themeType,
        componentConfigs = listOf(
            GalleryComponentConfig(80, 80),
            TitleComponentConfig(2),
            PriceComponentConfig(),
            RatingComponentConfig(),
            DeliveryComponentConfig()
        ),
        widthDp      = 360,
        isHorizontal = true
    )
}
