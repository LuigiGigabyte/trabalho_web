<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="entidade.Professor"%>
<%@page import="entidade.Aluno"%>
<%@page import="entidade.Disciplina"%>
<%@page import="model.DisciplinaDAO"%>
<%@page import="model.ProfessorDAO"%>
<%@page import="model.AlunoDAO"%>
<%@page import="entidade.Turma"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Lista de Turmas</title>
        <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <jsp:include page="../../comum/menu.jsp" />
            <div class="mt-5">
                <h1>Área Restrita</h1>
                <h2>Lista de Turmas</h2>
                
                <a href="/aplicacaoMVC/admin/TurmaController?acao=Incluir" class="mb-2 btn btn-primary">Incluir</a>
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th scope="col">Código da Turma</th>
                                <th scope="col">Disciplina</th>
                                <th scope="col">Professor</th>
                                <th scope="col">Aluno</th>
                                <th scope="col">Nota</th>
                                <th scope="col">Ações</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                 
                                ArrayList<Turma> listaTurmas = (ArrayList<Turma>) request.getAttribute("listaTurmas");
                                
   
                                DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
                                ProfessorDAO professorDAO = new ProfessorDAO();
                                AlunoDAO alunodao = new AlunoDAO();
                                        
                                

                                
                                
                                
                                for (Turma turma : listaTurmas) {
                                    
                                    
                                    Disciplina disciplina = disciplinaDAO.get(turma.getDisciplinaId());
                                    Professor professor = professorDAO.get(turma.getProfessorId());
                                    Aluno aluno = alunodao.get(turma.getAlunoId());
                            %>
                                        <tr>
                                            <td><%= turma.getCodigoTurma() %></td>
                                            
                                            <td><%= disciplina.getNome() %></td>
                                            
                                            <td><%= professor.getNome() %></td>
                                            
                                            <td><%= aluno.getNome() %></td>
                                            
                                            <td><%= turma.getNota() %></td>
                                            
                                            <td>
                                                <a href="/aplicacaoMVC/admin/TurmaController?acao=Alterar&id=<%=turma.getId()%>" class="btn btn-warning">Alterar</a>
                                                <a href="/aplicacaoMVC/admin/TurmaController?acao=Excluir&id=<%=turma.getId()%>" class="btn btn-danger">Excluir</a>
                                            </td>
                                        </tr>
                            <%  
                                }
                            %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>
