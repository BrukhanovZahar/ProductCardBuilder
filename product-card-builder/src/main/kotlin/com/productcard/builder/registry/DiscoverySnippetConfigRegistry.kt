package com.productcard.builder.registry

import com.productcard.builder.factory.FallbackConfigProvider
import com.productcard.builder.factory.DiscoverySnippetConfigFactory
import com.productcard.builder.model.DiscoverySnippetConfig
import com.productcard.builder.model.ServiceSnippetThemeType
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class DiscoverySnippetConfigRegistry(
    private val factoriesByThemeType: Map<ServiceSnippetThemeType, DiscoverySnippetConfigFactory>,
    private val fallback: FallbackConfigProvider
) {
    fun getConfig(themeType: ServiceSnippetThemeType): DiscoverySnippetConfig {
        val factory = factoriesByThemeType[themeType]
        if (factory != null) return factory.create()
        logger.warn("No factory for $themeType — using fallback config")
        return fallback.createFallbackConfig()
    }

    companion object {
        private val logger = LoggerFactory.getLogger(DiscoverySnippetConfigRegistry::class.java)
    }
}
