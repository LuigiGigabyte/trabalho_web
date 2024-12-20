<%@page import="entidade.Aluno"%>
<%@page import="entidade.Disciplina"%>
<%@page import="entidade.Turma"%>
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
                <% Turma turma = new Turma();
                    String acao = (String) request.getAttribute("acao"); 
                    switch(acao) { 
                        case "Incluir": 
                            out.println("<h1>Incluir Turma</h1>"); 
                            break; 
                        case "Alterar":
                            turma = (Turma) request.getAttribute("turma");
                            out.println("<h1>Alterar Turma</h1>");
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

                <form action="/aplicacaoMVC/admin/TurmaController" method="POST">
                    <input
                            type="hidden"
                            name="id"
                            value="<%=turma.getId()%>"
                            class="form-control"
                    />
                    <div class="mb-3">
                        <label for="professor_id" class="form-label">Professor</label>
                        <select name="professor_id" class="form-control" required>
                            <% 
                                ArrayList<Professor> listaProfessores = (ArrayList<Professor>) request.getAttribute("listaProfessores");
                                for (Professor professor : listaProfessores) {
                            %>
                                <option <%=turma.getProfessorId() == professor.getId() ? "selected" : ""%> value="<%=professor.getId()%>"><%= professor.getNome() %></option>
                            <% } %>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="disciplina_id" class="form-label">Disciplina</label>
                        <select name="disciplina_id" class="form-control" required>
                            
                            <% 
                                ArrayList<Disciplina> listaDisciplinas = (ArrayList<Disciplina>) request.getAttribute("listaDisciplinas");
                                for (Disciplina disciplina : listaDisciplinas) {
                            %>
                                <option <%=turma.getDisciplinaId() == disciplina.getId() ? "selected" : ""%> value="<%=disciplina.getId()%>"><%= disciplina.getNome() %></option>
                            <% } %>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="aluno_id" class="form-label">Aluno</label>
                        <select name="aluno_id" class="form-control" required>

                            <% 
                                ArrayList<Aluno> listaAlunos = (ArrayList<Aluno>) request.getAttribute("listaAlunos");
                                for (Aluno aluno : listaAlunos) {
                            %>
                                <option <%=turma.getAlunoId() == aluno.getId() ? "selected" : ""%> value="<%=aluno.getId()%>"><%= aluno.getNome() %></option>
                            <% } %>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="codigo_turma" class="form-label">Código da Turma</label>
                        <input value="<%=turma.getCodigoTurma()%>" type="text" name="codigo_turma" class="form-control" placeholder="Código da turma" required>
                    </div>

                    <div class="mb-3">
                        <label for="nota" class="form-label">Nota</label>
                        <input value="<%=turma.getNota()%>" type="number" name="nota" class="form-control" step="0.01" placeholder="Nota da turma" required>
                    </div>

                    <div class="row">
                        <div class="col-sm-2">
                            <input value="<%=acao%>" type="submit" name="btEnviar" class="btn btn-primary">
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
