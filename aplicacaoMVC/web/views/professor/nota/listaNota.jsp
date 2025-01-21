<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="model.DisciplinaDAO"%>
<%@page import="model.AlunoDAO"%>
<%@page import="entidade.Turma"%>
<%@page import="entidade.Aluno"%>
<%@page import="entidade.Disciplina"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Notas</title>
        <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <jsp:include page="../../comum/menu.jsp" />
            <div class="mt-5">
                <h1>Notas</h1>

                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th scope="col">Código da Turma</th>
                                <th scope="col">Disciplina</th>
                                <th scope="col">Aluno</th>
                                <th scope="col">Nota</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                ArrayList<Turma> listaTurmas = (ArrayList<Turma>) request.getAttribute("listaTurmas");
                                DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
                                AlunoDAO alunoDAO = new AlunoDAO();
                                
                                for (Turma turma : listaTurmas) {
                                    Disciplina disciplina = disciplinaDAO.get(turma.getDisciplinaId());
                                    Aluno aluno = alunoDAO.get(turma.getAlunoId());
                                        
                                    out.println("<tr>");
                                    out.println("<td>" + turma.getCodigoTurma() + "</td>");
                                    out.println("<td>" + disciplina.getNome() + "</td>");
                                    out.println("<td>" + aluno.getNome() + "</td>");
                                    out.println("<td>" + turma.getNota() + "</td>");
                                    out.println("</tr>");
                                    
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
