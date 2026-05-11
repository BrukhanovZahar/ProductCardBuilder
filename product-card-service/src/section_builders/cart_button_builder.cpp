#include "cart_button_builder.h"
void CartButtonBuilder::Build(market::snippet::v1::TProductSnippet& snippet) const {
    auto* b = snippet.mutable_product_payload()->mutable_cart_button();
    b->set_offer_id(data_.id);
    b->set_in_cart(false);
}
