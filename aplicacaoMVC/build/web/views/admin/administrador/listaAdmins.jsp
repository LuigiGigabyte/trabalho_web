<%@page import="entidade.Administrador"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Lista Administradores</title>
        <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css"  rel="stylesheet">
    </head>
    <body>
        <div class="container">
             <jsp:include page="../../comum/menu.jsp" />
            <div class="mt-5">

                <h1>Área Restrita</h1>
                <h2>Lista de Administradores</h2>

                <a href="/aplicacaoMVC/admin/AdministradorController?acao=Incluir" class="mb-2 btn btn-primary">Incluir</a>
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th scope="col">Id</th>
                                <th scope="col">Nome</th>
                                <th scope="col">CPF</th>
                                <th scope="col">Endereço</th>
                                <th scope="col">Aprovado</th>
                                <th scope="col">Ações</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                ArrayList<Administrador> listaAdmins = (ArrayList<Administrador>) request.getAttribute("listaAdmins");

                                for (Administrador admin : listaAdmins) {
                                    out.println("<tr>");
                                    out.println("<th>" + admin.getId() + "</th>");
                                    out.println("<td>" + admin.getNome() + "</td>");
                                    out.println("<td>" + admin.getCpf() + "</td>");
                                    out.println("<td>" + admin.getEndereco() + "</td>");
                                    out.println("<td>" + (admin.getAprovado().equals("S") ? "Sim" : "Não") + "</td>");
                            %>
                            <td>
                                <a href="/aplicacaoMVC/admin/AdministradorController?acao=Alterar&id=<%=admin.getId()%>" class="btn btn-warning">Alterar</a>
                                <a href="/aplicacaoMVC/admin/AdministradorController?acao=Excluir&id=<%=admin.getId()%>" class="btn btn-danger">Excluir</a>
                            </td>
                            <%   
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
