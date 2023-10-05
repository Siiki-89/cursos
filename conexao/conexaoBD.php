<?php
$hostname = "localhost";
$bancodedados = "cursoetrabalho";
$usuario = "root";
$senha = "";

$conexaoBD = new mysqli($hostname, $usuario, $senha, $bancodedados);

if ($conexaoBD->connect_error) {
    die("Conexão falhou: " . $conexaoBD->connect_error);
  } 
  
?>