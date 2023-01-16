CREATE TABLE url (
    id SERIAL PRIMARY KEY NOT NULL,
    full_url VARCHAR UNIQUE NOT NULL,
    short_url VARCHAR UNIQUE NOT NULL,
    site_id INTEGER NOT NULL REFERENCES site(id)
);

COMMENT ON TABLE url IS 'Перечень URL, хранимых в системе';
COMMENT ON COLUMN url.id IS 'Идентификатор URL';
COMMENT ON COLUMN url.full_url IS 'Полный (исходный) URL';
COMMENT ON COLUMN url.short_url IS 'Сокращенный URL';
COMMENT ON COLUMN url.site_id IS 'Иддентификатор сайта, которому принадлежит URL (FK)';