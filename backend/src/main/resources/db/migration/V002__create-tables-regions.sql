create table tb_city (
        id bigserial not null,
        name varchar(255),
        region_id bigint,
        state_id bigint,
        city_id bigint,
        primary key (id)
);

create table tb_state (
     id bigserial not null,
     abbreviation char(2),
     name varchar(30),
     primary key (id)
);

create table tb_region (
      id bigserial not null,
      description varchar(255),
      name varchar(30),
      primary key (id)
);

alter table tb_city add constraint FK7itrdxo4akbp4yjyymiafjagd foreign key (region_id) references tb_region;
alter table tb_city add constraint FK1rn7oty4mwqviyw8vk67crapo foreign key (state_id) references tb_state;

