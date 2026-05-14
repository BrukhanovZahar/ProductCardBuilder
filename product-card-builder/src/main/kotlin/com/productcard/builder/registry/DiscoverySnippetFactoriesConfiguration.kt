package com.productcard.builder.registry

import com.productcard.builder.factory.DiscoverySnippetConfigFactory
import com.productcard.builder.model.ServiceSnippetThemeType
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DiscoverySnippetFactoriesConfiguration {

    @Bean
    fun factoriesByThemeType(
        factories: List<DiscoverySnippetConfigFactory>
    ): Map<ServiceSnippetThemeType, DiscoverySnippetConfigFactory> {
        val duplicates = factories.groupBy { it.themeType }.filter { it.value.size > 1 }
        if (duplicates.isNotEmpty())
            throw IllegalStateException("Duplicate snippet factories: $duplicates")

        val result = factories.associateBy { it.themeType }
        val missing = ServiceSnippetThemeType.values()
            .filter { it != ServiceSnippetThemeType.UNKNOWN && it !in result }
        if (missing.isNotEmpty()) logger.warn("No factory for themes: $missing")
        return result
    }

    companion object {
        private val logger = LoggerFactory.getLogger(DiscoverySnippetFactoriesConfiguration::class.java)
    }
}
