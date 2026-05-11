#include "delivery_builder.h"
void DeliveryBuilder::Build(market::snippet::v1::TProductSnippet& snippet) const {
    snippet.mutable_product_payload()->mutable_delivery()->set_text(data_.delivery_text);
}
