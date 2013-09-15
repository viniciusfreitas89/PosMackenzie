<?php defined('SYSPATH') or die('No direct script access.');

class Controller_Usuarios extends Controller {
    public function action_index(){
        die("Web Service PHP - Trabalho MyPlaces Mackenzie");
    }
    
    public function action_efetuarLogin(){
        $this->_auto_render = false;
        
        $ret            = new stdClass();
        $ret->status    = false;
        
        if($this->request->method() == 'POST'){
            $email  = trim($this->request->post("email"));
            $senha  = trim($this->request->post("senha"));
            
            $mdUsuarios = new Model_Usuarios();
            $rs         = $mdUsuarios->efetuarLogin($email, $senha);
            
            if($rs === FALSE){
                $ret->msg = "Usuário não encontrado!";
            }else{
                $usuario                = new stdClass();
                $usuario->id            = $mdUsuarios->id;
                $usuario->nome          = $mdUsuarios->nome;
                $usuario->email         = $mdUsuarios->email;
                $usuario->data_registro = $mdUsuarios->data_registro;
                
                $ret->status    = true;
                $ret->msg       = "Usuário encontrado!";
                $ret->usuario   = $usuario;
            }
        }else{
            $ret->msg = "Requisição post não encontrada";
        }
        
        echo json_encode($ret);
    }
    
    public function action_cadastrar(){
        $this->_auto_render = false;
        
        $ret            = new stdClass();
        $ret->status    = false;
        
        if($this->request->method() == 'POST'){
            $nome       = trim($this->request->post("nome"));
            $email      = trim($this->request->post("email"));
            $senha      = trim($this->request->post("senha"));
            $c_senha    = trim($this->request->post("c_senha"));
            
            if($senha != $c_senha){
                $ret->msg = "O campo senha e conformar senha devem ser idênticos!";
            }else{
                $mdUsuarios = new Model_Usuarios();
                $ret        = $mdUsuarios->salvarUsuario($nome, $email, $senha);
            }
        }else{
            $ret->msg = "Requisição post não encontrada";
        }
        
        echo json_encode($ret);
    }
    
    public function action_efetuarCheckin(){
        $this->_auto_render = false;
        
        $ret            = new stdClass();
        $ret->status    = false;
        
        if($this->request->method() == 'POST'){
            $mdCheckin = new Model_Checkin();
            $ret       = $mdCheckin->efetuarCheckin(array(
                "id_usuario"    => $this->request->post("id"),
                "id_local"      => $this->request->post("id_local"),
                "valor_gasto"   => $this->request->post("valor_gasto"),
            ));
        }else{
            $ret->msg = "Requisição post não encontrada";
        }
        
        echo json_encode($ret);
    }
    
    public function action_carregarTimeLine(){
        $this->_auto_render = false;
        
        $ret            = new stdClass();
        $ret->status    = false;
        
        if($this->request->method() == 'POST'){
            $mdUsuarios = new Model_Usuarios();
            $rs         = $mdUsuarios->carregarTimeLine($this->request->post("id"));
            
            if($rs->count() <= 0){
                $ret->msg = "Nenhum checkin encontrado";
            }else{
                $arrRet = array();
                
                foreach ($rs as $row){
                    $arrRet[] = array(
                        "local"         => $row->local,
                        "valor_gasto"   => $row->valor_gasto,
                        "data_registro" => $row->data_registro
                    );
                }
            }
        }else{
            $ret->msg = "Requisição post não encontrada";
        }
        
        echo json_encode($ret);
    }
}
