<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Member</title>
</head>
<body>

<h2>Update Member Details</h2>
<form action="<c:url value='/MemberControllerServlet?action=UPDATE' />" method="POST">
    <input type="hidden" name="memberId" value="${member.memberId}">

    <!-- Member Information -->
    <label for="firstName">First Name:</label>
    <input type="text" id="firstName" name="firstName" value="${member.firstName}" required><br><br>

    <label for="lastName">Last Name:</label>
    <input type="text" id="lastName" name="lastName" value="${member.lastName}" required><br><br>

    <label for="email">Email:</label>
    <input type="email" id="email" name="email" value="${member.email}" required><br><br>

    <label for="phone">Phone:</label>
    <input type="text" id="phone" name="phone" value="${member.phone}" required><br><br>

    <label for="membershipType">Membership Type:</label>
    <select id="membershipType" name="membershipType" required>
        <option value="Standard" <c:if test="${member.membershipType == 'Standard'}">selected</c:if>>Basic</option>
        <option value="Premium" <c:if test="${member.membershipType == 'Premium'}">selected</c:if>>Premium</option>
    </select><br><br>

    <!-- Subscription Information -->
    <h3>Subscription Details</h3>

    <label for="subscriptionDate">Subscription Date:</label>
    <input type="text" id="subscriptionDate" name="subscriptionDate" value="${subscription.subscriptionDate}" required><br><br>

    <label for="amount">Amount:</label>
    <input type="text" id="amount" name="amount" value="${subscription.amount}" required><br><br>

    <button type="submit">Update Member</button>
</form>

<br>
<a href="<c:url value='/MemberControllerServlet?action=LIST' />">Back to Members List</a>

</body>
</html>
