<?php

include "conexaoBD.php";

function buscarTrabalhos($conexaoBD, $sql) {
    $resultado = $conexaoBD->query($sql);
    if ($resultado === false) {
        die("Erro na consulta: " . $conexaoBD->error);
    }

    $trabalhos = array();

    while ($linha = mysqli_fetch_assoc($resultado)) {
        $trabalhos[] = $linha;
    }

    return $trabalhos;
}

$alternativaTrabalho = mysqli_real_escape_string($conexaoBD, $_POST['alternativaTrabalho']);

if ($alternativaTrabalho == "1") {
    $sql = "SELECT empresa.empresa_nome, empresa.empresa_cidade, empresa.empresa_endereco, empresa.empresa_UF, vaga.vaga_cargo, vaga.vaga_categoria, vaga.vaga_img, vaga.vaga_url FROM empresa INNER JOIN vaga ON empresa.empresa_nome = vaga.empresa_nome";
} elseif ($alternativaTrabalho == "2") {
    $categoriaTrabalho = mysqli_real_escape_string($conexaoBD, $_POST['categoriaTrabalho']);
    $sql = "SELECT empresa.empresa_nome, empresa.empresa_cidade, empresa.empresa_endereco, empresa.empresa_UF, vaga.vaga_cargo, vaga.vaga_categoria, vaga.vaga_img, vaga.vaga_url FROM empresa INNER JOIN vaga ON empresa.empresa_nome = vaga.empresa_nome WHERE vaga.vaga_categoria = '$categoriaTrabalho'";
} elseif ($alternativaTrabalho == "3") {
    $vagaTrabalho = mysqli_real_escape_string($conexaoBD, $_POST['vagaTrabalho']);
    $sql = "SELECT empresa.empresa_nome, empresa.empresa_cidade, empresa.empresa_UF, vaga.vaga_data_vaga, vaga_data_prazo, vaga.vaga_cargo, vaga.vaga_descricao, vaga.vaga_quantidade, vaga.vaga_img, vaga.vaga_url FROM empresa INNER JOIN vaga ON empresa.empresa_nome = vaga.empresa_nome WHERE vaga.vaga_cargo = '$vagaTrabalho'";
} else {
    die("Alternativa de trabalho inválida.");
}

$trabalhos = buscarTrabalhos($conexaoBD, $sql);

if (empty($trabalhos)) {
    echo "Nenhum dado encontrado na tabela empresa.";
} else {
    echo json_encode($trabalhos);
}

$conexaoBD->close();
?>