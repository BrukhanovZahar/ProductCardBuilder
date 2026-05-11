#pragma once
#include "product_card_service.grpc.pb.h"
#include "../data/product_loader.h"
#include "../handler/get_snippets_handler.h"

class ProductCardServiceImpl final
    : public market::snippet::v1::MarketSnippetService::Service {
public:
    explicit ProductCardServiceImpl(const ProductLoader& loader);
    grpc::Status GetSnippets(
        grpc::ServerContext* ctx,
        const market::snippet::v1::GetSnippetsRequest* req,
        market::snippet::v1::GetSnippetsResponse* resp) override;
private:
    GetSnippetsHandler handler_;
};
