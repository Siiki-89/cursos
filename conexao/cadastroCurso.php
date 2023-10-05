<?php

    include "conexaoBD.php";
    require_once "validate.php";

    $cursoDescricao = validate($_POST['cursoDescricao']);
    $cursoCategoria = validate($_POST['cursoCategoria']);
    $cursoFornecedor = validate($_POST['cursoFornecedor']);
    $cursoNome = validate($_POST['cursoNome']);
    $cursoUrl = validate($_POST['cursoUrl']);
    $cursoPresencial = validate($_POST['cursoPresencial']);
    $cursoQtdHrs = validate($_POST['cursoQtdHrs']);

    
    $sql = "INSERT INTO curso (curso_nome ,curso_descricao, curso_categoria, curso_fornecedor, curso_url, curso_presencial, curso_qtd_hrs)
    VALUES ('$cursoNome','$cursoDescricao','$cursoCategoria', '$cursoFornecedor', '$cursoUrl', '$cursoPresencial', '$cursoQtdHrs')";

    if($conexaoBD->query($sql)){
        echo "Registro inserido com sucesso!";
    }else{
        echo "Erro ao inserir registro: " . mysqli_error($conexaoBD);
    }
    $conexaoBD->close();

?>