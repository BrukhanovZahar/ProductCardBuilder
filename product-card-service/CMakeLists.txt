cmake_minimum_required(VERSION 3.16)
project(product_card_service CXX)
set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

if(APPLE)
  if(EXISTS "/opt/homebrew")
    list(PREPEND CMAKE_PREFIX_PATH "/opt/homebrew")
  else()
    list(PREPEND CMAKE_PREFIX_PATH "/usr/local")
  endif()
endif()

find_package(gRPC     CONFIG REQUIRED)
find_package(Protobuf REQUIRED)
find_package(nlohmann_json REQUIRED)

set(PROTO_SRC "${CMAKE_SOURCE_DIR}/../proto/product_card_service.proto")
set(PROTO_DIR "${CMAKE_SOURCE_DIR}/../proto")
set(PROTO_OUT "${CMAKE_BINARY_DIR}/generated")
file(MAKE_DIRECTORY "${PROTO_OUT}")

get_target_property(GRPC_CPP_PLUGIN gRPC::grpc_cpp_plugin LOCATION)

add_custom_command(
  OUTPUT
    "${PROTO_OUT}/product_card_service.pb.cc"
    "${PROTO_OUT}/product_card_service.pb.h"
    "${PROTO_OUT}/product_card_service.grpc.pb.cc"
    "${PROTO_OUT}/product_card_service.grpc.pb.h"
  COMMAND ${Protobuf_PROTOC_EXECUTABLE}
    "--proto_path=${PROTO_DIR}"
    "--cpp_out=${PROTO_OUT}"
    "--grpc_out=${PROTO_OUT}"
    "--plugin=protoc-gen-grpc=${GRPC_CPP_PLUGIN}"
    "${PROTO_SRC}"
  DEPENDS "${PROTO_SRC}"
)

configure_file(
  "${CMAKE_SOURCE_DIR}/data/products.json"
  "${CMAKE_BINARY_DIR}/data/products.json"
  COPYONLY
)

file(GLOB_RECURSE APP_SRCS "${CMAKE_SOURCE_DIR}/src/*.cpp")

add_executable(product_card_service
  ${APP_SRCS}
  "${PROTO_OUT}/product_card_service.pb.cc"
  "${PROTO_OUT}/product_card_service.grpc.pb.cc"
)

target_include_directories(product_card_service PRIVATE
  "${CMAKE_SOURCE_DIR}/src"
  "${PROTO_OUT}"
)

target_link_libraries(product_card_service
  gRPC::grpc++
  gRPC::grpc++_reflection
  protobuf::libprotobuf
  nlohmann_json::nlohmann_json
)
