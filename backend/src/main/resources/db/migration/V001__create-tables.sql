create table tb_category (
    id  bigserial not null,
    created_at TIMESTAMP WITHOUT TIME ZONE,
    name varchar(255),
    updated_at TIMESTAMP WITHOUT TIME ZONE,
    primary key (id)
);

create table tb_product (
   id  bigserial not null,
   date TIMESTAMP WITHOUT TIME ZONE,
   description TEXT,
   img_url varchar(255),
   name varchar(255),
   price float8,
   primary key (id)
);

create table tb_product_category (
   product_id int8 not null,
   category_id int8 not null,
   primary key (product_id, category_id)
);

create table tb_role (
   id  bigserial not null,
   authority varchar(255),
   primary key (id)
);

create table tb_user (
    id  bigserial not null,
    email varchar(255),
    first_name varchar(255),
    last_name varchar(255),
    password varchar(255),
    primary key (id)
);

create table tb_user_role (
    user_id int8 not null,
    role_id int8 not null,
    primary key (user_id, role_id)
);

alter table tb_user add constraint UK_4vih17mube9j7cqyjlfbcrk4m unique (email);
alter table tb_product_category add constraint FK5r4sbavb4nkd9xpl0f095qs2a foreign key (category_id) references tb_category;
alter table tb_product_category add constraint FKgbof0jclmaf8wn2alsoexxq3u foreign key (product_id) references tb_product;
alter table tb_user_role add constraint FKea2ootw6b6bb0xt3ptl28bymv foreign key (role_id) references tb_role;
alter table tb_user_role add constraint FK7vn3h53d0tqdimm8cp45gc0kl foreign key (user_id) references tb_user;
