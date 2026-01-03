<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.FileInfo" %>

<html>
<head>
    <title>File Manager</title>
</head>
<body>
    <h1>Generated at: ${time}</h1>

    <%-- Кнопка Наверх --%>
    <%
        String parentPath = (String) request.getAttribute("parentPath");
        if (parentPath != null) {
    %>
        <div style="margin-bottom: 15px;">
            <a href="files?path=<%= parentPath %>">
                <button type="button">⬆ На уровень выше</button>
            </a>
        </div>
    <% } %>

    <ul>
    <%
        List<FileInfo> filesInfo = (List<FileInfo>) request.getAttribute("fileInfoList");
        if (filesInfo != null) {
            for (FileInfo file : filesInfo) {
    %>
        <li>
            <% if (file.getIsDirectory()) { %>
                <a href="files?path=<%= file.getPath() %>">📁 <%= file.getName() %></a>
            <% } else { %>
                📄 <%= file.getName() %>
                <a href="download?path=<%= file.getPath() %>" style="color: blue;">[Скачать]</a>
            <% } %>
        </li>
    <%
            }
        }
    %>
    </ul>
</body>
</html>