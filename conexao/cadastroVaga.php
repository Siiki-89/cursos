<?php
include "conexaoBD.php";
require_once "validate.php";

function buscarIdEmpresa($conexaoBD, $empresaNome) {
    $empresaNome = validate($empresaNome);

    $sql = "SELECT empresa_id FROM empresa WHERE empresa_nome = '$empresaNome'";
    $result = $conexaoBD->query($sql);

    if ($result->num_rows > 0) {
        $row = $result->fetch_assoc();
        return $row['empresa_id'];
    } else {
        return false;
    }
}

$vagaEmpresaNome = validate($_POST['vagaEmpresaNome']);
$vagaDescricao = validate($_POST['vagaDescricao']);
$vagaCargo = validate($_POST['vagaCargo']);
$vagaDataVaga = validate($_POST['vagaDataVaga']);
$vagaDataPrazo = validate($_POST['vagaDataPrazo']);
$vagaCategoria = validate($_POST['vagaCategoria']);
$vagaQuantidade = validate($_POST['vagaQuantidade']);
$vagaImg = validate($_POST['vagaImg']);
$vagaUrl = validate($_POST['vagaUrl']);

$empresa_id = buscarIdEmpresa($conexaoBD, $vagaEmpresaNome);

if ($empresa_id !== false) {
    $sql = "INSERT INTO vaga (empresa_idempresa, vaga_descricao, vaga_cargo, vaga_data_vaga, vaga_data_prazo, vaga_categoria, vaga_quantidade, vaga_img, vaga_url)
            VALUES ('$empresa_id', '$vagaDescricao', '$vagaCargo', '$vagaDataVaga', '$vagaDataPrazo', '$vagaCategoria', '$vagaQuantidade', '$vagaImg', '$vagaUrl')";

    if ($conexaoBD->query($sql)) {
        echo json_encode([
            "Message" => "Sucesso ao inserir registro",
            "Status" => "Sucesso"
        ]);
    } else {
        echo json_encode([
            "Message" => "Erro ao inserir registro: " . $conexaoBD->error,
            "Status" => "Error"
        ]);
    }
} else {
    echo json_encode([
        "Message" => "Empresa não encontrada com o nome especificado.",
        "Status" => "Error"
    ]);
}

$conexaoBD->close();
?>
