insert into user(firstname, lastname, email) values('abul', 'rahman', 'abul@gmail.com');
insert into user(firstname, lastname, email) values('liakat', 'kapur', 'kany@gmail.com');


insert into post(postname, description, userid) values('corona', 'corona is pandemic', 1);
insert into post(postname, description, userid) values('ebola', 'ebola is pandemic', 1);
insert into post(postname, description, userid) values('diarrhea', 'diarrhea is pandemic', 2);



insert into pages (pagemembers, pagename, pagedescription) values(20000, 'aiesec', 'aiesec is great');
insert into pages (pagemembers, pagename, pagedescription) values(1000, 'chak de', 'chak is foul');

insert into tags(tagname) values('kahonah');
insert into tags(tagname) values('savefiroz');
insert into tags(tagname) values('aaaaaaaaaaaaaaaaaa');
insert into tags(tagname) values('bbbbbbbbbbbbbbbb');
insert into tags(tagname) values('ccccccccccccccccccc');
insert into tags(tagname) values('dddddddddddddd');
insert into tags(tagname) values('eeeeeeeeeeeeeeeee');
insert into tags(tagname) values('ffffffffffffffff');
insert into tags(tagname) values('gggggggggggg');
insert into tags(tagname) values('hhhhhhhhhhhhh');
insert into tags(tagname) values('iiiiiiiiiiii');
insert into tags(tagname) values('jjjjjjjjjjjjjjjjjjj');
insert into tags(tagname) values('kkkkkkkkkkkkkkkkkkkahonah');
insert into tags(tagname) values('llllllllllllllllllllll');
insert into tags(tagname) values('mmmmmmmmmmmmmmmmmm');
insert into tags(tagname) values('nnnnnnnnnnnnnnnnnnnnnnnnnnnn');


insert into user_pages (user_id, page_id) values(1, 1);
insert into user_pages (user_id, page_id) values(1, 2);

insert into post_tags (post_id, tag_id) values(1, 1);
insert into post_tags (post_id, tag_id) values(1, 2);


INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_MODERATOR');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');