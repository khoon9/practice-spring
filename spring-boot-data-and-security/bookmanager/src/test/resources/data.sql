insert into user(`id`, `name`, `email`, `created_at`, `updated_at`) values (1, 'sehun001', 'sehun001@naver.com', now(), now());
insert into user(`id`, `name`, `email`, `created_at`, `updated_at`) values (2, 'sehun002', 'sehun002@naver.com', now(), now());
insert into user(`id`, `name`, `email`, `created_at`, `updated_at`) values (3, 'sehun003', 'sehun003@naver.com', now(), now());
insert into user(`id`, `name`, `email`, `created_at`, `updated_at`) values (4, 'sehun004', 'sehun004@naver.com', now(), now());
insert into user(`id`, `name`, `email`, `created_at`, `updated_at`) values (5, 'sehun005', 'sehun005@naver.com', now(), now());

insert into publisher(`id`, `name`) values (1,'COCO');
insert into book(`id`, `name`, `publisher_id`, `deleted`, `status`) values (1, 'jpa book 01', 1, false, 100);
insert into book(`id`, `name`, `publisher_id`, `deleted`, `status`) values (2, 'jpa book 02', 1, false, 200);
insert into book(`id`, `name`, `publisher_id`, `deleted`, `status`) values (3, 'jpa book 03', 1, true, 100);

insert into review(`id`, `title`, `content`, `score`, `user_id`, `book_id`) values(1, "책 이름 01", "후기 내용 01", 5.0, 1, 1);
insert into review(`id`, `title`, `content`, `score`, `user_id`, `book_id`) values(2, "책 이름 02", "후기 내용 02", 3.0, 2, 2);

insert into comment(`id`, `comment`, `review_id`) values (1, "코멘트 01", 1);
insert into comment(`id`, `comment`, `review_id`) values (2, "코멘트 02", 1);
insert into comment(`id`, `comment`, `review_id`) values (3, "코멘트 03", 2);