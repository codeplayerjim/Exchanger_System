create table college
(
    collegeID   int          not null
        primary key,
    collegeName varchar(100) not null comment '课程名'
);

create table exchanger
(
    e_id       int auto_increment
        primary key,
    username   varchar(100) not null,
    age        int          null,
    createTime date         null comment '入学时间',
    collegeid  int          not null comment '院系id'
);

create index collegeID
    on exchanger (collegeid);

create table role
(
    roleID      int          not null
        primary key,
    roleName    varchar(20)  not null,
    permissions varchar(255) null comment '权限'
);

create table teacher
(
    userID    int auto_increment
        primary key,
    userName  varchar(200) not null,
    sex       varchar(20)  null,
    birthYear date         not null,
    degree    varchar(20)  null comment '学历',
    title     varchar(255) null comment '职称',
    grade     date         null comment '入职时间',
    collegeID int          not null comment '院系',
    constraint teacher_ibfk_1
        foreign key (collegeID) references college (collegeid)
);

create table course
(
    courseID   int          not null
        primary key,
    courseName varchar(200) not null comment '课程姓名',
    teacherID  int          not null,
    courseTime varchar(200) null comment '开课时间',
    classRoom  varchar(200) null comment '开课地点',
    courseWeek int(200)     null comment '学时',
    courseType varchar(20)  null comment '课程类型',
    collegeID  int          not null comment '所属院系',
    score      int          not null comment '学分',
    constraint course_ibfk_1
        foreign key (collegeID) references college (collegeid),
    constraint course_ibfk_2
        foreign key (teacherID) references teacher (userid)
);

create index collegeID
    on course (collegeID);

create index teacherID
    on course (teacherID);

create table selectedcourse
(
    courseID    int not null,
    exchangerID int not null,
    mark        int null comment '成绩',
    constraint selectedcourse_ibfk_1
        foreign key (courseID) references course (courseid),
    constraint selectedcourse_ibfk_2
        foreign key (exchangerID) references exchanger (e_id)
);

create index courseID
    on selectedcourse (courseID);

create index exchangerID
    on selectedcourse (exchangerID);

create index collegeID
    on teacher (collegeID);

create table userlogin
(
    userID   int auto_increment
        primary key,
    userName varchar(200)    not null,
    password varchar(200)    not null,
    role     int default '2' not null comment '角色权限',
    constraint userLogin_ibfk_1
        foreign key (role) references role (roleid)
);

create index role
    on userlogin (role);


