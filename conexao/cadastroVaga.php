<?php
include "conexaoBD.php";
require_once "validate.php";

$usuarioADMID = "1";
$vagaEmpresaNome = validate($_POST['vagaEmpresaNome']);
$vagaDescricao = validate($_POST['vagaDescricao']);
$vagaCargo = validate($_POST['vagaCargo']);
$vagaDataVaga = validate($_POST['vagaDataVaga']);
$vagaDataPrazo = validate($_POST['vagaDataPrazo']);
$vagaCategoria = validate($_POST['vagaCategoria']);
$vagaQuantidade = validate($_POST['vagaQuantidade']);

$empresaID = buscarEmpresaID($conexaoBD, $vagaEmpresaNome);

if ($empresaID !== null) {
    $sql = "INSERT INTO vaga (usuario_administrador_idusuario_administrador, empresa_idempresa, vaga_empresa_nome, vaga_descricao, vaga_cargo, vaga_data_vaga, vaga_data_prazo, vaga_categoria, vaga_quantidade)
            VALUES ('$usuarioADMID', '$empresaID', '$vagaEmpresaNome', '$vagaDescricao', '$vagaCargo', '$vagaDataVaga', '$vagaDataPrazo', '$vagaCategoria', '$vagaQuantidade')";

    if ($conexaoBD->query($sql)) {
        echo "Registro inserido com sucesso!";
    } else {
        echo "Erro ao inserir registro: " . mysqli_error($conexaoBD);
    }
} else {
    echo "Empresa não registrada";
}

$conexaoBD->close();

function buscarEmpresaID($conexao, $nomeEmpresa) {
    $nomeEmpresa = mysqli_real_escape_string($conexao, $nomeEmpresa);
    $query = "SELECT empresa_id FROM empresa WHERE empresa_nome = '$nomeEmpresa'";
    $result = mysqli_query($conexao, $query);

    if ($result && $row = mysqli_fetch_assoc($result)) {
        return $row['empresa_id'];
    }

    return null;
}
?>