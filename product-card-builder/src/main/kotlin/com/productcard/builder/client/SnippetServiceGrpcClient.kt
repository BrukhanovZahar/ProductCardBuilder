package com.productcard.builder.client

import com.snippets.proto.ESurface
import com.snippets.proto.GetSnippetsRequest
import com.snippets.proto.GetSnippetsResponse
import com.snippets.proto.MarketSnippetServiceGrpc
import io.grpc.ManagedChannelBuilder
import jakarta.annotation.PreDestroy
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class SnippetServiceGrpcClient(
    @Value("\${snippet-service.host:localhost}") host: String,
    @Value("\${snippet-service.port:50051}")     port: Int
) {
    private val channel = ManagedChannelBuilder.forAddress(host, port)
        .usePlaintext()
        .build()
    private val stub = MarketSnippetServiceGrpc.newBlockingStub(channel)

    fun getSnippets(
        offerIds:      List<String>,
        surface:       ESurface,
        themeOverride: String = ""
    ): GetSnippetsResponse =
        stub.getSnippets(
            GetSnippetsRequest.newBuilder()
                .addAllOfferIds(offerIds)
                .setSurface(surface)
                .setThemeOverride(themeOverride)
                .build()
        )

    @PreDestroy
    fun shutdown() = channel.shutdown()
}
