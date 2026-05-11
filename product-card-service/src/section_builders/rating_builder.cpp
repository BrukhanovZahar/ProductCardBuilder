#include "rating_builder.h"
void RatingBuilder::Build(market::snippet::v1::TProductSnippet& snippet) const {
    auto* r = snippet.mutable_product_payload()->mutable_rating();
    r->set_value(data_.rating_value);
    r->set_count(data_.rating_count);
}
