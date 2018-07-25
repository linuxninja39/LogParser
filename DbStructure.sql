create table Agent
(
  id    int auto_increment
    primary key,
  value varchar(255) not null,
  constraint UK_5otxyvraosv9djl7lwt3kgdn5
  unique (value)
);

create table IpAddress
(
  id    int auto_increment
    primary key,
  value varchar(255) not null,
  constraint UK_gx88mifevfrh6r5hiojmcbxkl
  unique (value)
);

create table BlockLog
(
  id           int auto_increment
    primary key,
  count        int          null,
  reason       varchar(255) null,
  ipAddress_id int          null,
  constraint FK1pvpyy9jkw0kh08d869k7wd7c
  foreign key (ipAddress_id) references IpAddress (id)
);

create index FK1pvpyy9jkw0kh08d869k7wd7c
  on BlockLog (ipAddress_id);

create table MethodProtocol
(
  id    int auto_increment
    primary key,
  value varchar(255) not null,
  constraint UK_bjx5or49ph1x1i72dhm7803cy
  unique (value)
);

create table Log
(
  id                int auto_increment
    primary key,
  statusCode        int      not null,
  timeStamp         datetime not null,
  agent_id          int      null,
  ipAddress_id      int      null,
  methodProtocol_id int      null,
  constraint FKla769vxlgt5im4ofjhpeu9vvq
  foreign key (agent_id) references Agent (id),
  constraint FKpjjqfdhtu97y1e3gn3ugxn2hi
  foreign key (ipAddress_id) references IpAddress (id),
  constraint FK6c2w4y3ovfmuejbce6sog2mbn
  foreign key (methodProtocol_id) references MethodProtocol (id)
);

create index FK6c2w4y3ovfmuejbce6sog2mbn
  on Log (methodProtocol_id);

create index FKla769vxlgt5im4ofjhpeu9vvq
  on Log (agent_id);

create index FKpjjqfdhtu97y1e3gn3ugxn2hi
  on Log (ipAddress_id);


