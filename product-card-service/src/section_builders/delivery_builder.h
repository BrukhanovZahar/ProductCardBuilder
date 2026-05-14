#pragma once
#include "offer_based_builder.h"
class DeliveryBuilder : public OfferBasedBuilder {
public:
    using OfferBasedBuilder::OfferBasedBuilder;
    void Build(card::v1::TProductCard& card) const override;
};
