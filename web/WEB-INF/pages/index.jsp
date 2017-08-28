<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta name="description" content="Avaliador de senha">
    <meta name="author" content="Rafael da Costa Farias">
    <link rel="icon" href="https://getbootstrap.com/docs/3.3/favicon.ico">
    <title>Avaliador de Senha</title>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
</head>

<body>

<div class="container">
    <div class="panel panel-default">
        <div class="panel-body">
            <p class="text-muted"><strong> Avaliador de seguranca de senha </strong></p>
            <div class="well well-xs">
                <form>
                    <div class="row">
                        <div class="col-xs-4">
                            <input type="password" class="form-control" id="senha" placeholder="Senha"
                                   onkeyup="function avaliarSenha(this) {
                            $.ajax({
                                        url : ' http://localhost:8080/avaliadorSenha',
                                        data : { senha: this.value },
                                        dataType : 'json',
                                        type : 'Post',
                                        success : function(data) {
                                            alert(data);
                                        },
                                        error : function() {
                                            alert('Erro ao carregar');
                                        }
                            });
                            }">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-12">
                            <span class="label label-default" id="pontuacao">0%</span>
                            <span class="label label-danger" id="complexidade">Muito Fraco</span>
                            <div class="col-xs-12">
                            </div>
                        </div>
                </form>
            </div>

        </div>
    </div>
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
</body>
</html>