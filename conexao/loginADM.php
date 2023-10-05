
<?php
    include 'conexaoBD.php';
    include 'validate.php';

    $usuarioNome=validate($_POST['usuarioNome']);
    $usuarioSenha=validate($_POST['usuarioSenha']);

    $sql= "SELECT * FROM usuario_administrador WHERE usuario_administrador_nome='$usuarioNome' AND usuario_administrador_senha='$usuarioSenha'";

    $resultado = $conexaoBD->query($sql);

    if($resultado->num_rows > 0){
        echo "success";
    } else {
        echo "failure";
    };

    $conexaoBD->close();

?>