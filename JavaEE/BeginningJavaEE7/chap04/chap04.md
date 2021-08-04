# Chapter 4 Java Persistence API

## Anatomy of An Entity

- An Entity (to be persistent) must be annotated with **@javax.persistence.Entity** (or denotated in XML Descriptor).
- Primary key must be annotated with **@javax.persistence.ID**.
- Entity class must have public/protected default constructor.
- Entity class must not be enum or interface.
- Entity class must not be final, including its methods or persistent instance variables.
- If an entity instance is passed by value as a detached object, it must implement **Serializable** interface.

## Object-Relational Mapping

Objects in Java can be mapped to RDBMS through metadata. Metadata are written through:

- Annotations
- XML descriptors
