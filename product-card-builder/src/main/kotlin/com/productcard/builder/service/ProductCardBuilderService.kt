package com.productcard.builder.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.productcard.builder.client.CardServiceGrpcClient
import com.productcard.builder.model.CardRenderContext
import com.productcard.builder.model.CardThemeType
import com.productcard.builder.registry.CardRenderConfigRegistry
import com.productcard.builder.render.DivKitRenderer
import com.productcard.proto.ESurface
import com.productcard.proto.TProductCard
import org.springframework.stereotype.Service

@Service
class ProductCardBuilderService(
    private val grpcClient: CardServiceGrpcClient,
    private val registry:   CardRenderConfigRegistry,
    private val mapper:     ObjectMapper
) {
    private val renderer = DivKitRenderer()

    fun buildCards(
        offerIds:      List<String>,
        surface:       String,
        themeOverride: String = ""
    ): List<Any> {
        val eSurface = when (surface.uppercase()) {
            "CART"       -> ESurface.CART
            "MODEL_CARD" -> ESurface.MODEL_CARD
            else         -> ESurface.SEARCH
        }
        val response = grpcClient.getCards(offerIds, eSurface, themeOverride)
        return response.cardsList.map { renderCard(it) }
    }

    private fun renderCard(card: TProductCard): Any {
        val themeType = CardThemeType.from(card.themeType)
        val config    = registry.getConfig(themeType)
        val context   = CardRenderContext(
            offerId   = card.cardId,
            payload   = card.productPayload,
            themeType = themeType
        )
        val divKitMap = renderer.render(config, context)
        return mapper.readValue(mapper.writeValueAsString(divKitMap), Any::class.java)
    }
}
