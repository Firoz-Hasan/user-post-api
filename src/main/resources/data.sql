insert into User(firstname, lastname, email) values('abul', 'rahman', 'abul@gmail.com');
insert into User(firstname, lastname, email) values('liakat', 'kapur', 'kany@gmail.com');


insert into Post(postname, description, userid) values('corona', 'corona is pandemic', 1);
insert into Post(postname, description, userid) values('ebola', 'ebola is pandemic', 1);
insert into Post(postname, description, userid) values('diarrhea', 'diarrhea is pandemic', 2);



insert into pages (pagemembers, pagename, pagedescription) values(20000, 'aiesec', 'aiesec is great');
insert into pages (pagemembers, pagename, pagedescription) values(1000, 'chak de', 'chak is foul');

insert into Tags(tagname) values('kahonah');
insert into Tags(tagname) values('savefiroz');
insert into Tags(tagname) values('aaaaaaaaaaaaaaaaaa');
insert into Tags(tagname) values('bbbbbbbbbbbbbbbb');
insert into Tags(tagname) values('ccccccccccccccccccc');
insert into Tags(tagname) values('dddddddddddddd');
insert into Tags(tagname) values('eeeeeeeeeeeeeeeee');
insert into Tags(tagname) values('ffffffffffffffff');
insert into Tags(tagname) values('gggggggggggg');
insert into Tags(tagname) values('hhhhhhhhhhhhh');
insert into Tags(tagname) values('iiiiiiiiiiii');
insert into Tags(tagname) values('jjjjjjjjjjjjjjjjjjj');
insert into Tags(tagname) values('kkkkkkkkkkkkkkkkkkkahonah');
insert into Tags(tagname) values('llllllllllllllllllllll');
insert into Tags(tagname) values('mmmmmmmmmmmmmmmmmm');
insert into Tags(tagname) values('nnnnnnnnnnnnnnnnnnnnnnnnnnnn');


insert into USER_PAGES (user_id, page_id) values(1, 1);
insert into USER_PAGES (user_id, page_id) values(1, 2);

insert into POST_TAGS (post_id, tag_id) values(1, 1);
insert into POST_TAGS (post_id, tag_id) values(1, 2);


INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_MODERATOR');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');