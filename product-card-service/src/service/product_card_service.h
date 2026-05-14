#pragma once
#include "product_card_service.grpc.pb.h"
#include "../data/product_loader.h"
#include "../handler/get_cards_handler.h"

class ProductCardServiceImpl final
    : public card::v1::CardService::Service {
public:
    explicit ProductCardServiceImpl(const ProductLoader& loader);
    grpc::Status GetCards(
        grpc::ServerContext* ctx,
        const card::v1::GetCardsRequest* req,
        card::v1::GetCardsResponse* resp) override;
private:
    GetCardsHandler handler_;
};
