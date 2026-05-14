package com.productcard.builder.registry

import com.productcard.builder.factory.CardRenderConfigFactory
import com.productcard.builder.model.CardThemeType
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CardRenderFactoriesConfiguration {

    @Bean
    fun factoriesByThemeType(
        factories: List<CardRenderConfigFactory>
    ): Map<CardThemeType, CardRenderConfigFactory> {
        val duplicates = factories.groupBy { it.themeType }.filter { it.value.size > 1 }
        if (duplicates.isNotEmpty())
            throw IllegalStateException("Duplicate card factories: $duplicates")

        val result = factories.associateBy { it.themeType }
        val missing = CardThemeType.values()
            .filter { it != CardThemeType.UNKNOWN && it !in result }
        if (missing.isNotEmpty()) logger.warn("No factory for themes: $missing")
        return result
    }

    companion object {
        private val logger = LoggerFactory.getLogger(CardRenderFactoriesConfiguration::class.java)
    }
}
