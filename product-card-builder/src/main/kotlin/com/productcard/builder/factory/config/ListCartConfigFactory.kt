package com.productcard.builder.factory.config

import com.productcard.builder.component.*
import com.productcard.builder.factory.CardRenderConfigFactory
import com.productcard.builder.model.CardRenderConfig
import com.productcard.builder.model.CardThemeType
import org.springframework.stereotype.Component

@Component
class ListCartConfigFactory : CardRenderConfigFactory {
    override val themeType = CardThemeType.LIST_CART
    override fun create() = CardRenderConfig(
        themeType        = themeType,
        componentConfigs = listOf(
            GalleryComponentConfig(80, 80),
            TitleComponentConfig(2),
            PriceComponentConfig(),
            CartButtonComponentConfig(),
            DeliveryComponentConfig()
        ),
        widthDp      = 360,
        isHorizontal = true
    )
}
