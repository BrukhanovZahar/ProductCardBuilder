package com.productcard.builder.factory.config

import com.productcard.builder.component.*
import com.productcard.builder.factory.DiscoverySnippetConfigFactory
import com.productcard.builder.model.DiscoverySnippetConfig
import com.productcard.builder.model.ServiceSnippetThemeType
import org.springframework.stereotype.Component

@Component
class ListCartConfigFactory : DiscoverySnippetConfigFactory {
    override val themeType = ServiceSnippetThemeType.LIST_CART
    override fun create() = DiscoverySnippetConfig(
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
