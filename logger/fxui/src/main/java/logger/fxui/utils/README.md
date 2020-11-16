# Utils

FXUI uses the following classes to support persistence in the Visit Log.

- **VisitLogDataAccess**: an _interface_ for persistence
  - **LocalVisitLogDataAccess**: to allow client-side persistence
  - **RemoteVisitLogDataAccess**: to allow server-side persistence
