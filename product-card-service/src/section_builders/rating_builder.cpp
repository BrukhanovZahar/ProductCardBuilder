#include "rating_builder.h"
void RatingBuilder::Build(card::v1::TProductCard& card) const {
    auto* r = card.mutable_product_payload()->mutable_rating();
    r->set_value(data_.rating_value);
    r->set_count(data_.rating_count);
}
