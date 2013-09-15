<?php defined('SYSPATH') or die('No direct script access.');

class Model_Locais extends ORM {
    protected $_table_name = "locais";

    public function salvarLocal($arrDados = null){
        $ret            = new stdClass();
        $ret->status    = false;
        
        $this->where("nome", "=", $arrDados['nome'])
             ->find();
        
        if($this->loaded()){
            $ret->msg = "Esse local já possui cadastro";
        }else{
            $this->nome             = $arrDados['nome'];
            $this->latitude         = $arrDados['latitude'];
            $this->longitude        = $arrDados['longitude'];
            $this->id_categoria     = $arrDados['id_categoria'];
            $this->data_registro    = date("Y-m-d H:i:s");
            
            $this->save();
            
            $ret->status    = true;
            $ret->msg       = "Local cadastrado com Sucesso!";
        }
        
        return $ret;
    }
}

?>