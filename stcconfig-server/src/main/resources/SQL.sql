create database stcconfig;
CREATE TABLE `env_info` (
                            `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
                            `env_name` varchar(64) NOT NULL COMMENT '环境名称',
                            `delete_flag` int NOT NULL DEFAULT '0' COMMENT '0 未删除 1 已删除',
                            `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                            `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
                            `parent_id` int NOT NULL DEFAULT '-1',
                            `disable_flag` int NOT NULL DEFAULT '0' COMMENT '0 有效 1 失效',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='环境表';

CREATE TABLE `project_info` (
                                `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                `project_name` varchar(64) NOT NULL COMMENT '项目名称',
                                `delete_flag` int NOT NULL DEFAULT '0' COMMENT '0 未删除 1 已删除',
                                `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
                                `env_id` int NOT NULL COMMENT '环境ID',
                                `disable_flag` int NOT NULL DEFAULT '0' COMMENT '0 有效 1 失效',
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='项目表';

CREATE TABLE `config_info` (
                               `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
                               `config_type` varchar(16) NOT NULL COMMENT '配置类型',
                               `config_file_name` varchar(128) NOT NULL COMMENT '配置文件名',
                               `config_file_type` varchar(16) NOT NULL COMMENT '配置文件后缀类型',
                               `config_file_version` int NOT NULL DEFAULT '1' COMMENT '配置文件版本号',
                               `config_file_version_previous` int NOT NULL DEFAULT '1' COMMENT '配置文件上一版本号',
                               `config_index` int NOT NULL DEFAULT '1' COMMENT '配置文件次序',
                               `config_file_content` text COMMENT '配置文件内容',
                               `delete_flag` int NOT NULL DEFAULT '0' COMMENT '0 未删除 1 已删除',
                               `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                               `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
                               `project_id` int NOT NULL COMMENT '项目ID',
                               `env_id` int NOT NULL COMMENT '环境ID',
                               `disable_flag` int NOT NULL DEFAULT '0' COMMENT '0 有效 1 失效',
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='配置表';

CREATE TABLE `config_info_history` (
                                       `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                       `config_type` varchar(16) NOT NULL COMMENT '配置类型',
                                       `config_file_name` varchar(128) NOT NULL COMMENT '配置文件名',
                                       `config_file_type` varchar(16) NOT NULL COMMENT '配置文件后缀类型',
                                       `config_file_version` int NOT NULL COMMENT '配置文件版本号',
                                       `config_file_version_previous` int NOT NULL COMMENT '配置文件上一版本号',
                                       `config_file_version_next` int NOT NULL COMMENT '配置文件下一版本号',
                                       `config_index` int NOT NULL COMMENT '配置文件次序',
                                       `config_file_content` text COMMENT '配置文件内容',
                                       `delete_flag` int NOT NULL DEFAULT '0' COMMENT '0 未删除 1 已删除',
                                       `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                       `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
                                       `project_id` int NOT NULL COMMENT '项目ID',
                                       `disable_flag` int NOT NULL DEFAULT '0' COMMENT '0 有效 1 失效',
                                       PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='配置历史表';

CREATE TABLE `user_info` (
                             `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
                             `account_name` varchar(32) NOT NULL COMMENT '账户名',
                             `account_password` varchar(32) NOT NULL COMMENT '账户密码',
                             `delete_flag` int NOT NULL DEFAULT '0' COMMENT '0 未删除 1 已删除',
                             `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                             `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
                             `disable_flag` int NOT NULL DEFAULT '0' COMMENT '0 有效 1 无效',
                             PRIMARY KEY (`id`),
                             UNIQUE KEY `account_name` (`account_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';