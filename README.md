# Product Catalog Service

Spring Boot REST service for product/category retrieval, creation, replacement, role-aware lookup, and paginated product-name search. It persists products in MySQL and registers with Eureka.

## Architecture and classes

`ProductController` maps HTTP requests to `IProductService`; `StorageProductService` is the primary implementation and uses `ProductRepo` plus a `RestTemplate` user lookup. `SearchController` delegates to `JpaSearchService` / `IsearchService`. JPA models are `Product`, `Category`, and `Role`; DTOs separate request/response data.

## API (as mapped in source)

| Method | Path | Purpose |
|---|---|---|
| GET | `/products` | List products |
| GET | `/products/{id}` | Fetch product by ID |
| GET | `/products/{userId}/{productId}` | Product lookup after a user-service request |
| POST | `/products` | Create product |
| PUT | `/products/{id}` | Replace product (service implementation currently returns `null`) |
| POST | `/search` | Paginated product-name search |
| GET | `/` | Basic service welcome response |

The search request contains `query`, `pageNumber`, and `pageLimit`; the source passes these to Spring Data `PageRequest`.

## Local run

Requires Java 17, Maven (or `mvnw.cmd`), MySQL database `productcatalogservice`, Eureka at `localhost:8761`, and the auth service at `localhost:9000` for the user/product lookup path. The configured service port is `5000`.

```powershell
.\mvnw.cmd spring-boot:run
```

## Known gaps and safety notes

- Supply MySQL credentials through local environment-backed properties. Never commit database passwords or other secrets.
- `replaceProduct()` is unimplemented. The user lookup calls a hard-coded localhost URL, and the auth `/user` mapping currently does not bind the requested ID.
- The role-aware method only checks that a user response exists; it does not enforce role-based product access.
- Add schema migrations, input/pagination validation, not-found behavior, integration tests, and controlled query logging. `ddl-auto=update` and SQL logging are development conveniences, not production defaults.
