#include "get_snippets_handler.h"
#include "../utils/theme_utils.h"
#include "../utils/builder_factory.h"
#include "../builders/fallback_builder.h"

using market::snippet::v1::GetSnippetsResponse;
using market::snippet::v1::EThemeType;

GetSnippetsResponse GetSnippetsHandler::Handle(
    const market::snippet::v1::GetSnippetsRequest& req) const {
    GetSnippetsResponse resp;
    for (const auto& id : req.offer_ids()) {
        const ProductData* data = loader_.find(id);
        if (!data) continue;

        EThemeType theme       = GetThemeType(req.surface(), req.theme_override());
        const ThemeConfig& cfg = GetThemeConfig(theme);
        try {
            auto builder = GetBuilder(req.surface(), *data, cfg);
            *resp.add_snippets() = builder->Build(theme);
        } catch (...) {
            FallbackBuilder fb(*data);
            *resp.add_snippets() = fb.Build(EThemeType::GRID_FEED);
        }
    }
    return resp;
}
