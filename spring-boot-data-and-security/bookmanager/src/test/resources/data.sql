insert into user(`id`, `name`, `email`, `created_at`, `updated_at`) values (1, 'sehun001', 'sehun001@naver.com', now(), now());
insert into user(`id`, `name`, `email`, `created_at`, `updated_at`) values (2, 'sehun002', 'sehun002@naver.com', now(), now());
insert into user(`id`, `name`, `email`, `created_at`, `updated_at`) values (3, 'sehun003', 'sehun003@naver.com', now(), now());
insert into user(`id`, `name`, `email`, `created_at`, `updated_at`) values (4, 'sehun004', 'sehun004@naver.com', now(), now());
insert into user(`id`, `name`, `email`, `created_at`, `updated_at`) values (5, 'sehun005', 'sehun005@naver.com', now(), now());

insert into publisher(`id`, `name`) values (1,'COCO');
insert into book(`id`, `name`, `publisher_id`, `deleted`, `status`) values (1, 'jpa book 01', 1, false, 100);
insert into book(`id`, `name`, `publisher_id`, `deleted`, `status`) values (2, 'jpa book 02', 1, false, 200);
insert into book(`id`, `name`, `publisher_id`, `deleted`, `status`) values (3, 'jpa book 03', 1, true, 100);