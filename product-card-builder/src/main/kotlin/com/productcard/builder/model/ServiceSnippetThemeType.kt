package com.productcard.builder.model

import com.snippets.proto.EThemeType

enum class ServiceSnippetThemeType(val protoValue: EThemeType) {
    GRID_FEED(EThemeType.GRID_FEED),
    GRID_VISUAL(EThemeType.GRID_VISUAL),
    GRID_FULL(EThemeType.GRID_FULL),
    LIST_SHORT(EThemeType.LIST_SHORT),
    LIST_CART(EThemeType.LIST_CART),
    UNKNOWN(EThemeType.UNKNOWN_THEME);

    companion object {
        fun from(proto: EThemeType): ServiceSnippetThemeType =
            values().firstOrNull { it.protoValue == proto } ?: UNKNOWN
    }
}
