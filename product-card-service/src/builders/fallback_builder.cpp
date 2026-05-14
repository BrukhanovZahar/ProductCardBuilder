#include "fallback_builder.h"

static ThemeConfig MakeFallbackConfig() {
    ThemeConfig c;
    c.with_title         = true;
    c.with_price         = true;
    c.with_gallery       = true;
    c.with_rating        = false;
    c.with_signal        = false;
    c.with_delivery      = false;
    c.with_cart_button   = false;
    c.max_gallery_images = 1;
    return c;
}

static const ThemeConfig kFallbackConfig = MakeFallbackConfig();

FallbackBuilder::FallbackBuilder(const ProductData& data)
    : BaseCardBuilder(data, kFallbackConfig) {}
