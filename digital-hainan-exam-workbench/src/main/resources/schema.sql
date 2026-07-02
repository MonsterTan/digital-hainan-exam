-- DDL 初始化脚本，应用启动时自动执行
CREATE TABLE IF NOT EXISTS book (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    creator VARCHAR(100),
    gmt_create TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modifier VARCHAR(100),
    gmt_modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_deleted BOOLEAN DEFAULT FALSE
);

-- 用户表
CREATE TABLE IF NOT EXISTS sys_user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(64) NOT NULL,
    salt VARCHAR(32) NOT NULL,
    status TINYINT DEFAULT 1,
    creator VARCHAR(100),
    gmt_create TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modifier VARCHAR(100),
    gmt_modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_deleted BOOLEAN DEFAULT FALSE
);

-- 预置管理员账号 (密码: admin123, salt: a1b2c3d4)
INSERT INTO sys_user (username, password, salt, status, creator, modifier)
VALUES ('admin', '2d1c9e37f67fd92e4475d454c6d2138d', 'a1b2c3d4', 1, 'System', 'System');
