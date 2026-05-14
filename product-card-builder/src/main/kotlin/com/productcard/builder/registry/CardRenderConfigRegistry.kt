package com.productcard.builder.registry

import com.productcard.builder.factory.CardRenderConfigFactory
import com.productcard.builder.factory.FallbackConfigProvider
import com.productcard.builder.model.CardRenderConfig
import com.productcard.builder.model.CardThemeType
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class CardRenderConfigRegistry(
    private val factoriesByThemeType: Map<CardThemeType, CardRenderConfigFactory>,
    private val fallback: FallbackConfigProvider
) {
    fun getConfig(themeType: CardThemeType): CardRenderConfig {
        val factory = factoriesByThemeType[themeType]
        if (factory != null) return factory.create()
        logger.warn("No factory for $themeType — using fallback config")
        return fallback.createFallbackConfig()
    }

    companion object {
        private val logger = LoggerFactory.getLogger(CardRenderConfigRegistry::class.java)
    }
}
