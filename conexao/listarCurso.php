<?php
include "conexaoBD.php";
require_once "validate.php";


function listarCurso($conexaoBD, $sql) {
    $resultado = $conexaoBD->query($sql);
    if ($resultado === false) {
        die("Erro na consulta: " . $conexaoBD->error);
    }

    $cursos = array();

    while ($linha = mysqli_fetch_assoc($resultado)) {
        $cursos[] = $linha;
    }
    return $cursos;
}

$alternativaCurso = mysqli_real_escape_string($conexaoBD, $_POST['alternativaCurso']);

if ($alternativaCurso == "1") {
    $sql = "SELECT * FROM curso";
} elseif ($alternativaCurso == "2") {
    $categoriaCurso = mysqli_real_escape_string($conexaoBD, $_POST['categoriaCurso']);
    $sql = "SELECT * FROM curso WHERE curso_categoria = '$categoriaCurso'";
} elseif ($alternativaCurso == "3") {
    $nomeCurso = mysqli_real_escape_string($conexaoBD, $_POST['nomeCurso']);
    $sql = "SELECT * FROM curso WHERE curso_Nome = '$nomeCurso'";
} else {
    echo "Alternativa de curso inválida.";
}

$cursos = listarCurso($conexaoBD, $sql);

if (empty($cursos)) {
    echo "Nenhum dado encontrado na tabela empresa.";
} else {
    echo json_encode($cursos);
}

$conexaoBD->close();
?>