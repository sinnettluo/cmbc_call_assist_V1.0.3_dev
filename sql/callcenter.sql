CREATE DATABASE  IF NOT EXISTS  `guiyu_callcenter`  DEFAULT CHARACTER SET utf8 ;

USE `guiyu_callcenter`;



DROP TABLE IF EXISTS `call_in_detail`;

CREATE TABLE `call_in_detail` (
  `call_id` varchar(50) DEFAULT NULL,
  `call_detail_id` varchar(50) NOT NULL,
  `accurate_intent` varchar(10) DEFAULT NULL,
  `agent_answer_text` varchar(255) DEFAULT NULL,
  `agent_answer_time` datetime DEFAULT NULL,
  `ai_duration` int(11) DEFAULT NULL,
  `asr_duration` int(11) DEFAULT NULL,
  `bot_answer_text` varchar(255) DEFAULT NULL,
  `bot_answer_time` datetime DEFAULT NULL,
  `call_detail_type` int(2) NOT NULL,
  `customer_say_text` varchar(255) DEFAULT NULL,
  `customer_say_time` datetime DEFAULT NULL,
  `reason` varchar(20) DEFAULT NULL,
  `total_duration` int(11) DEFAULT NULL,
  `sharding_value` int(2) NOT NULL DEFAULT '0',
  PRIMARY KEY (`call_detail_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `call_in_detail` */

/*Table structure for table `call_in_detail_0` */

DROP TABLE IF EXISTS `call_in_detail_0`;

CREATE TABLE `call_in_detail_0` (
  `call_id` varchar(50) DEFAULT NULL,
  `call_detail_id` varchar(50) NOT NULL,
  `accurate_intent` varchar(10) DEFAULT NULL,
  `agent_answer_text` varchar(255) DEFAULT NULL,
  `agent_answer_time` datetime DEFAULT NULL,
  `ai_duration` int(11) DEFAULT NULL,
  `asr_duration` int(11) DEFAULT NULL,
  `bot_answer_text` varchar(255) DEFAULT NULL,
  `bot_answer_time` datetime DEFAULT NULL,
  `call_detail_type` int(2) NOT NULL,
  `customer_say_text` varchar(255) DEFAULT NULL,
  `customer_say_time` datetime DEFAULT NULL,
  `reason` varchar(20) DEFAULT NULL,
  `total_duration` int(11) DEFAULT NULL,
  `sharding_value` int(2) NOT NULL DEFAULT '0',
  PRIMARY KEY (`call_detail_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `call_in_detail_0` */

/*Table structure for table `call_in_detail_1` */

DROP TABLE IF EXISTS `call_in_detail_1`;

CREATE TABLE `call_in_detail_1` (
  `call_id` varchar(50) DEFAULT NULL,
  `call_detail_id` varchar(50) NOT NULL,
  `accurate_intent` varchar(10) DEFAULT NULL,
  `agent_answer_text` varchar(255) DEFAULT NULL,
  `agent_answer_time` datetime DEFAULT NULL,
  `ai_duration` int(11) DEFAULT NULL,
  `asr_duration` int(11) DEFAULT NULL,
  `bot_answer_text` varchar(255) DEFAULT NULL,
  `bot_answer_time` datetime DEFAULT NULL,
  `call_detail_type` int(2) NOT NULL,
  `customer_say_text` varchar(255) DEFAULT NULL,
  `customer_say_time` datetime DEFAULT NULL,
  `reason` varchar(20) DEFAULT NULL,
  `total_duration` int(11) DEFAULT NULL,
  `sharding_value` int(2) NOT NULL DEFAULT '0',
  PRIMARY KEY (`call_detail_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `call_in_detail_1` */

/*Table structure for table `call_in_detail_record` */

DROP TABLE IF EXISTS `call_in_detail_record`;

CREATE TABLE `call_in_detail_record` (
  `call_id` varchar(50) NOT NULL DEFAULT '',
  `call_detail_id` varchar(50) NOT NULL,
  `agent_record_file` varchar(30) DEFAULT NULL,
  `agent_record_url` varchar(30) DEFAULT NULL,
  `bot_record_file` varchar(30) DEFAULT NULL,
  `bot_record_url` varchar(30) DEFAULT NULL,
  `customer_record_file` varchar(30) DEFAULT NULL,
  `customer_record_url` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`call_detail_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `call_in_detail_record` */

/*Table structure for table `call_in_plan` */

DROP TABLE IF EXISTS `call_in_plan`;

CREATE TABLE `call_in_plan` (
  `call_id` varchar(50) NOT NULL DEFAULT '',
  `phone_num` varchar(30) NOT NULL DEFAULT '',
  `customer_id` varchar(50) NOT NULL DEFAULT '',
  `temp_id` varchar(30) NOT NULL DEFAULT '',
  `line_id` int(11) NOT NULL,
  `serverId` varchar(30) DEFAULT NULL,
  `agent_id` varchar(30) DEFAULT NULL,
  `agent_answer_time` datetime DEFAULT NULL,
  `agent_channel_uuid` varchar(40) DEFAULT NULL,
  `agent_group_id` varchar(30) DEFAULT NULL,
  `agent_start_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `call_start_time` datetime DEFAULT NULL,
  `hangup_time` datetime DEFAULT NULL,
  `answer_time` datetime DEFAULT NULL,
  `duration` int(11) DEFAULT NULL,
  `bill_sec` int(11) DEFAULT NULL,
  `call_direction` int(2) DEFAULT NULL,
  `call_state` int(2) DEFAULT NULL,
  `hangup_direction` int(2) DEFAULT NULL,
  `accurate_intent` varchar(20) DEFAULT NULL,
  `reason` varchar(30) DEFAULT NULL,
  `hangup_code` varchar(10) DEFAULT NULL,
  `originate_cmd` varchar(500) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `has_tts` tinyint(1) DEFAULT NULL,
  `ai_id` varchar(50) DEFAULT NULL,
  `isdel` int(2) DEFAULT '0',
  `isread` int(2) NOT NULL DEFAULT '0',
  PRIMARY KEY (`call_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `call_in_plan` */

/*Table structure for table `call_in_plan_0` */

DROP TABLE IF EXISTS `call_in_plan_0`;

CREATE TABLE `call_in_plan_0` (
  `call_id` varchar(50) NOT NULL DEFAULT '',
  `phone_num` varchar(30) NOT NULL DEFAULT '',
  `customer_id` varchar(50) NOT NULL DEFAULT '',
  `temp_id` varchar(30) NOT NULL DEFAULT '',
  `line_id` int(11) NOT NULL,
  `serverId` varchar(30) DEFAULT NULL,
  `agent_id` varchar(30) DEFAULT NULL,
  `agent_answer_time` datetime DEFAULT NULL,
  `agent_channel_uuid` varchar(40) DEFAULT NULL,
  `agent_group_id` varchar(30) DEFAULT NULL,
  `agent_start_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `call_start_time` datetime DEFAULT NULL,
  `hangup_time` datetime DEFAULT NULL,
  `answer_time` datetime DEFAULT NULL,
  `duration` int(11) DEFAULT NULL,
  `bill_sec` int(11) DEFAULT NULL,
  `call_direction` int(2) DEFAULT NULL,
  `call_state` int(2) DEFAULT NULL,
  `hangup_direction` int(2) DEFAULT NULL,
  `accurate_intent` varchar(20) DEFAULT NULL,
  `reason` varchar(30) DEFAULT NULL,
  `hangup_code` varchar(10) DEFAULT NULL,
  `originate_cmd` varchar(500) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `has_tts` tinyint(1) DEFAULT NULL,
  `ai_id` varchar(50) DEFAULT NULL,
  `isdel` int(2) DEFAULT '0',
  `isread` int(2) NOT NULL DEFAULT '0',
  PRIMARY KEY (`call_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `call_in_plan_0` */

/*Table structure for table `call_in_plan_1` */

DROP TABLE IF EXISTS `call_in_plan_1`;

CREATE TABLE `call_in_plan_1` (
  `call_id` varchar(50) NOT NULL DEFAULT '',
  `phone_num` varchar(30) NOT NULL DEFAULT '',
  `customer_id` varchar(50) NOT NULL DEFAULT '',
  `temp_id` varchar(30) NOT NULL DEFAULT '',
  `line_id` int(11) NOT NULL,
  `serverId` varchar(30) DEFAULT NULL,
  `agent_id` varchar(30) DEFAULT NULL,
  `agent_answer_time` datetime DEFAULT NULL,
  `agent_channel_uuid` varchar(40) DEFAULT NULL,
  `agent_group_id` varchar(30) DEFAULT NULL,
  `agent_start_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `call_start_time` datetime DEFAULT NULL,
  `hangup_time` datetime DEFAULT NULL,
  `answer_time` datetime DEFAULT NULL,
  `duration` int(11) DEFAULT NULL,
  `bill_sec` int(11) DEFAULT NULL,
  `call_direction` int(2) DEFAULT NULL,
  `call_state` int(2) DEFAULT NULL,
  `hangup_direction` int(2) DEFAULT NULL,
  `accurate_intent` varchar(20) DEFAULT NULL,
  `reason` varchar(30) DEFAULT NULL,
  `hangup_code` varchar(10) DEFAULT NULL,
  `originate_cmd` varchar(500) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `has_tts` tinyint(1) DEFAULT NULL,
  `ai_id` varchar(50) DEFAULT NULL,
  `isdel` int(2) DEFAULT '0',
  `isread` int(2) NOT NULL DEFAULT '0',
  PRIMARY KEY (`call_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `call_in_plan_1` */

/*Table structure for table `call_in_record` */

DROP TABLE IF EXISTS `call_in_record`;

CREATE TABLE `call_in_record` (
  `call_id` varchar(50) NOT NULL DEFAULT '',
  `record_file` varchar(50) DEFAULT NULL,
  `record_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`call_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `call_in_record` */

/*Table structure for table `call_out_detail` */

DROP TABLE IF EXISTS `call_out_detail`;

CREATE TABLE `call_out_detail` (
  `call_id` varchar(50) DEFAULT NULL,
  `call_detail_id` varchar(50) NOT NULL,
  `accurate_intent` varchar(10) DEFAULT NULL,
  `agent_answer_text` varchar(255) DEFAULT NULL,
  `agent_answer_time` datetime DEFAULT NULL,
  `ai_duration` int(11) DEFAULT NULL,
  `asr_duration` int(11) DEFAULT NULL,
  `bot_answer_text` varchar(255) DEFAULT NULL,
  `bot_answer_time` datetime DEFAULT NULL,
  `call_detail_type` int(2) NOT NULL,
  `customer_say_text` varchar(255) DEFAULT NULL,
  `customer_say_time` datetime DEFAULT NULL,
  `reason` varchar(255) DEFAULT NULL,
  `total_duration` int(11) DEFAULT NULL,
  `sharding_value` int(2) NOT NULL DEFAULT '0',
  PRIMARY KEY (`call_detail_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `call_out_detail` */

/*Table structure for table `call_out_detail_0` */

DROP TABLE IF EXISTS `call_out_detail_0`;

CREATE TABLE `call_out_detail_0` (
  `call_id` varchar(50) DEFAULT NULL,
  `call_detail_id` varchar(50) NOT NULL,
  `accurate_intent` varchar(10) DEFAULT NULL,
  `agent_answer_text` varchar(255) DEFAULT NULL,
  `agent_answer_time` datetime DEFAULT NULL,
  `ai_duration` int(11) DEFAULT NULL,
  `asr_duration` int(11) DEFAULT NULL,
  `bot_answer_text` varchar(255) DEFAULT NULL,
  `bot_answer_time` datetime DEFAULT NULL,
  `call_detail_type` int(2) NOT NULL,
  `customer_say_text` varchar(255) DEFAULT NULL,
  `customer_say_time` datetime DEFAULT NULL,
  `reason` varchar(255) DEFAULT NULL,
  `total_duration` int(11) DEFAULT NULL,
  `sharding_value` int(2) NOT NULL DEFAULT '0',
  PRIMARY KEY (`call_detail_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/
DROP TABLE IF EXISTS `call_out_detail_1`;

CREATE TABLE `call_out_detail_1` (
  `call_id` varchar(50) DEFAULT NULL,
  `call_detail_id` varchar(50) NOT NULL,
  `accurate_intent` varchar(10) DEFAULT NULL,
  `agent_answer_text` varchar(255) DEFAULT NULL,
  `agent_answer_time` datetime DEFAULT NULL,
  `ai_duration` int(11) DEFAULT NULL,
  `asr_duration` int(11) DEFAULT NULL,
  `bot_answer_text` varchar(255) DEFAULT NULL,
  `bot_answer_time` datetime DEFAULT NULL,
  `call_detail_type` int(2) NOT NULL,
  `customer_say_text` varchar(255) DEFAULT NULL,
  `customer_say_time` datetime DEFAULT NULL,
  `reason` varchar(255) DEFAULT NULL,
  `total_duration` int(11) DEFAULT NULL,
  `sharding_value` int(2) NOT NULL DEFAULT '0',
  PRIMARY KEY (`call_detail_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `call_out_detail_record`;

CREATE TABLE `call_out_detail_record` (
  `call_id` varchar(50) NOT NULL DEFAULT '',
  `call_detail_id` varchar(50) NOT NULL,
  `agent_record_file` varchar(100) DEFAULT NULL,
  `agent_record_url` varchar(255) DEFAULT NULL,
  `bot_record_file` varchar(100) DEFAULT NULL,
  `bot_record_url` varchar(255) DEFAULT NULL,
  `customer_record_file` varchar(100) DEFAULT NULL,
  `customer_record_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`call_detail_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `call_out_plan`;

CREATE TABLE `call_out_plan` (
  `call_id` varchar(50) NOT NULL DEFAULT '',
  `phone_num` varchar(30) NOT NULL DEFAULT '',
  `customer_id` varchar(50) NOT NULL DEFAULT '',
  `temp_id` varchar(30) NOT NULL DEFAULT '',
  `line_id` int(11) NOT NULL,
  `serverId` varchar(30) DEFAULT NULL,
  `agent_id` varchar(30) DEFAULT NULL,
  `agent_answer_time` datetime DEFAULT NULL,
  `agent_channel_uuid` varchar(40) DEFAULT NULL,
  `agent_group_id` varchar(30) DEFAULT NULL,
  `agent_start_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `call_start_time` datetime DEFAULT NULL,
  `hangup_time` datetime DEFAULT NULL,
  `answer_time` datetime DEFAULT NULL,
  `duration` int(11) DEFAULT NULL,
  `bill_sec` int(11) DEFAULT NULL,
  `call_direction` int(2) DEFAULT NULL,
  `call_state` int(2) DEFAULT NULL,
  `hangup_direction` int(2) DEFAULT NULL,
  `accurate_intent` varchar(20) DEFAULT NULL,
  `reason` varchar(255) DEFAULT NULL,
  `hangup_code` varchar(10) DEFAULT NULL,
  `originate_cmd` varchar(500) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `has_tts` tinyint(1) DEFAULT NULL,
  `ai_id` varchar(50) DEFAULT NULL,
  `freason` int(2) DEFAULT NULL COMMENT '1:占线，2:无人接听,3:主叫停机,4:被叫停机,5:空号,6:关机,7:呼叫限制,8:用户拒接,9:无效号码,10:拒接',
  `isdel` int(2) NOT NULL DEFAULT '0',
  `isread` int(2) NOT NULL DEFAULT '0',
  PRIMARY KEY (`call_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `call_out_plan` */

/*Table structure for table `call_out_plan_0` */

DROP TABLE IF EXISTS `call_out_plan_0`;

CREATE TABLE `call_out_plan_0` (
  `call_id` varchar(50) NOT NULL DEFAULT '',
  `phone_num` varchar(30) NOT NULL DEFAULT '',
  `customer_id` varchar(50) NOT NULL DEFAULT '',
  `temp_id` varchar(30) NOT NULL DEFAULT '',
  `line_id` int(11) NOT NULL,
  `serverId` varchar(30) DEFAULT NULL,
  `agent_id` varchar(30) DEFAULT NULL,
  `agent_answer_time` datetime DEFAULT NULL,
  `agent_channel_uuid` varchar(40) DEFAULT NULL,
  `agent_group_id` varchar(30) DEFAULT NULL,
  `agent_start_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `call_start_time` datetime DEFAULT NULL,
  `hangup_time` datetime DEFAULT NULL,
  `answer_time` datetime DEFAULT NULL,
  `duration` int(11) DEFAULT NULL,
  `bill_sec` int(11) DEFAULT NULL,
  `call_direction` int(2) DEFAULT NULL,
  `call_state` int(2) DEFAULT NULL,
  `hangup_direction` int(2) DEFAULT NULL,
  `accurate_intent` varchar(20) DEFAULT NULL,
  `reason` varchar(255) DEFAULT NULL,
  `hangup_code` varchar(10) DEFAULT NULL,
  `originate_cmd` varchar(500) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `has_tts` tinyint(1) DEFAULT NULL,
  `ai_id` varchar(50) DEFAULT NULL,
  `freason` int(2) DEFAULT NULL COMMENT '1:占线，2:无人接听,3:主叫停机,4:被叫停机,5:空号,6:关机,7:呼叫限制,8:用户拒接,9:无效号码,10:拒接',
  `isdel` int(2) NOT NULL DEFAULT '0',
  `isread` int(2) NOT NULL DEFAULT '0',
  PRIMARY KEY (`call_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `call_out_plan_1`;

CREATE TABLE `call_out_plan_1` (
  `call_id` varchar(50) NOT NULL DEFAULT '',
  `phone_num` varchar(30) NOT NULL DEFAULT '',
  `customer_id` varchar(50) NOT NULL DEFAULT '',
  `temp_id` varchar(30) NOT NULL DEFAULT '',
  `line_id` int(11) NOT NULL,
  `serverId` varchar(30) DEFAULT NULL,
  `agent_id` varchar(30) DEFAULT NULL,
  `agent_answer_time` datetime DEFAULT NULL,
  `agent_channel_uuid` varchar(40) DEFAULT NULL,
  `agent_group_id` varchar(30) DEFAULT NULL,
  `agent_start_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `call_start_time` datetime DEFAULT NULL,
  `hangup_time` datetime DEFAULT NULL,
  `answer_time` datetime DEFAULT NULL,
  `duration` int(11) DEFAULT NULL,
  `bill_sec` int(11) DEFAULT NULL,
  `call_direction` int(2) DEFAULT NULL,
  `call_state` int(2) DEFAULT NULL,
  `hangup_direction` int(2) DEFAULT NULL,
  `accurate_intent` varchar(20) DEFAULT NULL,
  `reason` varchar(255) DEFAULT NULL,
  `hangup_code` varchar(10) DEFAULT NULL,
  `originate_cmd` varchar(500) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `has_tts` tinyint(1) DEFAULT NULL,
  `ai_id` varchar(50) DEFAULT NULL,
  `freason` int(2) DEFAULT NULL COMMENT '1:占线，2:无人接听,3:主叫停机,4:被叫停机,5:空号,6:关机,7:呼叫限制,8:用户拒接,9:无效号码,10:拒接',
  `isdel` int(2) NOT NULL DEFAULT '0',
  `isread` int(2) NOT NULL DEFAULT '0',
  PRIMARY KEY (`call_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `call_out_record`;

CREATE TABLE `call_out_record` (
  `call_id` varchar(50) NOT NULL DEFAULT '',
  `record_file` varchar(50) DEFAULT NULL,
  `record_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`call_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `error_match`;

CREATE TABLE `error_match` (
  `error_type` int(2) DEFAULT NULL,
  `error_name` varchar(100) DEFAULT NULL,
  `en_name` varchar(100) DEFAULT NULL,
  `key_word` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


insert  into `error_match`(`error_type`,`error_name`,`en_name`,`key_word`) values (0,'无人接听','no_answer','无人接听');
insert  into `error_match`(`error_type`,`error_name`,`en_name`,`key_word`) values (1,'主叫停机','initiative_stop','您的电话已欠费');
insert  into `error_match`(`error_type`,`error_name`,`en_name`,`key_word`) values (1,'主叫停机','initiative_stop','续交话费');
insert  into `error_match`(`error_type`,`error_name`,`en_name`,`key_word`) values (1,'主叫停机','initiative_stop','手机号码已暂停');
insert  into `error_match`(`error_type`,`error_name`,`en_name`,`key_word`) values (1,'主叫停机','initiative_stop','您的手机号码');
insert  into `error_match`(`error_type`,`error_name`,`en_name`,`key_word`) values (1,'主叫停机','initiative_stop','继续使用');
insert  into `error_match`(`error_type`,`error_name`,`en_name`,`key_word`) values (1,'主叫停机','initiative_stop','无法呼出');
insert  into `error_match`(`error_type`,`error_name`,`en_name`,`key_word`) values (1,'主叫停机','initiative_stop','您的电话已被停机');
insert  into `error_match`(`error_type`,`error_name`,`en_name`,`key_word`) values (2,'被叫停机','passive_stop','欠费');
insert  into `error_match`(`error_type`,`error_name`,`en_name`,`key_word`) values (2,'被叫停机','passive_stop','停机');
insert  into `error_match`(`error_type`,`error_name`,`en_name`,`key_word`) values (2,'被叫停机','passive_stop','通话已经被');
insert  into `error_match`(`error_type`,`error_name`,`en_name`,`key_word`) values (2,'被叫停机','passive_stop','所拨打的用户通话');
insert  into `error_match`(`error_type`,`error_name`,`en_name`,`key_word`) values (2,'被叫停机','passive_stop','暂停服务');
insert  into `error_match`(`error_type`,`error_name`,`en_name`,`key_word`) values (2,'被叫停机','passive_stop','未开通语音');
insert  into `error_match`(`error_type`,`error_name`,`en_name`,`key_word`) values (3,'关机或不在服务区','power_off','关机');
insert  into `error_match`(`error_type`,`error_name`,`en_name`,`key_word`) values (3,'关机或不在服务区','power_off','不在服务区');
insert  into `error_match`(`error_type`,`error_name`,`en_name`,`key_word`) values (4,'占线','busy','占线');
insert  into `error_match`(`error_type`,`error_name`,`en_name`,`key_word`) values (4,'占线','busy','通话中');
insert  into `error_match`(`error_type`,`error_name`,`en_name`,`key_word`) values (4,'占线','busy','正在通话');
insert  into `error_match`(`error_type`,`error_name`,`en_name`,`key_word`) values (4,'占线','busy','无法接通');
insert  into `error_match`(`error_type`,`error_name`,`en_name`,`key_word`) values (4,'占线','busy','正忙');
insert  into `error_match`(`error_type`,`error_name`,`en_name`,`key_word`) values (4,'占线','busy','无法接听');
insert  into `error_match`(`error_type`,`error_name`,`en_name`,`key_word`) values (4,'占线','busy','联通秘书');
insert  into `error_match`(`error_type`,`error_name`,`en_name`,`key_word`) values (4,'占线','busy','来电助手');
insert  into `error_match`(`error_type`,`error_name`,`en_name`,`key_word`) values (4,'占线','busy','来电宝');
insert  into `error_match`(`error_type`,`error_name`,`en_name`,`key_word`) values (4,'占线','busy','短信的方式');
insert  into `error_match`(`error_type`,`error_name`,`en_name`,`key_word`) values (4,'占线','busy','短信方式');
insert  into `error_match`(`error_type`,`error_name`,`en_name`,`key_word`) values (5,'空号','vacant_number','空号');
insert  into `error_match`(`error_type`,`error_name`,`en_name`,`key_word`) values (5,'空号','vacant_number','不存在');
insert  into `error_match`(`error_type`,`error_name`,`en_name`,`key_word`) values (5,'空号','vacant_number','不在使用');
insert  into `error_match`(`error_type`,`error_name`,`en_name`,`key_word`) values (6,'呼叫受限','call_limit','越权使用');
insert  into `error_match`(`error_type`,`error_name`,`en_name`,`key_word`) values (6,'呼叫受限','call_limit','呼叫受限');
insert  into `error_match`(`error_type`,`error_name`,`en_name`,`key_word`) values (6,'呼叫受限','call_limit','呼叫该号码');
insert  into `error_match`(`error_type`,`error_name`,`en_name`,`key_word`) values (6,'呼叫受限','call_limit','语音信箱');
insert  into `error_match`(`error_type`,`error_name`,`en_name`,`key_word`) values (7,'用户挂断','user_hangup','用户挂断');
insert  into `error_match`(`error_type`,`error_name`,`en_name`,`key_word`) values (8,'无效号码','Invalid_number','无效号码');
insert  into `error_match`(`error_type`,`error_name`,`en_name`,`key_word`) values (9,'已接通','connected','已接通');
insert  into `error_match`(`error_type`,`error_name`,`en_name`,`key_word`) values (10,'无人接听','no_answer','无人接听');

/*Table structure for table `fs_bind` */

DROP TABLE IF EXISTS `fs_bind`;

CREATE TABLE `fs_bind` (
  `service_id` varchar(20) NOT NULL DEFAULT '',
  `service_name` varchar(50) DEFAULT NULL,
  `fs_agent_id` varchar(30) NOT NULL DEFAULT '',
  `fs_agent_addr` varchar(30) DEFAULT NULL,
  `fs_esl_port` varchar(20) DEFAULT NULL,
  `fs_esl_pwd` varchar(20) DEFAULT NULL,
  `fs_in_port` varchar(20) DEFAULT NULL,
  `fs_out_port` varchar(20) DEFAULT NULL,
  `create_date` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `line_config`;

CREATE TABLE `line_config` (
  `line_id` varchar(30) DEFAULT NULL,
  `file_type` varchar(30) DEFAULT NULL,
  `file_name` varchar(50) DEFAULT NULL,
  `file_data` varchar(800) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `line_count`;

CREATE TABLE `line_count` (
  `calloutserver_id` varchar(30) NOT NULL DEFAULT '',
  `line_id` int(11) NOT NULL,
  `max_concurrent_calls` int(11) DEFAULT NULL,
  `used_concurrent_calls` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `line_info`;

CREATE TABLE `line_info` (
  `line_id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` varchar(11) DEFAULT NULL,
  `line_name` varchar(30) DEFAULT NULL,
  `sip_ip` varchar(20) DEFAULT NULL,
  `sip_port` varchar(20) DEFAULT NULL,
  `codec` varchar(20) DEFAULT NULL,
  `caller_num` varchar(100) DEFAULT NULL,
  `callee_prefix` varchar(20) DEFAULT NULL,
  `max_concurrent_calls` int(11) DEFAULT NULL,
  `create_date` varchar(20) DEFAULT NULL,
  `update_date` varchar(20) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `createt_by` varchar(255) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`line_id`)
) ENGINE=InnoDB AUTO_INCREMENT=249 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `report_call_count`;

CREATE TABLE `report_call_count` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `call_date` varchar(20) NOT NULL,
  `count_a` int(11) DEFAULT '0',
  `count_b` int(11) DEFAULT '0',
  `count_c` int(11) DEFAULT '0',
  `count_d` int(11) DEFAULT '0',
  `count_e` int(11) DEFAULT '0',
  `count_f` int(11) DEFAULT '0',
  `count_u` int(11) DEFAULT '0',
  `count_v` int(11) DEFAULT '0',
  `count_w` int(11) DEFAULT '0',
  `count_all` int(11) DEFAULT '0',
  `accurate_intent` varchar(10) DEFAULT NULL,
  `customer_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;

/*Data for the table `report_call_count` */

/*Table structure for table `report_call_day` */
DROP TABLE IF EXISTS `report_call_day`;

CREATE TABLE `report_call_day` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `call_date` varchar(50) DEFAULT NULL,
  `duration_type` int(2) DEFAULT NULL,
  `intent` varchar(10) DEFAULT NULL,
  `reason` varchar(50) DEFAULT NULL,
  `call_count` int(11) DEFAULT NULL,
  `duration_all` bigint(20) DEFAULT NULL,
  `customer_id` varchar(50) DEFAULT NULL,
  `tempid` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=322 DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `report_call_hour`;

CREATE TABLE `report_call_hour` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `call_time` datetime DEFAULT NULL,
  `out_count` int(11) DEFAULT NULL,
  `connect_count` int(11) DEFAULT NULL,
  `duration` bigint(20) DEFAULT NULL,
  `customer_id` varchar(50) DEFAULT NULL,
  `tempid` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `report_call_today`;

CREATE TABLE `report_call_today` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `duration_type` int(2) DEFAULT NULL,
  `intent` varchar(10) DEFAULT NULL,
  `reason` varchar(50) DEFAULT NULL,
  `call_count` int(11) DEFAULT NULL,
  `duration_all` bigint(20) DEFAULT NULL,
  `customer_id` varchar(50) DEFAULT NULL,
  `tempid` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

