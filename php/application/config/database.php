<?php defined('SYSPATH') OR die('No direct access allowed.');

return array
(
    'default' => array
    (
        'type'       => 'MySQL',
        'connection' => array(
            'hostname'   => 'mpcbsolutions.com',
            'database'   => 'mpcb_mackenzie',
            'username'   => 'mpcb_mackenzie',
            'password'   => 'mack@2*13',
            'persistent' => FALSE,
        ),
        'table_prefix' => '',
        'charset'      => 'utf8',
        'caching'      => FALSE,
    )
);

?>