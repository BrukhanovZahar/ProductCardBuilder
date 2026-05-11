#pragma once
#include "snippet_service.pb.h"
#include <unordered_map>
#include <optional>

struct ThemeConfig {
    bool with_title        = true;
    bool with_price        = true;
    bool with_gallery      = true;
    bool with_rating       = true;
    bool with_cart_button  = true;
    bool with_delivery     = false;
    bool with_signal       = false;
    std::optional<int> max_gallery_images;
    bool is_horizontal     = false;
};

extern const std::unordered_map<market::snippet::v1::EThemeType, ThemeConfig> kThemeConfigs;
