#include "title_builder.h"
void TitleBuilder::Build(card::v1::TProductCard& card) const {
    card.mutable_product_payload()->mutable_title()->set_text(data_.title);
}
