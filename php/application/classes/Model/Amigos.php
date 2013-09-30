<?php defined('SYSPATH') or die('No direct script access.');

class Model_Amigos extends ORM {
    protected $_table_name = "usuario_amigos";
    
    public function cadastrarAmizade($id_usuario, $id_amigo){
        $ret            = new stdClass();
        $ret->status    = false;
        
        $this->where("id_usuario", "=", $id_usuario)
             ->where("id_amigo", "=", $id_amigo)
             ->find();
        
        if($this->loaded()){
            $ret->msg = "Amizade já existe";
        }else{
            $this->id_usuario       = $id_usuario;
            $this->id_amigo         = $id_amigo;

            $this->save();

            $ret->status    = true;
            $ret->msg       = "Amizade concluida!";
        }
        
        return $ret;
    }
}

?>