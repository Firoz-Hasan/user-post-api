INSERT INTO user(firstname, lastname, email) VALUES(''abul'', ''rahman'', ''abul@gmail.com'');
INSERT INTO user(firstname, lastname, email) VALUES(''liakat'', ''kapur'', ''kany@gmail.com'');


INSERT INTO post(postname, description, userid) VALUES(''corona'', ''corona is pandemic'', 1);
INSERT INTO post(postname, description, userid) VALUES(''ebola'', ''ebola is pandemic'', 1);
INSERT INTO post(postname, description, userid) VALUES(''diarrhea'', ''diarrhea is pandemic'', 2);



INSERT INTO pages (pagemembers, pagename, pagedescription) VALUES(20000, ''aiesec'', ''aiesec is great'');
INSERT INTO pages (pagemembers, pagename, pagedescription) VALUES(1000, ''chak de'', ''chak is foul'');

INSERT INTO tags(tagname) VALUES(''kahonah'');
INSERT INTO tags(tagname) VALUES(''savefiroz'');
INSERT INTO tags(tagname) VALUES(''aaaaaaaaaaaaaaaaaa'');
INSERT INTO tags(tagname) VALUES(''bbbbbbbbbbbbbbbb'');
INSERT INTO tags(tagname) VALUES(''ccccccccccccccccccc'');
INSERT INTO tags(tagname) VALUES(''dddddddddddddd'');
INSERT INTO tags(tagname) VALUES(''eeeeeeeeeeeeeeeee'');
INSERT INTO tags(tagname) VALUES(''ffffffffffffffff'');


INSERT INTO user_pages (user_id, page_id) VALUES(1, 1);
INSERT INTO user_pages (user_id, page_id) VALUES(1, 2);
INSERT INTO post_tags(post_id, tag_id) VALUES(1, 1);
INSERT INTO post_tags(post_id, tag_id) VALUES(1, 2);


INSERT INTO roles(name) VALUES(''ROLE_USER'');
INSERT INTO roles(name) VALUES(''ROLE_MODERATOR'');
INSERT INTO roles(name) VALUES(''ROLE_ADMIN'');