#include "theme_utils.h"
#include <unordered_map>

using market::snippet::v1::EThemeType;
using market::snippet::v1::ESurface;

EThemeType GetThemeType(ESurface surface, const std::string& theme_override) {
    if (!theme_override.empty()) {
        static const std::unordered_map<std::string, EThemeType> kNames = {
            {"GRID_FEED",   EThemeType::GRID_FEED},
            {"GRID_VISUAL", EThemeType::GRID_VISUAL},
            {"GRID_FULL",   EThemeType::GRID_FULL},
            {"LIST_SHORT",  EThemeType::LIST_SHORT},
            {"LIST_CART",   EThemeType::LIST_CART},
        };
        auto it = kNames.find(theme_override);
        if (it != kNames.end()) return it->second;
    }
    switch (surface) {
        case ESurface::SEARCH:     return EThemeType::GRID_FEED;
        case ESurface::CART:       return EThemeType::LIST_CART;
        case ESurface::MODEL_CARD: return EThemeType::GRID_FULL;
        default:                   return EThemeType::GRID_FEED;
    }
}

const ThemeConfig& GetThemeConfig(EThemeType theme_type) {
    auto it = kThemeConfigs.find(theme_type);
    if (it != kThemeConfigs.end()) return it->second;
    return kThemeConfigs.at(EThemeType::GRID_FEED);
}
