<%@page import="entidade.Aluno"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Cadastro de Aluno</title>
        <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css"  rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <jsp:include page="../../comum/menu.jsp" />
            <div class="col-sm-6 offset-3 mt-5">
                <% Aluno aluno = new Aluno();
                    String acao = (String) request.getAttribute("acao"); 
                    switch(acao) { 
                        case "Incluir": 
                            out.println("<h1>Incluir Aluno</h1>"); 
                            break; 
                        case "Alterar":
                            aluno = (Aluno) request.getAttribute("aluno");
                            out.println("<h1>Alterar Aluno</h1>");
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

                <form action="/aplicacaoMVC/admin/AlunoController" method="POST">
                    <input
                            type="hidden"
                            name="id"
                            value="<%=aluno.getId()%>"
                            class="form-control"
                    />
                    <div class="mb-3">
                        <label for="nome" class="form-label">Nome</label>
                        <input value="<%=aluno.getNome()%>" type="text" name="nome" class="form-control" placeholder="Nome do aluno" required>
                    
                        <label for="email" class="form-label">Email</label>
                        <input value="<%=aluno.getEmail()%>" type="email" name="email" class="form-control" placeholder="Email do aluno" required>
                    
                        <label for="celular" class="form-label">Celular</label>
                        <input value="<%=aluno.getCelular()%>" type="text" name="celular" class="form-control" placeholder="(99) 99999-9999" required>
                    
                        <label for="cpf" class="form-label">CPF</label>
                        <input value="<%=aluno.getCpf()%>" type="text" name="cpf" class="form-control" placeholder="999.999.999-99" required>
                    
                        <label for="senha" class="form-label">Senha</label>
                        <input value="<%=aluno.getSenha()%>" type="password" name="senha" class="form-control" placeholder="Senha do aluno" required>
                    
                        <label for="endereco" class="form-label">Endereço</label>
                        <input value="<%=aluno.getEndereco()%>" type="text" name="endereco" class="form-control" placeholder="Endereço" required>
                    
                        <label for="cidade" class="form-label">Cidade</label>
                        <input value="<%=aluno.getCidade()%>" type="text" name="cidade" class="form-control" placeholder="Cidade" required>
                    
                        <label for="bairro" class="form-label">Bairro</label>
                        <input value="<%=aluno.getBairro()%>" type="text" name="bairro" class="form-control" placeholder="Bairro" required>
                    
                        <label for="cep" class="form-label">CEP</label>
                        <input value="<%=aluno.getCep()%>" type="text" name="cep" class="form-control" placeholder="00000-000" required>
                    </div>
                    <div >
                        
                        <input type="submit" name="btEnviar"  value="<%=acao%>" class="btn btn-primary">
                        
                        
                        <a href="/aplicacaoMVC/admin/AlunoController?acao=Listar" class="btn btn-danger">Retornar</a>
                        
                    </div>
                </form>

            </div>
        </div>
        <script src="views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>
