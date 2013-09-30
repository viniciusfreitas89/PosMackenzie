<?php defined('SYSPATH') or die('No direct script access.');

class Model_Solicitacao extends ORM {
    protected $_table_name = "usuario_solicitacao";
    
    public function cadastrarSolicitacao($id_usuario, $id_amigo){
        $ret            = new stdClass();
        $ret->status    = false;
        
        $this->where("id_usuario", "=", $id_usuario)
             ->where("id_amigo", "=", $id_amigo)
             ->find();
        
        if($this->loaded()){
            $ret->msg = "Solicitação já enviada";
        }else{
            $this->where("id_usuario", "=", $id_amigo)
                 ->where("id_amigo", "=", $id_usuario)
                 ->find();
            
            if($this->loaded()){
                $ret->msg = "Solicitação já enviada";
            }else{
                $this->id_usuario       = $id_usuario;
                $this->id_amigo         = $id_amigo;
                $this->data_solicitacao = date("Y-m-d H:i:s");

                $this->save();

                $ret->status    = true;
                $ret->msg       = "Solicitação enviada com Sucesso!";
            }
        }
        
        return $ret;
    }
}

?>