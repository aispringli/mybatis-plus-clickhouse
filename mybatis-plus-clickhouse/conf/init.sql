
# mysql

DROP TABLE
    IF
        EXISTS `user`;

CREATE TABLE `user` (
                       `id` int(11) NOT NULL,
                       `name` varchar(255) COLLATE utf8mb4_german2_ci DEFAULT NULL,
                       `is_deleted` tinyint,
                       PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_german2_ci;

# clickhouse
DROP TABLE
    IF
        EXISTS `user`;

CREATE TABLE user ENGINE = MergeTree PRIMARY KEY (`id`)
ORDER BY id as
SELECT *
FROM
    mysql(
            '192.168.1.129:3306',
            'domain_machine',
            'user',
            'newcore_dev',
            'newcore_dev'
        );