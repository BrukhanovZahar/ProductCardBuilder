#include "title_builder.h"
void TitleBuilder::Build(market::snippet::v1::TProductSnippet& snippet) const {
    snippet.mutable_product_payload()->mutable_title()->set_text(data_.title);
}
