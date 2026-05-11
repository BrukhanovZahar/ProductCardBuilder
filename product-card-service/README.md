# product-card-service

C++ gRPC-сервис, который по списку идентификаторов офферов возвращает сниппеты карточек товаров, адаптированные под нужную поверхность (поиск, корзина, карточка модели) и тему оформления.

## Требования

- CMake 3.16+
- Clang / GCC с поддержкой C++17
- gRPC + Protobuf (установить через Homebrew: `brew install grpc protobuf`)
- nlohmann_json (`brew install nlohmann-json`)

## Сборка

```bash
cd product-card-service
mkdir -p build && cd build
cmake ..
make -j4
```

Бинарник появится по пути `build/product_card_service`.

## Запуск

```bash
# Из директории build/ — данные берутся из build/data/products.json (скопировано cmake)
./product_card_service

# Явный путь к файлу с товарами
./product_card_service /path/to/products.json
```

Сервис слушает на `0.0.0.0:50051`.

## Данные

Файл `data/products.json` — список офферов в формате:

```json
{
  "offers": [
    {
      "id": "offer_001",
      "title": "...",
      "images": ["https://..."],
      "current_price": 89999,
      "original_price": 99999,
      "currency": "RUB",
      "rating_value": 4.7,
      "rating_count": 312,
      "in_cart": false,
      "delivery_text": "Доставка завтра",
      "signal_text": "Хит продаж",
      "signal_type": "bestseller"
    }
  ]
}
```

## gRPC API

Определение протокола: [`../proto/product_card_service.proto`](../proto/product_card_service.proto)

### Метод `GetSnippets`

**Запрос:**

| Поле | Тип | Описание |
|------|-----|----------|
| `offer_ids` | `repeated string` | Идентификаторы офферов |
| `surface` | `ESurface` | Поверхность: `SEARCH=1`, `CART=2`, `MODEL_CARD=3` |
| `theme_override` | `string` | Принудительная тема: `GRID_FEED`, `GRID_VISUAL`, `GRID_FULL`, `LIST_SHORT`, `LIST_CART` |

**Ответ:** список `TProductSnippet` — по одному на каждый найденный оффер.

### Пример с grpcurl

```bash
grpcurl -plaintext -d '{
  "offer_ids": ["offer_001", "offer_002"],
  "surface": 1
}' localhost:50051 market.snippet.v1.MarketSnippetService/GetSnippets
```
