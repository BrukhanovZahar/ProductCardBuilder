#include "signal_builder.h"
void SignalBuilder::Build(market::snippet::v1::TProductSnippet& snippet) const {
    auto* s = snippet.mutable_product_payload()->mutable_signal();
    if (data_.is_crossborder && data_.customs_fee > 0) {
        s->set_text("Из-за рубежа, пошлина " + std::to_string(data_.customs_fee) + " \xE2\x82\xBD");
        s->set_signal_type("CUSTOMS");
    } else if (data_.stock_count > 0 && data_.stock_count <= 5) {
        s->set_text("Осталось " + std::to_string(data_.stock_count) + " шт.");
        s->set_signal_type("STOCK");
    }
}
