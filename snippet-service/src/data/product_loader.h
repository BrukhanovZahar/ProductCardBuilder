#pragma once
#include "product_data.h"
#include <unordered_map>
#include <string>

class ProductLoader {
public:
    explicit ProductLoader(const std::string& json_path);
    const ProductData* find(const std::string& offer_id) const;
private:
    std::unordered_map<std::string, ProductData> offers_;
};
