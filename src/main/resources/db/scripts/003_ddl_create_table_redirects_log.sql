CREATE TABLE redirects_log (
    id SERIAL PRIMARY KEY NOT NULL,
    url_id INTEGER NOT NULL REFERENCES url(id),
    client_ip_address VARCHAR,
    datetime TIMESTAMP
);

COMMENT ON TABLE redirects_log IS 'Статистика редиректов';
COMMENT ON COLUMN redirects_log.id IS 'Идентификатор записи';
COMMENT ON COLUMN redirects_log.url_id IS 'Идентификатор URL, на который был осуществлен редирект (FK)';
COMMENT ON COLUMN redirects_log.client_ip_address IS 'IP адрес клиента, перешедшего по сокращенной ссылке';
COMMENT ON COLUMN redirects_log.datetime IS 'Временная метка зарегистрированного редиректа';