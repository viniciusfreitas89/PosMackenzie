<?php defined('SYSPATH') OR die('No direct script access.'); ?>

2013-09-07 14:49:21 --- EMERGENCY: Database_Exception [ 1146 ]: Table 'mpcb_mackenzie.usuarioses' doesn't exist [ SHOW FULL COLUMNS FROM `usuarioses` ] ~ MODPATH/database/classes/Kohana/Database/MySQL.php [ 194 ] in /home/mpcb/public_html/mackenzie/modules/database/classes/Kohana/Database/MySQL.php:359
2013-09-07 14:49:21 --- DEBUG: #0 /home/mpcb/public_html/mackenzie/modules/database/classes/Kohana/Database/MySQL.php(359): Kohana_Database_MySQL->query(1, 'SHOW FULL COLUM...', false)
#1 /home/mpcb/public_html/mackenzie/modules/orm/classes/Kohana/ORM.php(1668): Kohana_Database_MySQL->list_columns('usuarioses')
#2 /home/mpcb/public_html/mackenzie/modules/orm/classes/Kohana/ORM.php(444): Kohana_ORM->list_columns()
#3 /home/mpcb/public_html/mackenzie/modules/orm/classes/Kohana/ORM.php(389): Kohana_ORM->reload_columns()
#4 /home/mpcb/public_html/mackenzie/modules/orm/classes/Kohana/ORM.php(254): Kohana_ORM->_initialize()
#5 /home/mpcb/public_html/mackenzie/application/classes/Controller/Usuarios.php(18): Kohana_ORM->__construct()
#6 /home/mpcb/public_html/mackenzie/system/classes/Kohana/Controller.php(84): Controller_Usuarios->action_efetuarLogin()
#7 [internal function]: Kohana_Controller->execute()
#8 /home/mpcb/public_html/mackenzie/system/classes/Kohana/Request/Client/Internal.php(97): ReflectionMethod->invoke(Object(Controller_Usuarios))
#9 /home/mpcb/public_html/mackenzie/system/classes/Kohana/Request/Client.php(114): Kohana_Request_Client_Internal->execute_request(Object(Request), Object(Response))
#10 /home/mpcb/public_html/mackenzie/system/classes/Kohana/Request.php(986): Kohana_Request_Client->execute(Object(Request))
#11 /home/mpcb/public_html/mackenzie/index.php(118): Kohana_Request->execute()
#12 {main} in /home/mpcb/public_html/mackenzie/modules/database/classes/Kohana/Database/MySQL.php:359
2013-09-07 16:50:20 --- EMERGENCY: Database_Exception [ 1146 ]: Table 'mpcb_mackenzie.usuarioses' doesn't exist [ SHOW FULL COLUMNS FROM `usuarioses` ] ~ MODPATH/database/classes/Kohana/Database/MySQL.php [ 194 ] in /home/mpcb/public_html/mackenzie/modules/database/classes/Kohana/Database/MySQL.php:359
2013-09-07 16:50:20 --- DEBUG: #0 /home/mpcb/public_html/mackenzie/modules/database/classes/Kohana/Database/MySQL.php(359): Kohana_Database_MySQL->query(1, 'SHOW FULL COLUM...', false)
#1 /home/mpcb/public_html/mackenzie/modules/orm/classes/Kohana/ORM.php(1668): Kohana_Database_MySQL->list_columns('usuarioses')
#2 /home/mpcb/public_html/mackenzie/modules/orm/classes/Kohana/ORM.php(444): Kohana_ORM->list_columns()
#3 /home/mpcb/public_html/mackenzie/modules/orm/classes/Kohana/ORM.php(389): Kohana_ORM->reload_columns()
#4 /home/mpcb/public_html/mackenzie/modules/orm/classes/Kohana/ORM.php(254): Kohana_ORM->_initialize()
#5 /home/mpcb/public_html/mackenzie/application/classes/Controller/Usuarios.php(18): Kohana_ORM->__construct()
#6 /home/mpcb/public_html/mackenzie/system/classes/Kohana/Controller.php(84): Controller_Usuarios->action_efetuarLogin()
#7 [internal function]: Kohana_Controller->execute()
#8 /home/mpcb/public_html/mackenzie/system/classes/Kohana/Request/Client/Internal.php(97): ReflectionMethod->invoke(Object(Controller_Usuarios))
#9 /home/mpcb/public_html/mackenzie/system/classes/Kohana/Request/Client.php(114): Kohana_Request_Client_Internal->execute_request(Object(Request), Object(Response))
#10 /home/mpcb/public_html/mackenzie/system/classes/Kohana/Request.php(986): Kohana_Request_Client->execute(Object(Request))
#11 /home/mpcb/public_html/mackenzie/index.php(118): Kohana_Request->execute()
#12 {main} in /home/mpcb/public_html/mackenzie/modules/database/classes/Kohana/Database/MySQL.php:359
2013-09-07 17:19:51 --- EMERGENCY: ErrorException [ 4 ]: syntax error, unexpected '$this' (T_VARIABLE), expecting function (T_FUNCTION) ~ APPPATH/classes/Model/Usuarios.php [ 4 ] in file:line
2013-09-07 17:19:51 --- DEBUG: #0 [internal function]: Kohana_Core::shutdown_handler()
#1 {main} in file:line
2013-09-07 17:48:44 --- EMERGENCY: ErrorException [ 4 ]: syntax error, unexpected '$_table_name' (T_VARIABLE), expecting function (T_FUNCTION) ~ APPPATH/classes/Model/Usuarios.php [ 4 ] in file:line
2013-09-07 17:48:44 --- DEBUG: #0 [internal function]: Kohana_Core::shutdown_handler()
#1 {main} in file:line
2013-09-07 17:48:58 --- EMERGENCY: ErrorException [ 4 ]: syntax error, unexpected '$_table_name' (T_VARIABLE), expecting function (T_FUNCTION) ~ APPPATH/classes/Model/Usuarios.php [ 4 ] in file:line
2013-09-07 17:48:58 --- DEBUG: #0 [internal function]: Kohana_Core::shutdown_handler()
#1 {main} in file:line