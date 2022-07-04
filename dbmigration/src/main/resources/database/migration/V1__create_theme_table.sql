create table theme (
    name text primary key
);

insert into theme (name)
values ('scala'),
       ('java'),
       ('cli'),
       ('python'),
       ('sed'),
       ('awk'),
       ('html'),
       ('css'),
       ('perl');

create table tag (
    name text primary key
);

create table note_tag (
  note_id int not null,
  tag_id int not null,
  constraint note_tag_note_id_fk foreign key (note_id) references note(id),
  constraint note_tag_tag_id_fk foreign key (tag_id) references tag(name)
);

create table note (
    id integer primary key,
    theme_id text not null,
    note text not null,
    constraint note_theme_id foreign key (theme_id) references theme(name)
);


