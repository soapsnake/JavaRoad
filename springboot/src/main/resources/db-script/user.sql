CREATE TABLE `lab`.`user`
(
    `id`            int(32)      NOT NULL,
    `first_name`    varchar(30)  NOT NULL,
    `last_name`     varchar(30)  NOT NULL,
    `phone_number`  int(32)      NOT NULL,
    `email_address` varchar(100) NOT NULL,
    PRIMARY KEY (`id`, `email_address`)
) COMMENT ='';