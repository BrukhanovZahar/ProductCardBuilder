#include "product_card_service.h"

ProductCardServiceImpl::ProductCardServiceImpl(const ProductLoader& loader)
    : handler_(loader) {}

grpc::Status ProductCardServiceImpl::GetCards(
    grpc::ServerContext*,
    const card::v1::GetCardsRequest* req,
    card::v1::GetCardsResponse* resp) {
    *resp = handler_.Handle(*req);
    return grpc::Status::OK;
}
