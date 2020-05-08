CREATE TABLE RELEASES(
  ID                          bigserial PRIMARY KEY     NOT NULL ,
  RELEASE_ID                  VARCHAR (200)             NOT NULL ,
  SPRINT_ID                   VARCHAR (200)             NULL ,
  VERSION                     VARCHAR (100)             NOT NULL ,
  STATUS                      VARCHAR (100)             NOT NULL ,
  CREATE_DATE                 DATE                      NOT NULL ,
  RELEASE_DATE                DATE                      NOT NULL ,
  DESCRIPTION                 VARCHAR (200)             NOT NULL
);

CREATE INDEX RELEASES_ID_INDEX
  ON RELEASES(ID);
