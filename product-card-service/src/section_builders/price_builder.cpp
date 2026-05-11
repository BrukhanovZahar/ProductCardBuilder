#include "price_builder.h"
void PriceBuilder::Build(market::snippet::v1::TProductSnippet& snippet) const {
    auto* p = snippet.mutable_product_payload()->mutable_price();
    p->set_current_price(data_.current_price);
    p->set_original_price(data_.original_price);
    p->set_currency(data_.currency);
}
