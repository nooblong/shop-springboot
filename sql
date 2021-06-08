create table `order`
(
    id              int auto_increment
        primary key,
    submit_datetime datetime     not null,
    address         varchar(100) not null,
    customer_id     int          not null,
    state           int          not null
);

create table order_extend
(
    id         int auto_increment
        primary key,
    product_id int not null,
    count      int not null,
    order_id   int not null,
    constraint order_extend_order_id_fk
        foreign key (order_id) references `order` (id)
);

create table product
(
    id     int auto_increment
        primary key,
    name   varchar(100)         not null,
    image  varchar(100)         null,
    price  decimal(10, 2)       not null,
    stock  int        default 0 not null,
    `show` tinyint(1) default 1 not null
);

create table category
(
    id            int auto_increment
        primary key,
    product       int         not null,
    category_name varchar(20) not null,
    constraint category_product_id_fk
        foreign key (product) references product (id)
);

create table user
(
    id       int auto_increment
        primary key,
    username varchar(20)          not null,
    password varchar(20)          not null,
    isAdmin  tinyint(1) default 0 not null
);
