# Snippet Constructor Demo

Демо-реализация двухкомпонентной архитектуры конструктора визуальных элементов
товарных карточек из ВКР «Конструктор визуальных элементов товарных карточек».

## Архитектура

```
POST /api/cards/preview
        │
        ▼
[Kotlin: product-card-builder]   Spring Boot · gRPC client · DivKitRenderer
        │ gRPC GetCards
        ▼
[C++:   product-card-service]   gRPC server · BaseCardBuilder · ThemeConfig
        │ reads
        ▼
product-card-service/data/products.json
```

Серверный компонент (C++) реализует паттерн Template Method через `BaseCardBuilder`,
определяет состав секций карточки через `ThemeConfig`/`kThemeConfigs` и возвращает
`TProductCard` (Protobuf).

Клиентский компонент (Kotlin) десериализует протобаф, выбирает конфигурацию через
`CardRenderConfigRegistry` (Factory + Registry паттерны), собирает компоненты
и рендерит DivKit JSON.

## Сборка и запуск

### Требования

- macOS: `brew install grpc protobuf nlohmann-json cmake`
- JDK 17+, Gradle (через wrapper)

### C++ сервис (порт 50051)

```bash
cd product-card-service
mkdir -p build && cd build
cmake .. && make -j4
./product_card_service
```

### Kotlin сервис (порт 8080)

```bash
cd product-card-builder
./gradlew bootRun
```

## Использование

### Получить DivKit JSON для одного сниппета

```bash
curl -s -X POST http://localhost:8080/api/cards/preview \
  -H "Content-Type: application/json" \
  -d '{"offerIds":["offer_001"],"surface":"SEARCH"}'
```

Скопируйте ответ и вставьте в [divkit.tech/playground](https://divkit.tech/playground) — карточка отобразится справа.

### Параметры запроса

| Поле | Значения | По умолчанию |
|---|---|---|
| `surface` | `SEARCH`, `CART`, `MODEL_CARD` | `SEARCH` |
| `themeOverride` | `GRID_FEED`, `GRID_VISUAL`, `GRID_FULL`, `LIST_SHORT`, `LIST_CART` | определяется surface |

### Тестовые товары

| ID | Товар | Сигнал |
|---|---|---|
| `offer_001` | Samsung Galaxy S24 Ultra | «Осталось 3 шт.» |
| `offer_002` | Sony WH-1000XM5 | «Из-за рубежа, пошлина 1 200 ₽» |
| `offer_003` | Apple MacBook Air M3 | «Осталось 1 шт.» |

### Несколько карточек сразу

```bash
curl -s -X POST http://localhost:8080/api/cards \
  -H "Content-Type: application/json" \
  -d '{"offerIds":["offer_001","offer_002","offer_003"],"surface":"SEARCH"}'
```

## Темы и поверхности

| Surface | Тема по умолчанию | Секции |
|---|---|---|
| SEARCH | GRID_FEED | gallery · title · price · rating · signal · cart_button |
| CART | LIST_CART | gallery · title · price · cart_button · delivery (горизонтально) |
| MODEL_CARD | GRID_FULL | gallery · title · price · rating · signal · delivery · cart_button |
| — | GRID_VISUAL | gallery (large) · price · cart_button |
| — | LIST_SHORT | gallery · title · price · rating · delivery (горизонтально) |
