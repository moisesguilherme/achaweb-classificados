create table tb_groupcategory (
        id bigserial not null,
        description varchar(255),
        name varchar(255),
        primary key (id)
);

create table tb_groupcategory_category (
       groupcategory_id bigint not null,
       category_id bigint not null,
       primary key (groupcategory_id, category_id)
);

create table tb_region_groupcategory (
        region_id bigserial not null,
        groupcategory_id bigint not null,
        primary key (region_id, groupcategory_id)
);

alter table tb_groupcategory_category add constraint FK3xn9loddb3vj1wtrk9or9515c foreign key (groupcategory_id) references tb_groupcategory;
alter table tb_groupcategory_category add constraint FK9m0svklnu1kyuydouoq88s3bu foreign key (category_id) references tb_category;
alter table tb_region_groupcategory add constraint FKkfcrv4nby4gxwhem349uwbht2 foreign key (groupcategory_id) references tb_groupcategory;
alter table tb_region_groupcategory add constraint FKcj3y08r6xycjhjtgd79rt3goa foreign key (region_id) references tb_region;