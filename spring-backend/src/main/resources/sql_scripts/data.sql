
-- TIME
DO
$do$
    BEGIN
        FOR i IN 28..69999 LOOP
                INSERT INTO time (century)
                SELECT  i;
            END LOOP;
    END
$do$;

INSERT INTO role(name)
VALUES ('NOT_EMPLOYEE'),
       ('EMPLOYEE'),
       ('MODERATOR');

INSERT INTO status(name)
VALUES ('ESTABLISHED'),        -- common
       ('CANCELED'),           -- common

       ('IN_THE_BASKET'),      -- product order
       ('IN_TRANSIT'),         -- product order
       ('DELIVERED'),          -- product order

       ('IN_PROGRESS'),        -- analytics research
       ('AWAIT_CONFIRMATION'), -- analytics research
       ('ACCEPTED'),           -- analytics research
       ('NOT_ACCEPTED');       -- analytics research

INSERT INTO type(name)
VALUES ('ANALYTIC_ORDER'), -- order type
       ('PRODUCT_ORDER'),  -- order type

       ('CHANGE_EVENT'),   -- event type
       ('CREATE_EVENT'),   -- event type
       ('ERASE_EVENT' );    -- event type
