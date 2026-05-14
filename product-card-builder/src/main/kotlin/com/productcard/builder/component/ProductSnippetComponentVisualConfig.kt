package com.productcard.builder.component

import com.productcard.builder.model.DiscoverySnippetConfigContext

interface ProductSnippetComponentVisualConfig {
    val isRequired: Boolean get() = false
    fun createComponent(context: DiscoverySnippetConfigContext): SnippetComponent?
}
