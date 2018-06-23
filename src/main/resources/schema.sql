CREATE TABLE IF NOT EXISTS `monitors` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `origin` varchar(150) NOT NULL,
    `metric` varchar(150) NOT NULL,
    `rule` varchar(150) NOT NULL,
    `threshold` bigint(20) NOT NULL,
    PRIMARY KEY (`id`)
);
