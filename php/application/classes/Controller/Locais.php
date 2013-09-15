<?php defined('SYSPATH') or die('No direct script access.');

class Controller_Locais extends Controller {
    public function action_index(){
        die("Web Service PHP - Trabalho MyPlaces Mackenzie");
    }
    
    public function action_cadastrarLocal(){
        $this->_auto_render = false;
        
        $ret            = new stdClass();
        $ret->status    = false;
        
        if($this->request->method() == 'POST'){
            $mdLocais   = new Model_Locais();
            $ret        = $mdLocais->salvarLocal(array(
                            "nome"          => trim($this->request->post("nome")),
                            "latitude"      => trim($this->request->post("latitude")),
                            "longitude"     => trim($this->request->post("longitude")),
                            "id_categoria"  => trim($this->request->post("id_categoria")),
                          ));
        }else{
            $ret->msg = "Requisição post não encontrada";
        }
        
        echo json_encode($ret);
    }
}
