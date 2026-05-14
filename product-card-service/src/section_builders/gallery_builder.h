#pragma once
#include "offer_based_builder.h"
class GalleryBuilder : public OfferBasedBuilder {
public:
    GalleryBuilder(const ProductData& data, int max_images);
    void Build(card::v1::TProductCard& card) const override;
private:
    int max_images_;
};
