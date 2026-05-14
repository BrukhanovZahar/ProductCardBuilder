#pragma once
#include "product_card_service.grpc.pb.h"
#include "../data/product_loader.h"

class GetCardsHandler {
public:
    explicit GetCardsHandler(const ProductLoader& loader) : loader_(loader) {}
    card::v1::GetCardsResponse Handle(
        const card::v1::GetCardsRequest& req) const;
private:
    const ProductLoader& loader_;
};
