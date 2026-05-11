#pragma once
#include <string>
#include <vector>

struct ProductData {
    std::string              id;
    std::string              title;
    std::vector<std::string> images;
    int64_t                  current_price  = 0;
    int64_t                  original_price = 0;
    std::string              currency;
    float                    rating_value   = 0.f;
    int                      rating_count   = 0;
    std::string              delivery_text;
    int                      stock_count    = 0;
    bool                     is_crossborder = false;
    int                      customs_fee    = 0;
};
