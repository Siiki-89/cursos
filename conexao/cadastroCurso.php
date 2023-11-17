<?php
include "conexaoBD.php";

$cursoDescricao = isset($_POST['cursoDescricao']) ? $_POST['cursoDescricao'] : "";
$cursoCategoria = isset($_POST['cursoCategoria']) ? $_POST['cursoCategoria'] : "";
$cursoFornecedor = isset($_POST['cursoFornecedor']) ? $_POST['cursoFornecedor'] : "";
$cursoNome = isset($_POST['cursoNome']) ? $_POST['cursoNome'] : "";
$cursoUrl = isset($_POST['cursoUrl']) ? $_POST['cursoUrl'] : "";
$cursoPresencial = isset($_POST['cursoPresencial']) ? $_POST['cursoPresencial'] : "";
$cursoQtdHrs = isset($_POST['cursoQtdHrs']) ? $_POST['cursoQtdHrs'] : "";
$cursoImg = isset($_POST['cursoImg']) ? $_POST['cursoImg'] : "";

if (empty($cursoDescricao) || empty($cursoCategoria) || empty($cursoFornecedor) || empty($cursoNome) || empty($cursoUrl) || empty($cursoPresencial) || empty($cursoQtdHrs) || empty($cursoImg)) {
    echo json_encode([
        "Message" => "Parâmetros inválidos ou ausentes.",
        "Status" => "Error"
    ]);
    exit;
}


$sql = "INSERT INTO curso (curso_nome, curso_descricao, curso_categoria, curso_fornecedor, curso_url, curso_presencial, curso_qtd_hrs, curso_imagem, curso_visualizacao)
        VALUES ('$cursoNome', '$cursoDescricao', '$cursoCategoria', '$cursoFornecedor', '$cursoUrl', '$cursoPresencial', '$cursoQtdHrs', '$cursoImg', '0')";

if ($conexaoBD->query($sql)) {
    echo json_encode([
        "Message" => "Sucesso ao inserir registro: " . $conexaoBD->error,
        "Status" => "Sucesso"
    ]);


} else {
    echo json_encode([
        "Message" => "Erro ao inserir registro: " . $conexaoBD->error,
        "Status" => "Error"
    ]);
}

$conexaoBD->close();
?>