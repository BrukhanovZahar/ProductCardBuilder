#include "base_snippet_builder.h"
#include "../section_builders/title_builder.h"
#include "../section_builders/price_builder.h"
#include "../section_builders/gallery_builder.h"
#include "../section_builders/signal_builder.h"
#include "../section_builders/rating_builder.h"
#include "../section_builders/cart_button_builder.h"
#include "../section_builders/delivery_builder.h"

market::snippet::v1::TProductSnippet BaseSnippetBuilder::Build(
    market::snippet::v1::EThemeType theme_type) const {
    market::snippet::v1::TProductSnippet snippet;
    snippet.set_snippet_id(data_.id);
    snippet.set_theme_type(theme_type);
    BuildParts(snippet);
    return snippet;
}

void BaseSnippetBuilder::BuildParts(market::snippet::v1::TProductSnippet& s) const {
    if (theme_cfg_.with_gallery)      BuildGallery(s);
    if (theme_cfg_.with_title)        BuildTitle(s);
    if (theme_cfg_.with_price)        BuildPrice(s);
    if (theme_cfg_.with_rating)       BuildRating(s);
    if (theme_cfg_.with_signal)       BuildSignal(s);
    if (theme_cfg_.with_delivery)     BuildDelivery(s);
    if (theme_cfg_.with_cart_button)  BuildCartButton(s);
}

void BaseSnippetBuilder::BuildTitle(market::snippet::v1::TProductSnippet& s) const {
    TitleBuilder(data_).Build(s);
}
void BaseSnippetBuilder::BuildPrice(market::snippet::v1::TProductSnippet& s) const {
    PriceBuilder(data_).Build(s);
}
void BaseSnippetBuilder::BuildGallery(market::snippet::v1::TProductSnippet& s) const {
    GalleryBuilder(data_, theme_cfg_.max_gallery_images.value_or(1)).Build(s);
}
void BaseSnippetBuilder::BuildSignal(market::snippet::v1::TProductSnippet& s) const {
    SignalBuilder(data_).Build(s);
}
void BaseSnippetBuilder::BuildRating(market::snippet::v1::TProductSnippet& s) const {
    RatingBuilder(data_).Build(s);
}
void BaseSnippetBuilder::BuildCartButton(market::snippet::v1::TProductSnippet& s) const {
    CartButtonBuilder(data_).Build(s);
}
void BaseSnippetBuilder::BuildDelivery(market::snippet::v1::TProductSnippet& s) const {
    DeliveryBuilder(data_).Build(s);
}
