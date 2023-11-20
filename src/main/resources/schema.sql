create table board (
    id BIGINT(20) auto_increment,
    title varchar(255) NOT NULL,
    date_creation timestamp,
    date_last_activity timestamp
);