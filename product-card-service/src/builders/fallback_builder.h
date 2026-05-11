#pragma once
#include "base_snippet_builder.h"
class FallbackBuilder : public BaseSnippetBuilder {
public:
    explicit FallbackBuilder(const ProductData& data);
protected:
    void BuildSignal(market::snippet::v1::TProductSnippet&)    const override {}
    void BuildDelivery(market::snippet::v1::TProductSnippet&)  const override {}
    void BuildCartButton(market::snippet::v1::TProductSnippet&) const override {}
};
