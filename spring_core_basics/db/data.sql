insert into news (news_id, short_text, full_text, creation_date, modification_date) 
     values (1, 'Police release photo of London terror attack suspect', 
             'Police Friday released a photograph of the British national believed to be behind this week deadly terror attack in London.',
             CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into author (author_id, name) values (1, 'K. Smith');
insert into news_authors (news_id, author_id) values (1, 1);