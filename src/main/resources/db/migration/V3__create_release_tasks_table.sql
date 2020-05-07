CREATE TABLE RELEASE_TASKS  (
    ID                          bigserial PRIMARY KEY        NOT NULL ,
    TASK_ID                     VARCHAR (100)                NULL ,
    RELEASE_ID                  bigint                       NULL ,
    FOREIGN KEY (RELEASE_ID) REFERENCES RELEASES
);

CREATE INDEX RELEASE_TASKS_ID_INDEX
  ON RELEASE_TASKS(ID);