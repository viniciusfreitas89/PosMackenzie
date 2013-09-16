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
    
    public function action_listar(){
        $this->_auto_render = false;
        
        $ret            = new stdClass();
        $ret->status    = false;
        
        $mdLocais   = new Model_Locais();
        $rs         = $mdLocais->select(array("categorias.nome", "categoria"))
                               ->order_by("nome")
                               ->join("categorias")->on("categorias.id", "=", "locais.id_categoria")
                               ->find_all();
        
        if($rs->count() <= 0){
            $ret->msg = "Nenhum local encontrado";
        }else{
            $arrRet = array();
            
            foreach($rs as $row){
                $arrRet[] = array(
                    "id_local"      => $row->id,
                    "nome"          => $row->nome,
                    "num_checkins"  => $row->num_checkins,
                    "categoria" => array(
                        "id"    => $row->id_categoria,
                        "nome"  => $row->categoria
                    )
                );
            }
            
            $ret->status    = true;
            $ret->msg       = "Locais listados com sucesso!";
            $ret->locais    = $arrRet;
        }
        
        echo json_encode($ret);
    }
}
