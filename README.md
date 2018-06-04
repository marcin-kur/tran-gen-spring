<h1>Generator transakcji</h1>

Generator transakcji generuje transakcje wg wzoru:

{
  "id": 1,
  "timestamp": "2018-03-08T12:31:13.000-0100",
  "customer_id": 12,
  "items": [
    {
      "name": "bułeczka",
      "quantity": 3,
      "price": 1.2
    },
    {
      "name": "mleko 3% 1l",
      "quantity": 1,
      "price": 2.3
    }
  ],
  "sum": 4.5
}


- endpoint: http(s)://{server-app}/transactions
- header "Accept": application/json, application/xml, application/yml
- parametry URL: 
    - customersId=1:2
    - dateRange="2018-03-08T00:00:00.000-0100":"2018-03-08T23:59:59.999-0100"
    - itemsCount=1:2
    - itemsQuantity=1:2
    - eventsCount=10