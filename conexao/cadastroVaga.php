<?php
include "conexaoBD.php"; // Inclua o arquivo de conexão com o banco de dados
require_once "validate.php";

// Função para obter o empresa_id com base no nome da empresa
function buscarIpEmpresa($conexaoBD, $empresaNome) {
    $empresaNome = validate($empresaNome);
    
    // Consulta para buscar o empresa_id com base no nome
    $sql = "SELECT empresa_id FROM empresa WHERE empresa_nome = '$empresaNome'";
    $result = $conexaoBD->query($sql);

    if ($result->num_rows > 0) {
        $row = $result->fetch_assoc();
        return $row['empresa_id'];
    } else {
        return false; // Retorna false se a empresa não for encontrada
    }
}

// Valide os dados recebidos
$vagaEmpresaNome = validate($_POST['vagaEmpresaNome']);
$vagaDescricao = validate($_POST['vagaDescricao']);
$vagaCargo = validate($_POST['vagaCargo']);
$vagaDataVaga = validate($_POST['vagaDataVaga']);
$vagaDataPrazo = validate($_POST['vagaDataPrazo']);
$vagaCategoria = validate($_POST['vagaCategoria']);
$vagaQuantidade = validate($_POST['vagaQuantidade']);
$vagaNomeIMG = validate($_POST['vagaNomeIMG']);
$vagaImg = validate($_POST['vagaImg']);
$vagaUrl = validate($_POST['vagaUrl']);
$localImg = validate($_POST['IP']);

// Obtenha o empresa_id usando a função
$empresa_idempresa = buscarIpEmpresa($conexaoBD, $vagaEmpresaNome);

$target_dir = "/xampp/htdocs/conexao/imagens/vaga"; // Certifique-se de que o diretório seja relativo ao diretório atual

// Construa o caminho completo da imagem
$imagePath = $target_dir . "/" . $vagaNomeIMG . ".jpeg";
$localImg = "http://" . $localImg . "/conexao/imagens/vaga/" . $vagaNomeIMG . ".jpeg";

if ($empresa_idempresa !== false) {
    // Insira os dados na tabela vaga
    $sql = "INSERT INTO vaga (empresa_idempresa, vaga_descricao, vaga_cargo, vaga_data_vaga, vaga_data_prazo, vaga_categoria, vaga_quantidade, vaga_img, vaga_url)
            VALUES ('$empresa_idempresa', '$vagaDescricao', '$vagaCargo', '$vagaDataVaga', '$vagaDataPrazo', '$vagaCategoria', '$vagaQuantidade', '$localImg', '$vagaUrl')";

    if ($conexaoBD->query($sql)) {
        if (!file_exists($target_dir)) {
            mkdir($target_dir, 0777, true);
        }
    
        // Salve o arquivo da imagem
        if (file_put_contents($imagePath, base64_decode($vagaImg))) {
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
} else {
    echo json_encode([
        "Message" => "Empresa não encontrada com o nome especificado.",
        "Status" => "Error"
    ]);
}

// Feche a conexão com o banco de dados
$conexaoBD->close();
?>