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
                "id_usuario"    => $this->request->post("id_usuario"),
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
            $rs         = $mdUsuarios->carregarTimeLine((int)$this->request->post("id_usuario"));
            
            if($rs->count() <= 0){
                $ret->msg = "Nenhum checkin encontrado";
            }else{
                $arrRet = array();
                
                foreach ($rs as $row){
                    $arrRet[] = array(
                        "id_local"      => $row['id_local'],
                        "local"         => $row['local'],
                        "num_checkins"  => $row['num_checkins'],
                        "latitude"      => $row['latitude'],
                        "longitude"     => $row['longitude'],
                        "valor_gasto"   => $row['valor_gasto'],
                        "data_registro" => $row['data_registro'],
                        "categoria"     => array(
                            "id"    => $row['id_categoria'],
                            "nome"  => $row['categoria'],
                        ),
                        "usuario"       => array(
                            "id"    => $row['id_usuario'],
                            "nome"  => $row['nome_usuario'],
                        )
                    );
                }
                
                $ret->status    = true;
                $ret->msg       = "Timeline carregada com sucesso!";
                $ret->locais    = $arrRet;
            }
        }else{
            $ret->msg = "Requisição post não encontrada";
        }
        
        echo json_encode($ret);
    }
    
    public function action_buscarUsuario(){
        $this->_auto_render = false;
        
        $ret            = new stdClass();
        $ret->status    = false;
        
        if($this->request->method() == 'POST'){
            $palavra_chave = trim($this->request->post("palavra_chave"));
            
            if(strlen($palavra_chave) <= 0){
                $ret->msg = "Digite um termo para buscar usuários.";
            }else{
                $mdUsuarios = new Model_Usuarios();
                $rsUsuarios = $mdUsuarios->where("nome", "LIKE", DB::expr("'%{$palavra_chave}%'"))
                                         ->or_where("email", "LIKE", DB::expr("'%{$palavra_chave}%'"))
                                         ->order_by("nome")
                                         ->limit(10)
                                         ->find_all();
                
                if($rsUsuarios->count() > 0){
                    $arrRet = array();
                    
                    foreach($rsUsuarios as $row){
                        $arrRet[] = array(
                            "id_usuario"    => $row->id,
                            "nome"          => $row->nome,
                            "email"         => $row->email
                        );
                        
                        $ret->status    = true;
                        $ret->msg       = "Usuários encontrados";
                        $ret->usuarios  = $arrRet;
                    }
                }else{
                    $ret->msg = "Nenhum usuário encontrado!";
                }
            }
        }else{
            $ret->msg = "Requisição post não encontrada";
        }
        
        echo json_encode($ret);
    }
    
    public function action_solicitarAmizade(){
        $this->_auto_render = false;
        
        $ret            = new stdClass();
        $ret->status    = false;
        
        if($this->request->method() == 'POST'){
            $id_usuario = (int)$this->request->post("id_usuario");
            $id_amigo   = (int)$this->request->post("id_amigo");
            
            
            if($id_usuario <= 0 || $id_amigo <= 0){
                $ret->msg = "Dados da solicitação inválidos.";
            }else{
                $mdSolicitacao  = new Model_Solicitacao();
                $ret            = $mdSolicitacao->cadastrarSolicitacao($id_usuario, $id_amigo);
            }
        }else{
            $ret->msg = "Requisição post não encontrada";
        }
        
        echo json_encode($ret);
    }
    
    public function action_listarSolicitacoesAmizade(){
        $this->_auto_render = false;
        
        $ret            = new stdClass();
        $ret->status    = false;
        
        if($this->request->method() == 'POST'){
            $id_usuario = (int)$this->request->post("id_usuario");
            
            if($id_usuario <= 0){
                $ret->msg = "Dados da solicitação inválidos.";
            }else{
                $mdSolicitacao  = new Model_Solicitacao();
                $rsSolicitacao  = $mdSolicitacao->select(array("u.nome", "nome_usuario"), array("u.email", "email_usuario"), array("solicitacao.id", "id_solicitacao"))
                                                ->join(array("usuario", "u"))->on("u.id", "=", "id_usuario")
                                                ->where("id_amigo", "=", $id_usuario)
                                                ->where_open()
                                                    ->where("status", "=", "")
                                                    ->or_where("status", "IS", DB::expr("NULL"))
                                                ->where_close()
                                                ->order_by("data_solicitacao")
                                                ->find_all();
                
                if($rsSolicitacao->count() <= 0){
                    $ret->msg = "Nenhuma solicitação encontrada!";
                }else{
                    $arrRet = array();
                    
                    foreach($rsSolicitacao as $row){
                        $arrRet[] = array(
                            "id"                => $row->id_solicitacao,
                            "data_solicitacao"  => $row->data_solicitacao,
                            "usuario"           => array(
                                "id"    => $row->id_usuario, 
                                "nome"  => $row->nome_usuario,
                                "email" => $row->email_usuario,
                            )
                        );
                        
                        $ret->status        = true;
                        $ret->msg           = "Solicitaçõeas encontradas";
                        $ret->solicitacoes  = $arrRet;
                    }
                }
            }
        }else{
            $ret->msg = "Requisição post não encontrada";
        }
        
        echo json_encode($ret);
    }
    
    public function action_alterarSolicitacao(){
        $this->_auto_render = false;
        
        $ret            = new stdClass();
        $ret->status    = false;
        
        if($this->request->method() == 'POST'){
            $id_solicitacao = (int)$this->request->post("id_solicitacao");
            $status         = $this->request->post("status");
            
            if($id_solicitacao <= 0){
                $ret->msg = "Dados da solicitação inválidos.";
            }else{
                $mdSolicitacao  = new Model_Solicitacao();
                $mdSolicitacao->where("id", "=", $id_solicitacao)->find();
                
                if($mdSolicitacao->loaded()){
                    $mdSolicitacao->status = $status;
                    $mdSolicitacao->update();
                    
                    if($status == 'aceito'){
                        $mdUsuarioAmigo = new Model_Amigos();
                        $ret            = $mdUsuarioAmigo->cadastrarAmizade($mdSolicitacao->id_usuario, $mdSolicitacao->id_amigo);
                    }
                    
                    $ret->status    = true;
                    $ret->msg       = "Solicitação alterada com sucesso!";
                }else{
                    $ret->msg = "Solicitação não encontrada!";
                }
            }
        }else{
            $ret->msg = "Requisição post não encontrada";
        }
        
        echo json_encode($ret);
    }
    
    
    public function action_listarAmigos(){
        $this->_auto_render = false;
        
        $ret            = new stdClass();
        $ret->status    = false;
        
        if($this->request->method() == 'POST'){
            $id_usuario = (int)$this->request->post("id_usuario");
            
            if($id_usuario <= 0){
                $ret->msg = "Dados da solicitação inválidos.";
            }else{
                $mdAmigos  = new Model_Amigos();
                $rsAmigos  = $mdAmigos->where("id_usuario", "=", $id_usuario)
                                      ->or_where("id_amigo", "=", $id_usuario)
                                      ->find_all();
                
                if($rsAmigos->count() <= 0){
                    $ret->msg = "Nenhum amigo encontrado!";
                }else{
                    $arrRet = array();
                    
                    foreach($rsAmigos as $row){
                        $id_usuario = 0;
                        
                        if($row->id_usuario != $id_usuario){
                            $id_usuario = $row->id_usuario;
                        }else if($row->id_amigo != $id_usuario){
                            $id_usuario = $row->id_amigo;
                        }
                        
                        $mdUsuario = new Model_Usuarios();
                        $mdUsuario->where("id", "=", $id_usuario)->find();
                        
                        if($mdUsuario->loaded()){
                            $arrRet[] = array(
                                "id"    => $mdUsuario->id,
                                "nome"  => $mdUsuario->nome,
                                "email" => $mdUsuario->email,
                            );
                        }
                        
                        $ret->status        = true;
                        $ret->msg           = "Amigos encontrados";
                        $ret->usuarios      = $arrRet;
                    }
                }
            }
        }else{
            $ret->msg = "Requisição post não encontrada";
        }
        
        echo json_encode($ret);
    }
}
