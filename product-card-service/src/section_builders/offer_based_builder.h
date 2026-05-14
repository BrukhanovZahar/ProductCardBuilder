#pragma once
#include "product_card_service.pb.h"
#include "../data/product_data.h"

class OfferBasedBuilder {
public:
    explicit OfferBasedBuilder(const ProductData& data) : data_(data) {}
    virtual ~OfferBasedBuilder() = default;
    virtual void Build(card::v1::TProductCard& card) const = 0;
protected:
    const ProductData& data_;
};
