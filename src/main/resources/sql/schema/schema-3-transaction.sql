begin;

drop table if exists task;
create table task (
  guid    varchar(100)    not null,
  time   timestamp    not null,
  status  varchar(20) not null,

  primary key (guid)
);

commit;
