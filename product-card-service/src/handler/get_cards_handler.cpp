#include "get_cards_handler.h"
#include "../utils/theme_utils.h"
#include "../utils/builder_factory.h"
#include "../builders/fallback_builder.h"

using card::v1::GetCardsResponse;
using card::v1::EThemeType;

GetCardsResponse GetCardsHandler::Handle(
    const card::v1::GetCardsRequest& req) const {
    GetCardsResponse resp;
    for (const auto& id : req.offer_ids()) {
        const ProductData* data = loader_.find(id);
        if (!data) continue;

        EThemeType theme       = GetThemeType(req.surface(), req.theme_override());
        const ThemeConfig& cfg = GetThemeConfig(theme);
        try {
            auto builder = GetBuilder(req.surface(), *data, cfg);
            *resp.add_cards() = builder->Build(theme);
        } catch (...) {
            FallbackBuilder fb(*data);
            *resp.add_cards() = fb.Build(EThemeType::GRID_FEED);
        }
    }
    return resp;
}
