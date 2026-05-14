#include "base_card_builder.h"
#include "../section_builders/title_builder.h"
#include "../section_builders/price_builder.h"
#include "../section_builders/gallery_builder.h"
#include "../section_builders/signal_builder.h"
#include "../section_builders/rating_builder.h"
#include "../section_builders/cart_button_builder.h"
#include "../section_builders/delivery_builder.h"

card::v1::TProductCard BaseCardBuilder::Build(
    card::v1::EThemeType theme_type) const {
    card::v1::TProductCard card;
    card.set_card_id(data_.id);
    card.set_theme_type(theme_type);
    BuildParts(card);
    return card;
}

void BaseCardBuilder::BuildParts(card::v1::TProductCard& c) const {
    if (theme_cfg_.with_gallery)      BuildGallery(c);
    if (theme_cfg_.with_title)        BuildTitle(c);
    if (theme_cfg_.with_price)        BuildPrice(c);
    if (theme_cfg_.with_rating)       BuildRating(c);
    if (theme_cfg_.with_signal)       BuildSignal(c);
    if (theme_cfg_.with_delivery)     BuildDelivery(c);
    if (theme_cfg_.with_cart_button)  BuildCartButton(c);
}

void BaseCardBuilder::BuildTitle(card::v1::TProductCard& c) const {
    TitleBuilder(data_).Build(c);
}
void BaseCardBuilder::BuildPrice(card::v1::TProductCard& c) const {
    PriceBuilder(data_).Build(c);
}
void BaseCardBuilder::BuildGallery(card::v1::TProductCard& c) const {
    GalleryBuilder(data_, theme_cfg_.max_gallery_images.value_or(1)).Build(c);
}
void BaseCardBuilder::BuildSignal(card::v1::TProductCard& c) const {
    SignalBuilder(data_).Build(c);
}
void BaseCardBuilder::BuildRating(card::v1::TProductCard& c) const {
    RatingBuilder(data_).Build(c);
}
void BaseCardBuilder::BuildCartButton(card::v1::TProductCard& c) const {
    CartButtonBuilder(data_).Build(c);
}
void BaseCardBuilder::BuildDelivery(card::v1::TProductCard& c) const {
    DeliveryBuilder(data_).Build(c);
}
