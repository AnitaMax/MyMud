-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: localhost    Database: game
-- ------------------------------------------------------
-- Server version	8.0.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `items`
--

DROP TABLE IF EXISTS `items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `items` (
  `id` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `owner` varchar(100) DEFAULT NULL,
  `descripition` varchar(100) DEFAULT NULL,
  `effect` varchar(100) DEFAULT NULL,
  `ownertype` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `items`
--

LOCK TABLES `items` WRITE;
/*!40000 ALTER TABLE `items` DISABLE KEYS */;
INSERT INTO `items` VALUES ('xuanhuangjian','玄黄剑','yangzhou_guangchang','传自玄黄，锋无可挡','str,5,dex,-5','room'),('pufengdao','朴风刀','yangzhou_beidajie2','一把很重的刀','dex,5','room'),('maodun','矛盾','yangzhou_beidajie1','可能有特殊用处？',NULL,'room'),('dunmao','盾矛','yangzhou_beidajie3','传自神秘的人物，具有特殊的力量','maodun,dex,20','room');
/*!40000 ALTER TABLE `items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `players`
--

DROP TABLE IF EXISTS `players`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `players` (
  `experience` int(11) NOT NULL,
  `con` int(11) NOT NULL,
  `dex` int(11) NOT NULL,
  `str` int(11) NOT NULL,
  `wis` int(11) NOT NULL,
  `hp` int(11) NOT NULL,
  `max_hp` int(11) NOT NULL,
  `nl` int(11) NOT NULL,
  `max_nl` int(11) NOT NULL,
  `jl` int(11) NOT NULL,
  `max_jl` int(11) NOT NULL,
  `id` varchar(100) NOT NULL,
  `username` varchar(100) NOT NULL,
  `party` varchar(100) NOT NULL,
  `location` varchar(100) NOT NULL,
  `items` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `players`
--

LOCK TABLES `players` WRITE;
/*!40000 ALTER TABLE `players` DISABLE KEYS */;
INSERT INTO `players` VALUES (100,100,100,100,100,100,100,100,100,100,100,'admin','管理员','无','yangzhou_beidajie1',NULL),(100,100,100,100,100,100,100,100,100,100,100,'yangguo','杨过','古墓派','yangzhou_beidajie1',NULL),(100,100,100,100,100,100,100,100,100,100,100,'zhangwuji','张无忌','无','yangzhou_beidajie1',NULL);
/*!40000 ALTER TABLE `players` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rooms`
--

DROP TABLE IF EXISTS `rooms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `rooms` (
  `roomid` varchar(100) NOT NULL,
  `roomname` varchar(100) NOT NULL,
  `roomdescripition` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `north` varchar(100) DEFAULT NULL,
  `east` varchar(100) DEFAULT NULL,
  `northeast` varchar(100) DEFAULT NULL,
  `northwest` varchar(100) DEFAULT NULL,
  `items` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rooms`
--

LOCK TABLES `rooms` WRITE;
/*!40000 ALTER TABLE `rooms` DISABLE KEYS */;
INSERT INTO `rooms` VALUES ('yangzhou_guangchang','扬州广场',' 这里是城市的正中心，一个很宽阔的广场，铺着青石地面。一些游手好闲的人在这里溜溜达达，经常有艺人在这里表演。你可以看到东边和南边是几家大的店铺，来自全国各地的行人来来往往，西面是扬州戌守兵营，而北边则是扬州巡抚衙门的所在。广场中央有一棵老桃树(tree)，显得十分的粗壮，树上的树叶(leaf)非常茂密，有几只不知名的小鸟在其中窜来窜去。大树旁边有一块新立的牌子(sign)','yangzhou_beidajie1','yangzhou_dongdajie1',NULL,NULL,NULL),('yangzhou_beidajie1','北大街','你走在一条繁忙的街道上，看着操着南腔北调的人们行色匆匆，许多人都往南边走去，那里通向一个热闹的广场。东边是一家生意兴隆的宝昌客栈，来自各地的人们进进出出。西边是扬州一家老字号的钱庄，名为天阁斋。可以听到叮叮当当的金银声音。','yangzhou_beidajie2','yangzhou_kezhan',NULL,NULL,NULL),('yangzhou_beidajie2','北大街','你走在一条繁忙的街道上，东边是扬州驿站，来自京城的快马有时会急驶而入，片刻后又会有另一匹快马从里面冲出来绝尘而去，南边是一家大店铺，门前的绣金旗子上写着一个斗大的“当”字，仔细倾听，可以听到压低的讨价还价的声音。','yangzhou_beidajie3','yangzhou_yizhan',NULL,NULL,NULL),('yangzhou_beidajie3','北大街','这是一条宽阔的青石街道，向南北两头延伸。北边是北城门通向城外。南边显得很繁忙。东边是一家小吃店，店面窄小，恐怕只供应外卖。西边就是城隍庙。','yangzhou_beimen','yangzhou_xiaochidian',NULL,NULL,NULL),('yangzhou_beimen','北门','这是就是扬州城的北城门了，城门口一队队官兵正在盘查过往的百姓，一个手持长剑身穿铁甲武将正在门口巡视，近些时候因为江南一带的贩私盐愈演愈烈，所以朝廷以办事不利的罪名撤消了程要发的官职，上京问斩了，派了一位新的巡抚大人，弄的现在人心惶惶，谁都不敢在城门久留。城门的墙上张贴着一纸告示(gaoshi)。',NULL,'yangzhou_yizhan',NULL,NULL,NULL),('yangzhou_qianzhuang','天阁斋','这是一家老字号的钱庄，已有几百年的历史，在全国各地都有分店。它发行的银票信誉非常好，通行全国。',NULL,'yangzhou_beidajie1',NULL,NULL,NULL),('yangzhou_kezhan','宝昌客栈','这里是生意兴隆的宝昌客栈，附近的外地游客多选择在此落脚。一个年轻的店小二里里外外忙得团团转，接待着南腔北调的客人。客栈北面的墙上挂着一块醒目的招牌(zhaopai)，南面的墙上挂有一张作废过期的告示(gaoshi)。',NULL,NULL,NULL,NULL,NULL),('yangzhou_yizhan','驿站','这是扬州驿站，负责传送和京城里的往来公文。每天快马三匹，早午晚各传递书信三次。不管你的书信要寄到哪里，只要放到这里，一天之内绝对可以送到，北边是一家新开的花店，据说里面的老板非常漂亮。',NULL,NULL,NULL,NULL,NULL),('yangzhou_dangpu','当铺','这是一家素以买卖公平著称的老字号当铺，一个四尺高的柜台摆在你的面前，柜台上摆着一个牌子(paizi)， 柜台后坐着当铺的老板，一双鬼溜溜的眼睛上上下下打量着你.',NULL,'yangzhou_beidajie2',NULL,NULL,NULL),('yangzhou_chmiao','城隍庙','这是扬州城北的城隍庙。庙里平日香客稀少，赶上中秋节、端午节或者庙会的时候，才会有些香火。堂中放着一个城隍的塑像，上面落满了灰尘，墙角里有个破蜘蛛网，两只蜘蛛无聊地蹲在上面。',NULL,'yangzhou_beidajie3',NULL,NULL,NULL),('yangzhou_xiaochidian','小吃店','这是一家小店，生意倒是挺兴隆的，座位坐得满满的，还有些人在站着等座。看来多等也是无益，不如买点包子、鸡腿、烤鸭、米酒赶路吧。',NULL,NULL,NULL,NULL,NULL),('yangzhou_nandajie1','南大街','南大街乃是扬州城里的繁华地段，一到晚上，一派灯红酒绿，尤为热闹。笑声、歌声、琴声、箫声，汇成一片送入了你的耳朵，你不禁心猿意马，很想就此停步享受一番。北边是一个热闹的广场。西边是一片喧嚣，夹杂着“一五一十”的数钱声，原来那里是方圆千里之内最大的一家赌场','yangzhou_guangchang','yangzhou_duchang',NULL,NULL,NULL),('yangzhou_nandajie2','南大街','你走在一条繁华的街道上，向南北两头延伸。南边是几座园林，北边通往市中心，西边是一家顾客众多的茶馆，三教九流人士都在那里谈天说地。东边则是官府特设的扬州盐局。','yangzhou_nandajie1','yangzhou_chaguan',NULL,NULL,NULL),('yangzhou_nandajie3','南大街','你走在一条繁华的街道上，向南北两头延伸。南边是南城门，北边通往市中心，东西两边各是一座小园子','yangzhou_nandajie2','yangzhou_geyuan',NULL,NULL,NULL),('yangzhou_nanmen','南门','这是扬州的南城门，城墙上贴着一张官府的告示(gaoshi)。扬州是淮盐的集散地，往来的盐贩子很多，几个官兵正在认真地检查过往的行人，查看他们是否携带有私盐。南边的青石大道可以一直通到远处长江北岸的码头。','yangzhou_nandajie3',NULL,NULL,NULL,NULL),('yangzhou_duchang','赌场','这里是赌场的大堂，四周的房间里传出来吆五喝六的赌博声，北边是赌「大小」的房间，西面是赌「牌九」的房间，楼上的拱猪房灯火通明。',NULL,NULL,NULL,NULL,NULL),('yangzhou_bingying','兵营','这里是兵营，密密麻麻到处都是官兵，有的在武将的指挥下列队操练，有的独自在练功，有的坐着、躺着正在休息。南墙下坐着主帅，不动声色地寻视着四周。看到你进来，他们全都向你包围了过来，形势看来不太妙。西边有一个小窗口(window)。为了方便探监的百姓，可以从这个窗口传递(pass)些吃的东西。',NULL,'yangzhou_nandajie1',NULL,NULL,NULL),('yangzhou_chaguan','茶馆','这是扬州城南的一家茶馆。一走进来，就闻到一股茶香沁入心脾，你的精神为之一爽。几张八仙桌一字排开，坐满了客人，或高声谈笑，或交头接耳。壁上挂了一幅字贴(zitie) 。茶馆中有一说书之人，讲述三国志、水浒传、大明英烈传等等英雄故事。茶博士正在吆喝着招呼客人, 递茶送水, 忙的不亦乐乎。',NULL,NULL,NULL,NULL,NULL),('yangzhou_yanju','盐局','你来到一座结构宏伟的建筑前，左右石坛上各插着一根两丈多高的旗杆，杆上锦旗飘扬。左首旗子上写着“扬州盐局”四个黑字，银钩铁划，端的是刚劲非凡。扬州一带是江北淮盐的集散之地，官府为了便于管理, 征得盐税, 特设了扬州盐局, 直接听命于朝廷。入口处有一堆盐商在说笑，墙角堆着一堆石灰粉，旁边放着个布袋。',NULL,'yangzhou_nandajie2',NULL,NULL,NULL),('yangzhou_geyuan','个园','据传这里是盐商黄应泰修建。园内种竹千杆，因竹叶形如“个”字，故以是名。入园门左转至复道廊，迎面花坛种竹，竹间立石笋。北面园门上有“个园”题字石额。园门向北为桂花厅，前植桂树，后凿水池，六角攒尖式小亭隔水相望。延北墙是湖石假山，山上古松，山下趋桥流水。夏日晴雨，水中倒影多变，有下山之称。',NULL,NULL,NULL,NULL,NULL),('yangzhou_xiaopangu','小盘古','小盘古是扬州一座著名的小庭院。园内以湖石假山为主，山下有曲洞，绕洞而行，进入一条回廊，尽头紧接着山谷。山峰下，水池上，凌空架一石梁，通向水阁凉亭。',NULL,'yangzhou_nandajie3',NULL,NULL,NULL),('yangzhou_xidajie1','西大街','这是一条宽阔的青石板街道，向东西两头延伸。北边是一片青色的楼房，隐约传来阵阵的浪笑，那就是天下闻名的鸣玉坊。这里往东面走是一个热闹的广场。',NULL,'yangzhou_guangchang',NULL,NULL,NULL),('yangzhou_xidajie2','西大街','这是一条宽阔的青石板街道，向东西两头延伸。这里来来往往的游人你推我挤，热闹非常。从东面不时地传来笑语莺歌，声声撩人，而从西边则传来一阵阵的香气。南边是有名的扬州大明寺，北边就是瘦西湖边了。',NULL,'yangzhou_xidajie1',NULL,NULL,NULL),('yangzhou_xidajie3','西大街','你走在西大街上，感到这里的街面要比别处的干净、整洁。街上的行人比以前也多了许多，东面是扬州的著名风景“瘦西湖”，西边是西城门。南边是一家已经关门了的珠宝店，而北边则是一座已经打烊了的大酒楼，挂着一幅招帘，门额上悬挂一方横匾，黑暗之中看不太清匾上的字迹。',NULL,'yangzhou_xidajie2',NULL,NULL,NULL),('yangzhou_ximen','西门','这是西城门，城墙上贴着几张通缉告示(gaoshi)。西门是往西域的必经之地，官兵们戒备森严。一条笔直的青石板大道向东西两边延伸。西边是郊外，骑马的、坐轿的、走路的，行人匆匆。东边是城里的西大街。',NULL,'yangzhou_xidajie3',NULL,NULL,NULL),('yangzhou_dongdajie1','东大街',' 这是一条宽阔的青石板街道，向东西两头延伸。东边不时地传来朗朗的书声，西边是一个热闹的广场，南边则是一家古香古色的店铺，里边不时传出用乐器吹奏的曲调。',NULL,'yangzhou_dongdajie2',NULL,NULL,NULL),('yangzhou_dongdajie2','东大街','这是一条宽阔的青石板街道，向东西两头延伸。东边不时地传来金属撞击声，西边人声嘈杂。北边是一座简朴的院子，半月形的大门上方写着“白鹿书院”四个烫金大字，苍劲有力。里面不时地传来学生们抑扬顿挫的读书声。南边是一家杂货铺。',NULL,'yangzhou_dongdajie3',NULL,NULL,NULL),('yangzhou_dongdajie3','东大街','你走在东大街上，踩着坚实的青石板地面。东边是打铁铺和武器店，不时传来敲敲打打的金属撞击声，与西边朗朗的读书声混杂在一起。北边是一家老字号的药铺，南边是家寄卖店。',NULL,'yangzhou_dongmen',NULL,NULL,NULL),('yangzhou_dongmen','东门','这里是扬州东城门，城门正上方刻着“东门”两个楷书大字，城墙上贴着几张官府的告示(gaoshi)。官兵们警惕地注视着过往行人，通缉犯最好小心为妙。一条笔直的青石板大道向东西两边延伸。东边是郊外，隐约可见一片一望无际的树林，神秘莫测。',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `rooms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `users` (
  `id` varchar(100) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('admin','管理员','12345687'),('yangguo','杨过','12345687'),('zhangwuji','张无忌','12345687');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'game'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-04 17:14:15
