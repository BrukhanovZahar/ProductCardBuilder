#include "theme_configs.h"
using card::v1::EThemeType;

const std::unordered_map<EThemeType, ThemeConfig> kThemeConfigs = {
    {EThemeType::GRID_FEED,   {true,  true,  true,  true,  true,  false, true,  {1}, false}},
    {EThemeType::GRID_VISUAL, {false, true,  true,  false, true,  false, false, {3}, false}},
    {EThemeType::GRID_FULL,   {true,  true,  true,  true,  true,  true,  true,  {3}, false}},
    {EThemeType::LIST_SHORT,  {true,  true,  true,  true,  false, true,  false, {1}, true }},
    {EThemeType::LIST_CART,   {true,  true,  true,  false, true,  true,  false, {1}, true }},
};
