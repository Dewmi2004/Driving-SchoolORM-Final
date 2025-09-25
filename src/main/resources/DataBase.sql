-- we don't know how to generate root <with-no-name> (class Root) :(

grant select on performance_schema.* to 'mysql.session'@localhost;

grant trigger on sys.* to 'mysql.sys'@localhost;

grant audit_abort_exempt, firewall_exempt, select, system_user on *.* to 'mysql.infoschema'@localhost;

grant audit_abort_exempt, authentication_policy_admin, backup_admin, clone_admin, connection_admin, firewall_exempt, persist_ro_variables_admin, session_variables_admin, shutdown, super, system_user, system_variables_admin on *.* to 'mysql.session'@localhost;

grant audit_abort_exempt, firewall_exempt, system_user on *.* to 'mysql.sys'@localhost;

grant alter, alter routine, application_password_admin, audit_abort_exempt, audit_admin, authentication_policy_admin, backup_admin, binlog_admin, binlog_encryption_admin, clone_admin, connection_admin, create, create role, create routine, create tablespace, create temporary tables, create user, create view, delete, drop, drop role, encryption_key_admin, event, execute, file, firewall_exempt, flush_optimizer_costs, flush_status, flush_tables, flush_user_resources, group_replication_admin, group_replication_stream, index, innodb_redo_log_archive, innodb_redo_log_enable, insert, lock tables, passwordless_user_admin, persist_ro_variables_admin, process, references, reload, replication client, replication slave, replication_applier, replication_slave_admin, resource_group_admin, resource_group_user, role_admin, select, sensitive_variables_observer, service_connection_admin, session_variables_admin, set_user_id, show databases, show view, show_routine, shutdown, super, system_user, system_variables_admin, table_encryption_admin, telemetry_log_admin, trigger, update, xa_recover_admin, grant option on *.* to root@localhost;

create table courses
(
    course_id      bigint auto_increment
        primary key,
    courseDuration varchar(255) not null,
    courseFee      varchar(255) not null,
    courseName     varchar(255) not null
);

create table instructors
(
    instructor_id          bigint auto_increment
        primary key,
    instructorAvailability varchar(255) not null,
    instructorEmail        varchar(255) not null,
    instructorName         varchar(255) not null,
    instructorPhone        varchar(15)  null,
    constraint UK_7pw75bwu6fpprbw0hxdnpppm7
        unique (instructorEmail)
);

create table students
(
    registerDate   date         not null,
    student_id     bigint auto_increment
        primary key,
    studentPhone   varchar(15)  null,
    registerFee    varchar(255) null,
    studentAddress varchar(255) not null,
    studentEmail   varchar(255) not null,
    studentName    varchar(255) not null,
    constraint UK_onjm5qnx0pvqi86383s4vgrcf
        unique (studentEmail)
);

create table lessons
(
    lesson_id     bigint auto_increment
        primary key,
    date          date         not null,
    status        varchar(255) not null,
    time          varchar(255) not null,
    course_id     bigint       not null,
    instructor_id bigint       not null,
    student_id    bigint       not null,
    constraint FK17ucc7gjfjddsyi0gvstkqeat
        foreign key (course_id) references courses (course_id),
    constraint FK67t9pyepga3257rm2tb0br76t
        foreign key (instructor_id) references instructors (instructor_id),
    constraint FKl0r9v8xmhfoc6541lk18v5juh
        foreign key (student_id) references students (student_id)
);

create table payments
(
    payment_id bigint auto_increment
        primary key,
    amount     varchar(255) not null,
    date       date         not null,
    method     varchar(255) not null,
    course_id  bigint       not null,
    student_id bigint       not null,
    constraint UK_4idiedu2ax7tb2fxyo5vpu1i0
        unique (course_id),
    constraint FK6ooq278k2bs5xi8t5o6oort1v
        foreign key (student_id) references students (student_id),
    constraint FK8nlm4urshp5drsk0nlkprig36
        foreign key (course_id) references courses (course_id)
);

create table student_course
(
    student_id bigint not null,
    course_id  bigint not null,
    constraint FKnh5bqghcqt8f5p2yqshbr5g6q
        foreign key (student_id) references students (student_id),
    constraint FKpq6ovhxttt2ggd6cwu7sytpth
        foreign key (course_id) references courses (course_id)
);

create table user
(
    user_id      bigint auto_increment
        primary key,
    userName     varchar(255) not null,
    userPassword varchar(255) not null,
    userRole     varchar(255) not null
);

