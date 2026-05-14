#pragma once
#include "product_card_service.pb.h"
#include "../builders/base_card_builder.h"
#include "../data/product_data.h"
#include "../const/theme_configs.h"
#include <memory>

std::unique_ptr<BaseCardBuilder> GetBuilder(
    card::v1::ESurface surface,
    const ProductData& data,
    const ThemeConfig& config);
