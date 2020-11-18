# Source code for JSON parsing

The persistence layer consists of all serializing and deserializing classes for both Visit and VisitLog. It also handles reading Building data from a `.json` file.

## Class diagram

Class diagrams are found in [logger/diagrams](logger/diagrams).

![Json class diagram](logger/diagrams/json_core_class_diagram.png)

### Description

**VisitLogPersistence** delegates the job of either reading or writing of a **VisitLog**. The **VisitLogModule** creates serializers and deserializers for both Visit and VisitLog.

When deserializing, the module first (after creating the deserializers) deserializes VisitLog using the **VisitDeserializer**. It breaks the log down to individual visits, which it send to the **VisitDeserializer**. Then each visit is written to file.

When serializing, the process works backwards, first reading json objects, then generating Visits from the json data, and finally combining them into a visit log.
