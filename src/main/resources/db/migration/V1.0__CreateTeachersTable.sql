create table teachers
(
    id            bigserial primary key,
    first_name    text not null,
    last_name     text not null,
    creation_date timestamp(6) default now(),
    date_of_employment timestamp(6) default now(),
    weekly_hours integer default 20,
    birthday timestamp(6) default now()
);

-- alter sequence teachers_id_seq RESTART 1000;

create table lessons (
        id bigint generated by default as identity,
        name varchar(255),
        subject_id bigint,
        teacher_id bigint,
        topic_id bigint,
        primary key (id)
    );

    create table subjects (
        id bigint generated by default as identity,
        color varchar(255),
        designation varchar(255),
        topic_id bigint,
        primary key (id)
    );

    alter table if exists teachers 
       alter column first_name set data type varchar(255);

    alter table if exists teachers 
       alter column last_name set data type varchar(255);

    create table topics (
        id bigint generated by default as identity,
        name varchar(255),
        primary key (id)
    );

    alter table if exists lessons 
       add constraint FKe94a0k21xpi7glv89af90lwjv 
       foreign key (subject_id) 
       references subjects;

    alter table if exists lessons 
       add constraint FKbr76cuebuufbbltujpfq04tto 
       foreign key (teacher_id) 
       references teachers;

    alter table if exists lessons 
       add constraint FKn1lqfilldva7f2y31fxf3p4p1 
       foreign key (topic_id) 
       references topics;
/*
INSERT INTO teachers (first_name, last_name, creation_date) VALUES ('James', 'Smith', '2023-01-15');
INSERT INTO teachers (first_name, last_name, creation_date) VALUES ('Maria', 'Garcia', '2023-02-01'); 
INSERT INTO teachers (first_name, last_name, creation_date) VALUES ('Robert', 'Johnson', '2023-02-15');
INSERT INTO teachers (first_name, last_name, creation_date) VALUES ('Lisa', 'Brown', '2023-03-01');
INSERT INTO teachers (first_name, last_name, creation_date) VALUES ('Michael', 'Davis', '2023-03-15');
INSERT INTO teachers (first_name, last_name, creation_date) VALUES ('Emma', 'Wilson', '2023-04-01');
INSERT INTO teachers (first_name, last_name, creation_date) VALUES ('William', 'Taylor', '2023-04-15');
INSERT INTO teachers (first_name, last_name, creation_date) VALUES ('Sophie', 'Anderson', '2023-05-01');
INSERT INTO teachers (first_name, last_name, creation_date) VALUES ('David', 'Martinez', '2023-05-15');
INSERT INTO teachers (first_name, last_name, creation_date) VALUES ('Anna', 'Thomas', '2023-06-01');
INSERT INTO teachers (first_name, last_name, creation_date) VALUES ('John', 'White', '2023-06-15');
INSERT INTO teachers (first_name, last_name, creation_date) VALUES ('Sarah', 'Lee', '2023-07-01');
INSERT INTO teachers (first_name, last_name, creation_date) VALUES ('Daniel', 'Harris', '2023-07-15');
INSERT INTO teachers (first_name, last_name, creation_date) VALUES ('Emily', 'Clark', '2023-08-01');
INSERT INTO teachers (first_name, last_name, creation_date) VALUES ('Christopher', 'Lewis', '2023-08-15');
INSERT INTO teachers (first_name, last_name, creation_date) VALUES ('Olivia', 'Walker', '2023-09-01');
INSERT INTO teachers (first_name, last_name, creation_date) VALUES ('Andrew', 'Hall', '2023-09-15');
INSERT INTO teachers (first_name, last_name, creation_date) VALUES ('Isabella', 'Young', '2023-10-01');
INSERT INTO teachers (first_name, last_name, creation_date) VALUES ('Matthew', 'King', '2023-10-15');
INSERT INTO teachers (first_name, last_name, creation_date) VALUES ('Victoria', 'Wright', '2023-11-01');
*/

INSERT INTO topics (name) VALUES ('Deutsch');
INSERT INTO topics (name) VALUES ('Englisch');
INSERT INTO topics (name) VALUES ('Mathe');
INSERT INTO topics (name) VALUES ('Physik');
INSERT INTO topics (name) VALUES ('Sport');

INSERT INTO subjects (designation, topic_id) VALUES ('Biometrische Formeln', (SELECT id FROM topics WHERE name = 'Mathe'));
INSERT INTO subjects (designation, topic_id) VALUES ('Quadratische Gleichungen', (SELECT id FROM topics WHERE name = 'Mathe'));
INSERT INTO subjects (designation, topic_id) VALUES ('Integralrechnung', (SELECT id FROM topics WHERE name = 'Mathe'));

INSERT INTO subjects (designation, topic_id) VALUES ('Lyrik der Romantik', (SELECT id FROM topics WHERE name = 'Deutsch'));
INSERT INTO subjects (designation, topic_id) VALUES ('Faust I & II', (SELECT id FROM topics WHERE name = 'Deutsch'));
INSERT INTO subjects (designation, topic_id) VALUES ('Expressionismus', (SELECT id FROM topics WHERE name = 'Deutsch'));

INSERT INTO subjects (designation, topic_id) VALUES ('Shakespeare Plays', (SELECT id FROM topics WHERE name = 'Englisch'));
INSERT INTO subjects (designation, topic_id) VALUES ('Business English', (SELECT id FROM topics WHERE name = 'Englisch'));

INSERT INTO subjects (designation, topic_id) VALUES ('Mechanik', (SELECT id FROM topics WHERE name = 'Physik'));
INSERT INTO subjects (designation, topic_id) VALUES ('Elektromagnetismus', (SELECT id FROM topics WHERE name = 'Physik'));
INSERT INTO subjects (designation, topic_id) VALUES ('Optik', (SELECT id FROM topics WHERE name = 'Physik'));

INSERT INTO subjects (designation, topic_id) VALUES ('Leichtathletik', (SELECT id FROM topics WHERE name = 'Sport'));
INSERT INTO subjects (designation, topic_id) VALUES ('Ballsportarten', (SELECT id FROM topics WHERE name = 'Sport'));
INSERT INTO subjects (designation, topic_id) VALUES ('Gymnastik', (SELECT id FROM topics WHERE name = 'Sport'));
