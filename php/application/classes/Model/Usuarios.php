<?php defined('SYSPATH') or die('No direct script access.');

class Model_Usuarios extends ORM {
    protected $_table_name="usuario";

    public function efetuarLogin($email, $senha){
        $this->where("email", "=", $email)
             ->where("senha", "=", $senha)
             ->find();
        
        if($this->loaded()){
            return $this;
        }else{
            return FALSE;
        }
    }
    
    public function salvarUsuario($nome, $email, $senha){
        $ret            = new stdClass();
        $ret->status    = false;
        
        $this->where("email", "=", $email)
             ->find();
        
        if($this->loaded()){
            $ret->msg = "Esse e-mail já possui cadastro";
        }else{
            $this->nome             = $nome;
            $this->email            = $email;
            $this->senha            = $senha;
            $this->data_registro    = date("Y-m-d H:i:s");
            
            $this->save();
            
            $ret->status    = true;
            $ret->msg       = "Usuário cadastrado com Sucesso!";
        }
        
        return $ret;
    }
    
    public function efetuarCheckin($arrDados){
        $ret            = new stdClass();
        $ret->status    = false;
        
        $tbCheckin              = ORM::factory("checkin");
        $tbCheckin->id_usuario  = $arrDados['id_usuario'];
        $tbCheckin->id_usuario = $arrDados['id_usuario'];
        $tbCheckin->id_usuario = $arrDados['id_usuario'];
        $tbCheckin->id_usuario = $arrDados['id_usuario'];
        $tbCheckin->id_usuario = $arrDados['id_usuario'];
        
        
        $this->where("email", "=", $email)
             ->find();
        
        if($this->loaded()){
            $ret->msg = "Esse e-mail já possui cadastro";
        }else{
            $this->nome             = $nome;
            $this->email            = $email;
            $this->senha            = $senha;
            $this->data_registro    = date("Y-m-d H:i:s");
            
            $this->save();
            
            $ret->status    = true;
            $ret->msg       = "Usuário cadastrado com Sucesso!";
        }
        
        return $ret;
    }
}

?>