create database phoebe;

use phoebe;


CREATE TABLE `message_template`
(
    `id`               bigint(20) NOT NULL AUTO_INCREMENT,
    `name`             varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '标题',
    `audit_status`     tinyint(4) NOT NULL DEFAULT '0' COMMENT '当前消息审核状态： 10.待审核 20.审核成功 30.被拒绝',
    `flow_id`          varchar(50) COLLATE utf8mb4_unicode_ci COMMENT '工单ID',
    `msg_status`       tinyint(4) NOT NULL DEFAULT '0' COMMENT '当前消息状态：10.新建 20.停用 30.启用 40.等待发送 50.发送中 60.发送成功 70.发送失败',
    `cron_task_id`     bigint(20) COMMENT '定时任务Id (xxl-job-admin返回)',
    `cron_crowd_path`  varchar(500) COMMENT '定时发送人群的文件路径',
    `expect_push_time` varchar(100) COLLATE utf8mb4_unicode_ci COMMENT '期望发送时间：0:立即发送 定时任务以及周期任务:cron表达式',
    `id_type`          tinyint(4) NOT NULL DEFAULT '0' COMMENT '消息的发送ID类型：10. userId 20.did 30.手机号 40.openId 50.email 60.企业微信userId',
    `send_channel`     tinyint(4) NOT NULL DEFAULT '0' COMMENT '消息发送渠道：10.IM 20.Push 30.短信 40.Email 50.公众号 60.小程序 70.企业微信',
    `template_type`    tinyint(4) NOT NULL DEFAULT '0' COMMENT '10.运营类 20.技术类接口调用',
    `msg_type`         tinyint(4) NOT NULL DEFAULT '0' COMMENT '10.通知类消息 20.营销类消息 30.验证码类消息',
    `shield_type`      tinyint(4) NOT NULL DEFAULT '0' COMMENT '10.夜间不屏蔽 20.夜间屏蔽 30.夜间屏蔽(次日早上9点发送)',
    `msg_content`      varchar(600) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '消息内容 占位符用{$var}表示',
    `send_account`     tinyint(4) NOT NULL DEFAULT '0' COMMENT '发送账号 一个渠道下可存在多个账号',
    `creator`          varchar(45) COLLATE utf8mb4_unicode_ci  NOT NULL DEFAULT '' COMMENT '创建者',
    `updator`          varchar(45) COLLATE utf8mb4_unicode_ci  NOT NULL DEFAULT '' COMMENT '更新者',
    `auditor`          varchar(45) COLLATE utf8mb4_unicode_ci  NOT NULL DEFAULT '' COMMENT '审核人',
    `team`             varchar(45) COLLATE utf8mb4_unicode_ci  NOT NULL DEFAULT '' COMMENT '业务方团队',
    `proposer`         varchar(45) COLLATE utf8mb4_unicode_ci  NOT NULL DEFAULT '' COMMENT '业务方',
    `is_deleted`       tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除：0.不删除 1.删除',
    `created`          int(11) NOT NULL DEFAULT '0' COMMENT '创建时间',
    `updated`          int(11) NOT NULL DEFAULT '0' COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY                `idx_channel` (`send_channel`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='消息模板信息';



CREATE TABLE `sms_record`
(
    `id`                  bigint(20) NOT NULL AUTO_INCREMENT,
    `message_template_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '消息模板ID',
    `phone`               bigint(20) NOT NULL DEFAULT '0' COMMENT '手机号',
    `supplier_id`         tinyint(4) NOT NULL DEFAULT '0' COMMENT '发送短信渠道商的ID',
    `supplier_name`       varchar(40) COLLATE utf8mb4_unicode_ci  NOT NULL DEFAULT '' COMMENT '发送短信渠道商的名称',
    `msg_content`         varchar(600) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '短信发送的内容',
    `series_id`           varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '下发批次的ID',
    `charging_num`        tinyint(4) NOT NULL DEFAULT '0' COMMENT '计费条数',
    `report_content`      varchar(50)                             NOT NULL DEFAULT '' COMMENT '回执内容',
    `status`              tinyint(4) NOT NULL DEFAULT '0' COMMENT '短信状态： 10.发送 20.成功 30.失败',
    `send_date`           int(11) NOT NULL DEFAULT '0' COMMENT '发送日期：20211112',
    `created`             int(11) NOT NULL DEFAULT '0' COMMENT '创建时间',
    `updated`             int(11) NOT NULL DEFAULT '0' COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY                   `idx_send_date` (`send_date`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='短信记录信息';

-- 实时类型 短信（无占位符）
INSERT INTO phoebe.message_template (id, name, audit_status, flow_id, msg_status, cron_task_id, cron_crowd_path, expect_push_time, id_type, send_channel, template_type, msg_type, msg_content, send_account, creator, updator, auditor, team, proposer, is_deleted, created, updated) VALUES (1, '买一送十活动', 10, '', 10, null, '', '', 30, 30, 20, 20, '{"content":"6666","url":"","title":""}', 10, 'Java3y', 'Java3y', '3y', '公众号Java3y', '三歪', 0, 1646274112, 1646275242);

-- 实时类型 邮件（无占位符）
INSERT INTO phoebe.message_template (id, name, audit_status, flow_id, msg_status, cron_task_id, cron_crowd_path, expect_push_time, id_type, send_channel, template_type, msg_type, msg_content, send_account, creator, updator, auditor, team, proposer, is_deleted, created, updated) VALUES (2, '校招信息', 10, '', 10, null, '', '', 50, 40, 20, 10, '{"content":"你已成功获取到offer","url":"","title":"招聘通知"}', 10, 'Java3y', 'Java3y', '3y', '公众号Java3y', '鸡蛋', 0, 1646274195, 1646274195);

-- 实时类型 短信（有占位符）占位符key 为 content
INSERT INTO phoebe.message_template (id, name, audit_status, flow_id, msg_status, cron_task_id, cron_crowd_path, expect_push_time, id_type, send_channel, template_type, msg_type, msg_content, send_account, creator, updator, auditor, team, proposer, is_deleted, created, updated) VALUES (3, '验证码通知', 10, '', 10, null, '', '', 30, 30, 20, 30, '{"content":"{$content}","url":"","title":""}', 10, 'Java3y', 'Java3y', '3y', '公众号Java3y', '孙悟空', 0, 1646275213, 1646275213);

