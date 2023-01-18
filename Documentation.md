## API spec

## Create Product

Request:

- Method: POST
- End Point: `api/products`
- Header :
    - Content-Type: application/json
    - Accept: application/json
- Body :

```json
{
  "id": "string, unique",
  "name": "string",
  "price": "string",
  "quantity": "integer"
}
```

- Response :

```json
{
  "code": "number",
  "status": "string",
  "data": {
    "id": "string, unique",
    "name": "string",
    "price": "string",
    "quantity": "integer",
    "createdAt": "date",
    "updatedAt": "date"
  }
}
```

## Get Product

Request:

- Method: GET
- End Point: `api/products/{id_product}`
- Header :
    - Accept: application/json
- Response :

```json
{
  "code": "number",
  "status": "string",
  "data": {
    "id": "string",
    "name": "string",
    "price": "string",
    "quantity": "integer",
    "createdAt": "date",
    "updatedAt": "date"
  }
}
```

## Update Product

Request:

- Method: PUT
- End Point: `api/products/{id_product}`
- Header :
    - Content-Type: application/json
    - Accept: application/json
- Body :

```json
{
  "name": "string",
  "price": "string",
  "quantity": "integer"
}
```

- Response :

```json
{
  "code": "number",
  "status": "string",
  "data": {
    "id": "string",
    "name": "string",
    "price": "string",
    "quantity": "integer",
    "createdAt": "date",
    "updatedAt": "date"
  }
}
```

## List Product

Request:

- Method: GET
- End Point: `api/products`
- Header :
    - Accept: application/json
- Query Param:
    - size : number,
    - page: number
- Response :

```json
{
  "code": "number",
  "status": "string",
  "data": [
    {
      "id": "string",
      "name": "string",
      "price": "string",
      "quantity": "integer",
      "createdAt": "date",
      "updatedAt": "date"
    }
  ]
}
```

## Delete Product

Request:

- Method: DELETE
- End Point: `api/products/{id_product}`
- Header :
    - Accept: application/json
- Response:

```json
{
  "code": "number",
  "status": "string"
}

```