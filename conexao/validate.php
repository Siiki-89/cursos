<?php
  function validate($data) {
    // Retire caracteres desnecessários, por exemplo, espaço extra, tabulação, nova linha da entrada do usuário
    $data = trim($data);
    // Remover barras invertidas  
    $data = stripslashes($data);
    // Converta caracteres especiais em entidades HTML, evitando assim que invasores explorem o código
    $data = htmlspecialchars($data);
    // Retornar dados validados
    return $data;
  }
?>