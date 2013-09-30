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
        
        if($this->request->method() == 'POST'){
            $limit      = (int)$this->request->post("numero_max_resultados");
            $latitude   = (double)$this->request->post("latitude");
            $longitude  = (double)$this->request->post("longitude");
            
            $mdLocais   = new Model_Locais();
            $rs         = $mdLocais->select(array("categorias.nome", "categoria"))
                                   ->order_by(DB::expr("ABS(ABS(latitude-{$latitude}) + ABS(longitude-{$longitude}))"), "ASC")
                                   ->join("categorias")->on("categorias.id", "=", "locais.id_categoria")
                                   ->limit($limit)
                                   ->find_all();

            if($rs->count() <= 0){
                $ret->msg = "Nenhum local encontrado";
            }else{
                $arrRet = array();

                foreach($rs as $row){
                    $arrRet[] = array(
                        "id_local"      => $row->id,
                        "local"         => $row->nome,
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
        }else{
            $ret->msg = "Requisição post não encontrada";
        }
        
        echo json_encode($ret);
    }
    
    public function action_relatorioGastos(){
        $this->_auto_render = false;
        
        $ret            = new stdClass();
        $ret->status    = false;
        
        if($this->request->method() == 'POST'){
            $id_usuario = (int)$this->request->post("id_usuario");
            $dtInicio   = $this->request->post("data_inicio");
            $dtFim      = $this->request->post("data_fim");
            
            $mdLocais   = new Model_Locais();
            $rs         = $mdLocais->select(array("categorias.nome", "categoria"), array(DB::expr("SUM(ck.valor_gasto)"), "total"))
                                   ->join("categorias")->on("categorias.id", "=", "locais.id_categoria")
                                   ->join(array("checkin", "ck"))->on("ck.id_local", "=", "locais.id")
                                   ->where("ck.data_registro", ">=", $dtInicio)
                                   ->where("ck.data_registro", "<=", $dtFim)
                                   ->where("ck.id_usuario", "=", $id_usuario)
                                   ->group_by("categorias.id")
                                   ->find_all();

            if($rs->count() <= 0){
                $ret->msg = "Nenhum gasto encontrado!";
            }else{
                $arrRet = array();

                foreach($rs as $row){
                    $arrRet[] = array(
                        "categoria"     => $row->categoria,
                        "total"         => $row->total,
                    );
                }

                $ret->status    = true;
                $ret->msg       = "Relatório listado com sucesso!";
                $ret->gastos    = $arrRet;
            }
        }else{
            $ret->msg = "Requisição post não encontrada";
        }
        
        echo json_encode($ret);
    }
}
