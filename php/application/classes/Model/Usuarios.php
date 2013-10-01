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
    
    public function carregarTimeLine($id_usuario){
        $sql = "SELECT * FROM
                (
                    (
                        SELECT 
                            l.nome as 'local',
                            l.id as 'id_local',
                            l.num_checkins,
                            l.latitude,
                            l.longitude,
                            l.id_categoria,
                            ck.data_registro,
                            ck.valor_gasto,
                            c.nome as 'categoria',
                            u.id as 'id_usuario',
                            u.nome as 'nome_usuario'
                        FROM
                            checkin ck
                        INNER JOIN
                            usuario u ON u.id = ck.id_usuario
                        INNER JOIN
                            locais l ON l.id = ck.id_local
                        INNER JOIN
                            categorias c ON c.id = l.id_categoria
                        WHERE
                            ck.id_usuario = {$id_usuario}
                    )
                    UNION
                    (
                        SELECT 
                            l.nome as 'local',
                            l.id as 'id_local',
                            l.num_checkins,
                            l.latitude,
                            l.longitude,
                            l.id_categoria,
                            ck.data_registro,
                            ck.valor_gasto,
                            c.nome as 'categoria',
                            u.id as 'id_usuario',
                            u.nome as 'nome_usuario'
                        FROM
                            checkin ck
                        INNER JOIN
                            usuario u ON u.id = ck.id_usuario
                        INNER JOIN
                            locais l ON l.id = ck.id_local
                        INNER JOIN
                            categorias c ON c.id = l.id_categoria
                        WHERE
                            ck.id_usuario IN(SELECT id_amigo FROM usuario_amigos WHERE id_usuario = {$id_usuario} OR id_amigo = {$id_usuario})
                        OR
                            ck.id_usuario IN(SELECT id_usuario FROM usuario_amigos WHERE id_usuario = {$id_usuario} OR id_amigo = {$id_usuario})
                    )
                ) U
                ORDER BY U.data_registro DESC
                ;";
        
        return DB::query(Database::SELECT, $sql)->execute();
    }
}

?>