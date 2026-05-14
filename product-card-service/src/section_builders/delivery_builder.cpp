#include "delivery_builder.h"
void DeliveryBuilder::Build(card::v1::TProductCard& card) const {
    card.mutable_product_payload()->mutable_delivery()->set_text(data_.delivery_text);
}
