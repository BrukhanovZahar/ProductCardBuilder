#include "builder_factory.h"
#include "../builders/search_snippet_builder.h"
#include "../builders/cart_snippet_builder.h"
#include "../builders/model_card_builder.h"
#include "../builders/fallback_builder.h"

using market::snippet::v1::ESurface;

std::unique_ptr<BaseSnippetBuilder> GetBuilder(
    ESurface surface,
    const ProductData& data,
    const ThemeConfig& config) {
    switch (surface) {
        case ESurface::SEARCH:
            return std::make_unique<SearchSnippetBuilder>(data, config);
        case ESurface::CART:
            return std::make_unique<CartSnippetBuilder>(data, config);
        case ESurface::MODEL_CARD:
            return std::make_unique<ModelCardBuilder>(data, config);
        default:
            return std::make_unique<FallbackBuilder>(data);
    }
}
