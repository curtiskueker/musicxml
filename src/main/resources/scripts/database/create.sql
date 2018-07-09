-- mysql statements for create database and user, grants
-- edit as necessary for other dialects, usernames, etc.
create user 'musicxml' identified by 'musicxml';
grant all on musicxml.* to 'musicxml';

drop database musicxml;
create database musicxml;
use musicxml;
