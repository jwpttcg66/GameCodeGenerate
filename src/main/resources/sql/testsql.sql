
CREATE TABLE `testsql` (
  `Id` int(11) NOT NULL,
  `name` tinytext,
  `varCh` varchar(255) DEFAULT NULL,
  `bigI` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `test2sql` (
                           `id` int(11) NOT NULL,
                           `name` tinytext,
                           `varCh` varchar(255) DEFAULT NULL,
                           `bigI` bigint(20) DEFAULT NULL,
                           PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;