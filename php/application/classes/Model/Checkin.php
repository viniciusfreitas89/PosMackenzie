<?php defined('SYSPATH') or die('No direct script access.');

class Model_Checkin extends ORM {
    protected $_table_name = "checkin";

    public function efetuarCheckin($arrDados){
        $this->id_usuario      = $arrDados['id_usuario'];
        $this->id_local        = $arrDados['id_local'];
        $this->valor_gasto     = $arrDados['valor_gasto'];
        $this->data_registro   = date("Y-m-d H:i:s");
        
        $this->save();
        
        $mdLocais = new Model_Locais();
        $mdLocais->where("id", "=", $arrDados['id_local'])->find();
        
        if($mdLocais->loaded()){
            $mdLocais->num_checkins = $mdLocais->num_checkins+1;
            $mdLocais->update();
        }
        
        $ret            = new stdClass();
        $ret->status    = true;
        $ret->msg       = "Checkin efetuado com Sucesso!";
        
        return $ret;
    }
}

?>