package com.productcard.builder.client

import com.productcard.proto.ESurface
import com.productcard.proto.GetCardsRequest
import com.productcard.proto.GetCardsResponse
import com.productcard.proto.CardServiceGrpc
import io.grpc.ManagedChannelBuilder
import jakarta.annotation.PreDestroy
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class CardServiceGrpcClient(
    @Value("\${card-service.host:localhost}") host: String,
    @Value("\${card-service.port:50051}")     port: Int
) {
    private val channel = ManagedChannelBuilder.forAddress(host, port)
        .usePlaintext()
        .build()
    private val stub = CardServiceGrpc.newBlockingStub(channel)

    fun getCards(
        offerIds:      List<String>,
        surface:       ESurface,
        themeOverride: String = ""
    ): GetCardsResponse =
        stub.getCards(
            GetCardsRequest.newBuilder()
                .addAllOfferIds(offerIds)
                .setSurface(surface)
                .setThemeOverride(themeOverride)
                .build()
        )

    @PreDestroy
    fun shutdown() = channel.shutdown()
}
