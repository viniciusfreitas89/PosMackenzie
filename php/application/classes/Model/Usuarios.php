<?php defined('SYSPATH') or die('No direct script access.');

class Model_Usuarios extends ORM {
    protected $_table_name="usuario";

public function efetuarLogin($email, $senha){
        $this->where("email", "=", $email)
             ->where("senha", "=", $senha)
             ->find();
        
        if($this->loaded()){
            return $this;
        }else{
            return FALSE;
        }
    }
}

?>