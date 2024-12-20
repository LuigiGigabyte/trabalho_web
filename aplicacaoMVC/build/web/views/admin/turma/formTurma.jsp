<%@page import="entidade.Aluno"%>
<%@page import="entidade.Disciplina"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidade.Professor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Cadastro de Turma</title>
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

                <h3>Cadastro de Turma</h3>

                <form action="/aplicacaoMVC/admin/TurmaController?acao=Incluir" method="POST">
                    <div class="mb-3">
                        <label for="professor_id" class="form-label">Professor</label>
                        <select name="professor_id" class="form-control" required>
                            <option value="" disabled selected>Selecione o Professor</option>
                            <% 
                                ArrayList<Professor> listaProfessores = (ArrayList<Professor>) request.getAttribute("listaProfessores");
                                for (Professor professor : listaProfessores) {
                            %>
                                <option value="<%= professor.getId() %>"><%= professor.getNome() %></option>
                            <% } %>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="disciplina_id" class="form-label">Disciplina</label>
                        <select name="disciplina_id" class="form-control" required>
                            <option value="" disabled selected>Selecione a Disciplina</option>
                            <% 
                                ArrayList<Disciplina> listaDisciplinas = (ArrayList<Disciplina>) request.getAttribute("listaDisciplinas");
                                for (Disciplina disciplina : listaDisciplinas) {
                            %>
                                <option value="<%= disciplina.getId() %>"><%= disciplina.getNome() %></option>
                            <% } %>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="aluno_id" class="form-label">Aluno</label>
                        <select name="aluno_id" class="form-control" required>
                            <option value="" disabled selected>Selecione o Aluno</option>
                            <% 
                                ArrayList<Aluno> listaAlunos = (ArrayList<Aluno>) request.getAttribute("listaAlunos");
                                for (Aluno aluno : listaAlunos) {
                            %>
                                <option value="<%= aluno.getId() %>"><%= aluno.getNome() %></option>
                            <% } %>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="codigo_turma" class="form-label">Código da Turma</label>
                        <input type="text" name="codigo_turma" class="form-control" placeholder="Código da turma" required>
                    </div>

                    <div class="mb-3">
                        <label for="nota" class="form-label">Nota</label>
                        <input type="number" name="nota" class="form-control" step="0.01" placeholder="Nota da turma" required>
                    </div>

                    <div class="row">
                        <div class="col-sm-2">
                            <input type="submit" value="Cadastrar" class="btn btn-primary">
                        </div>
                        <div class="col-sm-2">
                            <a href="/aplicacaoMVC/admin/TurmaController?acao=Listar" class="btn btn-danger">Retornar</a>
                        </div>
                    </div>
                </form>

            </div>
        </div>
        <script src="views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>
