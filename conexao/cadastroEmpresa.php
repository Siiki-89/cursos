<?php

    include "conexaoBD.php";
    require_once "validate.php";

    $empresaCNPJ = validate($_POST['empresaCNPJ']);
    $empresaNome = validate($_POST['empresaNome']);
    $empresaCEP = validate($_POST['empresaCEP']);
    $empresaUF = validate($_POST['empresaUF']);
    $empresaCidade = validate($_POST['empresaCidade']);
    $empresaEndereco = validate($_POST['empresaEndereco']);
    $empresaEmail = validate($_POST['empresaEmail']);
    $empresaTelefone = validate($_POST['empresaTelefone']);
    
    
    $sql = "INSERT INTO empresa (empresa_cnpj, empresa_nome, empresa_cep, empresa_UF, empresa_cidade, empresa_endereco, empresa_email, empresa_contato)
    VALUES ('$empresaCNPJ', '$empresaNome', '$empresaCEP', '$empresaUF', '$empresaCidade', '$empresaEndereco', '$empresaEmail', '$empresaTelefone')";

    if($conexaoBD->query($sql)){
        echo "sucesso";
    }else{
        echo "erro" . mysqli_error($conexaoBD);
    }
    $conexaoBD->close();

?>