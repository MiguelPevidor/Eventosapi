CREATE TABLE event (
    id UUID  PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description VARCHAR(250) NOT NULL,
    img_url TEXT NOT NULL,
    event_url TEXT NOT NULL,
    date TIMESTAMP NOT NULL,
    remote BOOLEAN NOT NULL
);