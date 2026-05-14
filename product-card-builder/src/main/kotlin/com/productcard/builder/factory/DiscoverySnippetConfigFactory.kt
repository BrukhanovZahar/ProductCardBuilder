package com.productcard.builder.factory

import com.productcard.builder.model.DiscoverySnippetConfig
import com.productcard.builder.model.ServiceSnippetThemeType

interface DiscoverySnippetConfigFactory {
    val themeType: ServiceSnippetThemeType
    fun create(): DiscoverySnippetConfig
}
