CREATE TABLE IF NOT EXISTS time
(
    century FLOAT PRIMARY KEY NOT NULL CHECK ( century > 27 AND (century < 70000 OR century > 15000))
    );


CREATE TABLE IF NOT EXISTS human
(
    human_id      SERIAL PRIMARY KEY,
    name          VARCHAR(100) NOT NULL,
    surname       VARCHAR(100) NOT NULL,
    birthday_date DATE         NOT NULL,
    fate          text,
    time          FLOAT REFERENCES time (century),
    user_id       BIGINT REFERENCES "user" (user_id)
    );


CREATE TABLE IF NOT EXISTS "user"
(
    user_id      SERIAL PRIMARY KEY,
    login        VARCHAR(255) NOT NULL UNIQUE,
    password     VARCHAR(255) NOT NULL,
    phone        VARCHAR(20)  NOT NULL,
    email        VARCHAR(100) NOT NULL UNIQUE,
    created_date DATE         NOT NULL DEFAULT CURRENT_DATE,
    human_id     BIGINT REFERENCES human (human_id)
    );


CREATE TABLE IF NOT EXISTS role
(
    role_id SERIAL PRIMARY KEY,
    name    VARCHAR(255) UNIQUE NOT NULL
    );

CREATE TABLE IF NOT EXISTS ref_user_role
(
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    CONSTRAINT user_id_fk FOREIGN KEY (user_id) REFERENCES "user"
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT role_id_fk FOREIGN KEY (role_id) REFERENCES role
    ON DELETE CASCADE
    ON UPDATE CASCADE
    );

CREATE TABLE IF NOT EXISTS status
(
    name VARCHAR(100) UNIQUE NOT NULL
    );

CREATE TABLE IF NOT EXISTS type
(
    name VARCHAR(100) UNIQUE NOT NULL
    );

CREATE TABLE IF NOT EXISTS event
(
    event_id     SERIAL PRIMARY KEY,
    name         VARCHAR(100) NOT NULL,
    description  text,
    created_date DATE         NOT NULL DEFAULT CURRENT_DATE,
    start_time   FLOAT        NOT NULL REFERENCES time (century),
    end_time     FLOAT REFERENCES time (century)
    );

CREATE TABLE IF NOT EXISTS ref_event_human
(
    human_id BIGINT NOT NULL,
    event_id BIGINT NOT NULL,
    CONSTRAINT human_id_fk FOREIGN KEY (human_id) REFERENCES human
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT event_id_fk FOREIGN KEY (event_id) REFERENCES event
    ON DELETE CASCADE
    ON UPDATE CASCADE
    );

CREATE TABLE IF NOT EXISTS research_data
(
    research_data_id SERIAL PRIMARY KEY,
    name             VARCHAR(100) NOT NULL,
    description      text,
    created_date     DATE         NOT NULL DEFAULT CURRENT_DATE,
    modified_date    DATE         NOT NULL DEFAULT CURRENT_DATE,
    created_by       BIGINT       NOT NULL REFERENCES "user" (user_id),
    modified_by      BIGINT       NOT NULL REFERENCES "user" (user_id),
    human_id         BIGINT REFERENCES human (human_id),
    event_id         BIGINT REFERENCES event (event_id)
    );



CREATE TABLE IF NOT EXISTS mor
(
    mor_id          SERIAL PRIMARY KEY,
    success_percent FLOAT CHECK ( success_percent >= 0 AND success_percent <= 100),
    description     TEXT,
    created_date    DATE   NOT NULL DEFAULT CURRENT_DATE,
    modified_date   DATE   NOT NULL DEFAULT CURRENT_DATE,
    created_by      BIGINT NOT NULL REFERENCES "user" (user_id),
    modified_by     BIGINT NOT NULL REFERENCES "user" (user_id)
    );



CREATE TABLE IF NOT EXISTS impact_mor_on_event
(
    mor_id            BIGINT      NOT NULL,
    event_id          BIGINT      NOT NULL,
    CONSTRAINT mor_id_fk FOREIGN KEY (mor_id) REFERENCES mor
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT event_id_fk FOREIGN KEY (event_id) REFERENCES event
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    modification_type VARCHAR(50) NOT NULL,
    description       TEXT

    );

CREATE TABLE IF NOT EXISTS analytic_order_info
(
    analytic_order_info_id SERIAL PRIMARY KEY,
    research_data_id       BIGINT NOT NULL REFERENCES research_data (research_data_id),
    human_id               BIGINT REFERENCES human (human_id),
    event_id               BIGINT REFERENCES event (event_id)
    );

CREATE TABLE IF NOT EXISTS ref_analytic_order_info_mor
(
    analytic_order_info_id BIGINT NOT NULL,
    mor_id                 BIGINT NOT NULL,
    CONSTRAINT analytic_order_info_id_fk FOREIGN KEY (analytic_order_info_id) REFERENCES analytic_order_info
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT mor_id_fk FOREIGN KEY (mor_id) REFERENCES mor
    ON DELETE CASCADE
    ON UPDATE CASCADE
    );
CREATE TABLE IF NOT EXISTS product
(
    product_id   SERIAL PRIMARY KEY,
    name         VARCHAR(100) NOT NULL,
    description  text,
    created_date DATE         NOT NULL DEFAULT CURRENT_DATE,
    owner        BIGINT REFERENCES "user" (user_id),
    time_current FLOAT        NOT NULL REFERENCES time (century)
);

CREATE TABLE IF NOT EXISTS product_order_info
(
    product_order_info_id SERIAL PRIMARY KEY,
    product_id            BIGINT NOT NULL UNIQUE REFERENCES product (product_id),
    from_user             BIGINT NOT NULL REFERENCES "user" (user_id),
    to_user               BIGINT NOT NULL REFERENCES "user" (user_id),
    from_time             FLOAT  NOT NULL REFERENCES time (century),
    to_time               FLOAT  NOT NULL REFERENCES time (century)
);

CREATE TABLE IF NOT EXISTS "order"
(
    order_id            SERIAL PRIMARY KEY,
    created_date        DATE         NOT NULL DEFAULT CURRENT_DATE,
    modified_date       DATE         NOT NULL DEFAULT CURRENT_DATE,
    created_by          BIGINT       NOT NULL REFERENCES "user" (user_id),
    modified_by         BIGINT       NOT NULL REFERENCES "user" (user_id),
    order_type          VARCHAR(100) NOT NULL REFERENCES type (name),
    order_status        VARCHAR(100) NOT NULL REFERENCES status (name),
    product_order_info  BIGINT REFERENCES product_order_info (product_order_info_id),
    analytic_order_info BIGINT REFERENCES analytic_order_info (analytic_order_info_id)
    );
