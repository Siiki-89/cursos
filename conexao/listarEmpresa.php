<?php

    include "conexaoBD.php";
    require_once "validate.php";

    $sql = "SELECT * FROM empresa";

    $resultado = mysqli_query($conexaoBD, $sql);

    if (mysqli_num_rows($resultado) > 0) {
        $empresas = array();

        while ($linha = mysqli_fetch_assoc($resultado)) {
            $empresas[] = $linha;
        }

        echo json_encode($empresas);
    } else {
        echo "Nenhum dado encontrado na tabela empresa.";
    }

    mysqli_close($conexaoBD);
?>