#pragma once
#include "base_card_builder.h"
class FallbackBuilder : public BaseCardBuilder {
public:
    explicit FallbackBuilder(const ProductData& data);
protected:
    void BuildSignal(card::v1::TProductCard&)     const override {}
    void BuildDelivery(card::v1::TProductCard&)   const override {}
    void BuildCartButton(card::v1::TProductCard&) const override {}
};
