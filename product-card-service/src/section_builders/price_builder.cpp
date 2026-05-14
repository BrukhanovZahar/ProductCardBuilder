#include "price_builder.h"
void PriceBuilder::Build(card::v1::TProductCard& card) const {
    auto* p = card.mutable_product_payload()->mutable_price();
    p->set_current_price(data_.current_price);
    p->set_original_price(data_.original_price);
    p->set_currency(data_.currency);
}
