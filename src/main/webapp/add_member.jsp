<!-- /WEB-INF/views/add_member.jsp -->

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Member</title>
</head>
<body>

<h2>Add a New Member</h2>
<form action="<c:url value='/MemberControllerServlet?action=ADD' />" method="POST">
    <label for="firstName">First Name:</label>
    <input type="text" id="firstName" name="firstName" required><br><br>

    <label for="lastName">Last Name:</label>
    <input type="text" id="lastName" name="lastName" required><br><br>

    <label for="email">Email:</label>
    <input type="email" id="email" name="email" required><br><br>

    <label for="phone">Phone:</label>
    <input type="text" id="phone" name="phone" required><br><br>

    <label for="membershipType">Membership Type:</label>
    <select id="membershipType" name="membershipType" required>
        <option value="Standard">Standard</option>
        <option value="Premium">Premium</option>
    </select><br><br>

    <button type="submit">Add Member</button>
</form>

<br>
<a href="<c:url value='/MemberControllerServlet?action=LIST' />">Back to Members List</a>

</body>
</html>
