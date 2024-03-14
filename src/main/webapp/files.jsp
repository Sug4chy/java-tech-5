<%@ page import="java.io.File" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%
    String path = request.getParameter("path");
    if (path == null) {
        path = "C:\\";
    }
    File currentDirectory = new File(path);
    String parentDirectoryPath = currentDirectory.getParent();

    if (parentDirectoryPath == null) {
        parentDirectoryPath = "C:\\";
    }
%>
<html>
<head>
    <title>File manager</title>
    <link rel="stylesheet" href="files.css">
</head>
<body>
    <h3><%=new Date()%></h3>
    <h1><%=path%></h1>
    <hr/>
    <p>
        <img src="icons8-папка-симлинк-100.png" alt="">
        <a href=<%="?path=" + parentDirectoryPath%>>Вверх</a>
    </p>
    <table>
        <tr>
            <td>Файл</td>
            <td>Размер</td>
            <td>Дата</td>
        </tr>
        <%
            File[] directories = (File[]) request.getAttribute("directories");
            for (File directory : directories) {
        %>
        <tr>
            <th>
                <img src="icons8-папка-48.png" alt="">
                <a href="<%="files?path=" + directory.getAbsolutePath()%>"><%=directory.getName() + "/"%></a>
            </th>
            <th></th>
            <th><%=new Date(directory.lastModified())%></th>
        </tr>
        <%}%>
        <%
            File[] files = (File[]) request.getAttribute("files");
            for (File file : files) {
        %>
        <tr>
            <th>
                <img src="icons8-файл-128.png" alt="">
                <a href=<%="download?path=" + file.getAbsolutePath()%>><%=file.getName()%></a>
            </th>
            <th><%=file.length() + " B"%></th>
            <th><%=new Date(file.lastModified())%></th>
        </tr>
        <%}%>
    </table>
</body>
</html>