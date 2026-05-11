#include <grpcpp/grpcpp.h>
#include "service/product_card_service.h"
#include "data/product_loader.h"
#include <iostream>
#include <string>

int main(int argc, char** argv) {
    std::string products_path = "data/products.json";
    if (argc > 1) products_path = argv[1];

    std::cout << "Loading products from: " << products_path << std::endl;
    ProductLoader loader(products_path);

    ProductCardServiceImpl service(loader);

    grpc::ServerBuilder builder;
    builder.AddListeningPort("0.0.0.0:50051", grpc::InsecureServerCredentials());
    builder.RegisterService(&service);

    auto server = builder.BuildAndStart();
    std::cout << "Product card service listening on 0.0.0.0:50051" << std::endl;
    server->Wait();
    return 0;
}
