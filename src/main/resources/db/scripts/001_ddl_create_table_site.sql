CREATE TABLE site (
    id SERIAL PRIMARY KEY NOT NULL,
    domain_name VARCHAR UNIQUE NOT NULL,
    login VARCHAR UNIQUE NOT NULL,
    password VARCHAR UNIQUE NOT NULL
);

COMMENT ON TABLE site IS 'Сайты, зарегистрированные в системе';
COMMENT ON COLUMN site.id IS 'Идентификатор сайта';
COMMENT ON COLUMN site.domain_name IS 'Доменное имя сайта';
COMMENT ON COLUMN site.login IS 'Логин сайта в системе';
COMMENT ON COLUMN site.password IS 'Пароль сайта в системе';