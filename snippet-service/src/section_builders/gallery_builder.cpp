#include "gallery_builder.h"
#include <algorithm>
GalleryBuilder::GalleryBuilder(const ProductData& data, int max_images)
    : OfferBasedBuilder(data), max_images_(max_images) {}

void GalleryBuilder::Build(market::snippet::v1::TProductSnippet& snippet) const {
    auto* g = snippet.mutable_product_payload()->mutable_gallery();
    int n = std::min((int)data_.images.size(), max_images_);
    for (int i = 0; i < n; ++i) g->add_image_urls(data_.images[i]);
}
