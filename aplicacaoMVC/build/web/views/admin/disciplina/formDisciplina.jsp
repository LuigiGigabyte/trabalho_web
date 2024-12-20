<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Cadastro de Disciplina</title>
        <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css"  rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <jsp:include page="../../comum/menu.jsp" />
            <div class="col-sm-6 offset-3 mt-5">
                <%
                    String msgError = (String) request.getAttribute("msgError");
                    if ((msgError != null) && (!msgError.isEmpty())) {%>
                <div class="alert alert-danger" role="alert">
                    <%= msgError%>
                </div>
                <% }%>

                <h3>Cadastro de Disciplina</h3>

                <form action="/aplicacaoMVC/admin/DisciplinaController?acao=Incluir" method="POST">
                    <div class="mb-3">
                        <label for="nome" class="form-label">Nome</label>
                        <input type="text" name="nome" class="form-control" placeholder="Nome da disciplina" required>
                    </div>
                    <div class="mb-3">
                        <label for="requisito" class="form-label">Requisito</label>
                        <input type="text" name="requisito" class="form-control" placeholder="Requisito da disciplina" required>
                    </div>
                    <div class="mb-3">
                        <label for="ementa" class="form-label">Ementa</label>
                        <textarea name="ementa" class="form-control" placeholder="Ementa da disciplina" required></textarea>
                    </div>
                    <div class="mb-3">
                        <label for="cargaHoraria" class="form-label">Carga Horária</label>
                        <input type="number" name="cargaHoraria" class="form-control" placeholder="Carga horária da disciplina" required>
                    </div>
                    <div class="row">
                        <div class="col-sm-2">
                            <input type="submit" value="Cadastrar" class="btn btn-primary">
                        </div>
                        <div class="col-sm-2">
                            <a href="/aplicacaoMVC/admin/DisciplinaController?acao=Listar" class="btn btn-danger">Retornar</a>
                        </div>
                    </div>
                </form>

            </div>
        </div>
        <script src="views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>
