CREATE TABLE NEWS(news_id bigint not null auto_increment primary key,
                  short_text varchar(255),
                  full_text varchar(255),
                  creation_date TIMESTAMP WITH TIME ZONE,
                  modification_date TIMESTAMP WITH TIME ZONE);
ALTER TABLE NEWS ALTER COLUMN news_id RESTART WITH 10;
CREATE TABLE AUTHOR(author_id bigint not null auto_increment primary key, name varchar(64));
ALTER TABLE AUTHOR ALTER COLUMN author_id RESTART WITH 10;
CREATE TABLE NEWS_AUTHORS (news_id bigint not null, author_id bigint not null);
ALTER TABLE NEWS_AUTHORS ADD PRIMARY KEY (news_id, author_id);

