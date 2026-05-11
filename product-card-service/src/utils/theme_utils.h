#pragma once
#include "product_card_service.pb.h"
#include "../const/theme_configs.h"
#include <string>

market::snippet::v1::EThemeType GetThemeType(
    market::snippet::v1::ESurface surface,
    const std::string& theme_override);

const ThemeConfig& GetThemeConfig(market::snippet::v1::EThemeType theme_type);
