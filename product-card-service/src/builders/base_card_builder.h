#pragma once
#include "product_card_service.pb.h"
#include "../data/product_data.h"
#include "../const/theme_configs.h"

class BaseCardBuilder {
public:
    BaseCardBuilder(const ProductData& data, const ThemeConfig& config)
        : data_(data), theme_cfg_(config) {}
    virtual ~BaseCardBuilder() = default;

    card::v1::TProductCard Build(
        card::v1::EThemeType theme_type) const;

protected:
    virtual void BuildTitle(card::v1::TProductCard& c) const;
    virtual void BuildPrice(card::v1::TProductCard& c) const;
    virtual void BuildGallery(card::v1::TProductCard& c) const;
    virtual void BuildSignal(card::v1::TProductCard& c) const;
    virtual void BuildRating(card::v1::TProductCard& c) const;
    virtual void BuildCartButton(card::v1::TProductCard& c) const;
    virtual void BuildDelivery(card::v1::TProductCard& c) const;

    void BuildParts(card::v1::TProductCard& c) const;

    const ProductData& data_;
    const ThemeConfig& theme_cfg_;
};
