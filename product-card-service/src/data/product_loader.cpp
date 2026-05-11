#include "product_loader.h"
#include <nlohmann/json.hpp>
#include <fstream>
#include <stdexcept>

ProductLoader::ProductLoader(const std::string& json_path) {
    std::ifstream f(json_path);
    if (!f.is_open())
        throw std::runtime_error("Cannot open products file: " + json_path);

    auto j = nlohmann::json::parse(f);
    for (auto& o : j.at("offers")) {
        ProductData d;
        d.id             = o.at("id").get<std::string>();
        d.title          = o.at("title").get<std::string>();
        for (auto& img : o.at("images")) d.images.push_back(img.get<std::string>());
        d.current_price  = o.at("current_price").get<int64_t>();
        d.original_price = o.at("original_price").get<int64_t>();
        d.currency       = o.at("currency").get<std::string>();
        d.rating_value   = o.at("rating_value").get<float>();
        d.rating_count   = o.at("rating_count").get<int>();
        d.delivery_text  = o.at("delivery_text").get<std::string>();
        d.stock_count    = o.at("stock_count").get<int>();
        d.is_crossborder = o.at("is_crossborder").get<bool>();
        d.customs_fee    = o.at("customs_fee").get<int>();
        offers_[d.id] = std::move(d);
    }
}

const ProductData* ProductLoader::find(const std::string& offer_id) const {
    auto it = offers_.find(offer_id);
    return it != offers_.end() ? &it->second : nullptr;
}
