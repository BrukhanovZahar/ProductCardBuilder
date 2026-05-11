#pragma once
#include "offer_based_builder.h"
class TitleBuilder : public OfferBasedBuilder {
public:
    using OfferBasedBuilder::OfferBasedBuilder;
    void Build(market::snippet::v1::TProductSnippet& snippet) const override;
};
