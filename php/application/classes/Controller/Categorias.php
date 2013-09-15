<?php defined('SYSPATH') or die('No direct script access.');

class Controller_Categorias extends Controller {
    public function action_index(){
        die("Web Service PHP - Trabalho MyPlaces Mackenzie");
    }
    
    public function action_listar(){
        $this->_auto_render = false;
        
        $ret            = new stdClass();
        $ret->status    = false;
        
        
        $mdCategorias = new Model_Categorias();
        $rs           = $mdCategorias->order_by("nome")->find_all();

        if($rs->count() <= 0){
            $ret->msg = "Nenhuma categoria encontrada";
        }else{
            $arrCategorias = array();
            
            foreach($rs as $row){
                $arrCategorias[] = array(
                    "id"    => $row->id,
                    "nome"  => $row->nome,
                    "icone" => $row->icone,
                );
            }
            
            $ret->status        = true;
            $ret->msg           = "Categorias carregadas com sucesso!";
            $ret->categorias    = $arrCategorias;
        }
        
        
        echo json_encode($ret);
    }
}
