<?php
$hostname = "34.151.231.177";
$bancodedados = "eurobotic";
$usuario = "root";
$senha = "123";

$conexaoBD = new mysqli($hostname, $usuario, $senha, $bancodedados);

if ($conexaoBD->connect_error) {
    die("Conexão falhou: " . $conexaoBD->connect_error);
  } 
  
?>