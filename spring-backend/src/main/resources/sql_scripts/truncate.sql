TRUNCATE ref_user_role, "role", ref_event_human, impact_mor_on_event,
    ref_analytic_order_info_mor, mor, "order", status, "type", product_order_info,
    analytic_order_info, research_data, "event", product, "user", human, "time";

ALTER SEQUENCE human_human_id_seq RESTART WITH 1;
ALTER SEQUENCE user_user_id_seq RESTART WITH 1;
ALTER SEQUENCE role_role_id_seq RESTART WITH 1;
ALTER SEQUENCE event_event_id_seq RESTART WITH 1;
ALTER SEQUENCE research_data_research_data_id_seq RESTART WITH 1;
ALTER SEQUENCE product_order_info_product_order_info_id_seq RESTART WITH 1;
ALTER SEQUENCE mor_mor_id_seq RESTART WITH 1;
ALTER SEQUENCE analytic_order_info_analytic_order_info_id_seq RESTART WITH 1;
ALTER SEQUENCE order_order_id_seq RESTART WITH 1;
ALTER SEQUENCE product_product_id_seq RESTART WITH 1;