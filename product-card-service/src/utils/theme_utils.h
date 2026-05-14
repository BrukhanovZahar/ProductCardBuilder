#pragma once
#include "product_card_service.pb.h"
#include "../const/theme_configs.h"
#include <string>

card::v1::EThemeType GetThemeType(
    card::v1::ESurface surface,
    const std::string& theme_override);

const ThemeConfig& GetThemeConfig(card::v1::EThemeType theme_type);
