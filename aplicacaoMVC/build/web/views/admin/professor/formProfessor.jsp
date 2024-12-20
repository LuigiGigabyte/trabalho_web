<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entidade.Professor" %>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Cadastro de Professor</title>
        <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css"  rel="stylesheet">
    </head>
    <body>
        <div class="col-sm-6 offset-3 mt-5">
                <% Professor professor = new Professor();
                    String acao = (String) request.getAttribute("acao"); 
                    switch(acao) { 
                        case "Incluir": 
                            out.println("<h1>Incluir professor</h1>"); 
                            break; 
                        case "Alterar":
                            professor = (Professor) request.getAttribute("professor");
                            out.println("<h1>Alterar professor</h1>");
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
                <form action="/aplicacaoMVC/admin/ProfessorController" method="POST">
                    <input
                            type="hidden"
                            name="id"
                            value="<%=professor.getId()%>"
                            class="form-control"
                    />
                    <div class="mb-3">
                        <label for="nome" class="form-label">Nome</label>
                        <input value="<%=professor.getNome()%>" type="text" name="nome" class="form-control" placeholder="Nome do professor" required>
                    </div>
                    <div class="mb-3">
                        <label for="email" class="form-label">Email</label>
                        <input value="<%=professor.getEmail()%>" type="email" name="email" class="form-control" placeholder="Email do professor" required>
                    </div>
                    <div class="mb-3">
                        <label for="cpf" class="form-label">CPF</label>
                        <input value="<%=professor.getCpf()%>" type="text" name="cpf" class="form-control" placeholder="999.999.999-99" required>
                    </div>
                    <div class="mb-3">
                        <label for="senha" class="form-label">Senha</label>
                        <input value="<%=professor.getSenha()%>" type="password" name="senha" class="form-control" placeholder="Senha do professor" required>
                    </div>
                    <div class="row">
                        <div class="col-sm-2">
                            <input type="submit" value="<%=acao%>" name="btEnviar" class="btn btn-primary">
                        </div>
                        <div class="col-sm-2">
                            <a href="/aplicacaoMVC/admin/ProfessorController?acao=Listar" class="btn btn-danger">Retornar</a>
                        </div>
                    </div>
                </form>

            </div>
        </div>
        <script src="views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>
