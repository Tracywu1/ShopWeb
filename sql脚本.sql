CREATE TABLE `tb_address` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '地址id',
  `userId` int NOT NULL COMMENT '用户id',
  `receiverName` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '收货人',
  `receiverPhone` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_cs NOT NULL COMMENT '收货人电话号码',
  `receiverAddress` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '收货人详细地址',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tb_blog` (
  `id` int NOT NULL COMMENT '动态id',
  `storeId` int NOT NULL COMMENT '店铺id',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '动态内容',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tb_cart` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '购物车id',
  `userId` int NOT NULL COMMENT '用户id',
  `productId` int NOT NULL COMMENT '商品id',
  `count` int NOT NULL DEFAULT '0' COMMENT '购买数量',
  `isSelected` int NOT NULL DEFAULT '2' COMMENT '是否勾选，默认未勾选',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tb_chat` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '聊天id',
  `senderId` int NOT NULL COMMENT '发送者id',
  `receiverId` int NOT NULL COMMENT '接收者id',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '聊天内容',
  `sendTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发送时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tb_comment` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `creatorId` int NOT NULL COMMENT '创建者id',
  `productId` int NOT NULL COMMENT '商品id',
  `nickName` varchar(255) NOT NULL COMMENT '用户昵称',
  `image` varchar(255) NOT NULL COMMENT '用户头像url',
  `content` varchar(255) NOT NULL COMMENT '评论内容',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tb_order` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `orderNo` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '订单编号',
  `userId` int NOT NULL COMMENT '用户id',
  `storeId` int DEFAULT NULL COMMENT '店铺id',
  `addressId` int DEFAULT NULL COMMENT '地址id',
  `status` int NOT NULL DEFAULT '1' COMMENT '订单状态',
  `totalPrice` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '订单金额',
  `receiverName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '收货人',
  `receiverPhone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '收货人电话号码',
  `receiverAddress` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '收货人地址',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `orderNum` (`orderNo`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tb_order_item` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '订单详情id',
  `orderNo` varchar(255) NOT NULL COMMENT '订单编号',
  `productId` int NOT NULL COMMENT '商品id',
  `productName` varchar(50) NOT NULL COMMENT '商品名称',
  `productImage` varchar(255) NOT NULL DEFAULT 'https://img1.baidu.com/it/u=1841333429,3927409117&fm=253&fmt=auto&app=138&f=JPEG?w=610&h=500' COMMENT '商品图片',
  `unitPrice` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '商品单价',
  `totalPrice` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '总价',
  `count` int NOT NULL DEFAULT '0' COMMENT '商品数量',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tb_product` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `storeId` int DEFAULT NULL COMMENT '所属店铺id',
  `productName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品名称',
  `storeName` varchar(255) DEFAULT NULL COMMENT '所属店铺名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '商品描述',
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'https://img1.baidu.com/it/u=415449740,540746270&fm=253&fmt=auto&app=138&f=GIF?w=500&h=500' COMMENT '商品图片url',
  `price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '商品价格',
  `productCount` int NOT NULL DEFAULT '0' COMMENT '库存',
  `saleCount` int DEFAULT '0' COMMENT '总销售量',
  `createTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tb_product_application` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '申请id',
  `storeId` int DEFAULT NULL COMMENT '店铺id',
  `productName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品名称',
  `storeName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '店铺名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '商品描述',
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'https://img1.baidu.com/it/u=415449740,540746270&fm=253&fmt=auto&app=138&f=GIF?w=500&h=500' COMMENT '商品图片url',
  `price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '商品价格',
  `productCount` int NOT NULL DEFAULT '0' COMMENT '库存',
  `status` int NOT NULL COMMENT '申请状态',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tb_report` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '举报id',
  `userId` int NOT NULL COMMENT '用户',
  `username` varchar(255) NOT NULL COMMENT '用户名称',
  `content` varchar(255) NOT NULL COMMENT '举报内容',
  `reportTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '举报时间',
  `status` int NOT NULL DEFAULT '1' COMMENT '处理状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tb_return` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '退货id',
  `userId` int NOT NULL COMMENT '用户id',
  `orderNum` varchar(0) NOT NULL COMMENT '订单编号',
  `reason` varchar(255) NOT NULL COMMENT '退货原因',
  `status` enum('待处理','已处理','已退款','已取消') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '待处理' COMMENT '退货状态',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '退货时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tb_store` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '店铺id',
  `managerId` int NOT NULL COMMENT '店铺管理员id',
  `storeName` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '店铺名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '店铺介绍',
  `logo` varchar(255) NOT NULL COMMENT '店铺头像地址',
  `fansNum` int NOT NULL DEFAULT '0' COMMENT '粉丝数量',
  `saleCount` int NOT NULL DEFAULT '0' COMMENT '总销量',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tb_store_appliacation` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '店铺id',
  `userId` int NOT NULL COMMENT '店铺管理员id',
  `storeName` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '店铺名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '店铺介绍',
  `logo` varchar(255) NOT NULL COMMENT '店铺头像地址',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tb_subscribe` (
  `id` int NOT NULL COMMENT '关注id',
  `userId` int NOT NULL COMMENT '用户id',
  `storeId` int NOT NULL COMMENT '店铺id',
  `storeName` varchar(255) NOT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '关注时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tb_user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `storeId` int DEFAULT '0' COMMENT '店铺的id（只有店铺管理员有，普通用户默认为0）',
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `nickname` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '昵称',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '地址',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '电子邮箱',
  `phoneNum` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '电话号码',
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'https://c-ssl.duitang.com/uploads/item/202004/14/20200414210224_dnzpo.jpg' COMMENT '头像地址',
  `userRole` int NOT NULL DEFAULT '0' COMMENT '角色',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;