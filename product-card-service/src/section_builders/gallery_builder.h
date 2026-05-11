#pragma once
#include "offer_based_builder.h"
class GalleryBuilder : public OfferBasedBuilder {
public:
    GalleryBuilder(const ProductData& data, int max_images);
    void Build(market::snippet::v1::TProductSnippet& snippet) const override;
private:
    int max_images_;
};
