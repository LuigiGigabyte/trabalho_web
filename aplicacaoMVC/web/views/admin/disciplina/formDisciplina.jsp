<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entidade.Disciplina"%>
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
                <% Disciplina disciplina = new Disciplina();
                    String acao = (String) request.getAttribute("acao"); 
                    switch(acao) { 
                        case "Incluir": 
                            out.println("<h1>Incluir Disciplina</h1>"); 
                            break; 
                        case "Alterar":
                            disciplina = (Disciplina) request.getAttribute("disciplina");
                            out.println("<h1>Alterar Disciplina</h1>");
                            break; 
                    }; 
                    String msgError = (String) request.getAttribute("msgError"); 
                    if ((msgError != null) && (!msgError.isEmpty())) {
                    %>
                    <div class="alert alert-danger" role="alert">
                    <%=msgError
                    %>
                    </div>
                    <% }%>

                <form action="/aplicacaoMVC/admin/DisciplinaController" method="POST">
                    <input
                            type="hidden"
                            name="id"
                            value="<%=disciplina.getId()%>"
                            class="form-control"
                    />
                    <div class="mb-3">
                        <label for="nome" class="form-label">Nome</label>
                        <input value="<%=disciplina.getNome()%>" type="text" name="nome" class="form-control" placeholder="Nome da disciplina" required>
                    </div>
                    <div class="mb-3">
                        <label for="requisito" class="form-label">Requisito</label>
                        <input value="<%=disciplina.getRequisito()%>" type="text" name="requisito" class="form-control" placeholder="Requisito da disciplina" required>
                    </div>
                    <div class="mb-3">
                        <label for="ementa" class="form-label">Ementa</label>
                        <textarea value="<%=disciplina.getEmenta()%>" name="ementa" class="form-control" placeholder="Ementa da disciplina" required><%=disciplina.getEmenta()%></textarea>
                    </div>
                    <div class="mb-3">
                        <label for="cargaHoraria" class="form-label">Carga Horária</label>
                        <input value="<%=disciplina.getCargaHoraria()%>" type="number" name="cargaHoraria" class="form-control" placeholder="Carga horária da disciplina" required>
                    </div>
                    <div class="row">
                        <div class="col-sm-2">
                            <input value="<%=acao%>" type="submit" name="btEnviar" class="btn btn-primary">
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
