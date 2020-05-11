CREATE TABLE RELEASES(
  ID                          bigserial PRIMARY KEY     NOT NULL ,
  RELEASE_ID                  VARCHAR (200)             NOT NULL ,
  VERSION                     VARCHAR (100)             NOT NULL ,
  STATUS                      VARCHAR (100)             NULL ,
  CREATE_DATE                 DATE                      NULL ,
  RELEASE_DATE                DATE                      NULL ,
  DESCRIPTION                 VARCHAR (200)             NOT NULL
);

CREATE INDEX RELEASES_ID_INDEX
  ON RELEASES(ID);
