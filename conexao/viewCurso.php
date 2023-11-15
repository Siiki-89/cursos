<?php

    include "conexaoBD.php";
    require_once "validate.php";

    $cursoNome = validate($_POST['cursoNome']);
    
    $sql = "UPDATE curso
    SET curso_visualizacao = curso_visualizacao + 1
    WHERE curso_nome = '$cursoNome';";

    if($conexaoBD->query($sql)){
        echo "sucesso";
    }else{
        echo "erro" . mysqli_error($conexaoBD);
    }
    $conexaoBD->close();

?>