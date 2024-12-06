CREATE TABLE book (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
                      title VARCHAR(255) NOT NULL COMMENT '书名',
                      author VARCHAR(255) NOT NULL COMMENT '作者',
                      isbn VARCHAR(20) NOT NULL UNIQUE COMMENT '国际标准书号',
                      price DECIMAL(10, 2) NOT NULL COMMENT '价格',
                      category VARCHAR(100) COMMENT '分类',
                      stock INT DEFAULT 0 COMMENT '库存数量',
                      description TEXT COMMENT '简介',
                      created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                      updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='图书表';

CREATE TABLE user (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
                      username VARCHAR(50) NOT NULL UNIQUE COMMENT '用户名',
                      email VARCHAR(100) NOT NULL UNIQUE COMMENT '邮箱',
                      password VARCHAR(255) NOT NULL COMMENT '加密存储的密码',
                      phone_number VARCHAR(20) COMMENT '手机号码',
                      role ENUM('customer', 'admin') DEFAULT 'customer' COMMENT '用户角色',
                      created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
                      updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户表';

INSERT INTO book (title, author, isbn, price, category, stock, description)
VALUES
    ('深入理解Java虚拟机', '周志明', '9787111213826', 88.00, '编程技术', 120, '一本专注于Java虚拟机底层技术的经典书籍。'),
    ('算法导论', 'Thomas H. Cormen', '9787111187776', 99.50, '编程技术', 80, '覆盖了大部分算法知识的基础书籍，适合初学者和专业人士。'),
    ('三体', '刘慈欣', '9787536692930', 45.00, '科幻', 300, '一部描述地球与外星文明碰撞的史诗级科幻小说。'),
    ('红楼梦', '曹雪芹', '9787020002207', 32.00, '经典文学', 500, '中国四大名著之一，讲述了贾府兴衰的故事。'),
    ('平凡的世界', '路遥', '9787020008735', 39.00, '当代文学', 200, '中国当代文学中影响深远的一部作品，描述了普通人追求理想的故事。');

INSERT INTO user (username, email, password, phone_number, role)
VALUES
    ('管理员', 'admin@bookstore.cn', '123456', '13800138000', 'admin'),
    ('张三', 'zhangsan@example.com', '123456', '13800138001', 'customer'),
    ('李四', 'lisi@example.com', '123456', '13800138002', 'customer'),
    ('王五', 'wangwu@example.com', '123456', '13800138003', 'customer'),
    ('赵六', 'zhaoliu@example.com', '123456', '13800138004', 'customer');

CREATE TABLE `tb_order` (
                         `order_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '订单ID',
                         `user_id` BIGINT NOT NULL COMMENT '用户ID',
                         `book_id` BIGINT NOT NULL COMMENT '书本ID, 也就是ISBN',
                         `book_price` DOUBLE NOT NULL COMMENT '书本价格',
                         `state` INT NOT NULL DEFAULT 0 COMMENT '0表示下单了但未支付, 1表示支付了',
                         `order_datetime` DATETIME NOT NULL COMMENT '下单日期, 准确到时分秒',
                         `address` VARCHAR(255) NOT NULL COMMENT '地址',
                         PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

CREATE TABLE `user` (
                        `userid` BIGINT NOT NULL AUTO_INCREMENT COMMENT '用户ID',
                        `user_name` VARCHAR(255) NOT NULL COMMENT '用户名',
                        `password` VARCHAR(255) NOT NULL COMMENT '密码',
                        `phone` VARCHAR(20) NOT NULL COMMENT '电话',
                        `role` VARCHAR(50) NOT NULL COMMENT '角色',  -- 假设 Role 是一个简单的字符串类型
                        PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

CREATE TABLE `cart` (
                        `id` BIGINT AUTO_INCREMENT COMMENT '主键 ID',
                        `user_id` BIGINT NOT NULL COMMENT '用户 ID',
                        `book_id` BIGINT NOT NULL COMMENT '书籍 ID',
                        `quantity` INT NOT NULL COMMENT '书籍数量',
                        `total_price` DECIMAL(10, 2) NOT NULL COMMENT '总价格',
                        `add_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
                        PRIMARY KEY (`id`),
                        KEY `idx_user_id` (`user_id`),
                        KEY `idx_book_id` (`book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='购物车表';
