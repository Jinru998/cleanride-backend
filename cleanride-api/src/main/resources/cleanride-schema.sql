DROP TABLE NOTIFICATION    CASCADE CONSTRAINTS;
DROP TABLE ORDERSTATUS     CASCADE CONSTRAINTS;
DROP TABLE RIDE           CASCADE CONSTRAINTS;
DROP TABLE PAYMENT        CASCADE CONSTRAINTS;
DROP TABLE ORDERS         CASCADE CONSTRAINTS;
DROP TABLE USERS          CASCADE CONSTRAINTS;

DROP SEQUENCE USERS_SEQ;
DROP SEQUENCE ORDER_SEQ;
DROP SEQUENCE PAYMENT_SEQ;
DROP SEQUENCE RIDE_SEQ;
DROP SEQUENCE NOTIFICATION_SEQ;
DROP SEQUENCE ORDERSTATUS_SEQ;


CREATE SEQUENCE USER_SEQ START WITH 1 INCREMENT BY 1 NOCYCLE;
CREATE SEQUENCE ORDER_SEQ START WITH 1 INCREMENT BY 1 NOCYCLE;
CREATE SEQUENCE PAYMENT_SEQ START WITH 1 INCREMENT BY 1 NOCYCLE;
CREATE SEQUENCE RIDE_SEQ START WITH 1 INCREMENT BY 1 NOCYCLE;
CREATE SEQUENCE NOTIFICATION_SEQ START WITH 1 INCREMENT BY 1 NOCYCLE;
CREATE SEQUENCE ORDERSTATUS_SEQ START WITH 1 INCREMENT BY 1 NOCYCLE;

-- Create APPUSER table (replaces USER)
CREATE TABLE USERS (
                       ID             NUMBER        NOT NULL,
                       CREATEDAT      TIMESTAMP(6) WITH TIME ZONE,
                       UPDATEDAT      TIMESTAMP(6 )WITH TIME ZONE,
                       EMAIL          VARCHAR2(255) NOT NULL,
                       PHONENUMBER    VARCHAR2(20),
                       FIREBASEID     VARCHAR2(255),
                       ADDRESS        VARCHAR2(500),
                       ORDERID        NUMBER,
                       CONSTRAINT PK_USERS PRIMARY KEY (ID)
);

-- Create ORDR table (replaces ORDER)
CREATE TABLE ORDERS (
                        ID             NUMBER          NOT NULL,
                        STATUS         VARCHAR2(50),
                        CREATEDAT      TIMESTAMP(6) WITH TIME ZONE,
                        UPDATEDAT      TIMESTAMP(6) WITH TIME ZONE,
                        USERID         NUMBER          NOT NULL,
                        PAYMENTID      NUMBER,
                        RIDEID         NUMBER,
                        NOTIFICATIONID NUMBER,
                        CONSTRAINT PK_ORDERS PRIMARY KEY (ID),
                        CONSTRAINT FK_ORDERS_USERID
                            FOREIGN KEY (USERID) REFERENCES USERS (ID)
);

-- Create PAYMENT table
CREATE TABLE PAYMENT (
                         ID             NUMBER        NOT NULL,
                         ORDERID        NUMBER,
                         STATUS         VARCHAR2(50),
                         AMOUNT         NUMBER(10,2),
                         CREATEDAT      TIMESTAMP(6) WITH TIME ZONE,
                         UPDATEDAT      TIMESTAMP(6) WITH TIME ZONE,
                         STRIPECHARGEID NUMBER,
                         CONSTRAINT PK_PAYMENT PRIMARY KEY (ID),
                         CONSTRAINT FK_PAYMENT_ORDERID
                             FOREIGN KEY (ORDERID) REFERENCES ORDERS (ID)
);

-- Create RIDE table
CREATE TABLE RIDE (
                      ID             NUMBER           NOT NULL,
                      ORDERID        NUMBER,
                      RIDETYPE       VARCHAR2(50),
                      UBRERIDEID     VARCHAR2(255),
                      STATUS         VARCHAR2(50),
                      SCHEDULEDTIME  TIMESTAMP(6),
                      CREATEDAT      TIMESTAMP(6) WITH TIME ZONE,
                      UPDATEDAT      TIMESTAMP(6) WITH TIME ZONE,
                      ADDRESS        VARCHAR2(500),
                      CONSTRAINT PK_RIDE PRIMARY KEY (ID),
                      CONSTRAINT FK_RIDE_ORDERID
                          FOREIGN KEY (ORDERID) REFERENCES ORDERS (ID)
);

-- Create NOTIFICATION table
CREATE TABLE NOTIFICATION (
                              ORDERID     NUMBER,
                              TYPE        VARCHAR2(50),
                              CHANNEL     VARCHAR2(50),
                              RECIPIENT   VARCHAR2(255),
                              SUBJECT     VARCHAR2(255),
                              MESSAGE     VARCHAR2(1000),
                              STATUS      VARCHAR2(50),
                              SENTAT      TIMESTAMP(6) WITH TIME ZONE,
                              CREATEDAT   TIMESTAMP(6) WITH TIME ZONE,
                              CONSTRAINT PK_NOTIFICATION PRIMARY KEY (ID),
                              CONSTRAINT FK_NOTIFICATION_ORDERID
                                  FOREIGN KEY (ORDERID) REFERENCES ORDERS (ID)
);

-- Create ORDERSTATUS table
CREATE TABLE ORDERSTATUS (
                             ID             NUMBER           NOT NULL,
                             ORDER_ID       NUMBER           NOT NULL,
                             PREVIOUSSTATUS VARCHAR2(50),
                             NEWSTATUS      VARCHAR2(50),
                             CREATEDAT      TIMESTAMP(6) WITH TIME ZONE,
                             CONSTRAINT PK_ORDERSTATUS PRIMARY KEY (ID),
                             CONSTRAINT FK_ORDERSTATUS_ORDERID
                                 FOREIGN KEY (ORDER_ID) REFERENCES ORDERS (ID)
);

INSERT INTO USERS (ID, CREATEDAT, UPDATEDAT, EMAIL, PHONENUMBER, FIREBASEID, ADDRESS, ORDERID)
VALUES (1,
        TIMESTAMP '2025-01-01 10:00:00 +00:00',
        TIMESTAMP '2025-01-01 12:00:00',
        'alice@example.com',
        '123-456-7890',
        'aliceFirebase123',
        '123 Main St, Springfield',
        NULL);

INSERT INTO USERS (ID, CREATEDAT, UPDATEDAT, EMAIL, PHONENUMBER, FIREBASEID, ADDRESS, ORDERID)
VALUES (2,
        TIMESTAMP '2025-01-01 09:30:00 +00:00',
        TIMESTAMP '2025-01-01 10:30:00',
        'bob@example.com',
        '555-999-0000',
        'bobFirebase456',
        '456 Oak Rd, Shelbyville',
        NULL);

INSERT INTO ORDERS (ID, STATUS, CREATEDAT, UPDATEDAT, USERID, PAYMENTID, RIDEID, NOTIFICATIONID)
VALUES (100,
        'CREATED',
        TIMESTAMP '2025-01-01 10:01:00 +00:00',
        TIMESTAMP '2025-01-01 10:15:00 +00:00',
        1,  -- references USERS.ID=1
        NULL,
        NULL,
        NULL);

INSERT INTO ORDERS (ID, STATUS, CREATEDAT, UPDATEDAT, USERID, PAYMENTID, RIDEID, NOTIFICATIONID)
VALUES (101,
        'COMPLETED',
        TIMESTAMP '2025-01-01 11:00:00 +00:00',
        TIMESTAMP '2025-01-01 11:30:00 +00:00',
        2,  -- references USERS.ID=2
        NULL,
        NULL,
        NULL);

INSERT INTO RIDE (ID, ORDERID, RIDETYPE, UBRERIDEID, STATUS, SCHEDULEDTIME, CREATEDAT, UPDATEDAT, ADDRESS)
VALUES (200,
        100,  -- references ORDERS.ID=100
        'STANDARD',
        'uberRide123',
        'SCHEDULED',
        TIMESTAMP '2025-01-01 10:30:00',
        TIMESTAMP '2025-01-01 10:05:00 +00:00',
        TIMESTAMP '2025-01-01 10:20:00 +00:00',
        '123 Main St');

INSERT INTO PAYMENT (ID, ORDERID, STATUS, AMOUNT, CREATEDAT, UPDATEDAT, STRIPECHARGEID)
VALUES (300,
        100,  -- references ORDERS.ID=100
        'PENDING',
        49.99,
        TIMESTAMP '2025-01-01 10:06:00 +00:00',
        TIMESTAMP '2025-01-01 10:25:00 +00:00',
        999888777);

INSERT INTO ORDERSTATUS (ID, ORDER_ID, PREVIOUSSTATUS, NEWSTATUS, CREATEDAT)
VALUES (400,
        100,   -- references ORDERS.ID=100
        'CREATED',
        'PICKING_RIDE',
        TIMESTAMP '2025-01-01 10:06:30 +00:00');

INSERT INTO ORDERSTATUS (ID, ORDER_ID, PREVIOUSSTATUS, NEWSTATUS, CREATEDAT)
VALUES (401,
        100,
        'PICKING_RIDE',
        'COMPLETED',
        TIMESTAMP '2025-01-01 10:20:30 +00:00');


COMMIT;