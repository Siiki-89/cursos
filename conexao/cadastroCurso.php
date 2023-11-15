<?php
include "conexaoBD.php";

$cursoDescricao = isset($_POST['cursoDescricao']) ? $_POST['cursoDescricao'] : "";
$cursoCategoria = isset($_POST['cursoCategoria']) ? $_POST['cursoCategoria'] : "";
$cursoFornecedor = isset($_POST['cursoFornecedor']) ? $_POST['cursoFornecedor'] : "";
$cursoNome = isset($_POST['cursoNome']) ? $_POST['cursoNome'] : "";
$cursoUrl = isset($_POST['cursoUrl']) ? $_POST['cursoUrl'] : "";
$cursoPresencial = isset($_POST['cursoPresencial']) ? $_POST['cursoPresencial'] : "";
$cursoQtdHrs = isset($_POST['cursoQtdHrs']) ? $_POST['cursoQtdHrs'] : "";
$cursoNomeIMG = isset($_POST['cursoNomeIMG']) ? $_POST['cursoNomeIMG'] : "";
$cursoImg = isset($_POST['cursoImg']) ? $_POST['cursoImg'] : "";
$localImg = isset($_POST['URL']) ? $_POST['URL'] : "";

if (empty($cursoDescricao) || empty($cursoCategoria) || empty($cursoFornecedor) || empty($cursoNome) || empty($cursoUrl) || empty($cursoPresencial) || empty($cursoQtdHrs) || empty($cursoNomeIMG) || empty($cursoImg) || empty($localImg)) {
    echo json_encode([
        "Message" => "Parâmetros inválidos ou ausentes.",
        "Status" => "Error"
    ]);
    exit;
}

// Defina o diretório de destino das imagens
$target_dir = "/xampp/htdocs/conexao/imagens/curso"; // Certifique-se de que o diretório seja relativo ao diretório atual

// Construa o caminho completo da imagem
$imagePath = $target_dir . "/" . $cursoNomeIMG . ".jpeg";
$localImg = "http://" . $localImg . "/conexao/imagens/curso/" . $cursoNomeIMG . ".jpeg";
// Insira os dados no banco de dados
$sql = "INSERT INTO curso (curso_nome, curso_descricao, curso_categoria, curso_fornecedor, curso_url, curso_presencial, curso_qtd_hrs, curso_img, curso_visualizacao)
        VALUES ('$cursoNome', '$cursoDescricao', '$cursoCategoria', '$cursoFornecedor', '$cursoUrl', '$cursoPresencial', '$cursoQtdHrs', '$localImg', '0')";

if ($conexaoBD->query($sql)) {
    // Certifique-se de que o diretório de destino exista
    if (!file_exists($target_dir)) {
        mkdir($target_dir, 0777, true);
    }

    // Salve o arquivo da imagem
    if (file_put_contents($imagePath, base64_decode($cursoImg))) {
        echo json_encode([
            "Message" => "Registro inserido com sucesso. A imagem foi carregada.",
            "Status" => "OK"
        ]);
    } else {
        echo json_encode([
            "Message" => "Erro ao fazer upload da imagem.",
            "Status" => "Error"
        ]);
    }
} else {
    echo json_encode([
        "Message" => "Erro ao inserir registro: " . $conexaoBD->error,
        "Status" => "Error"
    ]);
}

$conexaoBD->close();
?>