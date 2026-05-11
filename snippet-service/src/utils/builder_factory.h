#pragma once
#include "snippet_service.pb.h"
#include "../builders/base_snippet_builder.h"
#include "../data/product_data.h"
#include "../const/theme_configs.h"
#include <memory>

std::unique_ptr<BaseSnippetBuilder> GetBuilder(
    market::snippet::v1::ESurface surface,
    const ProductData& data,
    const ThemeConfig& config);
