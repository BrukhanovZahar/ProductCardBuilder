#include "product_card_service.h"

ProductCardServiceImpl::ProductCardServiceImpl(const ProductLoader& loader)
    : handler_(loader) {}

grpc::Status ProductCardServiceImpl::GetSnippets(
    grpc::ServerContext*,
    const market::snippet::v1::GetSnippetsRequest* req,
    market::snippet::v1::GetSnippetsResponse* resp) {
    *resp = handler_.Handle(*req);
    return grpc::Status::OK;
}
