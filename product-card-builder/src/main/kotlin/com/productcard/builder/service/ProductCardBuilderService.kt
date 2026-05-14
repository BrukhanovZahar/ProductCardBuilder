package com.productcard.builder.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.productcard.builder.client.SnippetServiceGrpcClient
import com.productcard.builder.model.DiscoverySnippetConfigContext
import com.productcard.builder.model.ServiceSnippetThemeType
import com.productcard.builder.registry.DiscoverySnippetConfigRegistry
import com.productcard.builder.render.DivKitRenderer
import com.snippets.proto.ESurface
import com.snippets.proto.TProductSnippet
import org.springframework.stereotype.Service

@Service
class ProductCardBuilderService(
    private val grpcClient: SnippetServiceGrpcClient,
    private val registry:   DiscoverySnippetConfigRegistry,
    private val mapper:     ObjectMapper
) {
    private val renderer = DivKitRenderer()

    fun buildSnippets(
        offerIds:      List<String>,
        surface:       String,
        themeOverride: String = ""
    ): List<Any> {
        val eSurface = when (surface.uppercase()) {
            "CART"       -> ESurface.CART
            "MODEL_CARD" -> ESurface.MODEL_CARD
            else         -> ESurface.SEARCH
        }
        val response = grpcClient.getSnippets(offerIds, eSurface, themeOverride)
        return response.snippetsList.map { renderSnippet(it) }
    }

    private fun renderSnippet(snippet: TProductSnippet): Any {
        val themeType = ServiceSnippetThemeType.from(snippet.themeType)
        val config    = registry.getConfig(themeType)
        val context   = DiscoverySnippetConfigContext(
            offerId   = snippet.snippetId,
            payload   = snippet.productPayload,
            themeType = themeType
        )
        val divKitMap = renderer.render(config, context)
        return mapper.readValue(mapper.writeValueAsString(divKitMap), Any::class.java)
    }
}
