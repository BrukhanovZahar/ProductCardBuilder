package com.productcard.builder.model

import com.productcard.proto.TProductCardPayload

data class CardRenderContext(
    val offerId: String,
    val payload: TProductCardPayload,
    val themeType: CardThemeType
)
