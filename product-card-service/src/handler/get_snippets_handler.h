#pragma once
#include "product_card_service.grpc.pb.h"
#include "../data/product_loader.h"

class GetSnippetsHandler {
public:
    explicit GetSnippetsHandler(const ProductLoader& loader) : loader_(loader) {}
    market::snippet::v1::GetSnippetsResponse Handle(
        const market::snippet::v1::GetSnippetsRequest& req) const;
private:
    const ProductLoader& loader_;
};
