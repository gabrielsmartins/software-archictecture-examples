# Event Driven Architecture Examples

This repository contains some examples of **Event Driven Architecture (EDA)** using technologies such as Apache Kafka and AWS EventBrigde.

These projects were developed considering Event Collaboration and other patterns.

The following image represents the concept of Event Collaboration. 
![Event Collaboration Example](https://developer.confluent.io/fbd32003075f83926a09cf0b4aa34e03/event-collaboration.svg)


## Projects

You can see all projects available on this repository

|  Project| Description  |
|--|--|
| Basket Service | |
| Order Service |  |
| Fraud Service |  |
| Inventory Service |  |
| Payment Service |  |
| Shipping Service |  |

### References 
See [Confluent Developer](https://developer.confluent.io/patterns/compositional-patterns/event-collaboration) detailed explanation of Event Collaboration.

## UML diagrams

You can render UML diagrams using [Mermaid](https://mermaidjs.github.io/). For example, this will produce a sequence diagram:

```mermaid
sequenceDiagram
Basket Service ->> Order Service: Submit Order (Order order)
Bob-->>John: How about you John?
Bob--x Alice: I am good thanks!
Bob-x John: I am good thanks!
Note right of John: Bob thinks a long<br/>long time, so long<br/>that the text does<br/>not fit on a row.

Bob-->Alice: Checking with John...
Alice->John: Yes... John, how are you?
```
```
