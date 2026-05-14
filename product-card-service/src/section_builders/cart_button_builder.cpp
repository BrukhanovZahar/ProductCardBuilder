#include "cart_button_builder.h"
void CartButtonBuilder::Build(card::v1::TProductCard& card) const {
    auto* b = card.mutable_product_payload()->mutable_cart_button();
    b->set_offer_id(data_.id);
    b->set_in_cart(false);
}
