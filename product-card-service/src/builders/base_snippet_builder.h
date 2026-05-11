#pragma once
#include "product_card_service.pb.h"
#include "../data/product_data.h"
#include "../const/theme_configs.h"

class BaseSnippetBuilder {
public:
    BaseSnippetBuilder(const ProductData& data, const ThemeConfig& config)
        : data_(data), theme_cfg_(config) {}
    virtual ~BaseSnippetBuilder() = default;

    market::snippet::v1::TProductSnippet Build(
        market::snippet::v1::EThemeType theme_type) const;

protected:
    virtual void BuildTitle(market::snippet::v1::TProductSnippet& s) const;
    virtual void BuildPrice(market::snippet::v1::TProductSnippet& s) const;
    virtual void BuildGallery(market::snippet::v1::TProductSnippet& s) const;
    virtual void BuildSignal(market::snippet::v1::TProductSnippet& s) const;
    virtual void BuildRating(market::snippet::v1::TProductSnippet& s) const;
    virtual void BuildCartButton(market::snippet::v1::TProductSnippet& s) const;
    virtual void BuildDelivery(market::snippet::v1::TProductSnippet& s) const;

    void BuildParts(market::snippet::v1::TProductSnippet& s) const;

    const ProductData& data_;
    const ThemeConfig& theme_cfg_;
};
