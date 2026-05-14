package com.productcard.builder.model

import com.snippets.proto.TProductSnippetPayload

data class DiscoverySnippetConfigContext(
    val offerId: String,
    val payload: TProductSnippetPayload,
    val themeType: ServiceSnippetThemeType
)
